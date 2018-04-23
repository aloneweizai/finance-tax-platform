package com.abc12366.bangbang.model.bo;

import com.abc12366.bangbang.model.BaseListObject;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-25
 * Time: 9:53
 */
public class LetterListBO extends BaseListObject {
    List<LetterBO> dataList;

    public List<LetterBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<LetterBO> dataList) {
        this.dataList = dataList;
    }
}