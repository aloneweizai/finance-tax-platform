package com.abc12366.uc.service.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.ActivityMapper;
import com.abc12366.uc.mapper.db1.ActivityRoMapper;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.PointCalculateBO;
import com.abc12366.uc.model.bo.UserSimpleInfoBO;
import com.abc12366.uc.model.weixin.WxActivity;
import com.abc12366.uc.model.weixin.WxLotteryLog;
import com.abc12366.uc.model.weixin.WxRedEnvelop;
import com.abc12366.uc.model.weixin.bo.Id;
import com.abc12366.uc.model.weixin.bo.redpack.*;
import com.abc12366.uc.service.IActivityService;
import com.abc12366.uc.service.PointsService;
import com.abc12366.uc.service.UserService;
import com.abc12366.uc.service.order.CouponService;
import com.abc12366.uc.util.LocalIpAddressUtil;
import com.abc12366.uc.util.wx.SignUtil;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxMchConnectFactory;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lijun <ljun51@outlook.com>
 * @date 2017-09-14 11:24 AM
 * @since 1.0.0
 */
@Service("activityService")
public class ActivityService implements IActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityRoMapper activityRoMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private PointsService pointsService;

    @Override
    public List<WxActivity> selectList(WxActivity activity, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<WxActivity> dataList = activityRoMapper.selectList(activity);
        if (dataList.size() > 0) {
            dataList.stream().filter(wa -> wa.getStatus()).forEach(wa -> {
                // 统计中奖人数、金额
                SentReceived sr = activityRoMapper.selectSentReceivedCount(wa.getId());
                if (sr != null) {
                    wa.setSent(sr.getSent());
                    wa.setSentAmount(sr.getSentAmount());
                    wa.setReceived(sr.getReceived());
                    wa.setReceivedAmount(sr.getReceivedAmount());

                    // 统计参与人数
                    WxLotteryLog lotteryLog = new WxLotteryLog();
                    lotteryLog.setActivityId(wa.getId());
                    wa.setNop(activityRoMapper.selectLotteryLogList(lotteryLog).size());
                }
            });
        }
        return dataList;
    }

    @Override
    public List<ActivityBO> selectSimpleList(int page, int size) {
        return activityRoMapper.selectSimpleList();
    }

    @Override
    public WxActivity selectOne(String id) {
        return activityRoMapper.selectOne(id);
    }

    @Override
    public WxActivity insert(WxActivity activity) {
        checkInput(activity);

        activity.setId(Utils.uuid());
        Date now = new Date();
        activity.setLastUpdate(now);
        activity.setCreateTime(now);
        activityMapper.insert(activity);
        return activity;
    }

    /**
     * 活动输入校验
     */
    private void checkInput(WxActivity activity) {
        // 活动的金额类型为随机金额时，必须指定金额的最小值和最大值
        if (activity.getAmountType().equals("2")) {
            if (activity.getMinAmount() == null || activity.getAmount() == null) {
                throw new ServiceException(6014);
            }
            if (activity.getMinAmount() >= activity.getAmount()) {
                throw new ServiceException(6014);
            }
        }
        // 活动启用赠送积分时，必须输入大于0的积分数值
        if (activity.getGiftPoints()) {
            if (activity.getPoints() == null || activity.getPoints() < 1) {
                throw new ServiceException(6016);
            }
        }
        // 活动启用优惠劵时，必须指定优惠劵活动
        if (activity.getGiftCoupon()) {
            if (StringUtils.isEmpty(activity.getActivityId())) {
                throw new ServiceException(6015);
            }
        }
    }

    @Override
    public WxActivity update(WxActivity activity) {
        checkInput(activity);

        activity.setLastUpdate(new Date());
        activityMapper.update(activity);
        return activity;
    }

    @Override
    public void delete(String id) {
        activityMapper.delete(id);
    }

    /**
     * 生成口令
     */
    @Override
    public WxRedEnvelopBO generateSecret(String activityId, String businessId) {
        WxActivity activity = selectOne(activityId);
        if (activity != null) {
            // 活动是否激活
            if (!activity.getStatus()) {
                throw new ServiceException(6001);
            }
            Date now = new Date();
            // 活动是否在有效期内
            if (now.before(activity.getStartTime()) || now.after(activity.getEndTime())) {
                throw new ServiceException(6002);
            }
            String secret = secretRule(activity.getRuleType(), activity.getRule(), activityId).toLowerCase();
            WxRedEnvelop redEnvelop = new WxRedEnvelop.Builder()
                    .id(Utils.uuid())
                    .secret(secret)
                    .createTime(new Date())
                    .activityId(activity.getId())
                    .url(Constant.WEIXIN_LOTTERY
                            .replace("APPID", SpringCtxHolder.getProperty("abc.appid"))
                            .replace("REDIRECT_URI", SpringCtxHolder.getProperty("abc.redirect_uri"))
                            .replace("STATE", state(secret, activity.getId())))
                    .build();
            if (StringUtils.isNotEmpty(businessId)) {
                WxRedEnvelop wre = new WxRedEnvelop.Builder()
                        .businessId(businessId)
                        .activityId(activityId)
                        .build();
                wre = activityRoMapper.selectRedEnvelopOne(wre);
                if (wre != null) {
                    throw new ServiceException(6012);
                } else {
                    redEnvelop.setBusinessId(businessId);
                }
            }
            activityMapper.generateSecret(redEnvelop);
            WxRedEnvelopBO bo = new WxRedEnvelopBO();
            BeanUtils.copyProperties(redEnvelop, bo);
            return bo;
        }
        return null;
    }

    /**
     * 抽奖
     */
    @Override
    public WxRedEnvelop lottery(WxLotteryBO lotteryBO) {
        WxActivity activity = activityRoMapper.selectOne(lotteryBO.getActivityId());
        if (activity == null) {
            throw new ServiceException(6007);
        }

        LOGGER.info("活动是否启用:{}", activity.getStatus());
        if (!activity.getStatus()) {
            throw new ServiceException(6002);
        }

        Date now = new Date();
        LOGGER.info("活动是否在有效期内");
        if (now.before(activity.getStartTime()) || now.after(activity.getEndTime())) {
            throw new ServiceException(6002);
        }

        WxLotteryLog lotteryLog = new WxLotteryLog.Builder()
                .id(Utils.uuid())
                .activityId(lotteryBO.getActivityId())
                .openId(lotteryBO.getOpenId())
                .secret(lotteryBO.getSecret())
                .build();

        List<WxLotteryLog> lotteryLogs;
        // 规则类型为【口令】时, 查询每人每天可抽奖次数
        if ("1".equals(activity.getRuleType())) {
            lotteryLog.setCreateTime(now);
            lotteryLogs = activityRoMapper.selectLotteryLogList(lotteryLog);
            LOGGER.info("查询今天参与次数,活动:{}, openId:{}, 次数:{}",
                    lotteryBO.getActivityId(), lotteryBO.getOpenId(), lotteryLogs.size());
            if (lotteryLogs.size() >= activity.getTimes()) {
                throw new ServiceException(6006);
            }
        }

        // 规则类型为【关键字】时, 参与活动次数仅为1次
        if ("2".equals(activity.getRuleType())) {
            lotteryLogs = activityRoMapper.selectLotteryLogList(lotteryLog);
            LOGGER.info("查询今天参与次数,活动:{}, openId:{}, 次数:{}",
                    lotteryBO.getActivityId(), lotteryBO.getOpenId(), lotteryLogs.size());
            if (lotteryLogs.size() >= 1) {
                throw new ServiceException(6006);
            }
        }

        LOGGER.info("是否超出红包总数");
        if (isOverRedEnvelopCount(activity.getNum(), lotteryBO.getActivityId())) {
            throw new ServiceException(6005);
        }

        LOGGER.info("记录抽奖日志");
        lotteryLog.setCreateTime(now);
        activityMapper.insertLotteryLog(lotteryLog);

        List<WxRedEnvelop> dataList = selectRedEnvelopList(lotteryBO.getActivityId(), lotteryBO.getSecret().trim()
                .toLowerCase());
        LOGGER.info("红包密码是否正确:{}", dataList.size() > 0);
        if (dataList.size() < 1) {
            throw new ServiceException(6003);
        } else {
            // 用户已参与抽奖
            for (WxRedEnvelop redEnvelop : dataList) {
                if (StringUtils.isNotEmpty(redEnvelop.getOpenId())) {
                    if (lotteryBO.getOpenId().equals(redEnvelop.getOpenId())) {
                        if (StringUtils.isNotEmpty(redEnvelop.getReceiveStatus())
                                && !"NOT_WINNING".equalsIgnoreCase(redEnvelop.getReceiveStatus())
                                && !"UNUSED".equalsIgnoreCase(redEnvelop.getReceiveStatus())) {
                            throw new ServiceException(6017);
                        } else {
                            throw new ServiceException(6011);
                        }
                    } else {
                        throw new ServiceException(6013);
                    }
                } else {
                    // 未参与抽奖用户开始抽奖
                    redEnvelop.setOpenId(lotteryBO.getOpenId());
                    redEnvelop.setUserId(StringUtils.isNotEmpty(lotteryBO.getUserId()) ? lotteryBO.getUserId() : null);
                    lottery(activity, redEnvelop, now);
                    return redEnvelop;
                }
            }
        }
        return null;
    }

    @Override
    public List<WxRedEnvelop> selectRedEnvelopList(WxRedEnvelop redEnvelop, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<WxRedEnvelop> dataList = activityRoMapper.selectRedEnvelopList(redEnvelop);
        for (WxRedEnvelop re : dataList) {
            if (StringUtils.isNotEmpty(re.getOpenId())) {
                User user = new User();
                user.setWxopenid(re.getOpenId());
                user = userService.selectUserById(user);
                if (user != null) {
                    re.setPhone(user.getPhone());
                    re.setWxnickname(user.getWxnickname());
                }
            }
        }
        return dataList;
    }

    @Override
    public WxRedEnvelop gethbinfo(String id) {
        WxRedEnvelop redEnvelop = new WxRedEnvelop.Builder().id(id).build();
        redEnvelop = activityRoMapper.selectRedEnvelopOne(redEnvelop);
        getHbInfo(redEnvelop);
        return redEnvelop;
    }

    @Override
    public WxRedEnvelop gethbinfo(String activityId, String businessId) {
        WxRedEnvelop redEnvelop = new WxRedEnvelop.Builder()
                .activityId(activityId)
                .businessId(businessId)
                .build();
        redEnvelop = activityRoMapper.selectRedEnvelopOne(redEnvelop);
        getHbInfo(redEnvelop);
        return redEnvelop;
    }

    @Override
    public void importJSON(List<WxRedEnvelopBO> redEnvelopList) {
        if (redEnvelopList.size() > 0 && redEnvelopList.size() <= 1000) {
            List<WxRedEnvelop> dataList = new ArrayList<>();
            for (WxRedEnvelopBO redEnvelopBO : redEnvelopList) {

                String url = StringUtils.isNotEmpty(redEnvelopBO.getUrl()) ? redEnvelopBO.getUrl() :
                            Constant.WEIXIN_LOTTERY
                            .replace("APPID", SpringCtxHolder.getProperty("abc.appid"))
                            .replace("REDIRECT_URI", SpringCtxHolder.getProperty("abc.redirect_uri"))
                            .replace("STATE", state(redEnvelopBO.getSecret(), redEnvelopBO.getActivityId()));

                WxRedEnvelop redEnvelop = new WxRedEnvelop.Builder()
                        .id(Utils.uuid())
                        .createTime(new Date())
                        .secret(redEnvelopBO.getSecret())
                        .activityId(redEnvelopBO.getActivityId().trim())
                        .url(url)
                        .build();

                if (StringUtils.isNotEmpty(redEnvelopBO.getBusinessId())) {
                    redEnvelop.setBusinessId(redEnvelopBO.getBusinessId().trim());
                }
                dataList.add(redEnvelop);
            }
            activityMapper.batchGenerateSecret(dataList);
        } else {
            throw new ServiceException(6008);
        }
    }

    @Override
    public WxRedEnvelop send(String id) {
        WxRedEnvelop redEnvelop = new WxRedEnvelop.Builder().id(id).build();
        redEnvelop = activityRoMapper.selectRedEnvelopOne(redEnvelop);
        // 重新发送状态为【0-未发送、2-发送失败】的记录
        if (redEnvelop != null && StringUtils.isNotEmpty(redEnvelop.getBillno())) {
            WxActivity activity = selectOne(redEnvelop.getActivityId());
            SendRedPack srp = new SendRedPack.Builder()
                    .nonce_str(redEnvelop.getId())
                    .mch_billno(redEnvelop.getBillno())
                    .mch_id(SpringCtxHolder.getProperty("abc.mch_id"))
                    .wxappid(SpringCtxHolder.getProperty("abc.appid"))
                    .send_name(SpringCtxHolder.getProperty("abc.send_name"))
                    .re_openid(redEnvelop.getOpenId())
                    .total_amount(yuan2cent(redEnvelop.getSendAmount()).intValue())
                    .total_num(1)
                    .wishing(activity.getWishing())
                    .act_name(activity.getName())
                    .remark(activity.getRemark())
                    .scene_id("PRODUCT_2")
                    .client_ip(LocalIpAddressUtil.resolveLocalAddress() != null ? String.valueOf(LocalIpAddressUtil
                            .resolveLocalAddress()) : "127.0.0.1")
                    .build();
            srp.setSign(SignUtil.signKey(srp));

            ReceiveRedPack rrp = WxMchConnectFactory.post(WechatUrl.SENDREDPACK, null, srp, ReceiveRedPack.class);
            if (rrp != null) {
                // 发送请求成功
                if ("SUCCESS".equals(rrp.getReturn_code())) {
                    // 发红包成功
                    if ("SUCCESS".equals(rrp.getResult_code())) {
                        // 发送成功
                        redEnvelop.setSendStatus("1");
                    } else {
                        // 发送失败
                        redEnvelop.setSendStatus("2");
                    }
                    redEnvelop.setSendTime(new Date());
                    redEnvelop.setRemark(rrp.getErr_code_des());
                    activityMapper.updateRedEnvelop(redEnvelop);
                    if (!"SUCCESS".equals(rrp.getReturn_code())) {
                        throw new ServiceException(rrp.getResult_code(), rrp.getErr_code_des());
                    }
                } else {
                    throw new ServiceException(rrp.getReturn_code(), rrp.getReturn_msg());
                }
            }
        } else {
            throw new ServiceException(6009);
        }
        return redEnvelop;
    }

    @Override
    public void deleteSecret(String id) {
        WxRedEnvelop redEnvelop = new WxRedEnvelop.Builder().id(id).build();
        redEnvelop = activityRoMapper.selectRedEnvelopOne(redEnvelop);
        // 如果接收状态、发送状态都为空值，则为没有抽奖的口令
        if (redEnvelop != null &&
                StringUtils.isEmpty(redEnvelop.getReceiveStatus()) &&
                StringUtils.isEmpty(redEnvelop.getSendStatus())) {
            activityMapper.deleteSecret(id);
        } else {
            throw new ServiceException(6010);
        }
    }

    @Override
    public void batchDeleteSecret(List<Id> ids) {
        for (Id id : ids) {
            deleteSecret(id.getId());
        }
    }

    /**
     * 开始抽奖
     *
     * @param activity   红包活动信息
     * @param redEnvelop 红包信息
     * @param now        同步时间
     */
    private void lottery(WxActivity activity, WxRedEnvelop redEnvelop, Date now) {
        String probabilityStr = activity.getProbability();
        if (probabilityStr.contains("%")) {
            probabilityStr = probabilityStr.replaceAll("%", "");
            Double probability = Double.valueOf(probabilityStr) / 100;
            LOGGER.info("开始抽奖");
            // 中奖
            if (inProbability(probability)) {
                LOGGER.info("中奖:{}", redEnvelop.getSecret());
                redEnvelop.setSendAmount(amountRule(activity.getAmountType(), activity.getMinAmount(), activity
                        .getAmount()));
                // 已中奖未发送
                redEnvelop.setSendStatus("0");
                redEnvelop.setSendTime(now);
                redEnvelop.setStartTime(activity.getStartTime());
                redEnvelop.setEndTime(activity.getEndTime());
                redEnvelop.setMinAmount(activity.getMinAmount());
                redEnvelop.setAmount(activity.getAmount());
                redEnvelop.setAmountType(activity.getAmountType());
                redEnvelop.setProbability(activity.getProbability());
                redEnvelop.setBillno(billno());
                activityMapper.updateRedEnvelop(redEnvelop);

                LOGGER.info("发送微信红包");
                send(redEnvelop.getId());
                processPointsAndCoupon(activity, redEnvelop.getUserId());
            } else { // 未中奖
                LOGGER.info("未中奖:{}", redEnvelop.getSecret());
                redEnvelop.setReceiveStatus("NOT_WINNING");
                redEnvelop.setReceiveTime(now);
                redEnvelop.setStartTime(activity.getStartTime());
                redEnvelop.setEndTime(activity.getEndTime());
                redEnvelop.setMinAmount(activity.getMinAmount());
                redEnvelop.setAmount(activity.getAmount());
                redEnvelop.setAmountType(activity.getAmountType());
                redEnvelop.setProbability(activity.getProbability());
                activityMapper.updateRedEnvelop(redEnvelop);
            }
        } else {
            throw new ServiceException(5000);
        }
    }

    /**
     * 同步微信红包信息
     *
     * @param redEnvelop 红包信息
     */
    private void getHbInfo(WxRedEnvelop redEnvelop) {
        if (redEnvelop != null && StringUtils.isNotEmpty(redEnvelop.getBillno())) {
            GetRedPack grp = new GetRedPack.Builder()
                    .nonce_str(redEnvelop.getId())
                    .mch_billno(redEnvelop.getBillno())
                    .mch_id(SpringCtxHolder.getProperty("abc.mch_id"))
                    .appid(SpringCtxHolder.getProperty("abc.appid"))
                    .bill_type("MCHT")
                    .build();
            grp.setSign(SignUtil.signKey(grp));
            GetRedPackResp rpp = WxMchConnectFactory.post(WechatUrl.GETHBINFO, null, grp, GetRedPackResp.class);
            if (rpp != null) {
                // 发送请求成功
                if ("SUCCESS".equals(rpp.getReturn_code())) {
                    // 发红包成功
                    if ("SUCCESS".equals(rpp.getResult_code())) {
                        if (!"1".equals(redEnvelop.getSendStatus())) {
                            redEnvelop.setSendStatus("1");
                        }
                        redEnvelop.setReceiveStatus(rpp.getStatus());
                        redEnvelop.setReceiveTime(rpp.getRcv_time());
                        redEnvelop.setRemark(rpp.getErr_code_des());
                        activityMapper.updateRedEnvelop(redEnvelop);
                    } else {
                        throw new ServiceException(rpp.getResult_code(), rpp.getErr_code_des());
                    }
                } else {
                    throw new ServiceException(rpp.getReturn_code(), rpp.getReturn_msg());
                }
            }
        }
    }

    /**
     * 查询未抽奖的口令，过滤已中奖、已抽奖的数据
     */
    private List<WxRedEnvelop> selectRedEnvelop(String activityId, String secret) {
        WxRedEnvelop redEnvelop = new WxRedEnvelop.Builder()
                .activityId(activityId)
                .secret(secret)
                .build();
        return activityRoMapper.selectRedEnvelop(redEnvelop);
    }

    /**
     * 查询口令状态
     */
    private List<WxRedEnvelop> selectRedEnvelopList(String activityId, String secret) {
        WxRedEnvelop redEnvelop = new WxRedEnvelop.Builder()
                .activityId(activityId)
                .secret(secret)
                .build();
        return activityRoMapper.selectRedEnvelopList(redEnvelop);
    }

    /**
     * 元转成分
     */
    private Double yuan2cent(Double yuan) {
        return yuan * 100;
    }

    /**
     * 是否超出红包总数
     */
    private boolean isOverRedEnvelopCount(Integer num, String activityId) {
        return num <= activityRoMapper.queryRedEnvelopCount(activityId);
    }

    /**
     * 是否在概率中
     */
    private boolean inProbability(Double probability) {
        // 概率范围
        final int seed = 10000;
        return new Random().nextInt(seed) < seed * probability;
    }

    /**
     * 生成金额
     */
    private Double amountRule(String amountType, Double minAmount, Double maxAmount) {
        // 固定金额
        if ("1".equals(amountType)) {
            return maxAmount;
        } else { // 随机金额
            if (minAmount.intValue() == minAmount && maxAmount.intValue() == maxAmount) {
                return (double) ThreadLocalRandom.current().nextInt(minAmount.intValue(), maxAmount.intValue());
            } else {
                DecimalFormat df = new DecimalFormat("#.##");
                return Double.parseDouble(df.format(ThreadLocalRandom.current().nextDouble(minAmount, maxAmount)));
            }
        }
    }

    /**
     * 口令生成规则
     */
    private String secretRule(String ruleType, String rule, String activityId) {
        if (StringUtils.isNotEmpty(rule)) {
            rule = rule.toLowerCase();
        }
        if ("1".equals(ruleType)) {
            // 规则1口令格式为：大写字母+1至9个数字组合的随机字符串，总共8位长度
            String candidateStr = "abcdefghjkmnpqrstuvwxyz23456789";
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 8; i++) {
                int selectedChar = ThreadLocalRandom.current().nextInt(0, candidateStr.length());
                sb.append(candidateStr.charAt(selectedChar));
            }
            String secret = rule + sb.toString();
            List<WxRedEnvelop> dataList = selectRedEnvelop(activityId, secret);
            return dataList != null && dataList.size() > 0 ? secretRule(ruleType, rule, activityId).toLowerCase() :
                    secret;
        } else {
            // 规则2口令格式为：管理员自主输入的中文字符串，用#符号分割，
            // 如：艾博克#财税平台#爱我中华#美丽中国，只要匹配其中一个词即可
            String[] keywords = rule.split("#");
            return keywords[LocalDateTime.now().getSecond() % keywords.length];
        }
    }

    /**
     * 生成商户订单号
     *
     * @return 商户订单号
     */
    private String billno() {
        return SpringCtxHolder.getProperty("abc.mch_id") + DateUtils.getDataString()
                + activityMapper.selectBillno("wxlottery");
    }

    /**
     * 口令，活动ID编码
     */
    private static String state(String secret, String activityId) {
        try {
            return URLEncoder.encode(Utils.encode(secret + "," + activityId), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
        }
        return Utils.encode(secret + "," + activityId);
    }

    /**
     * 处理领取红包成功后的积分和优惠劵业务
     *
     * @param activity 优惠劵活动
     * @param userId   用户Id
     */
    private void processPointsAndCoupon(WxActivity activity, String userId) {
        LOGGER.info("开始处理领取红包成功后的积分和优惠劵业务userId:{}", userId);
        if (StringUtils.isNotEmpty(userId) && (activity.getGiftPoints() || activity.getGiftCoupon())) {
            UserSimpleInfoBO user = userService.selectSimple(userId);
            if (user != null) {
                LOGGER.info("查询用户信息:{}", userId);
                if (activity.getGiftPoints()) {
                    PointCalculateBO bo = new PointCalculateBO();
                    bo.setUserId(userId);
                    bo.setRuleCode(TaskConstant.POINT_RULE_WXHB_CODE);
                    bo.setRemark("微信红包赠送");
                    bo.setPoints(activity.getPoints());
                    LOGGER.info("处理积分业务:{}", bo);
                    pointsService.calculate(bo);
                }
                if (activity.getGiftCoupon() && StringUtils.isNotEmpty(activity.getActivityId())) {
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                            .getRequest();
                    LOGGER.info("处理优惠劵业务userId:{}, activityId:{}", user.getId(), activity.getActivityId());
                    couponService.userCollectCoupon(user.getId(), activity.getActivityId(), request);
                }
            }
        }
    }
}
