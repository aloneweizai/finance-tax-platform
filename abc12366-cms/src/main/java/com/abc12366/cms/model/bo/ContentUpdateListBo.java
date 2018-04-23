package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS内容批量更新
 **/
@SuppressWarnings("serial")
public class ContentUpdateListBo implements Serializable {

    private List<ContentBo> contentBoList;

    public List<ContentBo> getContentBoList() {
        return contentBoList;
    }

    public void setContentBoList(List<ContentBo> contentBoList) {
        this.contentBoList = contentBoList;
    }
}
