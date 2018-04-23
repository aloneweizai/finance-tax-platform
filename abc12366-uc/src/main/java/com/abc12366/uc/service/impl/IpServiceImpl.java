package com.abc12366.uc.service.impl;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.IpMapper;
import com.abc12366.uc.mapper.db2.IpRoMapper;
import com.abc12366.uc.model.Ip;
import com.abc12366.uc.model.bo.IpQueryResult;
import com.abc12366.uc.service.IpService;
import com.abc12366.uc.service.UserExtendService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author lijun <ljun51@outlook.com>
 * @date 2017-06-22 11:08 AM
 * @since 1.0.0
 */
@Service
public class IpServiceImpl implements IpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpServiceImpl.class);

    /**
     * IP地址更新周期为7天
     */
    private final static int UPDATE_CYCLE = 7;

    @Autowired
    private IpMapper ipMapper;

    @Autowired
    private IpRoMapper ipRoMapper;

    @Autowired
    private UserExtendService userExtendService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void merge(String ip, String userId) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Ip o = ipRoMapper.selectOne(ip);

        if (o != null) {
            if (new Date().after(DateUtils.addDays(o.getUpdateTime(), UPDATE_CYCLE))) {
                Ip updateObj = getIpObj(ip);

                if (updateObj != null) {
                    BeanUtils.copyProperties(updateObj, o, "id");
                    o.setUpdateTime(now);
                    ipMapper.update(o);
                }
            }
        } else {
            Ip bo = getIpObj(ip);

            if (bo != null) {
                bo.setId(Utils.uuid());
                bo.setCreateTime(now);
                bo.setUpdateTime(now);
                ipMapper.insert(bo);
                o = bo;
            }
        }

        LOGGER.info("更新用户省市地址信息:{}", userId);
        boolean isOper = o != null && StringUtils.isNotEmpty(o.getRegionId()) && StringUtils.isNotEmpty(o.getCityId());
        if (isOper) {
            userExtendService.updateUserAddress(userId, o.getRegionId(), o.getCityId());
        }
    }

    private Ip getIpObj(String ip) {
        LOGGER.info("{}, {}", Constant.IP_QUERY_URL, ip);
        String jsonStr = restTemplate.getForObject(Constant.IP_QUERY_URL, String.class, ip);
        LOGGER.info("{}", jsonStr);
        IpQueryResult ipQueryResult = null;
        try {
            ipQueryResult = JSON.parseObject(jsonStr, IpQueryResult.class);
        } catch (JSONException e) {
            LOGGER.error("{}", e);
        }
        return ipQueryResult != null && ipQueryResult.getCode() == 0 ?
                JSON.parseObject(ipQueryResult.getData(), Ip.class) : null;
    }
}
