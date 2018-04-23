package com.abc12366.uc.model.bo;

/**
 * 用户统计实体类
 *
 * @author lizhongwei
 * @create 2017-11-21 4:14 PM
 * @since 1.0.0
 */
public class UserStatisBO {

    /**
     * 用户总数
     */
    private Integer count;
    /**
     * 时间
     */
    private String days;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
