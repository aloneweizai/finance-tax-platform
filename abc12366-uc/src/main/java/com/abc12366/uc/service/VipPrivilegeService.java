package com.abc12366.uc.service;

import com.abc12366.uc.model.VipPrivilege;
import com.abc12366.uc.model.bo.VipPrivilegeBO;

import java.util.List;
import java.util.Map;

/**
 * 会员特权服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-08 4:02 PM
 * @since 1.0.0
 */
public interface VipPrivilegeService {

    /**
     * 查询会员特权列表
     *
     * @param map  name,code,status属性
     * @param size 每页大小
     * @param page 当前页
     * @return 会员特权对象列表
     */
    List<VipPrivilege> selectList(Map map, int page, int size);

    /**
     * 根据ID查看会员特权
     *
     * @param id PK
     * @return 会员特权对象
     */
    VipPrivilege selectOne(String id);

    /**
     * 新增特权对象
     *
     * @param bo 需要新增的特权对象
     * @return 新增之后的特权对象
     */
    VipPrivilege insert(VipPrivilegeBO bo);

    /**
     * 修改特权对象
     *
     * @param bo 需要修改的特权对象
     * @return 修改之后的特权对象
     */
    VipPrivilege update(VipPrivilegeBO bo);

    /**
     * 删除特权对象
     *
     * @param id PK
     * @return 删除是否成功
     */
    boolean delete(String id);

    /**
     * 根据ID启用或禁用会员特权
     *
     * @param id PK
     */
    void enableOrDisable(String id);
}
