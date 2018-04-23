package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.admin.LoginInfo;
import com.abc12366.uc.model.admin.bo.LoginInfoBO;
import org.apache.ibatis.annotations.Param;

/**
 * LoginInfoMapper数据库操作接口类
 **/

public interface LoginInfoMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByUserId(@Param("userId") String userId);

    /**
     * 添加
     **/
    int insert(LoginInfo record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(LoginInfo record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int update(LoginInfo record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(LoginInfo record);

    LoginInfo selectInfoByToken(LoginInfo loginInfo);

    LoginInfoBO selectLoginInfoByToken(String token);
}