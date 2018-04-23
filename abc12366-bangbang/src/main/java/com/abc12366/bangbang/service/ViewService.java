package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.ViewBO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-12
 * Time: 9:34
 */
public interface ViewService {
    ViewBO insert(String askId, HttpServletRequest request);

    void delete(String askId, HttpServletRequest request);

    List<ViewBO> selectList(String userId);

    int selectCount(String askId);
}
