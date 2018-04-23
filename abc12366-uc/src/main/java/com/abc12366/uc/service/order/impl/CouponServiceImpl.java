package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.bo.AppBO;
import com.abc12366.gateway.service.AppService;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.RemindConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.CouponMapper;
import com.abc12366.uc.mapper.db1.OperateMessageMapper;
import com.abc12366.uc.mapper.db1.UserExtendMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.CouponRoMapper;
import com.abc12366.uc.mapper.db2.OperateMessageRoMapper;
import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.UserExtend;
import com.abc12366.uc.model.admin.bo.YyxxLogBO;
import com.abc12366.uc.model.order.Coupon;
import com.abc12366.uc.model.order.CouponActivity;
import com.abc12366.uc.model.order.CouponUser;
import com.abc12366.uc.model.order.bo.*;
import com.abc12366.uc.service.MessageSendUtil;
import com.abc12366.uc.service.order.CouponService;
import com.abc12366.uc.web.order.CartController;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 优惠劵服务实现类
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-13 3:01 PM
 * @since 1.0.0
 */
@Service
public class CouponServiceImpl implements CouponService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
    /**
     * 优惠类型-满减
     */
    public static final String COUPONTYPE_MANJIAN = "MANJIAN";

    /**
     * 优惠类型-折扣
     */
    public static final String COUPONTYPE_ZHEKOU = "ZHEKOU";

    /**
     * 优惠类型-立减
     */
    public static final String COUPONTYPE_LIJIAN = "LIJIAN";

    /**
     * 有效期类型-固定时间段
     */
    public static final String VALIDTYPE_PERIOD = "PERIOD";

    /**
     * 有效期类型-固定天数
     */
    public static final String VALIDTYPE_DAYS = "DAYS";

    /**
     * 优惠劵活动状态-已领取
     */
    public static final String COUPON_STATUS_GET = "1";

    /**
     * 优惠劵活动状态-启用
     */
    public static final String COUPON_STATUS_ON = "2";

    /**
     * 优惠劵活动状态-已冻结
     */
    public static final String COUPON_STATUS_FREEZEN = "3";

    /**
     * 优惠劵活动状态-已删除
     */
    public static final String COUPON_STATUS_DEL = "4";

    /**
     * 优惠劵活动状态-已过期
     */
    public static final String COUPON_STATUS_OUTDATED = "5";

    /**
     * 领取方式-系统
     */
    public static final String COUPON_GETTYPE_SYSTEM = "SYSTEM";

    /**
     * 领取方式-用户
     */
    public static final String COUPON_GETTYPE_USER = "USER";

    /**
     * 发放目标-全部用户
     */
    public static final String TARGET_1 = "1";

    /**
     * 发放目标-部分用户
     */
    public static final String TARGET_2 = "2";

    /**
     * 发放目标-特定用户
     */
    public static final String TARGET_3 = "3";

    /**
     * 优惠劵模式-固定金额
     */
    public static final String COUPON_MODE_FIXED = "FIXED";

    /**
     * 优惠劵模式-浮动金额
     */
    public static final String COUPON_MODE_FLOAT = "FLOAT";

    /**
     * 优惠劵计算金额类型-订单
     */
    public static final String COUPON_AMOUNT_ORDER = "ORDER";

    /**
     * 优惠劵计算金额类型-邮费
     */
    public static final String COUPON_AMOUNT_POSTAGE = "POSTAGE";

    /**
     * 等于
     */
    public static final String OPER_EQUALS = "equals";

    /**
     * 不等于
     */
    public static final String OPER_NE = "ne";

    /**
     * 小于等于
     */
    public static final String OPER_LTE = "lte";

    /**
     * 大于等于
     */
    public static final String OPER_GTE = "gte";

    /**
     * 在...之间
     */
    public static final String OPER_BETWEEN = "between";
    /**
     * 所有金额
     */
    public static final String ALL = "ALL";

    /**
     * 优惠劵CRUD操作
     */
    @Autowired
    private CouponMapper couponMapper;

    /**
     * 优惠劵查询操作
     */
    @Autowired
    private CouponRoMapper couponRoMapper;

    /**
     * 消息操作
     */
    @Autowired
    private OperateMessageMapper operateMessageMapper;

    /**
     * 消息操作
     */
    @Autowired
    private OperateMessageRoMapper operateMessageRoMapper;

    /**
     * 用户Mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 消息发送工具类
     */
    @Autowired
    private MessageSendUtil messageSendUtil;

    /**
     * 用户扩展信息Mapper
     */
    @Autowired
    private UserExtendMapper userExtendMapper;

    /**
     * APP应用Service
     */
    @Autowired
    private AppService appService;

    @Override
    public List<CouponListBO> selectList(Coupon bo, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        return couponRoMapper.selectList(bo);
    }

    @Override
    public List<CouponActivityListBO> selectAdminActivityList(Map<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return couponRoMapper.selectAdminActivityList(map);
    }

    @Override
    public List<CouponActivityListBO> selectActivityList(CouponActivity bo, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CouponActivityListBO> dataList = couponRoMapper.selectActivityList(bo);
//        dataList.stream().filter(data -> COUPON_STATUS_ON.equals(data.getStatus())).forEach(data -> {
//            CouponUser cu = new CouponUser();
//            cu.setActivityId(bo.getId());
//            cu.setCouponId(bo.getCouponId());
//            couponRoMapper.selectUserList(cu).size();
//        });
//        return couponRoMapper.selectActivityList(bo);
        return dataList;
    }

    @Override
    public List<CouponUserListBO> selectUserList(CouponUser bo, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<CouponUserListBO> dataList;
        // 处理status=5的查询
        if (StringUtils.isNotEmpty(bo.getStatus()) && COUPON_STATUS_OUTDATED.equals(bo.getStatus())) {
//            bo.setValidEndTime(Utils);
            bo.setStatus(null);
            dataList = couponRoMapper.selectUserList(bo);
            // 处理过期的status显示
            for (CouponUserListBO data : dataList) {
//            Date now = DateUtils.getToday();
//            boolean outdated = (data.getValidStartTime() != null && now.before(data.getValidStartTime())) ||
//                    (data.getValidEndTime() != null && now.after(data.getValidEndTime()));
//            if (outdated) {
                data.setStatus(COUPON_STATUS_OUTDATED);
//            CouponUser couponUser = new CouponUser();
//            BeanUtils.copyProperties(data,couponUser);
//            LOGGER.info("修改领用优惠券状态");
//            couponMapper.updateUserCoupon(couponUser);
//            }
            }
        }else{
            dataList = couponRoMapper.selectUserList(bo);
        }
        return dataList;
    }

    @Override
    public boolean insert(@Valid CouponBO bo) {
        checkInput(bo);
        Coupon coupon = new Coupon();
        Date now = new Date();

        BeanUtils.copyProperties(bo, coupon);
        coupon.setId(Utils.uuid());
        coupon.setCategoryIds(bo.getCategoryIds());
        coupon.setCreateTime(now);
        coupon.setLastUpdate(now);
        return 1 == couponMapper.insert(coupon);
    }

    @Override
    public boolean update(@Valid CouponBO bo) {
        Assert.notNull(bo.getId(), "id can not empty");
        checkInput(bo);
        // 当优惠劵在活动中被使用时，不允许修改优惠劵
        isAllowUpdateCoupon(bo.getId());

        Coupon coupon = selectOne(bo.getId());
        coupon.setCouponName(bo.getCouponName());
        coupon.setCouponMode(bo.getCouponMode());
        coupon.setCouponType(bo.getCouponType());
        coupon.setParam1(bo.getParam1());

        coupon.setParam2(bo.getParam2());
        coupon.setParam3(bo.getParam3());
        coupon.setAmountType(bo.getAmountType());
        coupon.setValidType(bo.getValidType());
        coupon.setValidStartTime(bo.getValidStartTime());
        coupon.setValidEndTime(bo.getValidEndTime());

        coupon.setValidDays(bo.getValidDays());
        coupon.setDescription(bo.getDescription());
        coupon.setStatus(bo.getStatus());
        coupon.setLastUpdate(new Date());
        coupon.setCategoryIds(bo.getCategoryIds());

        return 1 == couponMapper.update(coupon);
    }

    @Override
    public Coupon selectOne(String id) {
        Assert.notNull(id, "id can not empty");
        return couponRoMapper.selectOne(id);
    }

    @Override
    public boolean delete(String id) {
        Assert.notNull(id, "id can not empty");

        // 当优惠劵在活动中被使用时，不允许操作优惠劵
        isAllowUpdateCoupon(id);

        Coupon coupon = selectOne(id);
        coupon.setStatus("0");
        coupon.setLastUpdate(new Date());
        return 1 == couponMapper.update(coupon);
    }

    @Override
    public boolean insertActivity(@Valid CouponActivityBO bo) {
        checkInput(bo);

        CouponActivity ca = new CouponActivity();
        ca.setId(Utils.uuid());
        ca.setActivityName(bo.getActivityName());
        ca.setActivityLink(bo.getActivityLink());
        ca.setCouponId(bo.getCouponId());
        ca.setActivityStartTime(bo.getActivityStartTime());

        ca.setActivityEndTime(bo.getActivityEndTime());
        ca.setCouponNum(bo.getCouponNum());
        ca.setLimit(bo.getLimit());
        ca.setLimitNum(bo.getLimitNum());
        ca.setGetType(bo.getGetType());

        ca.setValid(bo.getValid());
        ca.setValidApi(bo.getValidApi());
        ca.setTarget(bo.getTarget());
        ca.setAreaOper(bo.getAreaOper());
        ca.setAreaIds(bo.getAreaIds());

        ca.setTagOper(bo.getTagOper());
        ca.setTagIds(bo.getTagIds());
        ca.setRegTimeOper(bo.getRegTimeOper());
        ca.setRegStartTime(bo.getRegStartTime());
        ca.setRegEndTime(bo.getRegEndTime());

        ca.setVips(bo.getVips());
        ca.setUserIds(bo.getUserIds());
        ca.setDescription(bo.getDescription());
        ca.setImageUrl(bo.getImageUrl());
        ca.setStatus(bo.getStatus());

        Date now = new Date();
        ca.setCreateTime(now);
        ca.setLastUpdate(now);
        return 1 == couponMapper.insertActivity(ca);
    }

    @Override
    public boolean updateActivity(@Valid CouponActivityBO bo) {
        Assert.notNull(bo.getId(), "id can not empty");
        checkInput(bo);

        CouponActivity ca = selectOneActivity(bo.getId());
        ca.setActivityName(bo.getActivityName());
        ca.setActivityLink(bo.getActivityLink());
        ca.setCouponId(bo.getCouponId());
        ca.setActivityStartTime(bo.getActivityStartTime());

        ca.setActivityEndTime(bo.getActivityEndTime());
        ca.setCouponNum(bo.getCouponNum());
        ca.setLimit(bo.getLimit());
        ca.setLimitNum(bo.getLimitNum());
        ca.setGetType(bo.getGetType());

        ca.setValid(bo.getValid());
        ca.setValidApi(bo.getValidApi());
        ca.setTarget(bo.getTarget());
        ca.setAreaOper(bo.getAreaOper());
        ca.setAreaIds(bo.getAreaIds());

        ca.setTagOper(bo.getTagOper());
        ca.setTagIds(bo.getTagIds());
        ca.setRegTimeOper(bo.getRegTimeOper());
        ca.setRegStartTime(bo.getRegStartTime());
        ca.setRegEndTime(bo.getRegEndTime());

        ca.setVips(bo.getVips());
        ca.setUserIds(bo.getUserIds());
        ca.setDescription(bo.getDescription());
        ca.setImageUrl(bo.getImageUrl());
        ca.setStatus(bo.getStatus());

        ca.setLastUpdate(new Date());
        return 1 == couponMapper.updateActivity(ca);
    }


    @Override
    public CouponActivity selectOneActivity(String id) {
        Assert.notNull(id, "id can not empty");
        return couponRoMapper.selectOneActivity(id);
    }

    @Override
    public boolean deleteActivity(String id) {
        Assert.notNull(id, "id can not empty");
        CouponActivity ca = selectOneActivity(id);
        ca.setStatus("0");
        ca.setLastUpdate(new Date());
        return 1 == couponMapper.updateActivity(ca);
    }

    //生成min->max之间的数,最小生成的随机数为min，最大生成的随机数不能超过max
    public static double getRandomNum(double min, double max) {
        //return Math.round(Math.random() * (max - min)) + min;
        return getTwoDouble(max - Math.random() * (max - min));
    }

    @Override
    public boolean userCollectCoupon(String userId, String activityId, HttpServletRequest request) {
        Assert.notNull(userId, "userId can not empty");
        Assert.notNull(activityId, "activityId can not empty");
        CouponActivity ca = selectOneActivity(activityId);
        if (ca != null) {
            // 是否可以领取
            isAllowCollect(userId, ca);
            Coupon c = selectOne(ca.getCouponId());
            if (c != null) {
                CouponUser cu = new CouponUser();
                Date now = new Date();
                cu.setId(Utils.uuid());
                cu.setUserId(userId);
                cu.setActivityId(activityId);
                cu.setCouponId(ca.getCouponId());
                cu.setCouponName(c.getCouponName());

                cu.setCouponMode(c.getCouponMode());
                cu.setCouponType(c.getCouponType());
                cu.setParam1(c.getParam1());
                LOGGER.info("判断优惠劵模式-固定金额或浮动金额");
                if (StringUtils.isNotEmpty(c.getCouponMode()) && COUPON_MODE_FIXED.equals(c.getCouponMode())) {
                    cu.setParam2(c.getParam2());
                } else if (StringUtils.isNotEmpty(c.getCouponMode()) && COUPON_MODE_FLOAT.equals(c.getCouponMode())) {
                    cu.setParam2(getRandomNum(c.getParam2(), c.getParam3()));
                }
                cu.setAmountType(c.getAmountType());

                if (VALIDTYPE_PERIOD.equalsIgnoreCase(c.getValidType())) {
                    cu.setValidStartTime(c.getValidStartTime());
                    cu.setValidEndTime(c.getValidEndTime());
                } else {
                    cu.setValidStartTime(now);
                    cu.setValidEndTime(DateUtils.addDays(now, c.getValidDays()));
                }
                cu.setDescription(c.getDescription());
                cu.setStatus("1");
                cu.setCreateTime(now);

                cu.setLastUpdate(now);
                cu.setCategoryIds(c.getCategoryIds());

                int cInsert = couponMapper.insertUserCoupon(cu);
                if (cInsert != 1) {
                    LOGGER.info("用户领用优惠卷失败");
                    throw new ServiceException(7137);
                }

                LOGGER.info("给用户推送站内消息");
                send(userId, ca.getCouponId(), request);
                return true;
            }
        }
        return false;
    }

    @Override
    public double userUseCoupon(@Valid CouponOrderBO bo) {
        CouponCalculateBO cc = new CouponCalculateBO();
        cc.setUserId(bo.getUserId());
        cc.setAmount(bo.getAmount());
        cc.setCategoryId(bo.getCategoryId());
        cc.setUseCouponId(bo.getUseCouponId());
        //1：提交订单，0：取消订单
        if("1".equals(bo.getOperation())){
            double amountAfter = calculateOrderAmount(cc);
            if (amountAfter < bo.getAmount()) {
                Map<String, Object> map = new HashMap<>(16);
                map.put("orderNo", COUPON_STATUS_GET.equals(bo.getStatus()) ? "" : bo.getOrderNo());
                map.put("status", bo.getStatus());
                map.put("lastUpdate", new Date());
                map.put("id", bo.getUseCouponId());
                map.put("amountAfter", getTwoDouble(bo.getAmount()-amountAfter));
                couponMapper.batchUpdateUserCoupon(map);
            }
            BigDecimal b = new BigDecimal(amountAfter);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }else{
            Map<String, Object> map = new HashMap<>(16);
            map.put("orderNo", COUPON_STATUS_GET.equals(bo.getStatus()) ? "" : bo.getOrderNo());
            map.put("status", bo.getStatus());
            map.put("lastUpdate", new Date());
            map.put("id", bo.getUseCouponId());
            couponMapper.batchUpdateUserCoupon(map);
            return 0;
        }
    }

    @Override
    public double calculateOrderAmount(@Valid CouponCalculateBO bo) {
        LOGGER.info("{}", bo);
        // 优惠前的金额
        double amount = bo.getAmount();
        // 优惠后的金额
        double amountAfter = amount;

//        String id = bo.getUseCouponId();
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", id);
//        map.put("userId", bo.getUserId());
        CouponUser cu = couponRoMapper.selectUserCouponById(bo.getUseCouponId());
        if(cu == null){
            LOGGER.info("查询已经领用的优惠券失败");
            throw new ServiceException(7139);
        }
        checkCouponUser(bo.getUserId(), bo.getCategoryId(), cu);
        // 计算优惠后的金额
        switch (cu.getCouponType()) {
            case COUPONTYPE_MANJIAN:
                if (amount >= cu.getParam1()) {
                    amountAfter = getTwoDouble(amountAfter) - getTwoDouble(cu.getParam2());
                }else {
                    throw new ServiceException(7140);
                }
                break;
            case COUPONTYPE_ZHEKOU:
                if (amount >= cu.getParam1() || cu.getParam1() == 0) {//满0元就打折
                    //四舍五入保留两位小数
                    amountAfter = getTwoDouble(amountAfter * cu.getParam2());
                }else {
                    throw new ServiceException(7141);
                }
                break;
            case COUPONTYPE_LIJIAN:
                if (amount > cu.getParam2()) {
                    amountAfter = amountAfter - cu.getParam2();
                } else {
                    throw new ServiceException(7133);
                }
                break;
            default:
                throw new ServiceException(7102);
        }
        if(amountAfter <= 0){
            LOGGER.info("惠后的金额必须大于0元");
            throw new ServiceException(7144);
        }
        LOGGER.info("{}", amountAfter);
        return getTwoDouble(amountAfter);
    }

    //获取两位随机数
    public static double getTwoDouble(double value){
        BigDecimal b = new BigDecimal(value);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void checkCouponUser(String userId, String categoryId, CouponUser cu) {
        // 校验用户
        if (!cu.getUserId().equals(userId)) {
            throw new ServiceException(7118);
        }
        // 校验计算类型
        if (!COUPON_AMOUNT_ORDER.equalsIgnoreCase(cu.getAmountType())) {
            throw new ServiceException(7131);
        }
        // 校验商品品目
        if (!cu.getCategoryIds().contains(categoryId) && !cu.getCategoryIds().contains(ALL)) {
            throw new ServiceException(7119);
        }
        // 校验优惠劵有效期
        Date now = DateUtils.getToday();
        if (now.before(cu.getValidStartTime()) || now.after(cu.getValidEndTime())) {
            throw new ServiceException(7120);
        }
        // 校验优惠劵状态
        if (!COUPON_STATUS_GET.equals(cu.getStatus())) {
            throw new ServiceException(7121);
        }
    }

    @Override
    public boolean userDeleteCoupon(CouponUser bo) {
        Assert.notNull(bo.getId(), "map can not empty");
        CouponUser cu = couponRoMapper.selectUserCoupon(bo.getId());
        if (cu != null) {
            if (!cu.getUserId().equals(bo.getUserId())) {
                throw new ServiceException(7118);
            }
            if (COUPON_STATUS_ON.equals(cu.getStatus()) || COUPON_STATUS_FREEZEN.equals(cu.getStatus())) {
                throw new ServiceException(7121);
            }
            cu.setStatus(COUPON_STATUS_DEL);
            cu.setLastUpdate(new Date());
            return 1 == couponMapper.updateUserCoupon(cu);
        }
        return false;
    }

    @Override
    public CouponUser selectCouponUser(Map<String, Object> map) {
        return couponRoMapper.selectCouponUser(map);
    }

    @Override
    public String selectCouponId(CouponIdBO bo) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", bo.getUserId());
        List<CouponUser> dataList = couponRoMapper.selectUserCouponByIds(map);
        // 优惠前的金额
        double maxAmount = bo.getAmount();
        String id = "";
        if (dataList.size() > 0) {
            for (CouponUser cu : dataList) {
                // 优惠前的金额
                double amount = bo.getAmount();
                // 优惠后的金额
                double amountAfter = amount;
                checkCouponUser(bo.getUserId(), bo.getCategoryId(), cu);
                // 计算优惠后的金额
                switch (cu.getCouponType()) {
                    case COUPONTYPE_MANJIAN:
                        if (amount >= cu.getParam1()) {
                            amountAfter = amountAfter - cu.getParam2();
                        }
                        break;
                    case COUPONTYPE_ZHEKOU:
                        if (amount >= cu.getParam1() || cu.getParam1() == 0) {//满0元就打折
                            amountAfter = amountAfter * cu.getParam2();
                        }
                        break;
                    case COUPONTYPE_LIJIAN:
                        if (amount > cu.getParam2()) {
                            amountAfter = amountAfter - cu.getParam2();
                        } else {
                            throw new ServiceException(7133);
                        }
                        break;
                    default:
                        throw new ServiceException(7102);
                }
                if (amountAfter < maxAmount) {
                    id = cu.getCouponId();
                    maxAmount = amountAfter;
                }
            }
        }

        return id;
    }

    @Override
    public CouponActivityBO selectActivity(String id) {
        return couponRoMapper.selectActivity(id);
    }

    @Override
    public CouponBO selectCoupon(String id) {
        return couponRoMapper.selectCoupon(id);
    }

    @Override
    public List<CouponUserListBO> selectUserListByOrderNo(Map<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return couponRoMapper.selectUserListByOrderNo(map);
    }

    @Override
    public List<CouponUserListBO> selectAdminList(CouponUser bo, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return couponRoMapper.selectAdminList(bo);
    }

    /**
     * 是否允许用户领取优惠劵
     *
     * @param userId 用户ID
     * @param ca     优惠劵活动对象
     */
    private void isAllowCollect(String userId, CouponActivity ca) {
        LOGGER.info("活动状态判断:{}", ca.getId());
        if (!COUPON_STATUS_ON.equals(ca.getStatus())) {
            throw new ServiceException(7108);
        }
        LOGGER.info("活动有效期判断");
        Date now = DateUtils.getToday();
        if (now.before(ca.getActivityStartTime()) || now.after(ca.getActivityEndTime())) {
            throw new ServiceException(7120);
        }
        // 目标人群：1-全部用户，2-部分用户，3-特定用户
        if("2".equals(ca.getTarget())){
            CouponActivityBO bo = new CouponActivityBO();
            BeanUtils.copyProperties(ca,bo);
            User user = userMapper.selectOne(userId);
            UserExtend userExtend = userExtendMapper.selectOne(userId);
            List<String> tagIdList = operateMessageRoMapper.selectTagIdList(userId);
            if(!isPartUser(bo, user, userExtend, tagIdList)){
                LOGGER.info("抱歉，您还不满足领券条件");
                throw new ServiceException(7142);
            }
        }else if("3".equals(ca.getTarget())){
            if(!ca.getUserIds().contains(userId)){
                LOGGER.info("抱歉，您还不满足领券条件");
                throw new ServiceException(7143);
            }
        }
        CouponUser cu = new CouponUser();
        cu.setActivityId(ca.getId());
        int i = couponRoMapper.selectUserCouponCount(cu);
        LOGGER.info("活动优惠劵数量限制:{}", i);
        if (i >= ca.getCouponNum()) {
            throw new ServiceException(7110);
        }

        if (ca.getLimit()) {
            cu.setUserId(userId);
            i = couponRoMapper.selectUserCouponCount(cu);
            LOGGER.info("用户领取限制:{}", i);
            if (i >= ca.getLimitNum()) {
                throw new ServiceException(7111);
            }
        }
    }

    /**
     * 校验用户输入
     *
     * @param bo 优惠劵对象
     */
    private void checkInput(CouponBO bo) {
        // 优惠劵模式校验
        if (!COUPON_MODE_FIXED.equalsIgnoreCase(bo.getCouponMode()) &&
                !COUPON_MODE_FLOAT.equalsIgnoreCase(bo.getCouponMode())) {
            throw new ServiceException(7122);
        }

        // 优惠劵类型校验
        boolean couponType = StringUtils.isNotEmpty(bo.getCouponType()) &&
                (COUPONTYPE_MANJIAN.equalsIgnoreCase(bo.getCouponType()) ||
                        COUPONTYPE_ZHEKOU.equalsIgnoreCase(bo.getCouponType()) ||
                        COUPONTYPE_LIJIAN.equalsIgnoreCase(bo.getCouponType()));
        // 优惠劵类型校验
        if (couponType) {
            if (COUPONTYPE_MANJIAN.equalsIgnoreCase(bo.getCouponType()) ||
                    COUPONTYPE_ZHEKOU.equalsIgnoreCase(bo.getCouponType())) {
                if (bo.getParam1() == null || bo.getParam2() == null) {
                    throw new ServiceException(7100);
                }
            }
            if (COUPONTYPE_ZHEKOU.equalsIgnoreCase(bo.getCouponType())) {
                if (0 >= bo.getParam2() || bo.getParam2() >= 1) {
                    throw new ServiceException(7132);
                }
            }
            if (COUPONTYPE_LIJIAN.equalsIgnoreCase(bo.getCouponType())) {
                if (bo.getParam2() == null) {
                    throw new ServiceException(7101);
                }
            }
        } else {
            throw new ServiceException(7102);
        }

        // 计算金额类型校验
        if (!COUPON_AMOUNT_ORDER.equalsIgnoreCase(bo.getAmountType()) &&
                !COUPON_AMOUNT_POSTAGE.equalsIgnoreCase(bo.getAmountType())) {
            throw new ServiceException(7123);
        }

        // 有效期类型校验
        boolean validType = StringUtils.isNotEmpty(bo.getValidType()) &&
                (VALIDTYPE_PERIOD.equalsIgnoreCase(bo.getValidType())
                        || VALIDTYPE_DAYS.equalsIgnoreCase(bo.getValidType()));
        if (validType) {
            // 有效期起止校验
            if (VALIDTYPE_PERIOD.equalsIgnoreCase(bo.getValidType())) {
                if (bo.getValidStartTime() == null || bo.getValidEndTime() == null) {
                    throw new ServiceException(7104);
                }
                if (bo.getValidStartTime().after(bo.getValidEndTime())) {
                    throw new ServiceException(7130);
                }
            }
            // 有效期天数校验
            if (VALIDTYPE_DAYS.equalsIgnoreCase(bo.getValidType())) {
                if (bo.getValidDays() == null) {
                    throw new ServiceException(7105);
                }
            }
        } else {
            throw new ServiceException(7103);
        }
    }

    /**
     * 校验用户输入
     *
     * @param bo 优惠劵活动对象
     */
    private void checkInput(CouponActivityBO bo) {
        // 领取限制校验
        if (bo.getLimit()) {
            if (bo.getLimitNum() == null) {
                throw new ServiceException(7112);
            }
        }
        // 领取方式校验
        if (!COUPON_GETTYPE_SYSTEM.equalsIgnoreCase(bo.getGetType()) &&
                !COUPON_GETTYPE_USER.equalsIgnoreCase(bo.getGetType())) {
            throw new ServiceException(7113);
        }
        // 外部接口校验
        if (bo.getValid() && StringUtils.isEmpty(bo.getValidApi())) {
            throw new ServiceException(7114);
        }
        // 发放目标校验
        if (TARGET_1.equals(bo.getTarget()) || TARGET_2.equals(bo.getTarget()) || TARGET_3.equals(bo.getTarget())) {
            if (TARGET_2.equals(bo.getTarget())) {
                if (bo.getAreaIds() == null &&
                        bo.getTagIds() == null &&
                        bo.getRegStartTime() == null &&
                        bo.getRegEndTime() == null &&
                        bo.getVips() == null) {
                    throw new ServiceException(7116);
                }
                // 操作符校验
                if (StringUtils.isNotEmpty(bo.getAreaOper()) &&
                        !OPER_EQUALS.equalsIgnoreCase(bo.getAreaOper()) &&
                        !OPER_NE.equalsIgnoreCase(bo.getAreaOper())) {
                    throw new ServiceException(7126);
                }
                if (StringUtils.isNotEmpty(bo.getTagOper()) &&
                        !OPER_EQUALS.equalsIgnoreCase(bo.getTagOper()) &&
                        !OPER_NE.equalsIgnoreCase(bo.getTagOper())) {
                    throw new ServiceException(7127);
                }
                if (StringUtils.isNotEmpty(bo.getRegTimeOper()) &&
                        !OPER_LTE.equalsIgnoreCase(bo.getRegTimeOper()) &&
                        !OPER_GTE.equalsIgnoreCase(bo.getRegTimeOper()) &&
                        !OPER_BETWEEN.equalsIgnoreCase(bo.getRegTimeOper())) {
                    throw new ServiceException(7128);
                }
                // 注册时间校验
                if (bo.getRegStartTime() != null && bo.getRegEndTime() != null &&
                        bo.getRegEndTime().before(bo.getRegStartTime())) {
                    throw new ServiceException(7129);
                }
            }
            if (TARGET_3.equals(bo.getTarget())) {
                if (bo.getUserIds() == null || "".equals(bo.getUserIds())) {
                    throw new ServiceException(7115);
                }
            }
        } else {
            throw new ServiceException(7124);
        }

        Coupon c = selectOne(bo.getCouponId());
        if (c == null) {
            throw new ServiceException(7125);
        }
        // 如果活动启用，请确保优惠劵已启用
        if (COUPON_STATUS_ON.equals(bo.getStatus()) && !COUPON_STATUS_ON.equals(c.getStatus())) {
            throw new ServiceException(7107);
        }
        // 活动有效期限制校验
        /*if (bo.getActivityStartTime().after(c.getValidStartTime()) ||
                bo.getActivityEndTime().before(c.getValidEndTime()) ||
                bo.getActivityEndTime().before(bo.getActivityEndTime())) {；



            throw new ServiceException(7117);
        }*/
        if (!VALIDTYPE_DAYS.equals(c.getValidType())) {
//            if (c.getValidStartTime().after(c.getValidEndTime())) {
//                LOGGER.info("优惠卷的开始时间不能大于结束时间");
//                throw new ServiceException(7145);
//            }
            if (bo.getActivityStartTime().after(c.getValidStartTime()) || c.getValidStartTime().after(bo.getActivityEndTime())) {
                LOGGER.info("优惠劵的开始时间，必须在活动时间之间");
                throw new ServiceException(7136);
            }
        }
    }

    /**
     * 当优惠劵在活动中被使用时，不允许修改优惠劵
     *
     * @param couponId 优惠劵ID
     */
    private void isAllowUpdateCoupon(String couponId) {
        CouponActivity ca = new CouponActivity();
        ca.setCouponId(couponId);
        ca.setStatus(COUPON_STATUS_ON);
        if (couponRoMapper.selectActivityList(ca).size() != 0) {
            throw new ServiceException(7106);
        }
    }

    public void send(String userId, String couponId, HttpServletRequest request) {
        User user = userMapper.selectOne(userId);
        UserExtend userExtend = userExtendMapper.selectOne(userId);
        List<String> tagIdList = operateMessageRoMapper.selectTagIdList(userId);
        if (user == null) {
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("couponId", couponId);
        map.put("status", COUPON_STATUS_ON);
        List<CouponActivityBO> operateMessageBOList = couponRoMapper.selectCouponActivityList(map);
        for (CouponActivityBO o : operateMessageBOList) {
            //判断运营消息任务的有效性
            if (org.springframework.util.StringUtils.isEmpty(o.getStatus()) || !o.getStatus().equals("1") ||
                    o.getActivityStartTime() == null || o.getActivityEndTime() == null ||
                    o.getActivityStartTime().after(new Date()) || o.getActivityEndTime().before(new Date())) {
                continue;
            }
            //判断用户是否在消息发送范围内
            //目标人群：1-全部用户，2-部分用户，3-特定用户
            if (!org.springframework.util.StringUtils.isEmpty(o.getTarget())) {
                switch (o.getTarget()) {
                    case "1":
                        //根据运营消息频率查看是否已发送
                        sendYyxx(o, user, request);
                        break;
                    case "2":
                        sendPart(o, user, userExtend, tagIdList, request);
                        break;
                    case "3":
                        if (org.springframework.util.StringUtils.isEmpty(o.getUserIds())) {
                            break;
                        }
                        if (o.getUserIds().contains(userId)) {
                            sendYyxx(o, user, request);
                            break;
                        }
                }
            }
        }
    }

    public void sendYyxx(CouponActivityBO o, User user, HttpServletRequest request) {
        //获取运营管理系统accessToken
        AppBO appBO = appService.selectByName("abc12366-admin");
        Date lastRest = appBO.getLastResetTokenTime();
        if (lastRest.before(new Date())) {
            appBO.setLastResetTokenTime(org.apache.commons.lang3.time.DateUtils.addHours(new Date(), 2));
            appService.update(appBO);
        }
        //系统消息
        //系统消息日志
        Message message = new Message();
        message.setBusinessId(o.getId());
        message.setBusiType(MessageConstant.YYXX_BUSITYPE);
        message.setType(MessageConstant.SYS_MESSAGE);
        message.setContent(RemindConstant.COUPON_YYXX.replaceAll("\\{#DATA.COUPON\\}", o.getActivityName()));
        message.setUserId(user.getId());
        //message.setUrl(o.getUrl());
        messageSendUtil.sendMessage(message, request);
        operateMessageMapper.yyxxLog(new YyxxLogBO(Utils.uuid(), user.getId(), o.getId(), MessageConstant.YYXX_WEB, new Date()));
    }

    public boolean tagIdContains(List<String> tagIdList, String tagIds) {
        if (tagIds == null || tagIds.split(",").length < 1) {
            return true;
        }
        if (tagIdList == null || tagIdList.size() < 1) {
            return false;

        }
        String[] tagArrays = tagIds.split(",");
        for (int i = 0; i < tagIdList.size(); i++) {
            for (String tagArray : tagArrays) {
                if (tagIdList.get(i).trim().equals(tagArray.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void sendPart(CouponActivityBO o, User user, UserExtend userExtend, List<String> tagIdList, HttpServletRequest request) {

        if (isPartUser(o, user, userExtend, tagIdList)) {
            sendYyxx(o, user, request);
        }
    }

    /**
     * 判断是否满足部分用户
     */
    public boolean isPartUser(CouponActivityBO o, User user, UserExtend userExtend, List<String> tagIdList) {
        boolean sendArea = false;
        boolean sendTag = false;
        boolean sendRegTime = false;
        boolean vip = false;
        //地域
        if (!org.springframework.util.StringUtils.isEmpty(o.getAreaOper()) && !org.springframework.util.StringUtils.isEmpty(o.getAreaIds())) {
            //地域限制
            if (o.getAreaOper().trim().equals("equals") && userExtend != null) {
                if ((!org.springframework.util.StringUtils.isEmpty(userExtend.getProvince()) && o.getAreaIds().contains(userExtend.getProvince()))
                        || (!org.springframework.util.StringUtils.isEmpty(userExtend.getCity()) && o.getAreaIds().contains(userExtend.getCity()))
                        || (!org.springframework.util.StringUtils.isEmpty(userExtend.getArea()) && o.getAreaIds().contains(userExtend.getArea()))) {
                    sendArea = true;
                }
                //地域排除
            } else if (o.getAreaOper().trim().equals("ne")) {
                if (userExtend == null || (((org.springframework.util.StringUtils.isEmpty(userExtend.getProvince())) || (!o.getAreaIds().contains(userExtend.getProvince()))) &&
                        ((org.springframework.util.StringUtils.isEmpty(userExtend.getCity())) || (!o.getAreaIds().contains(userExtend.getCity()))) &&
                        ((org.springframework.util.StringUtils.isEmpty(userExtend.getArea())) || (!o.getAreaIds().contains(userExtend.getArea()))))) {
                    sendArea = true;
                }
            }
        } else {
            sendArea = true;
        }
        //标签
        if (!org.springframework.util.StringUtils.isEmpty(o.getTagOper()) && !org.springframework.util.StringUtils.isEmpty(o.getTagIds())) {
            //标签限制
            if (o.getTagOper().trim().equals("equals")) {
                if (tagIdContains(tagIdList, o.getTagIds())) {
                    sendTag = true;
                }
                //标签排除
            } else if (o.getTagOper().trim().equals("ne")) {
                if (!tagIdContains(tagIdList, o.getTagIds())) {
                    sendTag = true;
                }
            }
        } else {
            sendTag = true;
        }
        //注册时间
        if (!org.springframework.util.StringUtils.isEmpty(o.getRegTimeOper())) {
            if (o.getRegTimeOper().trim().equals("lte") && o.getRegEndTime() != null) {
                if (user.getCreateTime() != null && user.getCreateTime().getTime() <= o.getRegEndTime().getTime()) {
                    sendRegTime = true;
                }
            }
            if (o.getRegTimeOper().trim().equals("gte") && o.getRegStartTime() != null) {
                if (user.getCreateTime() != null && user.getCreateTime().getTime() >= o.getRegStartTime().getTime()) {
                    sendRegTime = true;
                }
            }
        } else {
            sendRegTime = true;
        }
        //用户类型
        if (StringUtils.isNotEmpty(o.getVips()) && o.getVips().contains(user.getVipLevel())) {
            vip = true;
        }
        if (sendArea && sendTag && sendRegTime && vip) {
            return true;
        }
        return false;
    }

}
