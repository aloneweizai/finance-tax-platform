package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.Coupon;
import com.abc12366.uc.model.order.CouponActivity;
import com.abc12366.uc.model.order.CouponUser;
import com.abc12366.uc.model.order.bo.*;

import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-13 3:07 PM
 * @since 1.0.0
 */
public interface CouponRoMapper {

    /**
     * 查询优惠劵列表
     *
     * @param bo 查询条件：couponMode,couponType,status
     * @return 优惠劵列表
     */
    List<CouponListBO> selectList(Coupon bo);

    /**
     * 查询优惠劵活动列表
     *
     * @param bo 查询条件：activityName,couponId,status
     * @return 优惠劵列表
     */
    List<CouponActivityListBO> selectActivityList(CouponActivity bo);

    /**
     * 查询用户优惠劵活动列表
     *
     * @param bo userId,activityId,couponId,status,validEndTime,orderNo
     * @return 用户优惠劵活动列表
     */
    List<CouponUserListBO> selectUserList(CouponUser bo);

    /**
     * 查询用户优惠劵活动数量
     *
     * @param bo userId,activityId,status
     * @return 记录数量
     */
    int selectUserCouponCount(CouponUser bo);

    /**
     * 根据ID查看优惠劵
     *
     * @param id 优惠劵ID
     * @return 优惠劵对象
     */
    Coupon selectOne(String id);

    /**
     * 根据ID查看优惠劵活动
     *
     * @param id 活动ID
     * @return 优惠劵活动对象
     */
    CouponActivity selectOneActivity(String id);

    /**
     * 根据ID查询用户优惠劵对象
     *
     * @param id 用户优惠劵主键ID
     * @return 用户优惠劵对象
     */
    CouponUser selectUserCoupon(String id);

    /**
     * 根据优惠劵Ids查询用户优惠劵列表
     *
     * @param map 优惠劵Map
     * @return 用户优惠劵列表
     */
    List<CouponUser> selectUserCouponByIds(Map<String, Object> map);

    /**
     * 获取活动列表
     * @param map
     * @return
     */
    List<CouponActivityBO> selectCouponActivityList(Map<String,Object> map);

    /**
     * 根据订单获取元优惠券
     * @param map map
     * @return 领取的优惠券对象
     */
    CouponUser selectCouponUser(Map<String, Object> map);

    /**
     * 后台查询优惠券活动
     * @param bo 活动对象
     * @return 对象列表
     */
    List<CouponActivityListBO> selectAdminActivityList(Map<String, Object> bo);

    /**
     * 前端-查看优惠劵活动
     * @param id id
     * @return 活动对象
     */
    CouponActivityBO selectActivity(String id);

    /**
     * 前端-查看优惠劵
     * @param id id
     * @return 优惠券
     */
    CouponBO selectCoupon(String id);

    /**
     * 查找领用的优惠券
     * @param useCouponId 领用的ID
     * @return 领用的优惠券对象
     */
    CouponUser selectUserCouponById(String useCouponId);

    /**
     * 查询订单使用的优惠卷
     */
    List<CouponUserListBO> selectUserListByOrderNo(Map<String, Object> map);

    List<CouponUserListBO> selectAdminList(CouponUser bo);
}
