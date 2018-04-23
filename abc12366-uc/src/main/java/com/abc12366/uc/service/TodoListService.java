package com.abc12366.uc.service;

import java.util.Map;

/**
 * 待办列表服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-10-24 3:51 PM
 * @since 1.0.0
 */
public interface TodoListService {

    /**
     * 查询待办列表数量
     *
     * @return Map
     */
    Map<String, Integer> selectList();

    /**
     * 统计3-付款中，4-付款成功，6-订单完成，7-订单结束，9-已退款，订单总数的数量
     *
     * @return Map
     */
    Map<String, Integer> orderStat();
}
