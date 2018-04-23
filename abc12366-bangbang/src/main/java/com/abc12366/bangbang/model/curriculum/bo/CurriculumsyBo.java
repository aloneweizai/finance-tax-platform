package com.abc12366.bangbang.model.curriculum.bo;

import com.abc12366.bangbang.model.curriculum.CurriculumLabel;
import com.abc12366.bangbang.model.curriculum.CurriculumMembergrade;
import com.abc12366.bangbang.model.curriculum.CurriculumUvipPrice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 课程表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumsyBo implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

    /**课程标题**varchar(200)**/
    private String title;

    /**浏览量**tinyint(11)**/
    private Integer browseNum;

    /**观看数**tinyint(11)**/
    private Integer watchNum;

    /**商品ID**varchar(64)**/
    private String goodsId;

	/**发布时间**datetime**/
	private java.util.Date issueTime;

	/**课程封面**varchar(200)**/
	private String cover;

	/**课程收费**tinyint(1)**/
	private Integer isFree;

    /**成本价格**double**/
    private Double costPrice;

    /**销售价格**double**/
    private Double sellPrice;

    /**市场价格**double**/
    private Double marketPrice;

    /**积分价格**double**/
    private Double integralPrice;

	/**课程简介**longtext**/
	private String curriculumidIntro;

    /**好评数**int**/
    private Integer evaluateNum45;

    /**中等评数**int**/
    private Integer evaluateNum23;

    /**差评论数**int**/
    private Integer evaluateNum1;

    /**总评论数**int**/
    private Integer evaluateNum;


    /**培训时间起**datetime**/
    private java.util.Date trainTimeBegin;

    /**培训时间止**datetime**/
    private java.util.Date trainTimeEnd;

    /**是否需要报名**tinyint(1)**/
    private Integer isApply;

    /**人数限制**int(11)**/
    private Integer peopleLimit;

    /**报名时间起**datetime**/
    private java.util.Date applyTimeBegin;

    /**报名时间止**datetime**/
    private java.util.Date applyTimeEnd;

    /**报名时交费**tinyint(1)**/
    private Integer applyPay;

    /**是否需要签到**tinyint(1)**/
    private Integer isSign;

    /**签到时间起**datetime**/
    private java.util.Date signTimeBegin;

    /**签到时间止**datetime**/
    private java.util.Date signTimeEnd;

    /**授课方式**varchar(64)**/
    private String teachingMethod;



    //标签
    private List<CurriculumLabel> labelList;

    //会员等级
    private List<CurriculumMembergrade> membergradeList;

    //讲师
    private List<CurriculumLecturerBo> lecturerList;

    //章节
    private List<CurriculumChapterBo> chapterBoList;

    //相关课程
    List<CurriculumListsyBo> curriculumListBoList;

    private List<CurriculumUvipPriceBo> uvipPriceBoList;

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

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public Integer getEvaluateNum45() {
        return evaluateNum45;
    }

    public void setEvaluateNum45(Integer evaluateNum45) {
        this.evaluateNum45 = evaluateNum45;
    }

    public Integer getEvaluateNum23() {
        return evaluateNum23;
    }

    public void setEvaluateNum23(Integer evaluateNum23) {
        this.evaluateNum23 = evaluateNum23;
    }

    public Integer getEvaluateNum1() {
        return evaluateNum1;
    }

    public void setEvaluateNum1(Integer evaluateNum1) {
        this.evaluateNum1 = evaluateNum1;
    }

    public Integer getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(Integer evaluateNum) {
        this.evaluateNum = evaluateNum;
    }


    public Date getTrainTimeBegin() {
        return trainTimeBegin;
    }

    public void setTrainTimeBegin(Date trainTimeBegin) {
        this.trainTimeBegin = trainTimeBegin;
    }

    public Date getTrainTimeEnd() {
        return trainTimeEnd;
    }

    public void setTrainTimeEnd(Date trainTimeEnd) {
        this.trainTimeEnd = trainTimeEnd;
    }

    public Integer getIsApply() {
        return isApply;
    }

    public void setIsApply(Integer isApply) {
        this.isApply = isApply;
    }

    public Integer getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(Integer peopleLimit) {
        this.peopleLimit = peopleLimit;
    }

    public Date getApplyTimeBegin() {
        return applyTimeBegin;
    }

    public void setApplyTimeBegin(Date applyTimeBegin) {
        this.applyTimeBegin = applyTimeBegin;
    }

    public Date getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(Date applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }

    public Integer getApplyPay() {
        return applyPay;
    }

    public void setApplyPay(Integer applyPay) {
        this.applyPay = applyPay;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public Date getSignTimeBegin() {
        return signTimeBegin;
    }

    public void setSignTimeBegin(Date signTimeBegin) {
        this.signTimeBegin = signTimeBegin;
    }

    public Date getSignTimeEnd() {
        return signTimeEnd;
    }

    public void setSignTimeEnd(Date signTimeEnd) {
        this.signTimeEnd = signTimeEnd;
    }


    public String getTeachingMethod() {
        return teachingMethod;
    }

    public void setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
    }

    public List<CurriculumLabel> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<CurriculumLabel> labelList) {
        this.labelList = labelList;
    }

    public List<CurriculumMembergrade> getMembergradeList() {
        return membergradeList;
    }

    public void setMembergradeList(List<CurriculumMembergrade> membergradeList) {
        this.membergradeList = membergradeList;
    }

    public List<CurriculumLecturerBo> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(List<CurriculumLecturerBo> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public List<CurriculumChapterBo> getChapterBoList() {
        return chapterBoList;
    }

    public void setChapterBoList(List<CurriculumChapterBo> chapterBoList) {
        this.chapterBoList = chapterBoList;
    }

    public List<CurriculumListsyBo> getCurriculumListBoList() {
        return curriculumListBoList;
    }

    public void setCurriculumListBoList(List<CurriculumListsyBo> curriculumListBoList) {
        this.curriculumListBoList = curriculumListBoList;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(Double integralPrice) {
        this.integralPrice = integralPrice;
    }

    public List<CurriculumUvipPriceBo> getUvipPriceBoList() {
        return uvipPriceBoList;
    }

    public void setUvipPriceBoList(List<CurriculumUvipPriceBo> uvipPriceBoList) {
        this.uvipPriceBoList = uvipPriceBoList;
    }
}
