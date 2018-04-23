package com.abc12366.uc.job.user;

import com.abc12366.gateway.util.DateUtils;
import com.abc12366.uc.service.AutoSendUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业使用情况统计定时器
 *
 * @author lizhongwei
 * @create 2017-12-12 12:21 PM
 * @since 1.0.0
 */
public class SystemRecordCompanyJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemRecordCompanyJob.class);

    @Autowired
    private AutoSendUtil autoSendUtil;

    @Override
    public void execute(JobExecutionContext context) {
        Map<String, Object> map = new HashMap<>();
        //获取上一天时间
        map.put("yyyyMMdd", DateUtils.getDateToYYYYMMDD(-1));
        LOGGER.info("SystemRecordCompanyJob: {}", context.getJobDetail().getKey().getName());
        autoSendUtil.autoRecordCompany(map);
    }

}
