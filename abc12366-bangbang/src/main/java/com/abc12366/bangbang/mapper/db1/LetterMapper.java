package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.Letter;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-24
 * Time: 18:30
 */
public interface LetterMapper {
    int insert(Letter letter);

    int read(String id);

    int delete(String id);
}
