package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CommentMapper;
import com.abc12366.bangbang.mapper.db2.CommentRoMapper;
import com.abc12366.bangbang.model.Comment;
import com.abc12366.bangbang.model.bo.CommentBO;
import com.abc12366.bangbang.model.bo.CommentInsertBO;
import com.abc12366.bangbang.model.bo.CommentUpdateBO;
import com.abc12366.bangbang.service.CommentService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-19
 * Time: 11:31
 */
@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentRoMapper commentRoMapper;

    @Override
    public CommentBO insert(CommentInsertBO commentInsertBO, String answerId) {
        LOGGER.info("{}", commentInsertBO);
        Comment comment = new Comment();
        Date date = new Date();
        BeanUtils.copyProperties(commentInsertBO, comment);
        comment.setId(Utils.uuid());
        comment.setAnswerId(answerId);
        comment.setCreateTime(date);
        comment.setLastUpdate(date);
        int result = commentMapper.insert(comment);
        if (result != 1) {
            LOGGER.warn("新增失败！");
            throw new ServiceException(4101);
        }
        CommentBO commentBO = new CommentBO();
        BeanUtils.copyProperties(comment, commentBO);
        return commentBO;
    }

    @Override
    public List<CommentBO> selectListForUser(String userId, String answerId, String commentedUserId) {
        LOGGER.info("{}:{}:{}", userId, answerId, commentedUserId);
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("answerId", answerId);
        map.put("commentedUserId", commentedUserId);
        return commentRoMapper.selectListForUser(map);
    }

    @Override
    public CommentBO selectOne(String id) {
        LOGGER.info("{}", id);
        return commentRoMapper.selectOne(id);
    }

    @Override
    public CommentBO update(String id, CommentUpdateBO commentUpdateBO) {
        LOGGER.info("{}:{}", id, commentUpdateBO);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentUpdateBO, comment);
        comment.setId(id);
        comment.setLastUpdate(new Date());
        int result = commentMapper.update(comment);
        if (result != 1) {
            LOGGER.warn("更新失败！");
            throw new ServiceException(4102);
        }
        CommentBO commentBO = new CommentBO();
        BeanUtils.copyProperties(comment, commentBO);
        return commentBO;
    }

    @Override
    public CommentBO block(String id) {
        LOGGER.info("{}", id);
        Comment comment = new Comment();
        comment.setId(id);
        comment.setStatus("2");
        comment.setLastUpdate(new Date());
        int result = commentMapper.update(comment);
        if (result != 1) {
            LOGGER.warn("更新失败！");
            throw new ServiceException(4102);
        }
        CommentBO commentBO = new CommentBO();
        BeanUtils.copyProperties(comment, commentBO);
        return commentBO;
    }

    @Override
    public List<CommentBO> selectListForAdmin(String userId, String answerId, String commentedUserId, String status) {
        LOGGER.info("{}:{}:{}:{}", userId, answerId, commentedUserId, status);
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("answerId", answerId);
        map.put("commentedUserId", commentedUserId);
        map.put("status", status);
        return commentRoMapper.selectListForAdmin(map);
    }

    @Override
    public int delete(String id, String userId) {
        LOGGER.info("{}:{}", id, userId);
        int result = commentMapper.delete(id);
        if (result < 1) {
            LOGGER.warn("删除失败！");
            throw new ServiceException(4103);
        }
        return 1;
    }
}
