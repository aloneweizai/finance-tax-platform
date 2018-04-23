package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.SysTaskBO;
import com.abc12366.uc.model.bo.SysTaskInsertAndUpdateBO;
import com.abc12366.uc.model.bo.SysTaskListBO;

import java.util.List;
import java.util.Map;

/**
 * 系统任务接口方法
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-23
 * Time: 17:52
 */
public interface SysTaskService {
    /**
     * 查询系统任务列表
     * @param map {@linkplain java.util.Map}
     * @return List<SysTaskBO> {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    List<SysTaskBO> selectList(Map<String, String> map);

    /**
     * 根据任务周期类型查询启用的任务列表
     * @param dateType 任务周期类型
     * @return List<SysTaskBO> {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    List<SysTaskBO> selectListByDateType(String dateType);

    /**
     * 查询系统任务列表：只查询启用的
     * @param map {@linkplain java.util.Map}
     * @return List<SysTaskBO> {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    List<SysTaskBO> selectDeployedList(Map<String, String> map);

    /**
     * 根据ID查询一条系统任务
     * @param id 系统任务ID
     * @return SysTaskBO {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    SysTaskBO selectOne(String id);

    /**
     * 增加一条系统任务
     * @param sysTaskInsertBO {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     * @return SysTaskBO {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    SysTaskBO insert(SysTaskInsertAndUpdateBO sysTaskInsertBO);

    /**
     * 根据ID修改一条系统任务
     * @param sysTaskUpdateBO {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     * @param id 系统任务ID
     * @return SysTaskBO {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    SysTaskBO update(SysTaskInsertAndUpdateBO sysTaskUpdateBO, String id);

    /**
     * 根据ID删除一条系统任务（逻辑删除）
     * @param id 系统任务ID
     * @return boolean
     */
    boolean delete(String id);

    

    /**
     * 根据任务类型查询所有的任务列表
     * @param type 系统任务类型
     * @return List<SysTaskListBO> {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    List<SysTaskBO> selectListByType(String type);

    /**
     * 根据任务类型和周期类型查询启用的任务列表
     * @param type 系统任务类型
     * @param dateType 系统任务周期类型
     * @return List<SysTaskListBO> {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    List<SysTaskBO> selectValidListByTypeAndDateType(String type, String dateType);

    /**
     * 根据任务类型查询有时间限制的系统任务的任务列表
     * @param type 系统任务类型
     * @return List<SysTaskListBO> {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    List<SysTaskBO> selectTimeLimitedListByType(String type);

    /**
     * 根据编码查询一条系统任务
     * @param taskCode 系统任务编码
     * @return SysTaskBO {@linkplain com.abc12366.uc.model.bo.SysTaskBO}
     */
    SysTaskBO selectValidOneByCode(String taskCode);
}
