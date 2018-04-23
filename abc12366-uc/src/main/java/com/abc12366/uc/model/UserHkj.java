package com.abc12366.uc.model;

/**
 * 用户实体类
 *
 */
public class UserHkj {
    /**用户手机号*/
   private String mobile;
   /**用户昵称*/
   private String  username;
   /**用户标识*/
   private String identify;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
}
