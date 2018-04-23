package com.abc12366.uc.model.bo;

/**
 * 用户简单信息实体类：用户ID，用户昵称，用户头像，擅长领域
 *
 * @author lizhongwei
 * Date: 2017-11-29
 * Time: 10:31
 */
public class UserExprotInfoBO {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 所在 （地区）
     */
    private String area;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
