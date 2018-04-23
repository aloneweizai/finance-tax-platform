package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.Ask;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-08
 * Time: 16:25
 */
public interface AskMapper {

    int insert(Ask ask);

    int update(Ask ask);

    int delete(String id);
}
