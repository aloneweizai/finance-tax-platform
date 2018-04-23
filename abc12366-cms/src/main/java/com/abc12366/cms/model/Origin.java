package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * 来源
 **/
@SuppressWarnings("serial")
public class Origin implements Serializable {

    /**
     * originId**varchar(64)
     **/
    private String originId;

    /**
     * 来源名称**varchar(255)
     **/
    private String originName;

    /**
     * 来源文章总数**int(11)
     **/
    private Integer refCount;

    public String getOriginId() {
        return this.originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getOriginName() {
        return this.originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Integer getRefCount() {
        return this.refCount;
    }

    public void setRefCount(Integer refCount) {
        this.refCount = refCount;
    }

}
