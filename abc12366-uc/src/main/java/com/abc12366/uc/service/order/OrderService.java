package com.abc12366.uc.service.order;

import com.abc12366.uc.model.order.Order;
import com.abc12366.uc.model.order.bo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by lizhongwei on 2017-10-19.
 */
public interface OrderService {

    /**
     * 查询订单列表信息
     *
     * @param orderBO  订单对象
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 订单列表
     */
    List<OrderBO> selectList(OrderBO orderBO, int pageNum, int pageSize);

    /**
     * 查询订单信息
     *
     * @param orderNo 订单编号
     * @return 订单对象
     */
    OrderBO selectByOrderNo(String orderNo);

    /**
     * 加入购物车
     *
     * @param orderBO 订单对象
     * @return 订单对象
     */
    OrderBO joinCart(OrderBO orderBO);

    /**
     * 修改购物车
     *
     * @param orderBO 订单对象
     * @return 订单对象
     */
    OrderBO updateCart(OrderBO orderBO);

    /**
     * 查询列表
     *
     * @param order    订单编号
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 订单列表
     */
    List<OrderBO> selectOrderList(OrderBO order, int pageNum, int pageSize);

    /**
     * 删除购物车
     *
     * @param orderBO 订单对象
     */
    void deleteCart(OrderBO orderBO);

    /**
     * 用户直接提交订单
     *
     * @param orderSubmitBO 订单对象
     * @return 订单对象
     */
    OrderBO submitOrder(OrderSubmitBO orderSubmitBO);

    /**
     * 查询购物车列表
     *
     * @param order 订单对象
     * @return 订单列表
     */
    List<OrderBO> selectCartList(OrderBO order);

    /**
     * 购物车中提交订单
     *
     * @param order 订单对象
     */
    void submitCart(Order order);

    /**
     * 删除订单
     *
     * @param orderBO 订单编号
     */
    void deleteOrder(OrderBO orderBO);

    /**
     * 取消订单
     *
     * @param orderCancelBO 订单取消对象
     * @return 订单对象
     */
    OrderBO cancelOrder(OrderCancelBO orderCancelBO);

    /**
     * 前台用户，查询所有订单
     *
     * @param order    订单对象
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 订单列表
     */
    List<OrderBO> selectUserAllOrderList(OrderBO order, int pageNum, int pageSize);

    /**
     * 修改支付状态
     *
     * @param orderPayBO 订单支付对象
     * @param type 类型
     */
    void paymentOrder(OrderPayBO orderPayBO, String type, HttpServletRequest request);

    /**
     * 查询导出订单
     *
     * @param order 订单导出对象
     * @return 订单导出列表
     */
    List<OrderListBO> selectExprotOrder(Order order);

    /**
     * 查询导入订单
     *
     * @param orderBOList   订单导出对象
     * @param expressCompId 物流公司ID
     */
    void selectImportOrder(List<OrderBO> orderBOList, String expressCompId, HttpServletRequest request);

    /**
     * 订单发货
     * @param orderOperationBO 订单操作对象
     */
    void sendOrder(OrderOperationBO orderOperationBO, HttpServletRequest request);

    /**
     * 订单作废
     *
     * @param orderOperationBO 订单操作对象
     */
    void invalidOrder(OrderOperationBO orderOperationBO);

    /**
     * 自动确认收货
     */
    void automaticReceipt();

    /**
     * 自动取消订单
     */
    void automaticCancel();

    /**
     * 根据GoodsId和UserId查询订单信息
     *
     * @param map 订单对象
     * @return 订单对象
     */
    OrderBO selectOrderByGoodsIdAndUserId(Map<String, Object> map);

    /**
     * 用户修改订单
     *
     * @param orderUpdateBO 订单修改对象
     * @return 订单修改对象
     */
    OrderUpdateBO updateOrder(OrderUpdateBO orderUpdateBO);

    /**
     * 确认订单
     *
     * @param order 订单对象
     */
    void confirmOrder(Order order);

    /**
     * 根据发票查询订单列表
     *
     * @param order    订单对象
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 订单列表
     */
    List<OrderBO> selectOrderListByInvoice(OrderBO order, int pageNum, int pageSize);

    /**
     * 查询课程订单列表
     *
     * @param orderBO  订单对象
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 订单列表
     */
    List<OrderBO> selectCurriculumOrderList(OrderBO orderBO, int pageNum, int pageSize);

    /**
     * 状态为[付款成功]订单数量
     *
     */
    Integer selectTodoListCount();

    /**
     * 统计3-付款中，4-付款成功，6-订单完成，7-订单结束，9-已退款，订单总数的数量
     *
     */
    OrderStatBO orderStat();

    /**
     * 后台用户查询订单详情
     * @param orderNo 订单号
     * @return 订单对象
     */
    OrderBO selectByOrderNoAdmin(String orderNo);

    /**
     * 根据交易流水号查询订单合并内容
     * @param tradeNo 订单号
     * @return 订单交易对象
     */
    OrderTradeBO selectOrderTrade(String tradeNo);

    /**
     * 删除订单-后台管理员
     * @param orderBO 订单对象
     */
    void adminDeleteOrder(OrderBO orderBO);

    /**
     * 根据订单号查询订单库存
     */
    void selectStock(String tradeNo);


    /**
     * 统计订单，统计维度为【订单状态】
     * @param map map
     * @return 订单统计对象
     */
    List<OrderStatisBO> statisOrderByStatus(Map<String, Object> map);

    /**
     * 统计订单，统计维度为【月份】
     * @param map map
     * @return 订单统计的对象
     */
    List<OrderStatisBO> statisOrderByMonth(Map<String, Object> map);

    /**
     * 修改订单是否可退货
     * @param map map
     */
    void updateOrderReturn(Map<String, Object> map,HttpServletRequest httpServletRequest);

    /**
     * 查看订单详情信息
     * @param orderNo 订单号
     * @return 订单对象
     */
    OrderBO selectOrderDetail(String orderNo);

    /**
     * 前台-查询订单详情
     * @param orderNo 订单号
     * @return 订单对象
     */
    OrderBO selectWebByOrderNo(String orderNo);

    /**
     * 微信端-查询订单详情
     * @param orderNo 订单号
     * @return 订单对象
     */
    OrderBO selectWeChatByOrderNo(String orderNo);

    List<OrderBO> selectMyOrderList(Map<String, Object> map, int pageNum, int pageSize);

    Double selectMyOrderMoney(Map<String, Object> map);
}
