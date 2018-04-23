package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS专题内容关联表
 **/
@SuppressWarnings("serial")
public class ContentTopicListBo implements Serializable {

    private List<ContentTopicBo> topicBoList;

    public List<ContentTopicBo> getTopicBoList() {
        return topicBoList;
    }

    public void setTopicBoList(List<ContentTopicBo> topicBoList) {
        this.topicBoList = topicBoList;
    }
}
