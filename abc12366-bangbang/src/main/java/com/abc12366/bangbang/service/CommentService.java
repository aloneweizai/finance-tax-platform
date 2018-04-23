package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.CommentBO;
import com.abc12366.bangbang.model.bo.CommentInsertBO;
import com.abc12366.bangbang.model.bo.CommentUpdateBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-19
 * Time: 11:31
 */
public interface CommentService {
    CommentBO insert(CommentInsertBO commentInsertBO, String answerId);

    List<CommentBO> selectListForUser(String userId, String answerId, String commentedUserId);

    CommentBO selectOne(String id);

    CommentBO update(String id, CommentUpdateBO commentUpdateBO);

    CommentBO block(String id);

    List<CommentBO> selectListForAdmin(String userId, String answerId, String commentedUserId, String status);

    int delete(String id, String userId);
}
