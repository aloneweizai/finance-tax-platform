package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.SfExportBO;
import com.abc12366.uc.model.order.OrderExchange;
import com.abc12366.uc.model.order.bo.ExchangeCompletedOrderBO;
import com.abc12366.uc.model.order.bo.ExchangeOrderInvoiceBO;
import com.abc12366.uc.model.order.bo.OrderExchangeExportBO;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-10 11:02 AM
 * @since 1.0.0
 */
public interface OrderExchangeRoMapper {
    List<OrderExchange> selectList(OrderExchange oe);

    List<OrderExchange> selectListForFinance(OrderExchange oe);

    List<OrderExchange> selectUndoneList(String orderNo);

    OrderExchange selectOne(String id);

    List<SfExportBO> export(OrderExchangeExportBO data);

    ExchangeCompletedOrderBO selectCompletedOrder(String orderNo);

    List<ExchangeOrderInvoiceBO> selectOrderInvoice(String orderNo);

    List<ExchangeOrderInvoiceBO> selectInvoice(String orderNo);

    OrderExchange selectById(String id);

    OrderExchange selectByOrderNo(String orderNo);

    /**
     * 统计某一状态的退换货数
     *
     * @param status 状态
     * @return Integer
     */
    Integer selectTodoListCount(String status);

    /**
     * 根据订单号查询审批通过的退换货记录
     * @param orderNo
     * @return
     */
    OrderExchange selectByOrderNoAndStatus(String orderNo);
}
