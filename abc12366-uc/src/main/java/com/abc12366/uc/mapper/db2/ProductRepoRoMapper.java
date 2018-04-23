package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.ProductRepo;
import com.abc12366.uc.model.order.bo.ProductRepoBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ProductRepoMapper数据库操作接口类
 **/

public interface ProductRepoRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    ProductRepo selectByPrimaryKey(@Param("id") String id);

    List<ProductRepoBO> selectList(ProductRepoBO productRepoBO);

    ProductRepoBO selectById(String id);

    ProductRepoBO selectByGoodsId(ProductRepoBO productRepoBO);

    List<ProductRepoBO> selectProductRepoDetail(ProductRepo productRepo);
}