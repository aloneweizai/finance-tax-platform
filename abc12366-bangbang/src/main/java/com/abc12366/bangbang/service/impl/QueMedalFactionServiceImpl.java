package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db2.QuestionMedalFactionRoMapper;
import com.abc12366.bangbang.model.question.bo.QuestionMedalFactionBo;
import com.abc12366.bangbang.service.QueMedalFactionService;
import com.abc12366.gateway.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyanmao on 2017/10/24.
 */
@Service
public class QueMedalFactionServiceImpl implements QueMedalFactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueMedalFactionServiceImpl.class);

    @Autowired
    private QuestionMedalFactionRoMapper medalFactionRoMapper;

    @Override
    public List<QuestionMedalFactionBo> selectListByFactionId(Map<String,Object> map) {
        List<QuestionMedalFactionBo> medalFactionBoList;
        try {
            //查询勋章列表
            medalFactionBoList = medalFactionRoMapper.selectListByFactionId(map);
        } catch (Exception e) {
            LOGGER.error("查询勋章列表信息异常：{}", e);
            throw new ServiceException(6380);
        }
        return medalFactionBoList;
    }

}
