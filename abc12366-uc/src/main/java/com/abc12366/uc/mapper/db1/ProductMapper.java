package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.Product;
import org.apache.ibatis.annotations.Param;

/**
 * ProductMapper数据增删改库操作接口类
 **/

public interface ProductMapper {


    /**
     * 修改（根据主键ID修改）
     **/
    int updateStatus(@Param("goodsId") String goodsId);

    int deleteByGoodsId(@Param("goodsId") String goodsId);

    /**
     * 添加
     **/
    int insert(Product record);


    /**
     * 修改 （匹配有值的字段）
     **/
    int update(Product record);


    int deleteById(String delId);
}