package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.UserAddressMapper;
import com.abc12366.uc.mapper.db2.UserAddressRoMapper;
import com.abc12366.uc.model.order.UserAddress;
import com.abc12366.uc.model.order.bo.UserAddressBO;
import com.abc12366.uc.model.order.bo.UserAddressUpdateBO;
import com.abc12366.uc.service.order.UserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @date  2017-05-15 10:17 AM
 * @since 1.0.0
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAddressServiceImpl.class);

    @Autowired
    private UserAddressRoMapper userAddressRoMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> selectList(String userId) {
        return userAddressRoMapper.selectList(userId);
    }

    @Override
    public UserAddressBO selectOne(UserAddress userAddress) {
        UserAddress address = userAddressRoMapper.selectOne(userAddress);
        if (address != null) {
            UserAddressBO bo = new UserAddressBO();
            BeanUtils.copyProperties(address, bo);
            return bo;
        } else {
            return null;
        }
    }

    @Transactional("db1TxManager")
    @Override
    public UserAddressBO updateUserAddress(UserAddressBO userAddressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBO, userAddress);
        Date date = new Date();
        userAddress.setLastUpdate(date);
        int update = userAddressMapper.update(userAddress);
        if (update != 1) {
            LOGGER.info("修改失败{}", userAddress);
            throw new ServiceException(4102);
        }
        UserAddressBO bo = new UserAddressBO();
        BeanUtils.copyProperties(userAddress, bo);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public int deleteByIdAndUserId(UserAddressBO userAddressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBO, userAddress);
        return userAddressMapper.deleteByIdAndUserId(userAddress);
    }

    @Transactional("db1TxManager")
    @Override
    public UserAddressBO addUserAddress(UserAddressBO userAddressBO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBO, userAddress);
        userAddress.setId(Utils.uuid());
        Date date = new Date();
        userAddress.setCreateTime(date);
        userAddress.setLastUpdate(date);
        int insert = userAddressMapper.insert(userAddress);
        if (insert != 1) {
            LOGGER.info("新增失败{}", userAddress);
            throw new ServiceException(4101);
        }
        UserAddressBO bo = new UserAddressBO();
        BeanUtils.copyProperties(userAddress, bo);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public UserAddressUpdateBO setDefaultAddrees(UserAddressUpdateBO userAddressBO) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(userAddressBO.getId());
        userAddress.setUserId(userAddressBO.getUserId());
        userAddress.setIsDefault(userAddressBO.getIsDefault());
        int update = userAddressMapper.update(userAddress);
        if (update != 1) {
            LOGGER.info("修改失败{}", userAddress);
            throw new ServiceException(4102);
        }

        List<UserAddress> list = userAddressRoMapper.selectByUserId(userAddress);
        for (UserAddress address : list) {
            address.setIsDefault(false);
            int upd = userAddressMapper.update(address);
            if (upd != 1) {
                LOGGER.info("修改失败!!!!{}", address);
                throw new ServiceException(4102);
            }
        }
        return userAddressBO;
    }

    @Override
    public List<UserAddressBO> selectBOList(String userId) {
        return userAddressRoMapper.selectBOList(userId);
    }
}
