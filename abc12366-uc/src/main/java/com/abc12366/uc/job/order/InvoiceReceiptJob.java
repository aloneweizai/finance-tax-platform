package com.abc12366.uc.job.order;

import com.abc12366.uc.service.invoice.InvoiceService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户收到发票后一段时间之后自动收货任务
 *
 * @author lizhongwei
 * @create 2017-08-07 12:21 PM
 * @since 1.0.0
 */
public class InvoiceReceiptJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceReceiptJob.class);

    @Autowired
    private InvoiceService invoiceService;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("InvoiceReceiptJob: {}", context.getJobDetail().getKey().getName());
        invoiceService.automaticReceiptInvoice();
    }

}
