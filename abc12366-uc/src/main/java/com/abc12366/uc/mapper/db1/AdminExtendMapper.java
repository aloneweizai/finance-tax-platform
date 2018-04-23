package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.admin.AdminExtend;
import org.apache.ibatis.annotations.Param;

/**
 * UserExtendMapper数据库操作接口类
 **/

public interface AdminExtendMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByUserId(@Param("userId") String userId);

    /**
     * 添加
     **/
    int insert(AdminExtend adminExtend);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(AdminExtend adminExtend);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(AdminExtend adminExtend);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateUserExtentBO(AdminExtend adminExtend);

}