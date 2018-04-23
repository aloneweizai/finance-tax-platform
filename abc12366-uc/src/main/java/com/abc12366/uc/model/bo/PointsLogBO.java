package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-15 10:18 PM
 * @since 2.0.0
 */
public class PointsLogBO {
    private String id;

    //用户ID
    @NotEmpty
    private String userId;

    //积分规则ID
    private String ruleId;

    //规则名称，查询时候用到
    private String name;
    //规则编码，查询时候用到
    private String code;
    //规则类型
    private String type;
    //积分收入
    private int income;
    //积分支出
    private int outgo;
    //当前可用积分
    private int usablePoints;
    //创建时间
    private Date createTime;
    //日志类型
    @Size(max = 20)
    private String logType;
    //备注
    @Size(max = 200)
    private String remark;

    public PointsLogBO() {
    }

    public PointsLogBO(String id, String userId, String ruleId, String name, String code, String type, int income,
                       int outgo, int usablePoints, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.ruleId = ruleId;
        this.name = name;
        this.code = code;
        this.type = type;
        this.income = income;
        this.outgo = outgo;
        this.usablePoints = usablePoints;
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getOutgo() {
        return outgo;
    }

    public void setOutgo(int outgo) {
        this.outgo = outgo;
    }

    public int getUsablePoints() {
        return usablePoints;
    }

    public void setUsablePoints(int usablePoints) {
        this.usablePoints = usablePoints;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PointsLogBO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", income=" + income +
                ", outgo=" + outgo +
                ", usablePoints=" + usablePoints +
                ", createTime=" + createTime +
                '}';
    }
}
