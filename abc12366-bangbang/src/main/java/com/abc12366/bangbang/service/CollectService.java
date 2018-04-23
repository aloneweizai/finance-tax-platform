package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.CollectBO;
import com.abc12366.bangbang.model.bo.CollectListBO;
import com.abc12366.bangbang.model.bo.MyCollectListBO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-10
 * Time: 17:40
 */
public interface CollectService {
    CollectBO insert(String askId, HttpServletRequest request);

    void delete(String askId, HttpServletRequest request);

    List<CollectListBO> selectList(String userId);

    String selectCount(String askId);

    List<MyCollectListBO> selectCollectListByUserId(String userId);
}
