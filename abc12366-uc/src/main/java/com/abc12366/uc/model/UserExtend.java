package com.abc12366.uc.model;

import java.util.Date;

/**
 * Created by lgy on 2017-05-05.
 */
public class UserExtend {

    //用户ID
    private String userId;
    //个性签名
    private String signature;
    //性别，0：女，1：男
    private String sex;
    //生日
    private Date birthday;
    //血型
    private String bloodType;
    //体重
    private String weight;
    //身高
    private String height;
    //婚姻状况
    private String marital;
    //教育程度
    private String education;
    //毕业院校
    private String graduate;
    //职业
    private String occupation;
    //收入水平
    private String income;
    //通讯地址
    private String postAddress;
    //真实姓名
    private String realName;
    //微信账号
    private String weixin;
    //QQ账号
    private String qq;
    //密保问题
    private String safeQuestion;
    //密保答案
    private String safeAnswer;
    //所在地（省）
    private String province;
    //所在（市）
    private String city;
    //所在 （地区）
    private String area;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastUpdate;
    //擅长领域
    private String tags;
    //身份证号
    private String idcard;
    //身份证正面图片地址
    private String frontImage;
    //图片地址
    private String backImage;
    //实名认证有效期起
    private Date startTime;
    //实名认证有效期止
    private Date endTime;
    //实名认证状态,实名认证状态可以为：0:未认证、1:待认证、2:已认证、3:认证失败
    private String validStatus;
    //实名认证时间
    private Date validTime;
    //认证备注
    private String remark;
    //从业时间
    private String careerDuration;
    //实名认证方式
    private String validType;

    public UserExtend() {
    }

    public String getCareerDuration() {
        return careerDuration;
    }

    public void setCareerDuration(String careerDuration) {
        this.careerDuration = careerDuration;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSafeQuestion() {
        return safeQuestion;
    }

    public void setSafeQuestion(String safeQuestion) {
        this.safeQuestion = safeQuestion;
    }

    public String getSafeAnswer() {
        return safeAnswer;
    }

    public void setSafeAnswer(String safeAnswer) {
        this.safeAnswer = safeAnswer;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
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

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    @Override
    public String toString() {
        return "UserExtend{" +
                "userId='" + userId + '\'' +
                ", signature='" + signature + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", bloodType='" + bloodType + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", marital='" + marital + '\'' +
                ", education='" + education + '\'' +
                ", graduate='" + graduate + '\'' +
                ", occupation='" + occupation + '\'' +
                ", income='" + income + '\'' +
                ", postAddress='" + postAddress + '\'' +
                ", realName='" + realName + '\'' +
                ", weixin='" + weixin + '\'' +
                ", qq='" + qq + '\'' +
                ", safeQuestion='" + safeQuestion + '\'' +
                ", safeAnswer='" + safeAnswer + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", tags='" + tags + '\'' +
                ", idcard='" + idcard + '\'' +
                ", frontImage='" + frontImage + '\'' +
                ", backImage='" + backImage + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", validStatus='" + validStatus + '\'' +
                ", validTime=" + validTime +
                ", remark='" + remark + '\'' +
                ", careerDuration='" + careerDuration + '\'' +
                ", validType='" + validType + '\'' +
                '}';
    }
}
