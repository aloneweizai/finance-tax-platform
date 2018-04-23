package com.abc12366.uc.service.order.impl;

import com.abc12366.uc.mapper.db2.OrderLogRoMapper;
import com.abc12366.uc.model.order.OrderLog;
import com.abc12366.uc.service.order.OrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @since 1.0.0
 */
@Service
public class OrderLogServiceImpl implements OrderLogService {

    @Autowired
    private OrderLogRoMapper orderLogRoMapper;

    @Override
    public List<OrderLog> selectList(OrderLog orderLog) {
        return orderLogRoMapper.selectList(orderLog);
    }
}
