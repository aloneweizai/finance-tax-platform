package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.ExpressCompMapper;
import com.abc12366.uc.mapper.db2.ExpressCompRoMapper;
import com.abc12366.uc.model.order.ExpressComp;
import com.abc12366.uc.model.order.bo.ExpressCompBO;
import com.abc12366.uc.service.order.ExpressCompService;
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
public class ExpressCompServiceImpl implements ExpressCompService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressCompServiceImpl.class);

    @Autowired
    private ExpressCompRoMapper expressCompRoMapper;

    @Autowired
    private ExpressCompMapper expressCompMapper;

    @Override
    public List<ExpressComp> selectList(ExpressComp expressComp) {
        return expressCompRoMapper.selectList(expressComp);
    }

    @Transactional("db1TxManager")
    @Override
    public ExpressCompBO add(ExpressCompBO expressCompBO) {
        ExpressComp expressComp = new ExpressComp();
        BeanUtils.copyProperties(expressCompBO, expressComp);
        expressComp.setId(Utils.uuid());
        Date date = new Date();
        expressComp.setCreateTime(date);
        expressComp.setLastUpdate(date);
        ExpressComp comp1 = expressCompRoMapper.selectByCompCode(expressCompBO.getCompCode());
        if (comp1 != null){
            LOGGER.info("{物流公司代号不能重复}", expressComp);
            throw new ServiceException(4915);
        }
        ExpressComp comp = expressCompRoMapper.selectByCompName(expressCompBO.getCompName());
        if (comp != null){
            LOGGER.info("{物流公司名称不能重复}", expressComp);
            throw new ServiceException(4916);
        }
        int insert = expressCompMapper.insert(expressComp);
        if (insert != 1) {
            LOGGER.info("{新增物流公司失败}", expressComp);
            throw new ServiceException(4101);
        }
        ExpressCompBO bo = new ExpressCompBO();
        BeanUtils.copyProperties(expressComp, bo);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public ExpressCompBO update(ExpressCompBO expressCompBO) {
        ExpressComp expressComp = new ExpressComp();
        BeanUtils.copyProperties(expressCompBO, expressComp);
        Date date = new Date();
        expressComp.setLastUpdate(date);

        ExpressComp comp1 = expressCompRoMapper.selectByCompCode(expressCompBO.getCompCode());
        if (comp1 != null && !comp1.getId().equals(expressCompBO.getId())){
            LOGGER.info("{物流公司代号不能重复}", expressComp);
            throw new ServiceException(4915);
        }
        ExpressComp comp = expressCompRoMapper.selectByCompName(expressCompBO.getCompName());
        if (comp != null && !comp.getId().equals(expressCompBO.getId())){
            LOGGER.info("{物流公司名称不能重复}", expressComp);
            throw new ServiceException(4916);
        }
        int update = expressCompMapper.update(expressComp);
        if (update != 1) {
            LOGGER.info("{修改物流公司失败}", expressComp);
            throw new ServiceException(4102);
        }
        ExpressCompBO bo = new ExpressCompBO();
        BeanUtils.copyProperties(expressComp, bo);
        return bo;
    }

    @Override
    public ExpressComp selectExpressComp(String id) {
        return expressCompRoMapper.selectByPrimaryKey(id);
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(String id) {
        int del = expressCompMapper.deleteByPrimaryKey(id);
        if (del != 1) {
            LOGGER.info("{删除物流公司失败}", del);
            throw new ServiceException(4103);
        }
    }
}
