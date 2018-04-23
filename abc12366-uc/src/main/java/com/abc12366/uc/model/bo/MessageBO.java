package com.abc12366.uc.model.bo;

import com.abc12366.uc.model.BaseObject;
import com.abc12366.uc.model.Message;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-09
 * Time: 14:47
 */
public class MessageBO extends BaseObject {
    private List<Message> data;

    public MessageBO() {
    }

    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }
}
