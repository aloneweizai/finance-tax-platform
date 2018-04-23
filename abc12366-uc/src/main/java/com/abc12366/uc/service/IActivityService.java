package com.abc12366.uc.service;

import com.abc12366.uc.model.weixin.WxActivity;
import com.abc12366.uc.model.weixin.WxRedEnvelop;
import com.abc12366.uc.model.weixin.bo.Id;
import com.abc12366.uc.model.weixin.bo.redpack.ActivityBO;
import com.abc12366.uc.model.weixin.bo.redpack.WxLotteryBO;
import com.abc12366.uc.model.weixin.bo.redpack.WxRedEnvelopBO;

import java.util.List;

/**
 * 微信红包服务接口
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-14 11:15 AM
 * @since 1.0.0
 */
public interface IActivityService {

    /**
     * 查看红包活动列表
     *
     * @param activity WxActivity
     * @param page     当前页
     * @param size     每页大小
     * @return List<WxActivity>
     */
    List<WxActivity> selectList(WxActivity activity, int page, int size);

    /**
     * 查看活动简单信息
     *
     * @param page 当前页
     * @param size 每页大小
     * @return List<ActivityBO>
     */
    List<ActivityBO> selectSimpleList(int page, int size);

    /**
     * 查看红包活动
     *
     * @param id PK
     * @return WxActivity
     */
    WxActivity selectOne(String id);

    /**
     * 新增活动
     *
     * @param activity WxActivity
     * @return WxActivity
     */
    WxActivity insert(WxActivity activity);

    /**
     * 修改活动
     *
     * @param activity WxActivity
     * @return WxActivity
     */
    WxActivity update(WxActivity activity);

    /**
     * 删除活动
     *
     * @param id PK
     */
    void delete(String id);

    /**
     * 生成红包口令
     *
     * @param activityId 活动ID
     * @param businessId 业务ID
     * @return WxRedEnvelopBO
     */
    WxRedEnvelopBO generateSecret(String activityId, String businessId);

    /**
     * 抽奖
     *
     * @param lotteryBO WxLotteryBO
     * @return WxRedEnvelop
     */
    WxRedEnvelop lottery(WxLotteryBO lotteryBO);

    /**
     * 抽奖列表明细
     *
     * @param redEnvelop WxRedEnvelop
     * @param page       当前页
     * @param size       每页大小
     * @return List<WxRedEnvelop>
     */
    List<WxRedEnvelop> selectRedEnvelopList(WxRedEnvelop redEnvelop, int page, int size);

    /**
     * 查询微信红包信息
     *
     * @param id PK
     * @return WxRedEnvelop
     */
    WxRedEnvelop gethbinfo(String id);

    /**
     * 根据业务ID查询微信红包信息
     *
     * @param activityId 红包活动ID
     * @param businessId 业务ID
     * @return WxRedEnvelop
     */
    WxRedEnvelop gethbinfo(String activityId, String businessId);

    /**
     * 导入红包数据
     *
     * @param redEnvelopList List<WxRedEnvelop>
     */
    void importJSON(List<WxRedEnvelopBO> redEnvelopList);

    /**
     * 发送红包
     *
     * @param id PK
     * @return WxRedEnvelop
     */
    WxRedEnvelop send(String id);

    /**
     * 删除未抽奖的口令
     *
     * @param id 红包口令表主键
     */
    void deleteSecret(String id);

    /**
     * 批量删除未抽奖的口令
     *
     * @param ids List<Id>
     */
    void batchDeleteSecret(List<Id> ids);
}
