package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * 用户登录后查询经验值日志列表接口返回参数实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-02
 * Time: 14:41
 */
public class ExpLogUcBO {
    //主键
    private String id;

    //获取/失去 经验值渠道
    private String channel;

    //经验值变化值
    private int exp;

    //当前经验值
    private int sums;

    //创建时间
    private Date createTime;

    //备注
    private String remark;

    public ExpLogUcBO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getSums() {
        return sums;
    }

    public void setSums(int sums) {
        this.sums = sums;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ExpLogUcBO{" +
                "id='" + id + '\'' +
                ", channel='" + channel + '\'' +
                ", exp=" + exp +
                ", sums=" + sums +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
