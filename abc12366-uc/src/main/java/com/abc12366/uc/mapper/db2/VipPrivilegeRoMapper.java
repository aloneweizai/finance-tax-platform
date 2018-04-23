package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.VipPrivilege;

import java.util.List;
import java.util.Map;

/**
 * 特权服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-08 4:02 PM
 * @since 1.0.0
 */
public interface VipPrivilegeRoMapper {

    /**
     * 查询会员特权列表
     *
     * @param map name,status属性
     * @return 员特权列表
     */
    List<VipPrivilege> selectList(Map map);

    /**
     * 根据ID查看会员特权
     *
     * @param id PK
     * @return VipPrivilege
     */
    VipPrivilege selectOne(String id);
}
