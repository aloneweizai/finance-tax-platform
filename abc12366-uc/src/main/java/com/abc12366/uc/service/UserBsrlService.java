package com.abc12366.uc.service;

import com.abc12366.uc.model.UserBsrl;
import com.abc12366.uc.model.bo.UserBsrlBO;

import java.util.List;
import java.util.Map;

/**
 * 用户办税日历接口
 * @author lizhongwei
 * @create 2018-01-02 3:18 PM
 */
public interface UserBsrlService {
    /**
     * 列表查询
     * @param map
     * @return
     */
    List<UserBsrl> selectList(Map<String, Object> map);

    /**
     * 单个查询
     * @param id
     * @return
     */
    UserBsrlBO selectById(String id);

    /**
     * 新增办理日历
     * @param userBsrlBO
     * @return
     */
    UserBsrlBO insert(UserBsrlBO userBsrlBO);


    /**
     * 修改办理日历
     * @param userBsrlBO
     * @return
     */
    UserBsrlBO update(UserBsrlBO userBsrlBO);

    /**
     * 删除办理日历
     * @param id
     * @return
     */
    void delete(String id);
}
