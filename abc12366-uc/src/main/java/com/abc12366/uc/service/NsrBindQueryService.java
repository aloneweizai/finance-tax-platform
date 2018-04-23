package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.NsrBindQueryBO;
import com.abc12366.uc.model.bo.UserDzsbBO;
import com.abc12366.uc.model.bo.UserHndsBO;
import com.abc12366.uc.model.bo.UserHngsBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-16
 * Time: 11:45
 */
public interface NsrBindQueryService {

    /**
     * 查询纳税人绑定信息列表
     *
     * @param map  查询条件
     * @param page 当前页
     * @param size 每页大小
     * @return 纳税人绑定信息列表
     */
    List<NsrBindQueryBO> selectList(Map<String, Object> map, int page, int size);

    UserDzsbBO selectDzsb(String id);

    UserHndsBO selectHnds(String id);

    UserHngsBO selectHngs(String id);
}
