package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.KnowledgeBaseMapper;
import com.abc12366.bangbang.mapper.db1.KnowledgeVoteLogMapper;
import com.abc12366.bangbang.model.KnowledgeVoteLog;
import com.abc12366.bangbang.model.bo.KnowledgeBaseParamBO;
import com.abc12366.bangbang.model.bo.KnowledgeVoteLogBO;
import com.abc12366.bangbang.service.KnowledgeVoteService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/11 09:36
 */
@Service
public class KnowledgeVoteServiceImpl implements KnowledgeVoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeVoteServiceImpl.class);

    @Autowired
    private KnowledgeBaseMapper knowledgeBaseMapper;

    @Autowired
    private KnowledgeVoteLogMapper knowledgeVoteLogMapper;


    @Transactional("db1TxManager")
    @Override
    public void addVote(KnowledgeVoteLog knowledgeVoteLog) {
        try {
            Boolean isUseFull = knowledgeVoteLog.getIsUseFull();
            String knowledgeId = knowledgeVoteLog.getKnowledgeId();
            if(isUseFull == Boolean.TRUE){
                knowledgeBaseMapper.addUsefulVoteByPK(knowledgeId);
            }else{
                knowledgeBaseMapper.addUselessVoteByPK(knowledgeId);
            }
//            knowledgeVoteLog.setUserId(UcUserCommon.getUserIdTwo());
            knowledgeVoteLog.setId(Utils.uuid());
            knowledgeVoteLogMapper.insert(knowledgeVoteLog);
        }catch (Exception e){
            LOGGER.error("KnowledgeBaseServiceImpl.addVote():" + e);
            throw new ServiceException(4504);
        }
    }

    @Override
    public void deleteVoteLogs(List<String> ids) {
        try {
            knowledgeVoteLogMapper.deleteByPrimaryKeys(ids);
        }catch (Exception e){
            LOGGER.error("KnowledgeBaseServiceImpl.deleteVoteLogs():" + e);
            throw new ServiceException(4505);
        }
    }

    @Override
    public void deleteVoteLog(String id) {
        try {
            knowledgeVoteLogMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            LOGGER.error("KnowledgeBaseServiceImpl.deleteVoteLogs():" + e);
            throw new ServiceException(4505);
        }
    }

    @Override
    public List<KnowledgeVoteLogBO> selectVoteList(KnowledgeBaseParamBO param) {
        return knowledgeVoteLogMapper.selectList(param);
    }

    @Override
    public KnowledgeVoteLog selectByUserVotedKnowledge(String userId, String knowledgeId) {
        return knowledgeVoteLogMapper.selectByUserVotedKnowledge(userId, knowledgeId);
    }
}
