package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.uc.mapper.db1.ExpressMapper;
import com.abc12366.uc.mapper.db2.ExpressRoMapper;
import com.abc12366.uc.model.invoice.Express;
import com.abc12366.uc.model.invoice.bo.ExpressBO;
import com.abc12366.uc.service.order.ExpressService;
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
public class ExpressServiceImpl implements ExpressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressServiceImpl.class);

    @Autowired
    private ExpressRoMapper expressRoMapper;

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public List<ExpressBO> selectList(ExpressBO expressBO) {
        return expressRoMapper.selectList(expressBO);
    }

    @Transactional("db1TxManager")
    @Override
    public ExpressBO send(Express express) {
        int update = expressMapper.update(express);
        if (update != 1) {
            LOGGER.info("{快递寄送失败}", express);
            throw new ServiceException(4144);
        }
        ExpressBO bo = new ExpressBO();
        BeanUtils.copyProperties(express, bo);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public ExpressBO update(ExpressBO expressBO) {
        Express express = new Express();
        BeanUtils.copyProperties(expressBO, express);
        Date date = new Date();
        express.setLastUpdate(date);
        int update = expressMapper.update(express);
        if (update != 1) {
            LOGGER.info("{修改快递信息失败}", express);
            throw new ServiceException(4102);
        }
        ExpressBO bo = new ExpressBO();
        BeanUtils.copyProperties(express, bo);
        return bo;
    }

    @Override
    public Express selectExpress(String id) {
        return expressRoMapper.selectByPrimaryKey(id);
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(String id) {
        int del = expressMapper.deleteByPrimaryKey(id);
        if (del != 1) {
            LOGGER.info("{删除快递信息失败}", del);
            throw new ServiceException(4103);
        }
    }

    @Transactional("db1TxManager")
    @Override
    public ExpressBO importExpress(ExpressBO expressBO) {
        Express express = new Express();
        BeanUtils.copyProperties(expressBO, express);
        Date date = new Date();
        express.setLastUpdate(date);
        int update = expressMapper.update(express);
        if (update != 1) {
            return null;
        }
        ExpressBO bo = new ExpressBO();
        BeanUtils.copyProperties(express, bo);
        return bo;
    }
}
