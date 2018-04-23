package com.abc12366.uc.model.weixin.bo.person;

import com.abc12366.uc.model.User;
import com.abc12366.uc.model.weixin.BaseWxRespon;

import java.util.Date;

/**
 * 微信用户信息
 *
 * @author zhushuai 2017-7-31
 */
public class WxPerson extends BaseWxRespon {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String subscribe;//订阅状态 0未关注 1已关注
    private String openid;//用户的标识，对当前公众号唯一
    private String nickname;//昵称
    private String sex;//性别  1时是男性，值为2时是女性，值为0时是未知
    private String language;//语言
    private String city;//城市
    private String province;//省份
    private String country;//国家
    private String headimgurl;//头像地址
    private Date subscribe_time;//关注时间
    private String remark;//备注
    private String groupid;//用户所在的分组ID
    private String unionid;//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。

    private String[] tagid_list;//用户被打上的标签ID列表
    private Date lastupdate;
    private String userid;//UC用户ID
    private User user;//UC用户信息
    //查询条件
    private Date startTime;
    private Date endTime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Date getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(Date subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String[] getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(String[] tagid_list) {
        this.tagid_list = tagid_list;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

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


}
