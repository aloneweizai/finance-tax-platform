package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.VipLevelBO;
import com.abc12366.uc.model.bo.VipLevelInsertBO;
import com.abc12366.uc.model.bo.VipLevelUpdateBO;

import java.util.List;
import java.util.Map;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-19 10:18 PM
 * @since 2.0.0
 */
public interface VipLevelService {
    List<VipLevelBO> selectList(Map map);

    VipLevelBO selectOne(String id);

    VipLevelBO insert(VipLevelInsertBO vipLevelInsertBO);

    VipLevelBO update(VipLevelUpdateBO vipLevelUpdateBO, String id);

    int delete(String id);

    void enableOrDisable(String id, String status);

    VipLevelBO selectByLevelCode(String levelCode);
}
