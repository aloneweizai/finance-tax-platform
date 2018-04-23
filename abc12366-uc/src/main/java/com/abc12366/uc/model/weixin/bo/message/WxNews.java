package com.abc12366.uc.model.weixin.bo.message;

import com.abc12366.uc.model.weixin.BaseWxRespon;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 图文消息
 *
 * @author zhushuai 2017-8-1
 */
public class WxNews extends BaseWxRespon {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @NotNull
    private List<WxArticle> articles; //图文列表

    private String media_id; //素材ID

    public List<WxArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<WxArticle> articles) {
        this.articles = articles;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }


}
