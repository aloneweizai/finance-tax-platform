package com.abc12366.bangbang.model.curriculum.bo;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 课程学习历史
 * 
 **/
@SuppressWarnings("serial")
public class CurrMyStudyBo implements Serializable {

	/**课程ID**/
	private String curriculumId;

    /**用户ID**/
    private String userId;

    /**订单时间**/
    private java.util.Date orderTime;

    /**订单价格**/
    private Double orderPrice;

    /**订单积分**/
    private Double orderIntegral;

    /**是否积分购买**/
    private Integer isIntegral;

    /**课程收费**/
    private Integer isFree;

    /**订单状态**/
    private String orderStatus;

    /**课程标题**/
    private String title;

    /**
     * 授课方式
     */
    private String teachingMethod;

    /**课程封页**/
    private String cover;

    /**学习时间**datetime**/
    private java.util.Date studyTime;

    /**课件ID**/
    private String coursewareId;

    /**评价次数**/
    private int evaluateNum;

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getOrderIntegral() {
        return orderIntegral;
    }

    public void setOrderIntegral(Double orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    public Integer getIsIntegral() {
        return isIntegral;
    }

    public void setIsIntegral(Integer isIntegral) {
        this.isIntegral = isIntegral;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Date studyTime) {
        this.studyTime = studyTime;
    }

    public String getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(String coursewareId) {
        this.coursewareId = coursewareId;
    }

    public int getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(int evaluateNum) {
        this.evaluateNum = evaluateNum;
    }
}
