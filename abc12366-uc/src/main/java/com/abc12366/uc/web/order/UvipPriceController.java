package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.order.UvipPrice;
import com.abc12366.uc.model.order.bo.UvipPriceBO;
import com.abc12366.uc.service.order.UvipPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 会员价格控制类
 *
 * @author lizhongwei
 * @create 2017-07-04
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/uvipprice", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UvipPriceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UvipPriceController.class);

    @Autowired
    private UvipPriceService uvipPriceService;

    /**
     * 会员价格列表管理
     *
     * @return
     */
    @GetMapping(path = "/select/{productId}")
    public ResponseEntity selectList(@PathVariable("productId") String productId) {
        LOGGER.info("{}:{}", productId);
        UvipPrice uvipPrice = new UvipPrice();
        uvipPrice.setProductId(productId);
        List<UvipPriceBO> uvipPriceList = uvipPriceService.selectList(uvipPrice);
        LOGGER.info("{}", uvipPriceList);
        return ResponseEntity.ok(Utils.kv("dataList", uvipPriceList));
    }

}
