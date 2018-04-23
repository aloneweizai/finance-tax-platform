package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.order.OrderLog;
import com.abc12366.uc.service.order.OrderLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单控制类
 *
 * @author lizhongwei
 * @create 2017-06-08
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/orderlog", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class OrderLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderLogController.class);

    @Autowired
    private OrderLogService orderLogService;

    /**
     * 订单日志列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "orderNo", required = true) String orderNo,
                                     @RequestParam(value = "exchangeId", required = true) String exchangeId,
                                     @RequestParam(value = "logType", required = true) String logType) {
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderNo(orderNo);
        orderLog.setLogType(logType);
        orderLog.setExchangeId(exchangeId);
        List<OrderLog> orderLogList = orderLogService.selectList(orderLog);
        LOGGER.info("{}", orderLogList);
        return (orderLogList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", orderLogList));
    }

}
