package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.admin.AdminExtend;
import org.apache.ibatis.annotations.Param;

/**
 * UserExtendMapper数据库操作接口类
 **/

public interface AdminExtendRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    AdminExtend selectById(@Param("id") String id);

    AdminExtend selectUserExtendByUserId(String userId);

}