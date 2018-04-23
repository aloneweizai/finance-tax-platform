package com.abc12366.uc.service;

import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.bo.MessageBO;
import com.abc12366.uc.model.bo.MessageListBO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-09
 * Time: 11:41
 */
public interface MessageService {
    MessageListBO selectList(String type, String busiType,String status,int page, int size, HttpServletRequest request) throws IOException;

    MessageBO insert(Message message, HttpServletRequest request) throws IOException;

    MessageBO selectOne(String id, HttpServletRequest request) throws IOException;

    MessageBO update(String id, HttpServletRequest request) throws IOException;

    MessageBO delete(String id, HttpServletRequest request) throws IOException;
}
