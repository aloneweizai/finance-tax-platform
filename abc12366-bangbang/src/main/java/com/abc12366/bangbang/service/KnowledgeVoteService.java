package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.KnowledgeVoteLog;
import com.abc12366.bangbang.model.bo.KnowledgeBaseParamBO;
import com.abc12366.bangbang.model.bo.KnowledgeVoteLogBO;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/11 09:36
 */
public interface KnowledgeVoteService {

    /*新增知识库投票数据*/
    void addVote(KnowledgeVoteLog knowledgeVoteLog);

    /*删除知识库投票数据*/
    void deleteVoteLogs(List<String> ids);

    /*删除知识库投票数据*/
    void deleteVoteLog(String id);

    /*知识库投票列表查询*/
    List<KnowledgeVoteLogBO> selectVoteList(KnowledgeBaseParamBO param);

    /*查询用户该知识库投过票的数据*/
    KnowledgeVoteLog selectByUserVotedKnowledge(String userId, String knowledgeId);

}
