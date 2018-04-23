package com.abc12366.uc.model.bo;

import java.util.Date;
import java.util.List;

/**
 * 用户留存统计实体类
 *
 * @author lizhongwei
 * @create 2017-11-21 4:14 PM
 * @since 1.0.0
 */
public class UserRetainedRateListBO {

    /**
     * 日期
     */
    private Date date;

    private List<UserRetainedRateBO> userRetainedRateBOList;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<UserRetainedRateBO> getUserRetainedRateBOList() {
        return userRetainedRateBOList;
    }

    public void setUserRetainedRateBOList(List<UserRetainedRateBO> userRetainedRateBOList) {
        this.userRetainedRateBOList = userRetainedRateBOList;
    }
}
