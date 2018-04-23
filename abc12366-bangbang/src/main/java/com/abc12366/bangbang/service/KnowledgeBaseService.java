package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.KnowledgeBase;
import com.abc12366.bangbang.model.KnowledgeTagRel;
import com.abc12366.bangbang.model.bo.KnowledgeBaseBO;
import com.abc12366.bangbang.model.bo.KnowledgeBaseHotParamBO;
import com.abc12366.bangbang.model.bo.KnowledgeBaseListBO;
import com.abc12366.bangbang.model.bo.KnowledgeBaseParamBO;

import java.util.List;
import java.util.Map;

/**
 * @Author liuqi
 * @Date 2017/8/2 19:53
 * 知识库 CRUD
 */
public interface KnowledgeBaseService {

    /* 帮助中心首页 热点问题，词条 */
    Map<String, List<KnowledgeBase>> hotMap(KnowledgeBaseHotParamBO paramBO);

    /* 财税网首页 最新问题 */
    List<KnowledgeBase> selectNearestList(KnowledgeBaseHotParamBO param);

    /* 财税网首页 热点问题，词条，不分小类 */
    List<KnowledgeBase> hotUnClassifyMap(KnowledgeBaseHotParamBO paramBO);

    /* UC知识库列表查询数据*/
    List<KnowledgeBase> selectUCList(KnowledgeBaseParamBO param);

    /* UC知识库列表查询数据*/
    List<KnowledgeBase> selectUCListByTag(KnowledgeBaseParamBO param);

    /* 知识库列表查询数据*/
    List<KnowledgeBaseListBO> selectList(KnowledgeBaseParamBO param);

    /* 根据知识ID 查询关联感兴趣的知识列表 */
    List<KnowledgeBase> interestedList(String id, int num);

    /* 根据知识ID 查询已关联的知识列表 */
    List<KnowledgeBase> relatedList(String id, int num);

    /* 知识库单个查询*/
    KnowledgeBase selectOne(String id);

    List<KnowledgeBase> wxhotUnClassifyMap(KnowledgeBaseHotParamBO param);

    /*采集来源列表*/
    List<String> selectSourceList();

    /*根据标题查询匹配的知识库*/
    List<KnowledgeBase> selectBySubject(String subject);



    /* 新增知识库数据 */
    void add(KnowledgeBase knowledgeBase);

    /* 批量删除知识库 */
    void delete(List<String> ids);

    /* 批量删除知识库 */
    void modifyStatus(Map<String,Object> map);

    /*添加知识库数据*/
    KnowledgeBaseBO add(KnowledgeBaseBO knowledgeBaseBO);

    /*修改知识库数据*/
    KnowledgeBaseBO modify(KnowledgeBaseBO knowledgeBaseBO);

    /*修改知识库数据 浏览量*/
    void addPV(String id);

    /*修改*/
    void modify(List<KnowledgeBase> list);

    /*批量修改标签*/
    void batchModifyTag(List<KnowledgeTagRel> list);
}
