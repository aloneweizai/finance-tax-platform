package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.UcUserLoginLog;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-31
 * Time: 16:04
 */
public interface UcUserLoginLogRoMapper {
    List<UcUserLoginLog> selectLoginLogList(Map<String, Object> map);
}
