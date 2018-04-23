package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.SupportBO;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-11
 * Time: 15:22
 */
public interface SupportRoMapper {
    String selectCount(Map map);

    List<SupportBO> selectList(Map map);

    List<SupportBO> selectExist(Map map);
}
