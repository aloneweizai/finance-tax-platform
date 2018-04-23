package com.abc12366.bangbang.model.bo;

import com.abc12366.bangbang.model.KnowledgeAttachment;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/2 17:58
 * 知识库表
 **/
public class KnowledgeBaseListBO {

    /**
     * PK
     **/
    private String id;

    /**
     * 分类Code
     **/
    private String categoryCode;

    /*
    *  分类名称
    **/
    private String categoryName;

    /**
     * 类别：QA问答，K知识
     **/
    private String type;

    /**
     * 推荐 QA(common常见,hot热点); K(top头条，hot热点)
     **/
    private String recommend;

    /**
     * 名称
     **/
    private String subject;

    /**
     * 内容
     **/
    private String content;

    /**
     * 状态
     **/
    private Boolean status;

    /**
     * 生效时间
     **/
    private java.util.Date activeTime;

    /**
     * 知识来源
     **/
    private String source;

    /**
     * 是否对外开放
     **/
    private Boolean isOpen;

    /**
     * 浏览量
     **/
    private Long pv;

    /**
     * 有用投票数
     **/
    private Long usefulVotes;

    /**
     * 无用投票数
     **/
    private Long uselessVotes;

    /**
     * 分享数量
     **/
    private Long shareNum;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 修改时间
     **/
    private java.util.Date updateTime;

    /**
     * 创建人
     **/
    private String createUser;

    /**
     * 修改人名称
     **/
    private String updateUserName;

    /**
     * 修改人
     **/
    private String updateUser;

    /**
     * 附件列表
     **/
    private List<KnowledgeAttachment> attachmentList;

    public List<KnowledgeAttachment> getAttachmentList() {
        return attachmentList;
    }

    /**
     * 标签名称列表
     */
    private List<String> tagNameList;

    public List<String> getTagNameList() {
        return tagNameList;
    }

    public KnowledgeBaseListBO setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
        return this;
    }

    public KnowledgeBaseListBO setAttachmentList(List<KnowledgeAttachment> attachmentList) {
        this.attachmentList = attachmentList;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecommend() {
        return this.recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public java.util.Date getActiveTime() {
        return this.activeTime;
    }

    public void setActiveTime(java.util.Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Long getPv() {
        return this.pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Long getUsefulVotes() {
        return this.usefulVotes;
    }

    public void setUsefulVotes(Long usefulVotes) {
        this.usefulVotes = usefulVotes;
    }

    public Long getUselessVotes() {
        return this.uselessVotes;
    }

    public void setUselessVotes(Long uselessVotes) {
        this.uselessVotes = uselessVotes;
    }

    public Long getShareNum() {
        return this.shareNum;
    }

    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
