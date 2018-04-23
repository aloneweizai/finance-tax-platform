package com.abc12366.uc.service.impl;

import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.TodoTaskMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.TodoTaskRoMapper;
import com.abc12366.uc.model.TodoTask;
import com.abc12366.uc.model.TodoTaskFront;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.ExperienceLogBO;
import com.abc12366.uc.model.bo.PointsLogBO;
import com.abc12366.uc.model.bo.SysTaskBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;
import com.abc12366.uc.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-12
 * Time: 14:43
 */
@Service
public class TodoTaskServiceImpl implements TodoTaskService {
    private static Logger LOGGER = LoggerFactory.getLogger(TodoTaskServiceImpl.class);

    @Autowired
    private TodoTaskMapper todoTaskMapper;

    @Autowired
    private TodoTaskRoMapper todoTaskRoMapper;

    @Autowired
    private SysTaskService sysTaskService;

    @Autowired
    private PointsLogService pointsLogService;

    @Autowired
    private ExperienceLogService experienceLogService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VipPrivilegeLevelService vipPrivilegeLevelService;

    @Override
    public void doTask(String userId, String taskCode) {
        try{
            //SysTaskBO sysTaskBO = sysTaskService.selectOne(sysTaskId);
            //新的查询系统任务方法：根据编码查询
            SysTaskBO sysTaskBO = sysTaskService.selectValidOneByCode(taskCode);
            LOGGER.info("用户正在完成任务，用户ID：{},任务名称:{}", userId, sysTaskBO);
            if (sysTaskBO == null) {
                return;
            }
            //做特殊任务
            if (sysTaskBO.getType().trim().equals(TaskConstant.SPECIAL_TASK_TYPE)) {
                doTaskSpecial(userId, sysTaskBO);
            }
            //做其他任务
            String dateType = sysTaskBO.getDateType();
            switch (dateType.trim()) {
                case TaskConstant.TASK_TIME_ONETIME_TYPE:
                    doTaskOneTime(userId, sysTaskBO);
                    break;
                case TaskConstant.TASK_TIME_DAY_TYPE:
                    doTaskDay(userId, sysTaskBO);
                    break;
                case TaskConstant.TASK_TIME_MONTH_TYPE:
                    doTaskMonth(userId, sysTaskBO);
                    break;
                case TaskConstant.TASK_TIME_YEAR_TYPE:
                    doTaskYear(userId, sysTaskBO);
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("做任务出现异常：{}" , e);
        }

    }

    @Override
    public boolean doTaskWithouComputeAward(String userId, String taskCode) {
        try{
            //查询系统任务方法：根据编码查询
            SysTaskBO sysTaskBO = sysTaskService.selectValidOneByCode(taskCode);
            if (sysTaskBO == null) {
                return false;
            }

            //用户每完成当天一项任务一次，更新已完成数，当已完成数等于该项任务数量，更新该项任务数量为已完成
            TodoTask todoTask = selectOne(userId, sysTaskBO.getId());

            //如果该项任务已完成，则返回
            if (todoTask == null || todoTask.getStatus().trim().equals(TaskConstant.TASK_FINISHED)) {
                return false;
            }

            todoTask.setFinishedCount(todoTask.getFinishedCount() + 1);
            if (todoTask.getFinishedCount() >= todoTask.getAllCount()) {
                todoTask.setStatus(TaskConstant.TASK_FINISHED);
            }
            todoTask.setLastUpdate(new Date());
            update(todoTask);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("做任务出现异常：{}", e);
        }

        return true;
    }

    @Override
    public void generateAllTodoTaskList(String userId) {
        //1.生成一次性任务
        generateOneTimeTaskList(userId);

        //2.生成日常任务
        generateNormalTaskList(userId);

        //3.生成特殊任务
        generateSpecialTaskList(userId);

        //4.生成帮帮任务
        generateBangbangTaskList(userId);
    }

    private void generateBangbangTaskList(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return;
        }
        List<SysTaskBO> sysTaskBOList = sysTaskService.selectTimeLimitedListByType(TaskConstant.BANGBANG_TASK_TYPE);
        if (sysTaskBOList == null || sysTaskBOList.size() < 1) {
            return;
        }
        for (SysTaskBO sysTaskBO : sysTaskBOList) {
            //根据用户ID和系统任务ID查看有时间限制的任务是否生成
            List<TodoTask> todoTaskList = todoTaskMapper.selectTimeLimitedOneByUserIdAndSysTaskId(userId, sysTaskBO.getId());
            //过滤掉已生成的特殊任务
            if (todoTaskList != null && todoTaskList.size() > 0) {
                continue;
            }
            generateOneTodoTask(userId, sysTaskBO);
        }
    }

    private void generateNormalTaskList(String userId) {
        //2.生成日度任务
        generateDayTaskList(userId);

        //3.生成月度任务
        generateMonthTaskList(userId);

        //4.生成年度任务
        generateYearTaskList(userId);
    }

    @Override
    public void update(TodoTask todoTask) {
        todoTaskMapper.update(todoTask);
    }

    @Override
    public TodoTask selectOne(String userId, String sysTaskId) {
        return todoTaskMapper.selectOneByDayBySysTaskId(userId, sysTaskId);
    }

    @Override
    public void computeAward(String userId, TodoTask todoTask) {
        //根据奖励的类型分别做计算
        if (todoTask.getAwardType().trim().equals(TaskConstant.AWARD_TYPE_EXP)) {
            ExperienceLogBO experienceLog = new ExperienceLogBO();
            experienceLog.setUserId(userId);
            experienceLog.setRuleId(todoTask.getRuleId());
            experienceLog.setIncome(todoTask.getAward());
            experienceLog.setOutgo(0);

            experienceLogService.insert(experienceLog);
        } else if (todoTask.getAwardType().trim().equals(TaskConstant.AWARD_TYPE_POINT)) {
            PointsLogBO pointsLog = new PointsLogBO();
            pointsLog.setUserId(userId);
            pointsLog.setRuleId(todoTask.getRuleId());
            pointsLog.setIncome(todoTask.getAward());
            pointsLog.setOutgo(0);
            //会员权限埋点（产品消费获得积分加成）
            User user = userMapper.selectOne(userId);
            if(user == null){
                return;
            }
            if (!StringUtils.isEmpty(user.getVipLevel()) && (pointsLog.getIncome() - pointsLog.getOutgo() > 0)) {
                VipPrivilegeLevelBO vipPrivilegeLevelBOPar = new VipPrivilegeLevelBO();
                vipPrivilegeLevelBOPar.setLevelId(user.getVipLevel());
                vipPrivilegeLevelBOPar.setPrivilegeId("A_XFJFJC");
                VipPrivilegeLevelBO vipPrivilegeLevelBO = vipPrivilegeLevelService.selectLevelIdPrivilegeId(vipPrivilegeLevelBOPar);
                if (vipPrivilegeLevelBO != null && vipPrivilegeLevelBO.getStatus()) {
                    if (!StringUtils.isEmpty(vipPrivilegeLevelBO.getVal1())) {
                        LOGGER.info("会员产品消费获得积分加成：{}", vipPrivilegeLevelBO.getVal1()+"倍");
                        pointsLog.setIncome((int) ((pointsLog.getIncome()) * Float.parseFloat(vipPrivilegeLevelBO.getVal1())));
                        pointsLog.setOutgo((int) ((pointsLog.getOutgo()) * Float.parseFloat(vipPrivilegeLevelBO.getVal1())));
                    }
                }
            }
            pointsLogService.insert(pointsLog);
        }

    }

    //生成特殊任务
    private void generateSpecialTaskList(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return;
        }

        List<SysTaskBO> sysTaskBOList = sysTaskService.selectTimeLimitedListByType(TaskConstant.SPECIAL_TASK_TYPE);
        if (sysTaskBOList == null || sysTaskBOList.size() < 1) {
            return;
        }
        for (SysTaskBO sysTaskBO : sysTaskBOList) {
            //根据用户ID和系统任务ID查看有时间限制的任务是否生成
            List<TodoTask> todoTaskList = todoTaskMapper.selectTimeLimitedOneByUserIdAndSysTaskId(userId, sysTaskBO.getId());
            //过滤掉已生成的特殊任务
            if (todoTaskList != null && todoTaskList.size() > 0) {
                continue;
            }
            generateOneTodoTask(userId, sysTaskBO);
        }
    }

    //生成一次性任务
    private void generateOneTimeTaskList(String userId) {
        List<SysTaskBO> sysTaskBOList = sysTaskService.selectValidListByTypeAndDateType(TaskConstant.NEW_USER_TASK_TYPE, TaskConstant.TASK_TIME_ONETIME_TYPE);
        for (SysTaskBO sysTaskBO : sysTaskBOList) {
            //假如成长任务已生成则不用再生成
            List<TodoTask> taskList = todoTaskMapper.selectListOneTime(userId, sysTaskBO.getId());
            if (taskList != null && taskList.size() > 0) {
                continue;
            }
            generateOneTodoTask(userId, sysTaskBO);
        }
    }

    //生成日度任务
    private void generateDayTaskList(String userId) {
        List<SysTaskBO> sysTaskBOList = sysTaskService.selectValidListByTypeAndDateType(TaskConstant.NORMAL_TASK_TYPE, TaskConstant.TASK_TIME_DAY_TYPE);
        for (SysTaskBO sysTaskBO : sysTaskBOList) {
            //假如日度任务已生成则不用再生成
            List<TodoTask> taskList = todoTaskMapper.selectListByDay(userId, sysTaskBO.getId());
            if (taskList != null && taskList.size() > 0) {
                continue;
            }
            generateOneTodoTask(userId, sysTaskBO);
        }
    }

    //生成年度任务
    private void generateYearTaskList(String userId) {
        List<SysTaskBO> sysTaskBOList = sysTaskService.selectValidListByTypeAndDateType(TaskConstant.NORMAL_TASK_TYPE, TaskConstant.TASK_TIME_YEAR_TYPE);
        for (SysTaskBO sysTaskBO : sysTaskBOList) {
            //假如年度任务已生成则不用再生成
            List<TodoTask> taskList = todoTaskMapper.selectListByYear(userId, sysTaskBO.getId());
            if (taskList != null && taskList.size() > 0) {
                continue;
            }
            generateOneTodoTask(userId, sysTaskBO);
        }
    }

    //生成月度任务
    private void generateMonthTaskList(String userId) {
        List<SysTaskBO> sysTaskBOList = sysTaskService.selectValidListByTypeAndDateType(TaskConstant.NORMAL_TASK_TYPE, TaskConstant.TASK_TIME_MONTH_TYPE);
        for (SysTaskBO sysTaskBO : sysTaskBOList) {
            //假如月度任务已生成则不用再生成
            List<TodoTask> taskList = todoTaskMapper.selectListByMonth(userId, sysTaskBO.getId());
            if (taskList != null && taskList.size() > 0) {
                continue;
            }
            generateOneTodoTask(userId, sysTaskBO);
        }
    }

    @Override
    public void insert(TodoTask todoTask) {
        LOGGER.info("{}", todoTask);
        todoTaskMapper.insert(todoTask);
    }

    @Override
    public void generateTodoTaskList(String userId, String type) {
        //如果用户的日常任务已生成则返回
        List<TodoTask> taskList = todoTaskMapper.selectList(type, userId);
        if (taskList != null && taskList.size() > 0) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        List<SysTaskBO> sysTaskBOList = sysTaskService.selectDeployedList(map);
        for (SysTaskBO sysTaskBO : sysTaskBOList) {
            TodoTask todoTask = new TodoTask();
            todoTask.setId(Utils.uuid());
            todoTask.setUserId(userId);
            todoTask.setSysTaskId(sysTaskBO.getId());
            todoTask.setAllCount(sysTaskBO.getCount());
            todoTask.setFinishedCount(0);
            todoTask.setAwardType(sysTaskBO.getAwardType());
            todoTask.setType(type);
            todoTask.setAward(sysTaskBO.getAward());
            todoTask.setStatus(TaskConstant.TASK_UNFINISHED);
            todoTask.setSkipUrl(sysTaskBO.getSkipURL());
            todoTask.setRuleId(sysTaskBO.getRuleId());
            Date date = new Date();
            todoTask.setCreateTime(date);
            todoTask.setLastUpdate(date);
            if (type.trim().equals(TaskConstant.SPECIAL_TASK_TYPE)) {
                long now = System.currentTimeMillis();
                //如果特殊任务已过期则不需要生成了
                if (now > sysTaskBO.getEndTime().getTime()) {
                    continue;
                }
                todoTask.setStartTime(sysTaskBO.getStartTime());
                todoTask.setEndTime(sysTaskBO.getEndTime());
            }
            insert(todoTask);
        }
    }

    @Override
    public List<TodoTask> selectList(String type, String userId) {
        LOGGER.info("{}:{}", type, userId);
        return todoTaskMapper.selectList(type, userId);
    }

    public void finishTask(String userId, TodoTask todoTask) {
        //如果该项任务不存在或已完成，则返回
        if (todoTask == null || todoTask.getStatus().trim().equals(TaskConstant.TASK_FINISHED)) {
            return;
        }

        //用户每完成当天一项任务一次，更新已完成数，当已完成数等于该项任务数量，更新该项任务数量为已完成
        todoTask.setFinishedCount(todoTask.getFinishedCount() + 1);
        if (todoTask.getFinishedCount() >= todoTask.getAllCount()) {
            todoTask.setStatus(TaskConstant.TASK_FINISHED);
        }
        todoTask.setLastUpdate(new Date());
        update(todoTask);

        //计算做任务的奖励（主要是经验值和积分的变化）
        if(TaskConstant.TASK_FINISHED.equals(todoTask.getStatus())){//任务完成后才给奖励
        	 computeAward(userId, todoTask);
        }
       
    }

    //做一次性任务
    public void doTaskOneTime(String userId, SysTaskBO sysTaskBO) {
        TodoTask todoTask = todoTaskMapper.selectOneTime(userId, sysTaskBO.getId());

        finishTask(userId, todoTask);
    }

    //做频率为一天的任务
    private void doTaskDay(String userId, SysTaskBO sysTaskBO) {
        TodoTask todoTask = todoTaskMapper.selectOneByDay(userId, sysTaskBO.getId());

        finishTask(userId, todoTask);

    }

    //做频率为一月的任务
    private void doTaskMonth(String userId, SysTaskBO sysTaskBO) {
        TodoTask todoTask = todoTaskMapper.selectOneByMonth(userId, sysTaskBO.getId());

        finishTask(userId, todoTask);
    }

    //做频率为一年的任务
    private void doTaskYear(String userId, SysTaskBO sysTaskBO) {
        TodoTask todoTask = todoTaskMapper.selectOneByYear(userId, sysTaskBO.getId());

        finishTask(userId, todoTask);
    }

    //做特殊任务
    private void doTaskSpecial(String userId, SysTaskBO sysTaskBO) {
        //特殊任务如果未开始或过期，则不能完成
        long now = System.currentTimeMillis();
        if (now < sysTaskBO.getStartTime().getTime() || now > sysTaskBO.getEndTime().getTime()) {
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("sysTaskId", sysTaskBO.getId());
        map.put("startTime", sysTaskBO.getStartTime());
        TodoTask todoTask = todoTaskMapper.selectSpecial(map);

        finishTask(userId, todoTask);
    }

    @Override
    public List<TodoTaskFront> selectNormalTaskList(String userId) {
        return todoTaskMapper.selectNormalTaskList(userId);
    }

    @Override
    public List<TodoTaskFront> selectSpecialTaskList(String userId) {
        return todoTaskMapper.selectSpecialTaskList(userId);
    }

    @Override
    public List<TodoTaskFront> selectOnetimeTaskList(String userId) {
        return todoTaskMapper.selectOnetimeTaskList(userId);
    }

    @Override
    public List<TodoTaskFront> selectBangbangTaskList(String userId) {
        return todoTaskMapper.selectBangbangTaskList(userId);
    }

    @Override
    public void generateOneTodoTask(String userId, SysTaskBO sysTaskBO) {
        TodoTask todoTask = new TodoTask();
        todoTask.setId(Utils.uuid());
        todoTask.setUserId(userId);
        todoTask.setSysTaskId(sysTaskBO.getId());
        todoTask.setAllCount(sysTaskBO.getCount());
        todoTask.setFinishedCount(0);
        todoTask.setAwardType(sysTaskBO.getAwardType());
        todoTask.setType(sysTaskBO.getType());
        todoTask.setAward(sysTaskBO.getAward());
        todoTask.setStatus(TaskConstant.TASK_UNFINISHED);
        todoTask.setSkipUrl(sysTaskBO.getSkipURL());
        todoTask.setRuleId(sysTaskBO.getRuleId());
        Date date = new Date();
        todoTask.setCreateTime(date);
        todoTask.setLastUpdate(date);
        todoTask.setDateType(sysTaskBO.getDateType());
        todoTask.setStartTime(sysTaskBO.getStartTime());
        todoTask.setEndTime(sysTaskBO.getEndTime());
        insert(todoTask);
    }

    
    @Transactional("db1TxManager")
	public int tasksArchiving() {
		int n = todoTaskMapper.doTasksArchiving();
		LOGGER.info("归档用户任务数量{}", n);
		int m = todoTaskMapper.cleanTasks();
		LOGGER.info("删除已归档用户任务数量{}", m);
		return n;
	}
}
