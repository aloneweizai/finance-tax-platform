package com.abc12366.uc.model.bo;

/**
 * 用户简单信息实体类：用户ID，用户昵称，用户头像，擅长领域
 *
 * @author liuguiyao<435720953@qq.com>
 * Date: 2017-10-12
 * Time: 10:31
 */
public class UserSimpleInfoBO {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像图片路径
     */
    private String userPicturePath;

    /**
     * 用户标签
     */
    private String tags;

    /**
     * 签名
     */
    private String signature;

    /**
     * 教育
     */
    private String education;

    /**
     * 毕业院校
     */
    private String graduate;

    /**
     * 行业
     */
    private String occupation;

    /**
     * 收入
     */
    private String income;

    /**
     * 工作经验
     */
    private String careerDuration;

    public UserSimpleInfoBO() {
    }

    public UserSimpleInfoBO(String id, String nickName, String userPicturePath, String tags, String signature, String
            education, String graduate, String occupation, String income, String careerDuration) {
        this.id = id;
        this.nickName = nickName;
        this.userPicturePath = userPicturePath;
        this.tags = tags;
        this.signature = signature;
        this.education = education;
        this.graduate = graduate;
        this.occupation = occupation;
        this.income = income;
        this.careerDuration = careerDuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public String getCareerDuration() {
        return careerDuration;
    }

    public void setCareerDuration(String careerDuration) {
        this.careerDuration = careerDuration;
    }

    @Override
    public String toString() {
        return "UserSimpleInfoBO{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userPicturePath='" + userPicturePath + '\'' +
                ", tags='" + tags + '\'' +
                ", signature='" + signature + '\'' +
                ", education='" + education + '\'' +
                ", graduate='" + graduate + '\'' +
                ", occupation='" + occupation + '\'' +
                ", income='" + income + '\'' +
                ", careerDuration='" + careerDuration + '\'' +
                '}';
    }
}
