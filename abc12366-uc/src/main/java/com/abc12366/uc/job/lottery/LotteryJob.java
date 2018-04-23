package com.abc12366.uc.job.lottery;

import com.abc12366.uc.service.LotteryActivityService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by andy on 2018/1/11.
 */
public class LotteryJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryJob.class);
    @Autowired
    private LotteryActivityService lotteryActivityService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("--------开始执行[抽奖派奖记录清零]定时任务----------");
        LOGGER.info("LotteryJob: {}", jobExecutionContext.getJobDetail().getKey().getName());
        lotteryActivityService.automaticResetTimeCount();
        LOGGER.info("--------结束执行[抽奖派奖记录清零]定时任务----------");
    }
}
