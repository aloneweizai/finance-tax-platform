package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db2.QuestionMedalUserRoMapper;
import com.abc12366.bangbang.model.question.bo.QuestionMedalUserBo;
import com.abc12366.bangbang.service.QueMedalUserService;
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
public class QueMedalUserServiceImpl implements QueMedalUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueMedalUserServiceImpl.class);

    @Autowired
    private QuestionMedalUserRoMapper medalUserRoMapper;

    @Override
    public List<QuestionMedalUserBo> selectListByUserId(Map<String,Object> map) {
        List<QuestionMedalUserBo> medalUserBoList;
        try {
            //查询勋章列表
            medalUserBoList = medalUserRoMapper.selectListByUserId(map);
        } catch (Exception e) {
            LOGGER.error("查询勋章列表信息异常：{}", e);
            throw new ServiceException(6380);
        }
        return medalUserBoList;
    }

}
