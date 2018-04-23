package com.abc12366.bangbang.model.curriculum;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author liuQi
 * @Date 2018/1/30 15:04
 */
@SuppressWarnings("serial")
public class CurriculumBrowserWatch implements Serializable {

    /**ID**varchar(64)**/
    private String id;

    /**课程id**varchar(64)**/
    private String curriculumId;

    /**浏览量**tinyint(11)**/
    private Integer browseNum;

    /**观看数**tinyint(11)**/
    private Integer watchNum;

    /**时间**datetime**/
    private java.util.Date date;

    public String getId() {
        return id;
    }

    public CurriculumBrowserWatch setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public CurriculumBrowserWatch setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
        return this;
    }

    public Integer getWatchNum() {
        return watchNum;
    }

    public CurriculumBrowserWatch setWatchNum(Integer watchNum) {
        this.watchNum = watchNum;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public CurriculumBrowserWatch setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getCurriculumId() {
        return curriculumId;
    }

    public CurriculumBrowserWatch setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
        return this;
    }
}
