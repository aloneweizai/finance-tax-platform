package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.CommentBO;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-19
 * Time: 11:36
 */
public interface CommentRoMapper {
    List<CommentBO> selectListForUser(Map map);

    List<CommentBO> selectListForAdmin(Map map);

    CommentBO selectOne(String id);
}
