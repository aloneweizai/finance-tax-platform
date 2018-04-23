package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-19
 */


public class LotteryTemplateBO{
     /** key */
    private String id;
     /** 名称 */
    private String name;
     /** 模版路径 */
    private String url;
     /** 模版类型 */
    private String types;
     /** 状态 */
    private Boolean status;
     /** 时间,create */
    private Date createTime;
     /** 修改时间 */
    private Date lastTime;

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
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
       this.url = url;
    }
    public String getTypes(){
        return types;
    }
    public void setTypes(String types){
       this.types = types;
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
    return "LotteryTemplate {\r\n"+
    "\""+"id"+"\""+":"+"\""+id+"\""+",\r\n"+
    "\""+"name"+"\""+":"+"\""+name+"\""+",\r\n"+
    "\""+"url"+"\""+":"+"\""+url+"\""+",\r\n"+
    "\""+"types"+"\""+":"+"\""+types+"\""+",\r\n"+
    "\""+"status"+"\""+":"+"\""+status+"\""+",\r\n"+
    "\""+"createTime"+"\""+":"+"\""+createTime+"\""+",\r\n"+
    "\""+"lastTime"+"\""+":"+"\""+lastTime+"\""+"}";
    }
}
