package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.VipLevelStatisticTemp;
import com.abc12366.uc.model.bo.VipLogBO;
import com.abc12366.uc.model.bo.VipLogOrderBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-19
 * Time: 15:40
 */
public interface VipLogRoMapper {
    List<VipLogBO> selectList(String userId);

    VipLevelStatisticTemp selectCountByCode(Map map);

    int selectCountAll(String levelCode);

    /**
     * 会员日志列表
     * @param userId
     * @return
     */
    List<VipLogOrderBO> selectListByOrder(String userId);
}
