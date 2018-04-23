package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.UvipPrice;
import org.apache.ibatis.annotations.Param;

/**
 * UvipPriceMapper数据库操作接口类
 **/

public interface UvipPriceMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int delete(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(UvipPrice record);


    /**
     * 修改（根据主键ID修改）
     **/
    int update(UvipPrice record);

    int deleteByProductId(String productId);
}