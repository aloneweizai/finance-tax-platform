package com.abc12366.bangbang.model.bo;


import com.abc12366.bangbang.model.BaseObject;
import com.abc12366.bangbang.model.Message;

import java.util.List;

/**
 * User: xieyanmao
 * Date: 2017-09-27
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
