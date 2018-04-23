package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS模型表
 **/
@SuppressWarnings("serial")
public class TopicListBo implements Serializable {

    private List<TopicBo> topicBoList;

    public List<TopicBo> getTopicBoList() {
        return topicBoList;
    }

    public void setTopicBoList(List<TopicBo> topicBoList) {
        this.topicBoList = topicBoList;
    }
}
