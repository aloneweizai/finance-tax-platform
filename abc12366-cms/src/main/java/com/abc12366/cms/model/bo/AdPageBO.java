package com.abc12366.cms.model.bo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * User: yuanluo<435720953@qq.com.com>
 * Date: 2017-07-26
 * Time: 11:06
 */
public class AdPageBO {

    private String id;

    @Size(max = 128)
    private String name;
    @Size(max = 128)
    private String url;
    @Size(max = 300)
    private String link;



    @Size(max = 50)
    private String style;
    /** 访问总数 */
    private Integer openCount;
    private Integer sort;
    @NotNull
    private Boolean showName;
    @NotNull
    private Boolean status;
    private Date createTime;
    private Date lastUpdate;
    private Date startTime;
    private Date endTime;

    public AdPageBO() {
    }

    public Integer getOpenCount() {
        return openCount;
    }

    public void setOpenCount(Integer openCount) {
        this.openCount = openCount;
    }

    public AdPageBO(String id, String name, String url, String link, Integer sort, Boolean showName, Boolean status,
                    Date createTime, Date lastUpdate) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.link = link;
        this.sort = sort;
        this.showName = showName;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getShowName() {
        return showName;
    }
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    public void setShowName(Boolean showName) {
        this.showName = showName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExperienceRuleInsertBO{" +
                "id='" + id + '\'' +
                ",name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", link=" + link +
                ", sort='" + sort + '\'' +
                ", showName='" + showName + '\'' +
                ", status=" + status + '\'' +
                ", createTime=" + createTime + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}