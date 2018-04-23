package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db2.QuestionCountRoMapper;
import com.abc12366.bangbang.model.question.bo.QuestionCountBo;
import com.abc12366.bangbang.service.QueCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xieyanmao on 2017/9/15.
 */
@Service
public class QueCountServiceImpl implements QueCountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueCountServiceImpl.class);

    @Autowired
    private QuestionCountRoMapper countMapper;


    @Override
    public List<QuestionCountBo> selectLike() {
        return countMapper.selectLike();
    }

    @Override
    public List<QuestionCountBo> selectAttention() {
        return countMapper.selectAttention();
    }

    @Override
    public List<QuestionCountBo> selectAccept() {
        return countMapper.selectAccept();
    }

    @Override
    public List<QuestionCountBo> selectAnswers() {
        return countMapper.selectAnswers();
    }

    @Override
    public List<QuestionCountBo> selectMedal() {
        return countMapper.selectMedal();
    }

    @Override
    public List<QuestionCountBo> selectAcceptExpert() {
        return countMapper.selectAcceptExpert();
    }
}
