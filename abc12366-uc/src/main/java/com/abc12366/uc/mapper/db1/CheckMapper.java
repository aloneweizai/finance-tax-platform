package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.Check;
import com.abc12366.uc.model.CheckRank;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-21
 * Time: 14:43
 */
public interface CheckMapper {

    int insert(Check check);

    int insertRank(CheckRank checkRank);

    int updateRank(CheckRank checkRank);
}
