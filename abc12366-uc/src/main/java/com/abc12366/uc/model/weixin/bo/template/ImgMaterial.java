package com.abc12366.uc.model.weixin.bo.template;


import com.abc12366.uc.model.weixin.BaseWxRespon;
/**
 * 微信图文素材
 * @author zhushuai 2017-11-6
 *
 */
public class ImgMaterial extends BaseWxRespon {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String media_id; //素材id
    private String url;//地址

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
