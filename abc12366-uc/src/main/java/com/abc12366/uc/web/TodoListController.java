package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.service.TodoListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 待办列表控制器
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-10-24 3:47 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class TodoListController {

    private static Logger LOGGER = LoggerFactory.getLogger(TodoTaskController.class);

    /**
     * 待办列表服务
     */
    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    /**
     * 查询待办列表
     *
     * @return ResponseEntity
     */
    @GetMapping("/todolist")
    public ResponseEntity selectList() {
        LOGGER.info("selectList");
        Map<String, Integer> map = todoListService.selectList();

        LOGGER.info("{}", map);
        return ResponseEntity.ok(Utils.kv("dataList", map));
    }

    /**
     * 统计3-付款中，4-付款成功，6-订单完成，7-订单结束，9-已退款，订单总数的数量
     *
     * @return ResponseEntity
     */
    @GetMapping("/stat/order")
    public ResponseEntity orderStat() {
        LOGGER.info("orderStat");
        Map<String, Integer> map = todoListService.orderStat();

        LOGGER.info("{}", map);
        return ResponseEntity.ok(Utils.kv("dataList", map));
    }
}
