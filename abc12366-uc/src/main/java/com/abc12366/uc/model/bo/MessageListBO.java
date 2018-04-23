package com.abc12366.uc.model.bo;


import com.abc12366.uc.model.BaseListObject;
import com.abc12366.uc.model.Message;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-09
 * Time: 14:21
 */
public class MessageListBO extends BaseListObject {
    private List<Message> dataList;

    public MessageListBO() {
    }

    public List<Message> getDataList() {
        return dataList;
    }

    public void setDataList(List<Message> dataList) {
        this.dataList = dataList;
    }
}
