package com.abc12366.uc.job.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.abc12366.uc.service.TodoTaskService;

public class UserTaskjob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(Systaskjob.class);

	@Autowired
	private TodoTaskService todoTaskService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		
		try {
			
			LOGGER.info("开始归档两日前用户没完成每日任务，日期:{}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			int num = todoTaskService.tasksArchiving();
			LOGGER.info("结束归档两日前用户没完成每日任务,处理数量:{}", num);
			
		} catch (Exception e) {
			LOGGER.error("归档两日前用户没完成每日任务异常:", e);
		}
		
	}

}
