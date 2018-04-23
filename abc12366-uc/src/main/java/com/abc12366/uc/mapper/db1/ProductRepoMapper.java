package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.ProductRepo;
import org.apache.ibatis.annotations.Param;

/**
 * ProductRepoMapper数据库操作接口类
 **/

public interface ProductRepoMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int delete(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(ProductRepo record);

    /**
     * 修改（根据主键ID修改）
     **/
    int update(ProductRepo record);

    int deleteByGoodsId(@Param("goodsId") String goodsId);

    int deleteByProductId(String delId);
}