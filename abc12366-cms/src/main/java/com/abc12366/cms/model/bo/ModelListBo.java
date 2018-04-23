package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS模型表
 **/
@SuppressWarnings("serial")
public class ModelListBo implements Serializable {

    private List<ModelBo> modelBoList;

    public List<ModelBo> getModelBoList() {
        return modelBoList;
    }

    public void setModelBoList(List<ModelBo> modelBoList) {
        this.modelBoList = modelBoList;
    }
}
