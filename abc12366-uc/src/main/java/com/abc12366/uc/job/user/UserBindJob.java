package com.abc12366.uc.job.user;

import com.abc12366.uc.service.UserBindService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户电子申报登录失效自动解绑
 *
 * @author lizhongwei
 * @create 2017-09-27 12:21 PM
 * @since 1.0.0
 */
public class UserBindJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBindJob.class);

    @Autowired
    private UserBindService userBindService;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("UserBindJob: {}", context.getJobDetail().getKey().getName());
        userBindService.automaticBindCancel();
        LOGGER.info("自动取消绑定完成: {}");
    }
}
