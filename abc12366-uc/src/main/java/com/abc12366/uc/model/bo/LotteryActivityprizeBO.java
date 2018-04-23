package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-21
 */


public class LotteryActivityprizeBO{
    private Boolean lotterySend;

    public Boolean getLotterySend() {
        return lotterySend;
    }

    public void setLotterySend(Boolean lotterySend) {
        this.lotterySend = lotterySend;
    }

    private Boolean noluck;

    public Boolean getNoluck() {
        return noluck;
    }

    public void setNoluck(Boolean noluck) {
        this.noluck = noluck;
    }
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
    public Integer getStock(){
        //库存  =  总数 - 销售数量
        if(balance == null)balance = 0;
        if(amount != null && balance != null) {
            return amount - balance;
        }
        return 0;
    }
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    private String activityName;
    private String lotteryName;
    public String getActivityName() {
        return activityName;
    }
    private String lotteryImage;

    public String getLotteryImage() {
        return lotteryImage;
    }

    public void setLotteryImage(String lotteryImage) {
        this.lotteryImage = lotteryImage;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
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
