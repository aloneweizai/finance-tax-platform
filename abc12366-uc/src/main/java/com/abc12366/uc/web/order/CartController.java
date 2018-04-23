package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.order.Order;
import com.abc12366.uc.model.order.bo.OrderBO;
import com.abc12366.uc.service.order.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 订单控制类
 *
 * @author lizhongwei
 * @create 2017-05-16
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/cart", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class CartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private OrderService orderService;


    /**
     * 订单列表查询
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @PathVariable("userId") String userId) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        OrderBO order = new OrderBO();
        order.setOrderStatus("0");
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<OrderBO> orderBOs = orderService.selectCartList(order);
        LOGGER.info("{}", orderBOs);
        return (orderBOs == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) orderBOs, "total", ((Page) orderBOs).getTotal()));
    }

    /**
     * 加入购物车
     *
     * @param orderBO
     * @param userId
     * @return
     */
    @PostMapping(path = "/{userId}")
    public ResponseEntity joinCart(@Valid @RequestBody OrderBO orderBO, @PathVariable("userId") String userId) {
        LOGGER.info("{}", orderBO);
        OrderBO bo = orderService.joinCart(orderBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 修改购物车
     *
     * @param orderBO
     * @param userId
     * @param id
     * @return
     */
    @PutMapping(path = "/{userId}/{id}")
    public ResponseEntity updateCart(@Valid @RequestBody OrderBO orderBO, @PathVariable("userId") String userId,
                                     @PathVariable("id") String id) {
        LOGGER.info("{}", orderBO);
        orderBO.setOrderNo(id);
        orderBO.setUserId(userId);
        OrderBO bo = orderService.updateCart(orderBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 提交购物车订单
     *
     * @param userId
     * @param id
     * @return
     */
    @PutMapping(path = "/submit/{userId}/{id}")
    public ResponseEntity submitCart(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        LOGGER.info("{}", userId, id);
        Order order = new Order();
        order.setOrderNo(id);
        order.setUserId(userId);
        orderService.submitCart(order);
        return ResponseEntity.ok(Utils.kv("data", order));
    }


    /**
     * 删除购物车
     *
     * @param userId
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{userId}/{id}")
    public ResponseEntity deleteCart(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        LOGGER.info("{}", userId, id);
        OrderBO orderBO = new OrderBO();
        orderBO.setOrderNo(id);
        orderBO.setUserId(userId);
        orderService.deleteCart(orderBO);
        return ResponseEntity.ok(Utils.kv("data", orderBO));
    }


}
