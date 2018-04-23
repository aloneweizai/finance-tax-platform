package com.abc12366.uc.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-08
 * Time: 11:00
 */
public class TagUserStaticBO {
    /**
     *标签名
     */
    private String tagName;
    /**
     *用户数
     */
    private int count;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TagUserStaticBO{" +
                "tagName='" + tagName + '\'' +
                ", count=" + count +
                '}';
    }
}
