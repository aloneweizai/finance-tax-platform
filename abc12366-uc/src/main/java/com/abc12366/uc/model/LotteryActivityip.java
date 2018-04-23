package com.abc12366.uc.model;

import java.util.Date;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-11-11
 */


public class LotteryActivityip{
 /** PK */
private String id;
 /** 抽奖活动id */
private String activityId;
 /** ip地址 */
private String ip;
 /** 描述 */
private String description;
 /** 创建时间 */
private Date createTime;
 /** 备注 */
private String remake;

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
public String getIp(){
    return ip;
}
public void setIp(String ip){
   this.ip = ip;
}
public String getDescription(){
    return description;
}
public void setDescription(String description){
   this.description = description;
}
public Date getCreateTime(){
    return createTime;
}
public void setCreateTime(Date createTime){
   this.createTime = createTime;
}
public String getRemake(){
    return remake;
}
public void setRemake(String remake){
   this.remake = remake;
}

}
