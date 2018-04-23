package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS内容表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class ChannelInitBo implements Serializable {
    private List<ModelItemBo> modelItems;
    //栏目模板前缀
    private String tplPrefix;

    public List<ModelItemBo> getModelItems() {
        return modelItems;
    }

    public void setModelItems(List<ModelItemBo> modelItems) {
        this.modelItems = modelItems;
    }

    public String getTplPrefix() {
        return tplPrefix;
    }

    public void setTplPrefix(String tplPrefix) {
        this.tplPrefix = tplPrefix;
    }
}
