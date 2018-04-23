package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * 用户签到情况列表查询接口返回参数实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-23
 * Time: 18:00
 */
public class CheckListBO {
    //日期
    private Date date;
    //是否补签
    private boolean isCheck;

    public CheckListBO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "CheckListBO{" +
                "date=" + date +
                ", isCheck=" + isCheck +
                '}';
    }
}
