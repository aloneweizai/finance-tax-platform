package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.UserPointsApplyMapper;
import com.abc12366.uc.mapper.db2.UserPointsApplyRoMapper;
import com.abc12366.uc.model.UserPointsApply;
import com.abc12366.uc.model.bo.PointsRuleBO;
import com.abc12366.uc.model.bo.UserPointsApplyBO;
import com.abc12366.uc.service.PointsRuleService;
import com.abc12366.uc.service.UserPointsApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @date  2017-04-26 5:01 PM
 * @since 1.0.0
 */
@Service
public class UserPointsApplyServiceImpl implements UserPointsApplyService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPointsApplyServiceImpl.class);
    @Autowired
    private UserPointsApplyRoMapper userPointsApplyRoMapper;

    @Autowired
    private UserPointsApplyMapper userPointsApplyMapper;

    @Autowired
    private PointsRuleService pointsRuleService;

    @Override
    public UserPointsApplyBO insert(UserPointsApplyBO userPointsApply) {
        Date date = new Date();
        userPointsApply.setPointsNo(DateUtils.getPointsString());
        userPointsApply.setCreateTime(date);
        userPointsApply.setLastUpdate(date);
        PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_GMJFFH_CODE);
//        if (pointsRuleBO == null) {
//            return ;
//        }
//        userPointsApply.set
        return null;
    }

    @Override
    public UserPointsApplyBO update(UserPointsApplyBO userPointsApply) {
        return null;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public List<UserPointsApply> selectUserPointsApplyList(Map<String, Object> map) {
        return null;
    }

    @Override
    public UserPointsApply selectById(String id) {
        return null;
    }
}
