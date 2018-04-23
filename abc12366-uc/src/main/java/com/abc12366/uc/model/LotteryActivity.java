package com.abc12366.uc.model;

import java.util.Date;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-21
 */


public class LotteryActivity{
    /**用户中奖最大值*/
    private Integer  userLotteryMax;
    /**用户每天中奖最大值*/
    private Integer  userLotteryMaxDay;
    /**用户每天免费次数*/
    private Integer  userFreeCount;
    /**领奖截至天数*/
    private Integer  getlotteyDay;

    public Integer getUserLotteryMax() {
        return userLotteryMax;
    }

    public void setUserLotteryMax(Integer userLotteryMax) {
        this.userLotteryMax = userLotteryMax;
    }

    public Integer getUserLotteryMaxDay() {
        return userLotteryMaxDay;
    }

    public void setUserLotteryMaxDay(Integer userLotteryMaxDay) {
        this.userLotteryMaxDay = userLotteryMaxDay;
    }

    public Integer getUserFreeCount() {
        return userFreeCount;
    }

    public void setUserFreeCount(Integer userFreeCount) {
        this.userFreeCount = userFreeCount;
    }

    public Integer getGetlotteyDay() {
        return getlotteyDay;
    }

    public void setGetlotteyDay(Integer getlotteyDay) {
        this.getlotteyDay = getlotteyDay;
    }
    /** 时段投库存 */
    private Integer timeStock;
    /** 时段段时间 */
    private Date timeDay;
    /** 时间段已发数量 */
    private Integer timeCount;
    /**中奖时间*/
    private Date luckTime;

    public Integer getTimeStock() {
        return timeStock;
    }

    public void setTimeStock(Integer timeStock) {
        this.timeStock = timeStock;
    }

    public Date getTimeDay() {
        return timeDay;
    }

    public void setTimeDay(Date timeDay) {
        this.timeDay = timeDay;
    }

    public Integer getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(Integer timeCount) {
        this.timeCount = timeCount;
    }

    public Date getLuckTime() {
        return luckTime;
    }

    public void setLuckTime(Date luckTime) {
        this.luckTime = luckTime;
    }
 /** key */
private String id;
 /** 名称 */
private String name;
 /** 活动类型 */
private String types;
 /** 描述 */
private String description;
 /** 抽奖次数 */
private Integer count;
 /** 标签 */
private String label;
 /** 用户等级 */
private String userLevel;
 /** 发布链接 */
private String url;
 /** 开始时间 */
private Date startTime;
 /** 结束时间 */
private Date endTime;
 /** 状态 */
private Boolean status;
 /** 模版id */
private String templateId;
 /** 创建时间 */
private Date createTime;

public String getId(){
    return id;
}
public void setId(String id){
   this.id = id;
}
public String getName(){
    return name;
}
public void setName(String name){
   this.name = name;
}
public String getTypes(){
    return types;
}
public void setTypes(String types){
   this.types = types;
}
public String getDescription(){
    return description;
}
public void setDescription(String description){
   this.description = description;
}
public Integer getCount(){
    return count;
}
public void setCount(Integer count){
   this.count = count;
}
public String getLabel(){
    return label;
}
public void setLabel(String label){
   this.label = label;
}
public String getUserLevel(){
    return userLevel;
}
public void setUserLevel(String userLevel){
   this.userLevel = userLevel;
}
public String getUrl(){
    return url;
}
public void setUrl(String url){
   this.url = url;
}
public Date getStartTime(){
    return startTime;
}
public void setStartTime(Date startTime){
   this.startTime = startTime;
}
public Date getEndTime(){
    return endTime;
}
public void setEndTime(Date endTime){
   this.endTime = endTime;
}
public Boolean getStatus(){
    return status;
}
public void setStatus(Boolean status){
   this.status = status;
}
public String getTemplateId(){
    return templateId;
}
public void setTemplateId(String templateId){
   this.templateId = templateId;
}
public Date getCreateTime(){
    return createTime;
}
public void setCreateTime(Date createTime){
   this.createTime = createTime;
}

}
