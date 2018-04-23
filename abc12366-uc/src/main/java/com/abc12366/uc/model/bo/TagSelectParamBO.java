package com.abc12366.uc.model.bo;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 10:31
 */
public class TagSelectParamBO {
    private String tagName;
    private String category;

    public TagSelectParamBO() {
    }

    public TagSelectParamBO(String tagName, String category) {
        this.tagName = tagName;
        this.category = category;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
