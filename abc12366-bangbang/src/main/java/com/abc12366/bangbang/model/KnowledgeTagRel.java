package com.abc12366.bangbang.model;

/**
 * 知识库标签关联表
 **/
public class KnowledgeTagRel {

    /**
     * 知识库 标签关联Id
     **/
    private String id;

    /**
     * 知识库ID
     **/
    private String knowledgeId;

    /**
     * 关联的标签ID
     **/
    private String tagId;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKnowledgeId() {
        return this.knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getTagId() {
        return this.tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

}
