package com.abc12366.uc.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-11
 * Time: 17:58
 */
public class BindStatisSingleBO {
    private String month;
    private String timeInterval;
    private String type;
    private int bindCount;

    public BindStatisSingleBO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBindCount() {
        return bindCount;
    }

    public void setBindCount(int bindCount) {
        this.bindCount = bindCount;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "BindStatisSingleBO{" +
                "month='" + month + '\'' +
                ", timeInterval='" + timeInterval + '\'' +
                ", type='" + type + '\'' +
                ", bindCount=" + bindCount +
                '}';
    }
}
