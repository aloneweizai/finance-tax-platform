package com.abc12366.uc.model.weixin.bo.message;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * 图文消息
 *
 * @author zhushuai 2017-8-1
 */
public class News {
    private String id;

    @Max(value = 8)
    private int articleCount;//
    // 多条图文消息信息，默认第一个item为大图
    @NotNull
    private List<Article> articles; //图文列表
    private Date creatDate; //创建时间
    private Date lastUpdate;//跟新时间


    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


}
