package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS模型项表
 **/
@SuppressWarnings("serial")
public class ModelItemListBo implements Serializable {

    private List<ModelItemBo> modelItemBoList;

    public List<ModelItemBo> getModelItemBoList() {
        return modelItemBoList;
    }

    public void setModelItemBoList(List<ModelItemBo> modelItemBoList) {
        this.modelItemBoList = modelItemBoList;
    }
}
