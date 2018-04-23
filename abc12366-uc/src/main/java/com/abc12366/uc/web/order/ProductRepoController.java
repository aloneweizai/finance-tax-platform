package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.order.bo.ProductRepoBO;
import com.abc12366.uc.service.order.impl.ProductRepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 库存控制类
 *
 * @author lizhongwei
 * @create 2017-07-01
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/productrepo", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ProductRepoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepoController.class);

    @Autowired
    private ProductRepoService productRepoService;


    /**
     * 查询库存详情
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/select/{id}")
    public ResponseEntity<?> selectById(@PathVariable String id) {
        LOGGER.info("{}", id);
        ProductRepoBO productRepoBO = productRepoService.selectById(id);
        LOGGER.info("{}", productRepoBO);
        return ResponseEntity.ok(Utils.kv("data", productRepoBO));
    }

    /**
     * 库存入库
     *
     * @return
     */
    @PostMapping(path = "/income")
    public ResponseEntity income(@Valid @RequestBody ProductRepoBO productRepoBO) {
        LOGGER.info("{}", productRepoBO);
        ProductRepoBO bo = productRepoService.income(productRepoBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 库存出库
     *
     * @return
     */
    @PostMapping(path = "/outcome")
    public ResponseEntity outcome(@Valid @RequestBody ProductRepoBO productRepoBO) {
        LOGGER.info("{}", productRepoBO);
        ProductRepoBO bo = productRepoService.outcome(productRepoBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

}
