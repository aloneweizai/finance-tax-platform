package com.abc12366.uc.model.weixin.bo.template;

import com.abc12366.uc.model.weixin.BaseWxRespon;

import java.util.List;

/**
 * WX模板消息返回
 *
 * @author zhushuai 2017-8-3
 */
public class WxTemplates extends BaseWxRespon {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<Template> template_list;

    public List<Template> getTemplate_list() {
        return template_list;
    }

    public void setTemplate_list(List<Template> template_list) {
        this.template_list = template_list;
    }


} 
