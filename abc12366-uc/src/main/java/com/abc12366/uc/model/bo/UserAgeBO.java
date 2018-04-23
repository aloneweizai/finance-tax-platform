package com.abc12366.uc.model.bo;

/**
 * 用户年龄分布统计
 *
 * @author lizhongwei
 * @create 2017-12-06 4:14 PM
 * @since 1.0.0
 */
public class UserAgeBO {

    /**
     *年龄段
     */
    private String ageGroup;

    /**
     * 人数
     */
    private Integer count;

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
