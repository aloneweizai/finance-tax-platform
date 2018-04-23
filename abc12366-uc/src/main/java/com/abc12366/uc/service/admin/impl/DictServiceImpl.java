package com.abc12366.uc.service.admin.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.DictMapper;
import com.abc12366.uc.mapper.db2.DictRoMapper;
import com.abc12366.uc.model.Dict;
import com.abc12366.uc.model.bo.DictBO;
import com.abc12366.uc.model.bo.DictUpdateBO;
import com.abc12366.uc.service.admin.DictService;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @date  2017-04-26 5:01 PM
 * @since 1.0.0
 */
@Service
public class DictServiceImpl implements DictService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictServiceImpl.class);
    @Autowired
    private DictRoMapper dictRoMapper;

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final String DICT_KEY = "_Dict";

    @Override
    public List<Dict> selectList(Dict dict) {
        return dictRoMapper.selectList(dict);
    }

    @Override
    public List<Dict> selectList(String dictId) {
        return dictRoMapper.selectByDictId(dictId);
    }

    @Override
    public DictBO selectListByName(String dictId) {
        return recursiveTree(dictId);
    }

    /**
     * 递归算法解析成树形结构
     *
     * @param id id
     */
    public DictBO recursiveTree(String id) {
        DictBO node = dictRoMapper.selectByFieldValue(id);
        List<DictBO> treeNodes = dictRoMapper.selectByParentDictId(id);
        //遍历子节点
        for (DictBO child : treeNodes) {
            DictBO n = recursiveTree(child.getFieldValue()); //递归
            node.getNodes().add(n);
        }
        return node;
    }

    @Override
    public List<DictBO> selectFirstLevel() {
        List<Dict> dicts = dictRoMapper.selectFirstLevel();
        if (dicts == null || dicts.size() == 0) {
            throw new ServiceException(4104);
        }
        List<DictBO> dictBOs = new ArrayList<>();
        for (Dict dict : dicts) {
            DictBO dictBO = new DictBO();
            BeanUtils.copyProperties(dict, dictBO);
            dictBOs.add(dictBO);
        }
        return dictBOs;
    }

    @Override
    public DictBO selectOne(Dict dict) {
        Dict dict1 = dictRoMapper.selectOne(dict);
        DictBO dictBO = new DictBO();
        if (dict1 == null) {
            return null;
        }
        BeanUtils.copyProperties(dict1, dictBO);
        return dictBO;
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public DictBO insert(DictBO dictBO) {
        //dictId，fieldKey确定数据唯一性
        Dict dict = dictRoMapper.selectDict(dictBO);
        if (dict != null) {
            LOGGER.info("dictId，fieldKey确定数据唯一性{}", dict);
            throw new ServiceException(4165);
        }
        dict = new Dict();
        BeanUtils.copyProperties(dictBO, dict);

        dict.setId(Utils.uuid());
        dict.setCreateTime(new Date());
        dict.setLastUpdate(new Date());

        dictMapper.insert(dict);
        // 删除redis
        redisTemplate.delete(dict.getDictId() + DICT_KEY);

        BeanUtils.copyProperties(dict, dictBO);
        return dictBO;
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public DictBO update(DictUpdateBO dictUpdateBO) {
        Dict dict = new Dict();
        dict.setLastUpdate(new Date());
        BeanUtils.copyProperties(dictUpdateBO, dict);
        int upd = dictMapper.update(dict);
        if (upd != 1) {
            throw new ServiceException(4102);
        }

        // 删除redis
        redisTemplate.delete(dictUpdateBO.getDictId() + DICT_KEY);

        DictBO dictBO = new DictBO();
        BeanUtils.copyProperties(dict, dictBO);
        return dictBO;
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public int delete(String id) {
        Dict dict = dictRoMapper.selectById(id);
        int del = dictMapper.delete(id);
        if (del != 1) {
            throw new ServiceException(4103);
        }
        // 删除redis
        redisTemplate.delete(dict.getDictId() + DICT_KEY);
        return del;
    }

    @Override
    public Dict selectById(String id) {
        return dictRoMapper.selectById(id);
    }

    @Override
    public List<DictBO> selectDictList(Dict dict) {
        List<DictBO> dictBOs;
        if (redisTemplate.hasKey(dict.getDictId() + DICT_KEY)) {
            dictBOs = JSONArray.parseArray(redisTemplate.opsForValue().get(dict.getDictId() + DICT_KEY), DictBO.class);
            LOGGER.info("从redis获取数据字典信息:{}", JSONArray.toJSONString(dictBOs));
        } else {
            dictBOs = dictRoMapper.selectDictList(dict);
            redisTemplate.opsForValue().set(dict.getDictId() + DICT_KEY,
                    JSONArray.toJSONString(dictBOs),
                    RedisConstant.DAY_30, TimeUnit.DAYS);
        }
        return dictBOs;
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public void batchDelete(Dict bo) {
        String id = bo.getId();
        if (id == null || "".equals(id)) {
            LOGGER.info("id不能为空{}", bo);
            throw new ServiceException(4150);
        }
        String[] ids = id.split(",");
        for (String dId : ids) {
            Dict dict = dictRoMapper.selectById(id);
            int del = dictMapper.delete(dId);
            if (del != 1) {
                throw new ServiceException(4103);
            }
            // 删除redis
            redisTemplate.delete(dict.getDictId() + DICT_KEY);
        }
    }

    @Override
    public List<Dict> selectListByDictName(Dict dict) {
        return dictRoMapper.selectListByDictName(dict);
    }

}
