package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.SysTask;
import com.abc12366.uc.model.bo.SysTaskBO;
import com.abc12366.uc.model.bo.SysTaskListBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-24
 * Time: 9:54
 */
public interface SysTaskRoMapper {
    List<SysTaskBO> selectList(Map<String, String> map);

    List<SysTaskBO> selectDeployedList(Map<String, String> map);

    SysTaskBO selectOne(String id);

    

    List<SysTaskBO> selectListByDateType(String dateType);

    List<SysTaskBO> selectListByType(String type);

    List<SysTask> selectValidSysTaskByRuleId(String ruleId);

    List<SysTaskBO> selectValidListByTypeAndDateType(@Param("type") String type, @Param("dateType") String dateType);

    List<SysTaskBO> selectTimeLimitedListByType(String type);

    SysTaskBO selectValidOneByCode(String taskCode);

    List<SysTaskBO> selectListByCode(String code);
}
