package com.abc12366.gateway.mapper.db2;

import com.abc12366.gateway.model.IpSetting;
import org.apache.ibatis.annotations.Param;

/**
 * IpSettingMapper数据库操作接口类
 **/

public interface IpSettingRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    IpSetting selectByPrimaryKey(@Param("id") String id);


    IpSetting selectOne();
}