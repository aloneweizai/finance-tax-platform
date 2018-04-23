package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.WhiteListMapper;
import com.abc12366.cms.mapper.db2.WhiteListRoMapper;
import com.abc12366.cms.model.questionnaire.WhiteList;
import com.abc12366.cms.model.questionnaire.bo.WhiteListBO;
import com.abc12366.cms.service.WhiteListService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lizhongwei
 * @create 2017-06-07 4:21 PM
 * @since 1.0.0
 */
@Service
public class WhiteListServiceImpl implements WhiteListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhiteListServiceImpl.class);
    @Autowired
    private WhiteListRoMapper whiteListRoMapper;

    @Autowired
    private WhiteListMapper whiteListMapper;

    @Override
    public List<WhiteList> selectList(WhiteList whiteList) {

        return whiteListRoMapper.selectList(whiteList);
    }

    @Override
    public WhiteListBO insert(WhiteListBO whiteListBO) {
        WhiteList whiteList = new WhiteList();
        BeanUtils.copyProperties(whiteListBO, whiteList);
        whiteList.setId(Utils.uuid());
        whiteList.setInputTime(new Date());
        int insert = whiteListMapper.insert(whiteList);
        if (insert != 1) {
            LOGGER.info("{新增白名单失败}", whiteList);
            throw new ServiceException(4398);
        }
        WhiteListBO bo = new WhiteListBO();
        BeanUtils.copyProperties(whiteList, bo);
        return bo;
    }

    @Override
    public void delete(WhiteListBO whiteListBO) {
        int del = whiteListMapper.deleteByPrimaryKey(whiteListBO.getId());
        if (del != 1) {
            LOGGER.info("{删除白名单失败}", whiteListBO);
            throw new ServiceException(4399);
        }
    }
}
