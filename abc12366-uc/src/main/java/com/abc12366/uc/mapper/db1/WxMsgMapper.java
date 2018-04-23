package com.abc12366.uc.mapper.db1;


import com.abc12366.uc.model.weixin.bo.message.Article;
import com.abc12366.uc.model.weixin.bo.message.News;
import com.abc12366.uc.model.weixin.bo.message.ReturnMsg;

public interface WxMsgMapper {

    void insertNews(News news);

    void insertArticle(Article article);

    int updateNews(News news);

    void deleteArticle(String id);

    void insertRemsg(ReturnMsg returnMsg);

    void deleteNews(String id);

    int updateRemsg(ReturnMsg returnMsg);

    void deleteWxremsg(String id);

}
