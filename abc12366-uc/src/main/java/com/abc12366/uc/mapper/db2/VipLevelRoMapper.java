package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.VipLevelBO;

import java.util.List;
import java.util.Map;

/**
 * Author: liuguiyao<435720953@qq.com>
 * Date: 2017-05-19
 * Time: 11:35
 */
public interface VipLevelRoMapper {
    List<VipLevelBO> selectList(Map map);

    VipLevelBO selectOne(String id);

    VipLevelBO selectByLevel(String level);

    VipLevelBO selectByLevelCode(String levelCode);

    List<User> selectUserByVipLevelCode(String id);
}
