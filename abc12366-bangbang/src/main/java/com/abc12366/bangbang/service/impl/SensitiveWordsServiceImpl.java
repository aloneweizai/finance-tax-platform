package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.SensitiveWordsMapper;
import com.abc12366.bangbang.mapper.db2.SensitiveWordsRoMapper;
import com.abc12366.bangbang.model.SensitiveWords;
import com.abc12366.bangbang.service.SensitiveWordsService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lizhongwei
 * @create 2017-06-20
 * @since 1.0.0
 */
@Service
public class SensitiveWordsServiceImpl implements SensitiveWordsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveWordsServiceImpl.class);

    @Autowired
    private SensitiveWordsMapper sensitiveWordsMapper;

    @Autowired
    private SensitiveWordsRoMapper sensitiveWordsRoMapper;


    @Override
    public List<SensitiveWords> selectList(SensitiveWords sensitiveWords) {
        return sensitiveWordsRoMapper.selectList(sensitiveWords);
    }

    @Override
    public SensitiveWords selectOne(String id) {
        return sensitiveWordsRoMapper.selectByPrimaryKey(id);
    }

    @Override
    public SensitiveWords update(SensitiveWords sensitiveWords) {
        sensitiveWords.setUpdateAdmin(Utils.getAdminId());
        int update = sensitiveWordsMapper.update(sensitiveWords);
        if (update != 1) {
            LOGGER.info("敏感词数据，修改失败", sensitiveWords);
            throw new ServiceException(4506);
        }
        return sensitiveWords;
    }

    @Override
    public SensitiveWords add(SensitiveWords sensitiveWords) {
        sensitiveWords.setId(Utils.uuid());
        sensitiveWords.setUpdateAdmin(Utils.getAdminId());
        int insert = sensitiveWordsMapper.insert(sensitiveWords);
        if (insert != 1) {
            LOGGER.info("敏感词数据，新增失败", sensitiveWords);
            throw new ServiceException(4505);
        }
        return sensitiveWords;
    }

    @Override
    public void delete(String id) {
        int del = sensitiveWordsMapper.deleteByPrimaryKey(id);
        if (del != 1) {
            LOGGER.info("敏感词数据，删除失败", id);
            throw new ServiceException(4507);
        }
    }

    @Override
    public boolean isAuthentication(Map<String, String> parmMap) {
        return false;
    }

    @Override
    public Set<String> selectListKeywords(SensitiveWords sensitiveWords) {
        return sensitiveWordsRoMapper.selectListKeywords();
    }

}
