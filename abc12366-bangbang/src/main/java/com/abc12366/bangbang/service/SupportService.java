package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.SupportBO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-11
 * Time: 11:48
 */
public interface SupportService {
    SupportBO insert(String subject, String id, HttpServletRequest request);

    void delete(String subject, String id, HttpServletRequest request);

    int selectCount(String subject, String id);

    List<SupportBO> selectList(String subject, String userId);
}
