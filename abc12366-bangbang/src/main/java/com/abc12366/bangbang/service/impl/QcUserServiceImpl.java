package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db2.QcUserRoMapper;
import com.abc12366.bangbang.model.question.bo.QcBangUserBo;
import com.abc12366.bangbang.model.question.bo.QcTitleBo;
import com.abc12366.bangbang.service.QcUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xieyanmao on 2017/9/15.
 */
@Service
public class QcUserServiceImpl implements QcUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QcUserServiceImpl.class);

    @Autowired
    private QcUserRoMapper qcUserRoMapper;

    @Override
    public List<QcBangUserBo> selectList() {
        //查询帮友
        return qcUserRoMapper.selectList();
    }

    @Override
    public List<QcTitleBo> selectQuestionList(String userId) {
        //查询帮友
        return qcUserRoMapper.selectQuestionList(userId);
    }


}
