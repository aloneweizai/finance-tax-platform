package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db1.VipLogMapper;
import com.abc12366.uc.mapper.db2.VipLogRoMapper;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.VipLog;
import com.abc12366.uc.model.bo.VipLevelStatisticTemp;
import com.abc12366.uc.model.bo.VipLogBO;
import com.abc12366.uc.model.bo.VipLogOrderBO;
import com.abc12366.uc.service.VipLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-19
 * Time: 15:39
 */
@Service
public class VipLogServiceImpl implements VipLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VipLogServiceImpl.class);

    @Autowired
    private VipLogRoMapper vipLogRoMapper;

    @Autowired
    private VipLogMapper vipLogMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<VipLogBO> selectList(String userId) {
        return vipLogRoMapper.selectList(userId);
    }

    @Override
    public VipLogBO insert(VipLogBO vipLogBO,int type) {
        if (vipLogBO == null) {
            LOGGER.warn("新增失败，参数：" + null);
            throw new ServiceException(4101);
        }
        VipLog vipLog = new VipLog();
        BeanUtils.copyProperties(vipLogBO, vipLog);
        vipLog.setId(Utils.uuid());
        vipLog.setCreateTime(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(vipLog.getCreateTime());

        if (Constant.USER_ORIGINAL_LEVEL.equalsIgnoreCase(vipLog.getLevelId())) {
            // 普通用户到期时间为创建时间加10年
            calendar.add(Calendar.YEAR, 10);
        } else {
            //1：会员充值，2：会员退订
            if(type == 1){
                // 会员到期时间为创建时间加一年,加入是会员未到期再购买则在原有到期时间基础上再延长一年
                if (!StringUtils.isEmpty(vipLogBO.getUserId()) && !StringUtils.isEmpty(vipLogBO.getLevelId())) {
                    User user = userMapper.selectOne(vipLogBO.getUserId());
                    if (user != null && user.getVipExpireDate() != null && !StringUtils.isEmpty(user.getVipLevel())
                            && vipLogBO.getLevelId().equals(user.getVipLevel())) {
                        calendar.setTime(user.getVipExpireDate());
                    }
                    calendar.add(Calendar.YEAR, 1);
                }
            }else if(type == 2){
                calendar.setTime(vipLog.getVipExpireDate());
            }
        }
        vipLog.setVipExpireDate(calendar.getTime());
        int result = vipLogMapper.insert(vipLog);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + vipLog.toString());
            throw new ServiceException(4101);
        }
        VipLogBO vipLogBOReturn = new VipLogBO();
        BeanUtils.copyProperties(vipLog, vipLogBOReturn);
        return vipLogBOReturn;
    }

    @Override
    public VipLevelStatisticTemp selectCountByCode(Map map) {
        return vipLogRoMapper.selectCountByCode(map);
    }

    @Override
    public int selectCountAll(String levelCode) {
        return vipLogRoMapper.selectCountAll(levelCode);
    }

    @Override
    public List<VipLogOrderBO> selectListByOrder(String userId) {
        return vipLogRoMapper.selectListByOrder(userId);
    }
}
