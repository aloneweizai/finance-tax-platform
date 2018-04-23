package com.abc12366.uc.job.order;

import com.abc12366.uc.service.order.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户下单未付款超过一定时间自动取消订单
 *
 * @author lizhongwei
 * @create 2017-08-07 12:21 PM
 * @since 1.0.0
 */
public class OrderCancelJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCancelJob.class);

    @Autowired
    private OrderService orderService;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("OrderCancelJob: {}", context.getJobDetail().getKey().getName());
        orderService.automaticCancel();
    }

}
