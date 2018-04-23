package com.abc12366.bangbang.model.bo;

/**
 * @Author liuqi
 * @Date 2017/8/5 10:47
 *
 * 知识库 帮助中心首页 知识库推荐（热点问题知识...）
 */
public class KnowledgeBaseHotParamBO {

    /* 分类个数 */
    private int categoryNum;

    /* 每个分类 下面的 知识条数 */
    private int KnowledgePageSize;

    /* 知识库类型 （QA问答，K知识）*/
    private String KnowledgeType;

    /* 知识库推荐  QA(common常见,hot热点); K(top头条，hot热点)*/
    private String KnowledgeRecommend;

    public KnowledgeBaseHotParamBO(int categoryNum,  int knowledgePageSize, String knowledgeType, String knowledgeRecommend) {
        this.categoryNum = categoryNum;
        KnowledgeType = knowledgeType;
        KnowledgePageSize = knowledgePageSize;
        KnowledgeRecommend = knowledgeRecommend;
    }
    public KnowledgeBaseHotParamBO(int knowledgePageSize, String knowledgeType, String knowledgeRecommend) {
        KnowledgeType = knowledgeType;
        KnowledgePageSize = knowledgePageSize;
        KnowledgeRecommend = knowledgeRecommend;
    }

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        this.categoryNum = categoryNum;
    }

    public int getKnowledgePageSize() {
        return KnowledgePageSize;
    }

    public void setKnowledgePageSize(int knowledgePageSize) {
        KnowledgePageSize = knowledgePageSize;
    }

    public String getKnowledgeType() {
        return KnowledgeType;
    }

    public void setKnowledgeType(String knowledgeType) {
        KnowledgeType = knowledgeType;
    }

    public String getKnowledgeRecommend() {
        return KnowledgeRecommend;
    }

    public void setKnowledgeRecommend(String knowledgeRecommend) {
        KnowledgeRecommend = knowledgeRecommend;
    }
}
