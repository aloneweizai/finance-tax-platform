package com.abc12366.uc.model.bo;



import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by lgy on 2017-05-09.
 */
public class UserExtendUpdateBO {
    private String userId;
    @Size(max = 200)
    private String signature;
    @Size(max = 1)
    private String sex;
    private Date birthday;
    @Size(max = 2)
    private String bloodType;
    @Size(max = 10)
    private String weight;
    @Size(max = 10)
    private String height;
    @Size(max = 6)
    private String marital;
    @Size(max = 32)
    private String education;
    @Size(max = 100)
    private String graduate;
    @Size(max = 100)
    private String occupation;
    @Size(max = 20)
    private String income;
    @Size(max = 200)
    private String postAddress;
    @Size(max = 16)
    private String realName;
    @Size(max = 50)
    private String weixin;
    @Size(max = 50)
    private String qq;
    @Size(max = 200)
    private String safeQuestion;
    @Size(max = 200)
    private String safeAnswer;
    @Size(max = 45)
    private String province;
    @Size(max = 45)
    private String city;
    @Size(max = 45)
    private String area;
    private String tags;
    @Size(max = 18)
    private String idcard;
    @Size(max = 100)
    private String frontImage;
    @Size(max = 100)
    private String backImage;
    private Date startTime;
    private Date endTime;
    @Size(max = 1)
    private String validStatus;
    private Date validTime;
    @Size(max = 500)
    private String remark;
    @Size(max = 10)
    private String careerDuration;

    public UserExtendUpdateBO() {
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
}
