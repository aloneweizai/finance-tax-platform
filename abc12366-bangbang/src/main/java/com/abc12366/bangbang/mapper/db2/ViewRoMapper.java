package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.ViewBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-12
 * Time: 10:04
 */
public interface ViewRoMapper {
    List<ViewBO> selectList(String userId);

    String selectCount(String askId);
}
