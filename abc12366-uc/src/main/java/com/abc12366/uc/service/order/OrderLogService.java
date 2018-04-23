package com.abc12366.uc.service.order;

import com.abc12366.uc.model.order.OrderLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderLogService {

    /**
     * 查询订单日志列表信息
     *
     * @param orderLog
     * @return
     */
    List<OrderLog> selectList(OrderLog orderLog);

}
