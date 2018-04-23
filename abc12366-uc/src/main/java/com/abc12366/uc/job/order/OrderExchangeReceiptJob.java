package com.abc12366.uc.job.order;

import com.abc12366.uc.service.order.OrderExchangeService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 退换货自动确认收货
 *
 * @author lizhongwei
 * @create 2017-08-07 12:21 PM
 * @since 1.0.0
 */
public class OrderExchangeReceiptJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderExchangeReceiptJob.class);

    @Autowired
    private OrderExchangeService orderExchangeService;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("JobName: {}", context.getJobDetail().getKey().getName());
        try {
            orderExchangeService.automaticReceipt();
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
        }
    }
}
