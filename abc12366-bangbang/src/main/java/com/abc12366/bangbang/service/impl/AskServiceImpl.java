package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.AskMapper;
import com.abc12366.bangbang.mapper.db2.AskRoMapper;
import com.abc12366.bangbang.model.Ask;
import com.abc12366.bangbang.model.bo.*;
import com.abc12366.bangbang.service.AskService;
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
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-08
 * Time: 16:23
 */
@Service
public class AskServiceImpl implements AskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AskServiceImpl.class);

    @Autowired
    private AskMapper askMapper;

    @Autowired
    private AskRoMapper askRoMapper;

    @Override
    public List<AskBO> selectListForAdmin(AsksQueryParamBO asksQueryParamBO) {
        LOGGER.info("{}", asksQueryParamBO);
        return askRoMapper.selectListForAdmin();
    }

    @Override
    public List<AskBO> selectListForUser(AskQueryParamBO askQueryParamBO) {
        LOGGER.info("{}", askQueryParamBO);
        return askRoMapper.selectListForUser();
    }

    @Override
    public AskBO insert(AskInsertBO askInsertBO) {
        Ask ask = new Ask();
        BeanUtils.copyProperties(askInsertBO, ask);
        Date date = new Date();
        ask.setId(Utils.uuid());
        ask.setCreateTime(date);
        ask.setLastUpdate(date);
        ask.setIsSolve(false);
        int result = askMapper.insert(ask);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：{}", askInsertBO);
            throw new ServiceException(4101);
        }
        AskBO askBO = new AskBO();
        BeanUtils.copyProperties(ask, askBO);
        return askBO;
    }

    @Override
    public AskBO selectOne(String id) {
        //写日志，以便统计
        return askRoMapper.selectOne(id);
    }

    @Override
    public AskBO update(String id, AskUpdateBO askUpdateBO, String userId) {
        AskBO askBO = askRoMapper.selectOne(id);
        if (askBO == null) {
            LOGGER.warn("更新失败，不存在可被更新的数据，参数:ID=", id);
            throw new ServiceException(4102);
        }
        //问题修改权限控制，只有问题拥有者才可以修改
        //TODO

        Ask ask = new Ask();
        BeanUtils.copyProperties(askUpdateBO, ask);
        ask.setId(id);
        ask.setLastUpdate(new Date());
        int result = askMapper.update(ask);
        if (result != 1) {
            LOGGER.warn("更新失败，参数：{}", ask);
            throw new ServiceException(4102);
        }
        BeanUtils.copyProperties(ask, askBO);
        return askBO;
    }

    @Override
    public int delete(String id, String userId) {
        AskBO askBO = askRoMapper.selectOne(id);
        if (askBO == null) {
            LOGGER.warn("删除失败，不存在可被删除的数据，参数:ID=", id);
            throw new ServiceException(4103);
        }
        //1.问题删除权限控制，用户和管理员都可以删除问题
        //TODO

        //2.但是用户在提问超过某一阈值（24小时）之后不能删除
        if (System.currentTimeMillis() > (askBO.getCreateTime().getTime() + (24 * 3600 * 1000))) {
            LOGGER.warn("用户在提问超过某一阈值（24小时）之后不能删除!");
            return 0;
        }

        Ask ask = new Ask();
        int result = askMapper.delete(id);
        if (result != 1) {
            LOGGER.warn("删除失败，参数：{}", ask);
            throw new ServiceException(4103);
        }
        return 1;
    }

    @Override
    public int block(String id, String userId) {
        AskBO askBO = askRoMapper.selectOne(id);
        if (askBO == null) {
            LOGGER.warn("更新失败，不存在可被更新的数据，参数:ID=", id);
            throw new ServiceException(4102);
        }
        //屏蔽问题权限控制，只有后台用户可以做屏蔽问题操作
        //TODO

        Ask ask = new Ask();
        BeanUtils.copyProperties(askBO, ask);
        ask.setLastUpdate(new Date());
        ask.setStatus("2");
        int result = askMapper.update(ask);
        if (result != 1) {
            LOGGER.warn("更新失败，参数：{}", ask);
            throw new ServiceException(4102);
        }
        return 1;
    }

    @Override
    public List<HotspotAskBO> selectHotspotAsks() {
        return askRoMapper.selectHotspotAsks();
    }

    @Override
    public List<AskBO> selectHotspotComments() {
        return askRoMapper.selectHotspotComments();
    }
}
