package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.PrizeMapper;
import com.abc12366.cms.mapper.db1.PrizeSetMapper;
import com.abc12366.cms.mapper.db2.PrizeRoMapper;
import com.abc12366.cms.mapper.db2.PrizeSetRoMapper;
import com.abc12366.cms.model.questionnaire.Prize;
import com.abc12366.cms.model.questionnaire.PrizeSet;
import com.abc12366.cms.model.questionnaire.bo.PrizeSetBO;
import com.abc12366.cms.service.PrizeSetService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhongwei
 * @create 2017-06-07 4:21 PM
 * @since 1.0.0
 */
@Service
public class PrizeSetServiceImpl implements PrizeSetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeSetServiceImpl.class);
    @Autowired
    private PrizeSetRoMapper prizeSetRoMapper;

    @Autowired
    private PrizeSetMapper prizeSetMapper;

    @Autowired
    private PrizeRoMapper prizeRoMapper;

    @Autowired
    private PrizeMapper prizeMapper;

    @Override
    public PrizeSetBO selectOne(String id) {
        return prizeSetRoMapper.selectOne(id);
    }

    @Override
    public PrizeSetBO insert(PrizeSetBO prizeSetBO) {
        PrizeSet set = new PrizeSet();
        BeanUtils.copyProperties(prizeSetBO, set);
        int sInsert = prizeSetMapper.insert(set);
        if (sInsert != 1) {
            LOGGER.info("{新增奖品设置失败}", set);
            throw new ServiceException(4412);
        }
        List<Prize> pList = new ArrayList<Prize>();
        List<Prize> prizeList = prizeSetBO.getPrizeList();
        if (prizeList != null) {
            for (Prize prize : prizeList) {
                prize.setId(Utils.uuid());
                prize.setQuestionId(prizeSetBO.getQuestionId());
                int pInsert = prizeMapper.insert(prize);
                if (pInsert != 1) {
                    LOGGER.info("{新增奖品失败}", set);
                    throw new ServiceException(4415);
                }
                pList.add(prize);
            }
        }

        PrizeSetBO bo = new PrizeSetBO();
        BeanUtils.copyProperties(set, bo);
        bo.setPrizeList(pList);
        return bo;
    }

    @Override
    public PrizeSetBO update(PrizeSetBO prizeSetBO) {
        PrizeSet set = new PrizeSet();
        BeanUtils.copyProperties(prizeSetBO, set);
        int sUpdate = prizeSetMapper.update(set);
        if (sUpdate != 1) {
            LOGGER.info("{修改奖品设置失败}", set);
            throw new ServiceException(4413);
        }
        List<Prize> pList = new ArrayList<Prize>();
        List<Prize> prizeList = prizeSetBO.getPrizeList();
        if (prizeList != null) {
            //先删掉所有奖品
            for (Prize prize : prizeList) {
                int pInsert = prizeMapper.deleteByPrimaryKey(prize.getId());
                if (pInsert != 1) {
                    LOGGER.info("{删除奖品失败}", set);
                    throw new ServiceException(4414);
                }
            }
            //新增所有产品
            for (Prize prize : prizeList) {
                prize.setId(Utils.uuid());
                prize.setQuestionId(prizeSetBO.getQuestionId());
                int pInsert = prizeMapper.insert(prize);
                if (pInsert != 1) {
                    LOGGER.info("{新增奖品失败}", set);
                    throw new ServiceException(4415);
                }
                pList.add(prize);
            }
        }

        PrizeSetBO bo = new PrizeSetBO();
        BeanUtils.copyProperties(set, bo);
        bo.setPrizeList(pList);
        return bo;
    }

    @Override
    public void delete(PrizeSetBO prizeSetBO) {
        PrizeSet set = new PrizeSet();
        BeanUtils.copyProperties(prizeSetBO, set);
        int sUpdate = prizeSetMapper.update(set);
        if (sUpdate != 1) {
            LOGGER.info("{删除奖品设置失败}", set);
            throw new ServiceException(4414);
        }
        List<Prize> prizeList = prizeSetBO.getPrizeList();
        if (prizeList != null) {
            //先删掉所有奖品
            for (Prize prize : prizeList) {
                int pInsert = prizeMapper.deleteByPrimaryKey(prize.getId());
                if (pInsert != 1) {
                    LOGGER.info("{删除奖品失败}", set);
                    throw new ServiceException(4417);
                }
            }
        }

    }
}
