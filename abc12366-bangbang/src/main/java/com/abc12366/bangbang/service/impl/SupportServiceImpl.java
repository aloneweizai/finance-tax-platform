package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.SupportMapper;
import com.abc12366.bangbang.mapper.db2.SupportRoMapper;
import com.abc12366.bangbang.model.Support;
import com.abc12366.bangbang.model.bo.SupportBO;
import com.abc12366.bangbang.service.SupportService;
import com.abc12366.bangbang.util.MapUtil;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-11
 * Time: 11:48
 */
@Service
public class SupportServiceImpl implements SupportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupportServiceImpl.class);

    @Autowired
    private SupportMapper supportMapper;

    @Autowired
    private SupportRoMapper supportRoMapper;

    @Override
    public SupportBO insert(String subject, String id, HttpServletRequest request) {
        LOGGER.info("{}:{}:{}", subject, id, request);
        String userId = Utils.getUserId(request);
        //查看是否已经点赞
        Map map = MapUtil.kv("subjectId", id, "userId", userId, "type", subject);
        List<SupportBO> supportBOList = supportRoMapper.selectExist(map);
        if (supportBOList != null && supportBOList.size() > 0) {
            throw new ServiceException(4709);
        }

        Support support = new Support();
        Date date = new Date();
        support.setId(Utils.uuid());
        support.setSubjectId(id);
        support.setUserId(userId);
        support.setType(subject);
        support.setCreateTime(date);
        support.setLastUpdate(date);
        int result = supportMapper.insert(support);
        if (result < 1) {
            throw new ServiceException(4707);
        }
        SupportBO supportBO = new SupportBO();
        BeanUtils.copyProperties(support, supportBO);
        return supportBO;
    }

    @Override
    public void delete(String subject, String id, HttpServletRequest request) {
        LOGGER.info("{}:{}:{}", subject, id, request);
        String userId = Utils.getUserId(request);
        Support support = new Support();
        support.setType(subject);
        support.setSubjectId(id);
        support.setUserId(userId);
        int result = supportMapper.delete(support);
        if (result < 1) {
            throw new ServiceException(4708);
        }
    }

    @Override
    public int selectCount(String subject, String id) {
        LOGGER.info("{}:{}", subject, id);
        Map map = MapUtil.kv("subject", subject, "id", id);
        return Integer.parseInt(supportRoMapper.selectCount(map));
    }

    @Override
    public List<SupportBO> selectList(String subject, String userId) {
        LOGGER.info("{}:{}", subject, userId);
        Map map = MapUtil.kv("subject", subject, "userId", userId);
        return supportRoMapper.selectList(map);
    }
}
