package com.abc12366.uc.job.user;


import com.abc12366.uc.service.UserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 会员到期之后自动取消会员服务
 *
 * @author lizhongwei
 * @create 2017-09-27 12:21 PM
 * @since 1.0.0
 */
public class UserVipJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserVipJob.class);

    @Autowired
    private UserService userService;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("UserVipJob: {}", context.getJobDetail().getKey().getName());
        userService.automaticUserCancel();
    }

}
