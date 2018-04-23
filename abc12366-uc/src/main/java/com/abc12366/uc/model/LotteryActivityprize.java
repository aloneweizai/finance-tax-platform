package com.abc12366.uc.model;

import java.util.Date;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-21
 */


public class LotteryActivityprize{
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /** 时段投库存 */
    private Integer timeStock;
    /** 时段段时间 */
    private Date timeDay;
    /** 时间段已发数量 */
    private Integer timeCount;

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
    private Integer balance;
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
 /** PK */
private String id;
 /** 抽奖活动id */
private String activityId;
 /** 奖品id */
private String lotteryId;
 /** 保留字段 */
private String val1;
 /** 描述 */
private String description;
 /** 数据状态 */
private Boolean status;
 /** 创建时间 */
private Date createTime;
 /** 修改时间 */
private Date lastTime;
    /** 开始时间 */
    private Date startTime;
    /** 结束时间 */
    private Date endTime;
    /** 概率 百分比 */
    private Double luck;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getLuck() {
        return luck;
    }

    public void setLuck(Double luck) {
        this.luck = luck;
    }

    public String getId(){
    return id;
}
public void setId(String id){
   this.id = id;
}
public String getActivityId(){
    return activityId;
}
public void setActivityId(String activityId){
   this.activityId = activityId;
}
public String getLotteryId(){
    return lotteryId;
}
public void setLotteryId(String lotteryId){
   this.lotteryId = lotteryId;
}
public String getVal1(){
    return val1;
}
public void setVal1(String val1){
   this.val1 = val1;
}
public String getDescription(){
    return description;
}
public void setDescription(String description){
   this.description = description;
}
public Boolean getStatus(){
    return status;
}
public void setStatus(Boolean status){
   this.status = status;
}
public Date getCreateTime(){
    return createTime;
}
public void setCreateTime(Date createTime){
   this.createTime = createTime;
}
public Date getLastTime(){
    return lastTime;
}
public void setLastTime(Date lastTime){
   this.lastTime = lastTime;
}

@Override
public String toString() {
return "LotteryActivityprize {\r\n"+
"\""+"id"+"\""+":"+"\""+id+"\""+",\r\n"+
"\""+"activityId"+"\""+":"+"\""+activityId+"\""+",\r\n"+
"\""+"lotteryId"+"\""+":"+"\""+lotteryId+"\""+",\r\n"+
"\""+"val1"+"\""+":"+"\""+val1+"\""+",\r\n"+
"\""+"description"+"\""+":"+"\""+description+"\""+",\r\n"+
"\""+"status"+"\""+":"+"\""+status+"\""+",\r\n"+
"\""+"createTime"+"\""+":"+"\""+createTime+"\""+",\r\n"+
"\""+"lastTime"+"\""+":"+"\""+lastTime+"\""+"}";
}
}
