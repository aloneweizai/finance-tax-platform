package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.admin.LoginInfo;
import com.abc12366.uc.model.admin.bo.LoginInfoBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * LoginInfoMapper数据库操作接口类
 **/

public interface LoginInfoRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    LoginInfo selectByPrimaryKey(@Param("id") String id);


    LoginInfo selectOne(LoginInfo loginInfo);

    List<LoginInfo> selectByAppList(LoginInfo loginInfo);

    LoginInfo selectInfoByToken(LoginInfo loginInfo);

    LoginInfoBO selectLoginInfoByToken(String token);
}