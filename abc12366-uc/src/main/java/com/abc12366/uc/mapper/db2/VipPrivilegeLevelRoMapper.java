package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 9:57
 */
public interface VipPrivilegeLevelRoMapper {
    List<VipPrivilegeLevelBO> selectListN(Map map);
    List<VipPrivilegeLevelBO> selectList(String levelId);

    List<VipPrivilegeLevelBO> selectListbyPrivlege(String privilege);
    VipPrivilegeLevelBO selectOne(String id);
    VipPrivilegeLevelBO selectOneN(String id);
    VipPrivilegeLevelBO selectLevelIdPrivilegeId(VipPrivilegeLevelBO vipPrivilegeLevelBO);
}
