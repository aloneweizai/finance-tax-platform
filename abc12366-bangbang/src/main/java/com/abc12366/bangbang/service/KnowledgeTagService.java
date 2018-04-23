package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.KnowledgeTag;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/4 15:59
 * 知识库标签
 */
public interface KnowledgeTagService {


    /* 查询关联问题最多的标签 */
    List<String> selectHotTag(Integer num);

    /* 知识库标签查询 */
    List<KnowledgeTag> selectList(String keywords, Boolean status, String tagType);

    /* 知识库关联标签查询 */
    List<KnowledgeTag> selectRelatedTags(String knowledgeId);

    /* 知识库标签查询 */
    KnowledgeTag selectByPk(String id);

    /* 批量新增标签 */
    List<KnowledgeTag> addBatch(List<KnowledgeTag> knowledgeTags);

    /* 批量新增标签（通过知识库，课程页面添加） */
    List<KnowledgeTag> addBatchByOtherChannel(List<KnowledgeTag> knowledgeTags);

    /* 新增标签 */
    KnowledgeTag add(KnowledgeTag knowledgeTag);

    /* 编辑标签 */
    KnowledgeTag modify(KnowledgeTag knowledgeTag);

    /* 启用，停用 */
    void modifyStatus(String id, Boolean status);

    /* 删除标签 */
    void delete(String id);

    /* 批量删除标签 */
    void delete(List<String> ids);
}
