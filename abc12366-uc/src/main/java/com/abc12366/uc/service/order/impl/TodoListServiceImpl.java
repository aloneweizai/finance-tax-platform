package com.abc12366.uc.service.order.impl;

import com.abc12366.uc.model.order.bo.OrderStatBO;
import com.abc12366.uc.model.order.bo.RegsAndNsrloginStatBO;
import com.abc12366.uc.service.RealNameValidationService;
import com.abc12366.uc.service.TodoListService;
import com.abc12366.uc.service.UserBindServiceNew;
import com.abc12366.uc.service.gift.GiftService;
import com.abc12366.uc.service.invoice.InvoiceService;
import com.abc12366.uc.service.invoice.InvoiceUseApplyService;
import com.abc12366.uc.service.order.OrderExchangeService;
import com.abc12366.uc.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 待办列表实现类
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-10-24 3:54 PM
 * @since 1.0.0
 */
@Service
public class TodoListServiceImpl implements TodoListService{

    /**
     * 实名认证服务
     */
    private final RealNameValidationService realNameValidationService;

    private final OrderService orderService;

    private final OrderExchangeService orderExchangeService;

    private final InvoiceService invoiceService;

    private final InvoiceUseApplyService invoiceUseApplyService;

    @Autowired
    private GiftService giftService;
    @Autowired
    private UserBindServiceNew userBindServiceNew;

    @Autowired
    public TodoListServiceImpl(RealNameValidationService realNameValidationService,
                               OrderService orderService,
                               OrderExchangeService orderExchangeService,
                               InvoiceService invoiceService,
                               InvoiceUseApplyService invoiceUseApplyService) {
        this.realNameValidationService = realNameValidationService;
        this.orderService = orderService;
        this.orderExchangeService = orderExchangeService;
        this.invoiceService = invoiceService;
        this.invoiceUseApplyService = invoiceUseApplyService;
    }

    @Override
    public Map<String, Integer> selectList() {
        // 【待认证】实名认证数
        Integer num1 = realNameValidationService.selectTodoListCount();
        // 【确认付款】订单数
        Integer num2 = orderService.selectTodoListCount();
        // 【待审核】退换货申请数
        Integer num3 = orderExchangeService.selectTodoListCount("1");
        // 【待审批】发票申请数
        Integer num4 = invoiceService.selectTodoListCount();
        // 【确认退单】退换货申请数-需要退款的数量
        Integer num5 = orderExchangeService.selectTodoListCount("8");
        // 【待审核】发票领用数
        Integer num6 = invoiceUseApplyService.selectTodoListCount();
        RegsAndNsrloginStatBO statBO = userBindServiceNew.staRegsAndNsrlogins();


        Map<String, Integer> map = new HashMap<>(6);
        map.put("num1", num1);
        map.put("num2", num2);
        map.put("num3", num3);
        map.put("num4", num4);
        map.put("num5", num5);
        map.put("num6", num6);

        map.put("regsDay", statBO.getRegsDay());
        map.put("regsMonth", statBO.getRegsMonth());
        map.put("dzsbLoginsDay", userBindServiceNew.getDzsbnsrLoginTimesDay());
        map.put("nsrLoginsMonth", userBindServiceNew.getNnsrLoginTimesMonth());
        //待处理礼包
        map.put("giftCount", giftService.getGiftStatusCount("1"));
        //已处理礼包
        map.put("giftSend", giftService.getGiftStatusCount("2"));

        return map;
    }

    @Override
    public Map<String, Integer> orderStat() {
        OrderStatBO orderStatBO = orderService.orderStat();
        Map<String, Integer> map = new HashMap<>(10);
        map.put("orderStatus2", orderStatBO.getOrderStatus2());
        map.put("orderStatus3", orderStatBO.getOrderStatus3());
        map.put("orderStatus4", orderStatBO.getOrderStatus4());
        map.put("orderStatus6", orderStatBO.getOrderStatus6());
        map.put("orderStatus7", orderStatBO.getOrderStatus7());
        map.put("orderStatus9", orderStatBO.getOrderStatus9());
        map.put("orderStatus", orderStatBO.getOrderStatus());

        return map;
    }
}
