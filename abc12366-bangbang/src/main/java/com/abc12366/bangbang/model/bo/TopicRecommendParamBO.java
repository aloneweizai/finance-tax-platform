package com.abc12366.bangbang.model.bo;

/**
 * @Author liuQi
 * @Date 2017/10/11 15:20
 * 话题推荐请求参数
 */
public class TopicRecommendParamBO {

    private Integer page = 1;

    private Integer size = 15;

    /*关键字查询*/
    private String keywords;

    /*是否推荐*/
    private Boolean isRecommend;

    /*回答数，评论数，点赞数*/
    private String sortFieldName;
    /*asc.desc*/
    private String sortName;

    private String type;

    public String getType() {
        return type;
    }

    public TopicRecommendParamBO setType(String type) {
        this.type = type;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public TopicRecommendParamBO setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public TopicRecommendParamBO setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
        return this;
    }

    public String getSortFieldName() {
        return sortFieldName;
    }

    public TopicRecommendParamBO setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
        return this;
    }

    public String getSortName() {
        return sortName;
    }

    public TopicRecommendParamBO setSortName(String sortName) {
        this.sortName = sortName;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public TopicRecommendParamBO setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public TopicRecommendParamBO setSize(Integer size) {
        this.size = size;
        return this;
    }
}
