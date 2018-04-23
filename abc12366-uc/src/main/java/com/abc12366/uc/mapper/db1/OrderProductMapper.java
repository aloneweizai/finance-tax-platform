package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.OrderProduct;
import org.apache.ibatis.annotations.Param;

/**
 * OrderProductMapper数据库操作接口类
 **/

public interface OrderProductMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int delete(@Param("orderNo") String orderNo);

    /**
     * 添加
     **/
    int insert(OrderProduct record);


    /**
     * 修改（根据主键ID修改）
     **/
    int update(OrderProduct record);

    int updateByOrderNo(OrderProduct orderProduct);

}