package com.abc12366.gateway.mapper.db1;

import com.abc12366.gateway.model.IpSetting;

/**
 * IpSettingMapper数据库操作接口类
 **/

public interface IpSettingMapper {

    /**
     * 修改（根据主键ID修改）
     **/
    int update(IpSetting record);

}