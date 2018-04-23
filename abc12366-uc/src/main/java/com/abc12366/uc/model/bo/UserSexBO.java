package com.abc12366.uc.model.bo;

/**
 * 用户性别分布统计
 *
 * @author lizhongwei
 * @create 2017-12-06 4:14 PM
 * @since 1.0.0
 */
public class UserSexBO {

    /**
     *性别
     */
    private String sexGroup;

    /**
     * 人数
     */
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSexGroup() {
        return sexGroup;
    }

    public void setSexGroup(String sexGroup) {
        this.sexGroup = sexGroup;
    }
}
