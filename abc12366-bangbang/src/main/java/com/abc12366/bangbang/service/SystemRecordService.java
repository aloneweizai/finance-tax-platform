package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.*;
import com.abc12366.bangbang.model.bo.SystemRecordBO;
import com.abc12366.bangbang.model.bo.SystemRecordInsertBO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 用户日志服务
 *
 * @author lingsuzhi <554600654@qq.com.com>
 * @create 2017-08-16
 */
public interface SystemRecordService {

    /**
     * 查询用户日志列表
     *
     * @param map  Map appName,location,startTime,endTime
     * @param page 当前页
     * @param size 页大小
     * @return List<SystemRecordBO>
     * @see com.abc12366.bangbang.model.bo.SystemRecordBO
     */
    List<SystemRecordBO> selectList(Map<String, String> map, int page, int size);

    /**
     * 查看用户日志
     *
     * @param systemRecord SystemRecord
     * @return SystemRecordBO
     * @see com.abc12366.bangbang.model.bo.SystemRecordBO
     */
    SystemRecordBO selectOne(SystemRecord systemRecord);

    /**
     * 异步新增日志；如果规则代码有效，则新增用户经验值
     *
     * @param systemRecordInsertBO 日志BO
     * @return CompletableFuture<SystemRecordBO>
     * @see com.abc12366.bangbang.model.bo.SystemRecordInsertBO
     * @see com.abc12366.bangbang.model.bo.SystemRecordBO
     */
    CompletableFuture<SystemRecordBO> insert(SystemRecordInsertBO systemRecordInsertBO,HttpServletRequest request);

    /**
     * 软件用户行为统计
     * @param map
     * @return
     */
    List<SystemRecordStatis> statisList(Map<String, Object> map);

    /**
     * 自动统计上一天的用户操作日志
     * @param map
     */
    void autoRecordStatis(Map<String, Object> map);

    /**
     * 企业使用情况统计
     * @param map
     * @return
     */
    List<SystemRecordCompany> statisCompanyList(Map<String, Object> map);

    /**
     * 自动统计上一天的用户操作日志
     * @param map
     */
    void autoRecordCompany(Map<String, Object> map);

    /**
     *用户列表
     * @param map
     * @param page
     *@param size @return
     */
    List<User> statisRecordUserList(Map<String, Object> map, int page, int size);

    List<DzsbHngs> statisRecordCompanyList(Map<String, Object> map, int page, int size);
}
