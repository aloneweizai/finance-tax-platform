package com.abc12366.bangbang.model.curriculum.bo;
import com.abc12366.bangbang.model.curriculum.CurriculumLabel;
import com.abc12366.bangbang.model.curriculum.CurriculumLecturerGx;
import com.abc12366.bangbang.model.curriculum.CurriculumMembergrade;
import com.abc12366.bangbang.model.curriculum.CurriculumUvipPrice;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * 课程表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumBo implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

    /**课程标题**varchar(200)**/
    private String title;

    /**浏览量**tinyint(11)**/
    private Integer browseNum;

    /**观看数**tinyint(11)**/
    private Integer watchNum;

	/**课程分类**varchar(64)**/
	private String classify;

    /**分类名称**varchar(100)**/
    private String classifyName;

    /**商品ID**varchar(64)**/
    private String goodsId;

	/**课程推荐**varchar(64)**/
	private String recommend;

	/**授课方式**varchar(64)**/
	private String teachingMethod;

	/**培训时间起**datetime**/
	private java.util.Date trainTimeBegin;

	/**培训时间止**datetime**/
	private java.util.Date trainTimeEnd;

	/**是否需要报名**tinyint(1)**/
	private Integer isApply;

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

	/**发布时间**datetime**/
	private java.util.Date issueTime;

	/**人数限制**int(11)**/
	private Integer peopleLimit;

	/**培训地点**varchar(200)**/
	private String trainSite;

	/**课程封面**varchar(200)**/
	private String cover;

	/**相关标签**varchar(200)**/
	private String label;

	/**发布范围**varchar(64)**/
	private String issueScope;

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

	/**免费会员**varchar(200)**/
	private String freeMember;

	/**讲师ID**varchar(64)**/
	private String lecturerId;

	/**授课讲师名称**varchar(30)**/
	private String lecturerName;

	/**授课讲师图片**varchar(200)**/
	private String lecturerPicture;

	/**课程简介**longtext**/
	private String curriculumidIntro;

	/**课程状态**tinyint(1)**/
	private Integer status;

	/**创建人ID**varchar(64)**/
	private String createrId;

	/**创建人名称**varchar(30)**/
	private String createrName;

	/**创建时间**datetime**/
	private java.util.Date createrTime;

	/**修改时间**datetime**/
	private java.util.Date updateTime;


    private List<CurriculumLabel> labelList;

    private List<CurriculumMembergrade> membergradeList;

    private List<CurriculumLecturerGx> lecturerGxList;

    private List<CurriculumChapterBo> chapterBoList;

    private List<CurriculumUvipPriceBo> uvipPriceBoList;



	public void setCurriculumId(String curriculumId){
		this.curriculumId = curriculumId;
	}

	public String getCurriculumId(){
		return this.curriculumId;
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

    public void setClassify(String classify){
		this.classify = classify;
	}

	public String getClassify(){
		return this.classify;
	}

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public void setRecommend(String recommend){
		this.recommend = recommend;
	}

	public String getRecommend(){
		return this.recommend;
	}

	public void setTeachingMethod(String teachingMethod){
		this.teachingMethod = teachingMethod;
	}

	public String getTeachingMethod(){
		return this.teachingMethod;
	}

	public void setTrainTimeBegin(java.util.Date trainTimeBegin){
		this.trainTimeBegin = trainTimeBegin;
	}

	public java.util.Date getTrainTimeBegin(){
		return this.trainTimeBegin;
	}

	public void setTrainTimeEnd(java.util.Date trainTimeEnd){
		this.trainTimeEnd = trainTimeEnd;
	}

	public java.util.Date getTrainTimeEnd(){
		return this.trainTimeEnd;
	}

	public void setIsApply(Integer isApply){
		this.isApply = isApply;
	}

	public Integer getIsApply(){
		return this.isApply;
	}

	public void setApplyTimeBegin(java.util.Date applyTimeBegin){
		this.applyTimeBegin = applyTimeBegin;
	}

	public java.util.Date getApplyTimeBegin(){
		return this.applyTimeBegin;
	}

	public void setApplyTimeEnd(java.util.Date applyTimeEnd){
		this.applyTimeEnd = applyTimeEnd;
	}

	public java.util.Date getApplyTimeEnd(){
		return this.applyTimeEnd;
	}

	public void setApplyPay(Integer applyPay){
		this.applyPay = applyPay;
	}

	public Integer getApplyPay(){
		return this.applyPay;
	}

	public void setIsSign(Integer isSign){
		this.isSign = isSign;
	}

	public Integer getIsSign(){
		return this.isSign;
	}

	public void setSignTimeBegin(java.util.Date signTimeBegin){
		this.signTimeBegin = signTimeBegin;
	}

	public java.util.Date getSignTimeBegin(){
		return this.signTimeBegin;
	}

	public void setSignTimeEnd(java.util.Date signTimeEnd){
		this.signTimeEnd = signTimeEnd;
	}

	public java.util.Date getSignTimeEnd(){
		return this.signTimeEnd;
	}

	public void setIssueTime(java.util.Date issueTime){
		this.issueTime = issueTime;
	}

	public java.util.Date getIssueTime(){
		return this.issueTime;
	}

	public void setPeopleLimit(Integer peopleLimit){
		this.peopleLimit = peopleLimit;
	}

	public Integer getPeopleLimit(){
		return this.peopleLimit;
	}

	public void setTrainSite(String trainSite){
		this.trainSite = trainSite;
	}

	public String getTrainSite(){
		return this.trainSite;
	}

	public void setCover(String cover){
		this.cover = cover;
	}

	public String getCover(){
		return this.cover;
	}

	public void setLabel(String label){
		this.label = label;
	}

	public String getLabel(){
		return this.label;
	}

	public void setIssueScope(String issueScope){
		this.issueScope = issueScope;
	}

	public String getIssueScope(){
		return this.issueScope;
	}

	public void setIsFree(Integer isFree){
		this.isFree = isFree;
	}

	public Integer getIsFree(){
		return this.isFree;
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

    public void setFreeMember(String freeMember){
		this.freeMember = freeMember;
	}

	public String getFreeMember(){
		return this.freeMember;
	}

	public void setLecturerId(String lecturerId){
		this.lecturerId = lecturerId;
	}

	public String getLecturerId(){
		return this.lecturerId;
	}

	public void setLecturerName(String lecturerName){
		this.lecturerName = lecturerName;
	}

	public String getLecturerName(){
		return this.lecturerName;
	}

	public void setLecturerPicture(String lecturerPicture){
		this.lecturerPicture = lecturerPicture;
	}

	public String getLecturerPicture(){
		return this.lecturerPicture;
	}

	public void setCurriculumidIntro(String curriculumidIntro){
		this.curriculumidIntro = curriculumidIntro;
	}

	public String getCurriculumidIntro(){
		return this.curriculumidIntro;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setCreaterId(String createrId){
		this.createrId = createrId;
	}

	public String getCreaterId(){
		return this.createrId;
	}

	public void setCreaterName(String createrName){
		this.createrName = createrName;
	}

	public String getCreaterName(){
		return this.createrName;
	}

	public void setCreaterTime(java.util.Date createrTime){
		this.createrTime = createrTime;
	}

	public java.util.Date getCreaterTime(){
		return this.createrTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
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

    public List<CurriculumLecturerGx> getLecturerGxList() {
        return lecturerGxList;
    }

    public void setLecturerGxList(List<CurriculumLecturerGx> lecturerGxList) {
        this.lecturerGxList = lecturerGxList;
    }

    public List<CurriculumChapterBo> getChapterBoList() {
        return chapterBoList;
    }

    public void setChapterBoList(List<CurriculumChapterBo> chapterBoList) {
        this.chapterBoList = chapterBoList;
    }

	public List<CurriculumUvipPriceBo> getUvipPriceBoList() {
		return uvipPriceBoList;
	}

	public void setUvipPriceBoList(List<CurriculumUvipPriceBo> uvipPriceBoList) {
		this.uvipPriceBoList = uvipPriceBoList;
	}
}
