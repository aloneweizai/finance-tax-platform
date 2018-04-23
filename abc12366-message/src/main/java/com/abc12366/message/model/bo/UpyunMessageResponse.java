package com.abc12366.message.model.bo;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-01
 * Time: 17:05
 */
public class UpyunMessageResponse {

    private List<UpyunMessageBO> message_ids;

    public UpyunMessageResponse() {
    }

    public List<UpyunMessageBO> getMessage_ids() {
        return message_ids;
    }

    public void setMessage_ids(List<UpyunMessageBO> message_ids) {
        this.message_ids = message_ids;
    }
}
