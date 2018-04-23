package com.abc12366.bangbang.model.bo;

import com.abc12366.bangbang.model.BaseObject;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-08
 * Time: 21:43
 */
public class LetterResponse extends BaseObject {
    private List<LetterBO> data;

    public LetterResponse() {
    }

    public List<LetterBO> getData() {
        return data;
    }

    public void setData(List<LetterBO> data) {
        this.data = data;
    }
}
