package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.*;

import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-16
 * Time: 14:45
 */
public interface NsrBindQueryRoMapper {
    List<NsrBindQueryBO> selectList(NsrBindQueryParamBO nsrBindQueryParamBO);

    UserDzsbBO selectDzsb(String id);

    UserHndsBO selectHnds(String id);

    UserHngsBO selectHngs(String id);

    List<NsrBindQueryBO> selectDzsbList(NsrBindQueryParamBO bo);

    List<NsrBindQueryBO> selectHngsList(NsrBindQueryParamBO bo);

    List<NsrBindQueryBO> selectHndsList(NsrBindQueryParamBO bo);
}
