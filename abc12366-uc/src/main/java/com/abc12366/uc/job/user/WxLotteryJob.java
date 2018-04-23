package com.abc12366.uc.job.user;

import com.abc12366.uc.model.weixin.WxRedEnvelop;
import com.abc12366.uc.service.IActivityService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 定时同步状态为'已发送，接收状态为空、已发送待领取'值的微信红包信息
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-27 12:21 PM
 * @since 1.0.0
 */
public class WxLotteryJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxLotteryJob.class);

    @Autowired
    private IActivityService activityService;

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("WxLotteryJob: {}", context.getJobDetail().getKey().getName());

        // 接收状态为'空'的微信红包
        WxRedEnvelop unusedRedEnvelop = new WxRedEnvelop.Builder()
                .sendStatus("1")
                .receiveStatus("UNUSED")
                .build();
        List<WxRedEnvelop> unusedDataList = activityService.selectRedEnvelopList(unusedRedEnvelop, 1, 100);

        // 接收状态为'已发送待领取'的微信红包
        WxRedEnvelop sentRedEnvelop = new WxRedEnvelop.Builder()
                .sendStatus("1")
                .receiveStatus("SENT")
                .build();
        List<WxRedEnvelop> sentDataList = activityService.selectRedEnvelopList(sentRedEnvelop, 1, 100);
        // 数据集合并
        unusedDataList.addAll(sentDataList);


        for (WxRedEnvelop wre : unusedDataList) {
            LOGGER.info("同步口令ID: {}", wre.getId());
            WxRedEnvelop data = activityService.gethbinfo(wre.getId());
            LOGGER.info("同步完成信息: {}", data);
            try {
                // 0.5s发送一次
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error("WxLotteryJob线程中断: {}, {}", e.getMessage(), e);
            }
        }
    }
}