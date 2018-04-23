package com.abc12366.message.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-08
 * Time: 16:54
 */
public class UpyunTemplateAcceptOwnerBO {
    /**
     *用户编号
     */
    private String id;
    /**
     *用户昵称
     */
    private String name;
    /**
     *已发送行业短信条数
     */
    private String industry;
    /**
     *已发送营销短信条数
     */
    private String marketing;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMarketing() {
        return marketing;
    }

    public void setMarketing(String marketing) {
        this.marketing = marketing;
    }

    @Override
    public String toString() {
        return "UpyunTemplateAcceptOwnerBO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", industry='" + industry + '\'' +
                ", marketing='" + marketing + '\'' +
                '}';
    }
}
