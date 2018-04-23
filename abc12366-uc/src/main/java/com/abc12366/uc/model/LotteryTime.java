package com.abc12366.uc.model;

import java.util.Date;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-13
 */


public class LotteryTime{
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /** pk */
private String id;
 /** 创建时间 */
private Date createTime;
 /** 修改时间 */
private Date lastUpdate;
 /** 开始时间 */
private Date startTime;
 /** 结束时间 */
private Date endTime;
 /** 概率 */
private Integer luck;

public String getId(){
    return id;
}
public void setId(String id){
   this.id = id;
}
public Date getCreateTime(){
    return createTime;
}
public void setCreateTime(Date createTime){
   this.createTime = createTime;
}
public Date getLastUpdate(){
    return lastUpdate;
}
public void setLastUpdate(Date lastUpdate){
   this.lastUpdate = lastUpdate;
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
public Integer getLuck(){
    return luck;
}
public void setLuck(Integer luck){
   this.luck = luck;
}

@Override
public String toString() {
return "LotteryTime {\r\n"+
"\""+"id"+"\""+":"+"\""+id+"\""+",\r\n"+
"\""+"createTime"+"\""+":"+"\""+createTime+"\""+",\r\n"+
"\""+"lastUpdate"+"\""+":"+"\""+lastUpdate+"\""+",\r\n"+
"\""+"startTime"+"\""+":"+"\""+startTime+"\""+",\r\n"+
"\""+"endTime"+"\""+":"+"\""+endTime+"\""+",\r\n"+
"\""+"luck"+"\""+":"+"\""+luck+"\""+"}";
}
}
