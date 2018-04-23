package com.abc12366.uc.service.order.impl;

import com.abc12366.uc.model.order.ProductRepo;
import com.abc12366.uc.model.order.bo.ProductRepoBO;

import java.util.List;

/**
 * 库存Service
 *
 * @author lizhongwei
 * @date 2017-07-01
 * @since 1.0.0
 */
public interface ProductRepoService {

    /**
     * 查询库存列表信息
     *
     * @param productRepoBO 库存对象
     * @return 库存列表
     */
    List<ProductRepoBO> selectList(ProductRepoBO productRepoBO);

    /**
     * 查询库存信息
     * @param id id
     * @return  库存对象
     */
    ProductRepoBO selectById(String id);

    /**
     * 库存入库
     * @param productRepoBO 库存对象
     * @return 库存对象
     */
    ProductRepoBO income(ProductRepoBO productRepoBO);

    /**
     * 库存出库
     *
     * @param productRepoBO 库存对象
     * @return  库存对象
     */
    ProductRepoBO outcome(ProductRepoBO productRepoBO);

    List<ProductRepoBO> selectProductRepoDetail(ProductRepo productRepo);
}
