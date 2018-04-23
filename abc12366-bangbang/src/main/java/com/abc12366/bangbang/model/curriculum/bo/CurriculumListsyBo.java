package com.abc12366.bangbang.model.curriculum.bo;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 课程首页
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumListsyBo implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

    /**课程标题**varchar(200)**/
    private String title;

    /**
     * 授课方式
     */
    private String teachingMethod;

    /**销售价格**double**/
    private Double sellPrice;

    /**浏览量**tinyint(11)**/
    private Integer browseNum;

    /**观看数**tinyint(11)**/
    private Integer watchNum;

    /**商品ID**varchar(64)**/
    private String goodsId;

    /**课程封面**varchar(200)**/
    private String cover;

	/**发布时间**datetime**/
	private Date issueTime;

    /**收藏时间**datetime**/
    private Date collectTime;

	/**课程收费**tinyint(1)**/
	private Integer isFree;

    /**会员是否免费**tinyint(1)**/
    private Integer isVipFree;

    /**课程简介**longtext**/
    private String curriculumidIntro;

    /**课件数**tinyint(10)**/
    private Integer coursewareNum;


    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public void setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getWatchNum() {
        return watchNum;
    }

    public void setWatchNum(Integer watchNum) {
        this.watchNum = watchNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getCurriculumidIntro() {
        return curriculumidIntro;
    }

    public void setCurriculumidIntro(String curriculumidIntro) {
        this.curriculumidIntro = curriculumidIntro;
    }

    public Integer getCoursewareNum() {
        return coursewareNum;
    }

    public void setCoursewareNum(Integer coursewareNum) {
        this.coursewareNum = coursewareNum;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getIsVipFree() {
        return isVipFree;
    }

    public void setIsVipFree(Integer isVipFree) {
        this.isVipFree = isVipFree;
    }
}
