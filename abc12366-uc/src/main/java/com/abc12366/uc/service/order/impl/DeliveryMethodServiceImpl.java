package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.DeliveryMethodMapper;
import com.abc12366.uc.mapper.db2.DeliveryMethodRoMapper;
import com.abc12366.uc.model.order.DeliveryMethod;
import com.abc12366.uc.model.order.bo.DeliveryMethodBO;
import com.abc12366.uc.model.order.bo.DeliveryMethodUpdateBO;
import com.abc12366.uc.service.order.DeliveryMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @since 1.0.0
 */
@Service
public class DeliveryMethodServiceImpl implements DeliveryMethodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryMethodServiceImpl.class);

    @Autowired
    private DeliveryMethodRoMapper deliveryMethodRoMapper;

    @Autowired
    private DeliveryMethodMapper deliveryMethodMapper;

    @Override
    public List<DeliveryMethod> selectList(DeliveryMethod deliveryMethod) {
        return deliveryMethodRoMapper.selectList(deliveryMethod);
    }

    @Transactional("db1TxManager")
    @Override
    public DeliveryMethodBO add(DeliveryMethodBO deliveryMethodBO) {
        DeliveryMethod deliveryMethod = new DeliveryMethod();
        BeanUtils.copyProperties(deliveryMethodBO, deliveryMethod);
        deliveryMethod.setId(Utils.uuid());
        Date date = new Date();
        deliveryMethod.setCreateTime(date);
        deliveryMethod.setLastUpdate(date);
        DeliveryMethod method = deliveryMethodRoMapper.selectByName(deliveryMethod.getName());
        if (method != null) {
            LOGGER.info("{配送方式名称不能重复}", deliveryMethod);
            throw new ServiceException(4151);
        }
        int insert = deliveryMethodMapper.insert(deliveryMethod);
        if (insert != 1) {
            LOGGER.info("{新增配送方式失败}", deliveryMethod);
            throw new ServiceException(4101);
        }
        DeliveryMethodBO bo = new DeliveryMethodBO();
        BeanUtils.copyProperties(deliveryMethod, bo);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public DeliveryMethodBO update(DeliveryMethodBO deliveryMethodBO) {
        DeliveryMethod deliveryMethod = new DeliveryMethod();
        BeanUtils.copyProperties(deliveryMethodBO, deliveryMethod);
        Date date = new Date();
        deliveryMethod.setLastUpdate(date);
        int update = deliveryMethodMapper.update(deliveryMethod);
        if (update != 1) {
            LOGGER.info("{修改配送方式失败}", deliveryMethod);
            throw new ServiceException(4102);
        }
        DeliveryMethodBO bo = new DeliveryMethodBO();
        BeanUtils.copyProperties(deliveryMethod, bo);
        return bo;
    }

    @Override
    public DeliveryMethodBO selectDeliveryMethod(String id) {
        DeliveryMethod deliveryMethod = deliveryMethodRoMapper.selectByPrimaryKey(id);
        if (deliveryMethod == null) {
            throw new ServiceException(4104);
        }
        DeliveryMethodBO bo = new DeliveryMethodBO();
        BeanUtils.copyProperties(deliveryMethod, bo);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(String id) {
        int del = deliveryMethodMapper.deleteByPrimaryKey(id);
        if (del != 1) {
            LOGGER.info("{删除配送方式失败}", del);
            throw new ServiceException(4103);
        }
    }


    @Transactional("db1TxManager")
    @Override
    public void enable(DeliveryMethodUpdateBO deliveryMethodUpdateBO) {
        String ids[] = deliveryMethodUpdateBO.getId().split(",");
        DeliveryMethod deliveryMethod;
        for (String id : ids) {
            deliveryMethod = new DeliveryMethod();
            deliveryMethod.setId(id);
            deliveryMethod.setStatus(deliveryMethodUpdateBO.getStatus());
            int upd = deliveryMethodMapper.update(deliveryMethod);
            if (upd != 1) {
                LOGGER.info("{启用或禁用配送方式失败}", deliveryMethod);
                throw new ServiceException(4102);
            }
        }
    }
}
