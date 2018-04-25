package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.UserBankMapper;
import com.abc12366.uc.mapper.db2.UserBankRoMapper;
import com.abc12366.uc.model.UserBank;
import com.abc12366.uc.model.bo.UserBankBO;
import com.abc12366.uc.service.UserBankService;
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
public class UserBankServiceImpl implements UserBankService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBankServiceImpl.class);
    @Autowired
    private UserBankRoMapper userBankRoMapper;

    @Autowired
    private UserBankMapper userBankMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserBankBO insert(UserBankBO userBankBO) {
        userBankBO.setId(Utils.uuid());
        Date date = new Date();
        userBankBO.setLastUpdate(date);
        userBankBO.setCreateTime(date);
        UserBank userBank = new UserBank();
        BeanUtils.copyProperties(userBankBO,userBank);
        int insert =userBankMapper.insert(userBank);
        if(insert != 1){
            LOGGER.warn("新增失败，参数:{}");
            throw new ServiceException(4001);
        }
        return userBankBO;
    }

    @Override
    public UserBankBO update(UserBankBO userBankBO) {
        Date date = new Date();
        userBankBO.setLastUpdate(date);
        UserBank userBank = new UserBank();
        BeanUtils.copyProperties(userBankBO,userBank);
        int update =userBankMapper.update(userBank);
        if(update != 1){
            LOGGER.warn("修改失败，参数:{}");
            throw new ServiceException(4002);
        }
        return userBankBO;
    }

    @Override
    public int delete(String id) {
        int delete =userBankMapper.deleteByPrimaryKey(id);
        if(delete != 1){
            LOGGER.warn("修改失败，参数:{}");
            throw new ServiceException(4003);
        }
        return delete;
    }

    @Override
    public List<UserBank> selectUserBankList(Map<String, Object> map) {
        return userBankRoMapper.selectUserBankList(map);
    }

    @Override
    public UserBank selectById(String id) {
        return userBankRoMapper.selectByPrimaryKey(id);
    }
}
