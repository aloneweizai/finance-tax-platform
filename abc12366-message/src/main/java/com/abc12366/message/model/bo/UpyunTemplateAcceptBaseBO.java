package com.abc12366.message.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-08
 * Time: 16:54
 */
public class UpyunTemplateAcceptBaseBO {
    /**
     * 模板标题
     */
    private String title;
    /**
     *模板签名
     */
    private String temp_sign;
    /**
     *模板内容
     */
    private String content;
    /**
     *短信类型(营销、行业)
     */
    private String type;
    /**
     *模板状态（审核中、通过、失败)
     */
    private String status;
    /**
     *创建时间
     */
    private String updated_at;
    /**
     *原因
     */
    private String reason;
    /**
     *模板编号
     */
    private String id;
    /**
     *创建者信息
     */
    private UpyunTemplateAcceptOwnerBO owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTemp_sign() {
        return temp_sign;
    }

    public void setTemp_sign(String temp_sign) {
        this.temp_sign = temp_sign;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UpyunTemplateAcceptOwnerBO getOwner() {
        return owner;
    }

    public void setOwner(UpyunTemplateAcceptOwnerBO owner) {
        this.owner = owner;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "UpyunTemplateAcceptBaseBO{" +
                "title='" + title + '\'' +
                ", temp_sign='" + temp_sign + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", reason='" + reason + '\'' +
                ", id='" + id + '\'' +
                ", owner=" + owner +
                '}';
    }
}
