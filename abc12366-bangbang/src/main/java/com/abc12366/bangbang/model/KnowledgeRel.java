package com.abc12366.bangbang.model;

/**
 *
 *
 *
 **/
public class KnowledgeRel {

    /****/
    private String id;

    /**
     * bb_knowledge_base表的ID
     **/
    private String knowledgeId;

    /**
     * 关联的bb_knowledge_base表的ID
     **/
    private String relKnowledgeId;

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

    public String getRelKnowledgeId() {
        return this.relKnowledgeId;
    }

    public void setRelKnowledgeId(String relKnowledgeId) {
        this.relKnowledgeId = relKnowledgeId;
    }

}
