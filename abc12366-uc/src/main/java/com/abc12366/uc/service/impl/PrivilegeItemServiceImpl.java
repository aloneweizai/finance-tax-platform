package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.uc.mapper.db1.PrivilegeItemMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.PrivilegeItemRoMapper;
import com.abc12366.uc.model.PrivilegeItem;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.PrivilegeItemBO;
import com.abc12366.uc.service.PrivilegeItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-22
 * Time: 10:30
 */
@Service
public class PrivilegeItemServiceImpl implements PrivilegeItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeItemServiceImpl.class);

    @Autowired
    private PrivilegeItemMapper privilegeMapper;

    @Autowired
    private PrivilegeItemRoMapper privilegeRoMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PrivilegeItem update(PrivilegeItemBO privilegeItemBO) {
        LOGGER.info("{}", privilegeItemBO);
        PrivilegeItem privilegeItem = new PrivilegeItem();
        BeanUtils.copyProperties(privilegeItemBO, privilegeItem);
        privilegeMapper.update(privilegeItem);
        return selectOne(privilegeItemBO.getLevelId());
    }

    @Override
    public PrivilegeItem selecOneByUser(String userId) {
        User user = userMapper.selectOne(userId);
        //会员过期了，则返回普通用户权益
        if (user == null || user.getVipExpireDate() == null || user.getVipExpireDate().getTime() < System.currentTimeMillis()) {
            return privilegeRoMapper.selectOneByLevelCode(Constant.USER_ORIGINAL_LEVEL);
        }
        return privilegeRoMapper.selecOneByUser(userId);
    }

    @Override
    public int delete(String levelId) {
        return privilegeMapper.delete(levelId);
    }

    @Override
    public PrivilegeItem insert(PrivilegeItemBO privilegeItemBO) {
        LOGGER.info("{}", privilegeItemBO);
        PrivilegeItem privilegeItem = selectOne(privilegeItemBO.getLevelId());
        if (privilegeItem != null) {
            throw new ServiceException(4042);
        }
        privilegeItem = new PrivilegeItem();
        BeanUtils.copyProperties(privilegeItemBO, privilegeItem);
        privilegeMapper.insert(privilegeItem);
        return privilegeItem;
    }

    @Override
    public PrivilegeItem selectOne(String levelId) {
        LOGGER.info("{}", levelId);
        return privilegeRoMapper.selectOne(levelId);
    }
}
