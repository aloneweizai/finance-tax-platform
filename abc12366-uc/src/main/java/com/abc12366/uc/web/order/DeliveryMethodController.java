package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.order.DeliveryMethod;
import com.abc12366.uc.model.order.bo.DeliveryMethodBO;
import com.abc12366.uc.model.order.bo.DeliveryMethodUpdateBO;
import com.abc12366.uc.service.order.DeliveryMethodService;
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
 * 配送方式控制类
 * @author lizhongwei
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/delivery", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class DeliveryMethodController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryMethodController.class);

    @Autowired
    private DeliveryMethodService deliveryMethodService;

    /**
     * 列表查询
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "name", required = false) String name) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        DeliveryMethod deliveryMethod = new DeliveryMethod();
        deliveryMethod.setName(name);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<DeliveryMethod> deliveryList = deliveryMethodService.selectList(deliveryMethod);
        LOGGER.info("{}", deliveryList);
        return (deliveryList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) deliveryList, "total", ((Page) deliveryList).getTotal()));
    }

    /**
     * 新增
     * @param deliveryMethodBO
     * @return
     */
    @PostMapping
    public ResponseEntity addDeliveryMethod(@Valid @RequestBody DeliveryMethodBO deliveryMethodBO) {
        LOGGER.info("{}", deliveryMethodBO);
        DeliveryMethodBO bo = deliveryMethodService.add(deliveryMethodBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 查询单个配送方式
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectDeliveryMethod(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        DeliveryMethodBO deliveryMethodBO = deliveryMethodService.selectDeliveryMethod(id);
        LOGGER.info("{}", deliveryMethodBO);
        return ResponseEntity.ok(Utils.kv("data", deliveryMethodBO));
    }

    /**
     * 修改配送方式
     *
     * @param deliveryMethodBO
     * @param id
     * @return
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity updateDeliveryMethod(@Valid @RequestBody DeliveryMethodBO deliveryMethodBO, @PathVariable
            ("id") String id) {
        LOGGER.info("{}", deliveryMethodBO);
        deliveryMethodBO.setId(id);
        DeliveryMethodBO bo = deliveryMethodService.update(deliveryMethodBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 启用或禁用配送方式
     *
     * @param deliveryMethodUpdateBO
     * @return
     */
    @PutMapping(path = "/enable")
    public ResponseEntity enable(@Valid @RequestBody DeliveryMethodUpdateBO deliveryMethodUpdateBO) {
        LOGGER.info("{}", deliveryMethodUpdateBO);
        deliveryMethodService.enable(deliveryMethodUpdateBO);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 删除配送方式
     *
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        deliveryMethodService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }


}
