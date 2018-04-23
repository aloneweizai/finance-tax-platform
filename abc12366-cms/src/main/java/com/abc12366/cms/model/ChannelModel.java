package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * 栏目可选内容模型表
 **/
@SuppressWarnings("serial")
public class ChannelModel implements Serializable {

    /**
     * channelId**varchar(64)
     **/
    private String channelId;

    /**
     * 模型id**varchar(64)
     **/
    private String modelId;

    /**
     * 内容模板**varchar(100)
     **/
    private String tplContent;

    /**
     * 排序**int(11)
     **/
    private Integer priority;

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getModelId() {
        return this.modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getTplContent() {
        return this.tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
