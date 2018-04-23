package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.Comment;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-19
 * Time: 11:36
 */
public interface CommentMapper {
    int insert(Comment comment);

    int update(Comment comment);

    int delete(String id);
}
