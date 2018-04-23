package com.abc12366.uc.job.user;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.uc.model.Token;
import com.abc12366.uc.service.AuthService;
import com.abc12366.uc.service.AuthServiceNew;
import com.abc12366.uc.service.TodoTaskService;
import com.alibaba.fastjson.JSONObject;

/**
 * 新增一直在线用户第二天没有生成待办任务的定时任务，要求：只针对User-Token在当天零点还没有过期的用户生成待办任务
 * 
 * @author Administrator
 *
 */
public class Systaskjob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(Systaskjob.class);

	@Autowired
	private TodoTaskService todoTaskService;

	@Autowired
	private AuthServiceNew authServiceNew;
	
	@Autowired
	private AuthService authService;
	

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		LOGGER.info("开始生成一直在线用户的每日任务.......");

		List<Token> tokens = authServiceNew.getActiveToken();

		LOGGER.info("跨日一直在线用户信息：{}", JSONObject.toJSONString(tokens));

		if (tokens != null && tokens.size() > 0) {
             for(Token token:tokens){
            	 try {
					 String userId = token.getUserId();

					 LOGGER.info("如果用户当天定时任务没有完成，就在登录的时候生成:{}", userId);
					 todoTaskService.generateAllTodoTaskList(userId);
					 
					 LOGGER.info("登录任务日志:{}", userId);
					 boolean loginTask = todoTaskService.doTaskWithouComputeAward(userId, TaskConstant.SYS_TASK_LOGIN_CODE);

					 LOGGER.info("用户完成登录任务结果：{}",loginTask);
					 if (loginTask) {
					     LOGGER.info("计算用户登录经验值变化:{}", userId);
					     authService.computeExp(userId);
					 }

					 LOGGER.info("记用户登录日志:{}", userId);
					 authService.insertLoginLog(userId);
				} catch (Exception e) {
					LOGGER.info("生成用户日志异常,用户ID：{}", token.getUserId());
					continue;
				}
             }
		}
		
		LOGGER.info("跨日一直在线用户信息处理完毕!");
	}

}
