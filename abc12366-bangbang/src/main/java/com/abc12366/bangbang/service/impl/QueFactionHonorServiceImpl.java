package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db2.QuestionFactionHonorRoMapper;
import com.abc12366.bangbang.model.question.bo.QuestionFactionPhBo;
import com.abc12366.bangbang.service.QueFactionHonorService;
import com.abc12366.gateway.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xieyanmao on 2017/9/14.
 */
@Service
public class QueFactionHonorServiceImpl implements QueFactionHonorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueFactionHonorServiceImpl.class);

    @Autowired
    private QuestionFactionHonorRoMapper honorRoMapper;

    @Override
    public List<QuestionFactionPhBo> selectList(String honorTime) {
        List<QuestionFactionPhBo> factionPhBoList;
        try {
            //查询邦派排名列表
            factionPhBoList = honorRoMapper.selectList(honorTime);
        } catch (Exception e) {
            LOGGER.error("查询邦派排名列表信息异常：{}", e);
            throw new ServiceException(6120);
        }
        return factionPhBoList;
    }

    @Override
    public List<QuestionFactionPhBo> selectljList() {
        List<QuestionFactionPhBo> factionPhBoList;
        try {
            //查询邦派累计排名列表
            factionPhBoList = honorRoMapper.selectljList();
        } catch (Exception e) {
            LOGGER.error("查询邦派排名列表信息异常：{}", e);
            throw new ServiceException(6120);
        }
        return factionPhBoList;
    }

}
