package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionExpertMapper;
import com.abc12366.bangbang.mapper.db2.QuestionExpertRoMapper;
import com.abc12366.bangbang.model.question.QuestionExpert;
import com.abc12366.bangbang.model.question.bo.QuestionExpertBO;
import com.abc12366.bangbang.model.question.bo.QuestionExpertParamBo;
import com.abc12366.bangbang.service.QuestionExpertService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/9/15 10:37
 */
@Service
public class QuestionExpertServiceImpl implements QuestionExpertService{

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionExpertServiceImpl.class);

    @Autowired
    private QuestionExpertMapper questionExpertMapper;

    @Autowired
    private QuestionExpertRoMapper questionExpertRoMapper;

    @Override
    public List<QuestionExpertBO> selectList(QuestionExpertParamBo param) {
        return questionExpertRoMapper.selectList(param);
    }

    @Override
    public List<QuestionExpertBO> selectListDX(Map<String, Object> map) {
        return questionExpertRoMapper.selectListDX(map);
    }

    @Override
    public QuestionExpertBO selectOne(String id) {
        return questionExpertRoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(QuestionExpert questionExpert) {
        try{
            String adminId = Utils.getAdminId();
            questionExpert.setId(Utils.uuid());
            questionExpert.setCreateAdmin(adminId);
            questionExpert.setUpdateAdmin(adminId);
            questionExpertMapper.insert(questionExpert);
        }catch (Exception e){
            LOGGER.error("QuestionExpertServiceImpl.add():" + e);
            throw new ServiceException(6390);
        }
    }

    @Override
    public void modify(QuestionExpert questionExpert) {
        try {
            questionExpert.setUpdateAdmin(Utils.getAdminId());
            questionExpertMapper.updateByPrimaryKeySelective(questionExpert);
        }catch (Exception e){
            LOGGER.error("QuestionExpertServiceImpl.modify():" + e);
            throw new ServiceException(6391);
        }
    }

    @Override
    public void delete(String id) {
        try {
            questionExpertMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("QuestionExpertServiceImpl.delete():" + e);
            throw new ServiceException(6392);
        }
    }

    @Override
    public List<QuestionExpertBO> selectListByUserId(String userId) {
        return questionExpertRoMapper.selectListByUserId(userId);
    }
}
