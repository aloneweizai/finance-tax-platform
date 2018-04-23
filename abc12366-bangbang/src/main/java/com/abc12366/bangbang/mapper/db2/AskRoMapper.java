package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.AskBO;
import com.abc12366.bangbang.model.bo.HotspotAskBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-09
 * Time: 11:16
 */
public interface AskRoMapper {
    List<AskBO> selectListForAdmin();

    List<AskBO> selectListForUser();

    AskBO selectOne(String id);

    List<HotspotAskBO> selectHotspotAsks();

    List<AskBO> selectHotspotComments();
}
