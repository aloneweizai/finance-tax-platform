package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.LetterListBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-25
 * Time: 10:03
 */
public interface LetterRoMapper {
    List<LetterListBO> selectList(String toId);
}
