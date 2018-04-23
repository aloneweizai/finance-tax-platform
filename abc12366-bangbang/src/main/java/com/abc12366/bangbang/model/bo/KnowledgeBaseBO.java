package com.abc12366.bangbang.model.bo;

import com.abc12366.bangbang.model.KnowledgeBase;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/4 09:12
 */
public class KnowledgeBaseBO {

    private KnowledgeBase knowledgeBase;

    private List<String> tagIds;        //知识库关联的标签id

    private List<String> refKnowledgeId;//知识库关联的其他知识库id

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public KnowledgeBase getKnowledgeBase() {
        return knowledgeBase;
    }

    public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public List<String> getRefKnowledgeId() {
        return refKnowledgeId;
    }

    public void setRefKnowledgeId(List<String> refKnowledgeId) {
        this.refKnowledgeId = refKnowledgeId;
    }
}
