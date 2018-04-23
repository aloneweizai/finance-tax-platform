package com.abc12366.message.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-01
 * Time: 17:06
 */
public class UpyunMessageBO {
    private String message_id;
    private String mobile;

    public UpyunMessageBO() {
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
