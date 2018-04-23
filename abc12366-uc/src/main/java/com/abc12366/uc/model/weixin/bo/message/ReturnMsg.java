package com.abc12366.uc.model.weixin.bo.message;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class ReturnMsg {
    private String id; //主键
    private String toUserName; //接收人
    private String fromUserName; //发送人
    private long createTime; //创建时间
    @NotEmpty
    private String setting; //0:添加消息,1:回复消息,2:关键字消息
    private String keyString;//关键字
    private String searchTp;//匹配类型
    private Integer sort;//排序
    @NotEmpty
    private String msgType;//文件类型text,image,news
    //text
    private String content;//文本内容

    //image 
    private String mediaId;//素材
    private String imgurl;//图片地址

    //news
    private String newsId;//图文
    private News news;//图文内容

    private Date createDate; //创建日期
    private Date lastUpdate;//更新日期


    /**
     * 内容转换XML
     * @param toUserName 接收人
     * @param fromUserName 发送人
     * @param createTime 时间
     * @return
     */
	public String toWxXml(String toUserName, String fromUserName, long createTime) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        StringBuffer buffer = new StringBuffer("<xml>");
        buffer.append("<ToUserName><![CDATA[").append(this.toUserName).append("]]></ToUserName>");
        buffer.append("<FromUserName><![CDATA[").append(this.fromUserName).append("]]></FromUserName>");
        buffer.append("<CreateTime><![CDATA[").append(this.createTime).append("]]></CreateTime>");
        buffer.append("<MsgType><![CDATA[").append(this.msgType).append("]]></MsgType>");
        if ("text".equals(this.msgType)) {
            buffer.append("<Content><![CDATA[").append(this.content).append("]]></Content>");
        } else if ("image".equals(this.msgType)) {
            buffer.append("<Image><MediaId><![CDATA[").append(this.mediaId).append("]]></MediaId></Image>");
        } else if ("news".equals(this.msgType)){
            buffer.append("<ArticleCount><![CDATA[").append(this.news.getArticleCount()).append
                    ("]]></ArticleCount><Articles>");
            for (Article article : this.news.getArticles()) {
                buffer.append("<item>");
                buffer.append("<Title><![CDATA[").append(article.getTitle()).append("]]></Title>");
                buffer.append("<Description><![CDATA[").append(article.getDescription()).append("]]></Description>");
                buffer.append("<PicUrl><![CDATA[").append(article.getPicUrl()).append("]]></PicUrl>");
                buffer.append("<Url><![CDATA[").append(article.getUrl()).append("]]></Url>");
                buffer.append("</item>");
            }
            buffer.append("</Articles>");
        }
        buffer.append("</xml>");
        System.out.println("回复微信服务器信息:"+buffer.toString());
        return buffer.toString();
    }
	
    
    
    public String getSearchTp() {
		return searchTp;
	}

	public void setSearchTp(String searchTp) {
		this.searchTp = searchTp;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }


    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }


}
