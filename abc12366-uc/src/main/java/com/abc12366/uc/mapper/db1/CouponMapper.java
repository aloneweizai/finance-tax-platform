package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.Coupon;
import com.abc12366.uc.model.order.CouponActivity;
import com.abc12366.uc.model.order.CouponUser;

import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-13 3:06 PM
 * @since 1.0.0
 */
public interface CouponMapper {

    /**
     * 新增优惠劵
     *
     * @param coupon 优惠劵对象
     * @return 影响行数
     */
    int insert(Coupon coupon);

    /**
     * 修改优惠劵
     *
     * @param coupon 优惠劵对象
     * @return 影响行数
     */
    int update(Coupon coupon);

    /**
     * 新增优惠劵活动
     *
     * @param ca 优惠劵活动对象
     * @return 影响行数
     */
    int insertActivity(CouponActivity ca);

    /**
     * 更新优惠劵活动
     *
     * @param ca 优惠劵活动对象
     * @return 影响行数
     */
    int updateActivity(CouponActivity ca);

    /**
     * 新增用户优惠劵
     *
     * @param cu 优惠劵对象
     * @return 影响行数
     */
    int insertUserCoupon(CouponUser cu);

    /**
     * 修改用户优惠劵
     *
     * @param cu 优惠劵对象
     * @return 影响行数
     */
    int updateUserCoupon(CouponUser cu);

    /**
     * 批量修改用户优惠劵
     *
     * @param map orderNo,status,lastUpdate,ids
     * @return 影响行数
     */
    int batchUpdateUserCoupon(Map<String, Object> map);
}
