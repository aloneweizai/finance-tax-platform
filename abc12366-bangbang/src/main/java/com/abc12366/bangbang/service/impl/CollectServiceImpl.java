package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CollectMapper;
import com.abc12366.bangbang.mapper.db2.CollectRoMapper;
import com.abc12366.bangbang.model.Collect;
import com.abc12366.bangbang.model.bo.CollectBO;
import com.abc12366.bangbang.model.bo.CollectListBO;
import com.abc12366.bangbang.model.bo.MyCollectListBO;
import com.abc12366.bangbang.service.CollectService;
import com.abc12366.bangbang.util.MapUtil;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-11
 * Time: 9:38
 */
@Service
public class CollectServiceImpl implements CollectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectServiceImpl.class);

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private CollectRoMapper collectRoMapper;

    @Override
    public CollectBO insert(String askId, HttpServletRequest request) {
        LOGGER.info("{}:{}", askId, request);
        String userId = Utils.getUserId(request);
        //判断是否已经收藏
        Map map = MapUtil.kv("askId", askId, "userId", userId);
        List<CollectBO> collectBOList = collectRoMapper.selectExist(map);
        if (collectBOList != null && collectBOList.size() > 0) {
            throw new ServiceException(4706);
        }

        Collect collect = new Collect();
        Date date = new Date();
        collect.setId(Utils.uuid());
        collect.setAskId(askId);
        collect.setUserId(userId);
        collect.setCreateTime(date);
        collect.setLastUpdate(date);
        int result = collectMapper.insert(collect);
        if (result < 1) {
            throw new ServiceException(4705);
        }
        CollectBO collectBO = new CollectBO();
        BeanUtils.copyProperties(collect, collectBO);
        return collectBO;
    }

    @Override
    public void delete(String askId, HttpServletRequest request) {
        LOGGER.info("{}:{}", askId, request);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("askId", askId, "userId", userId);
        collectMapper.delete(map);
    }

    @Override
    public List<CollectListBO> selectList(String userId) {
        LOGGER.info("{}", userId);
        return collectRoMapper.selectList(userId);
    }

    @Override
    public String selectCount(String askId) {
        LOGGER.info("{}", askId);
        return collectRoMapper.selectCount(askId);
    }

    @Override
    public List<MyCollectListBO> selectCollectListByUserId(String userId) {
        return collectRoMapper.selectCollectListByUserId(userId);
    }
}
