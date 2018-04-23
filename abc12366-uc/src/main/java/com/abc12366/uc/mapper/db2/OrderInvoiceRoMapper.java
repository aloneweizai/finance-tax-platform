package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.OrderInvoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OrderInvoiceMapper数据库操作接口类
 **/

public interface OrderInvoiceRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    OrderInvoice selectById(@Param("id") String id);

    List<OrderInvoice> selectByInvoiceId(String id);

    OrderInvoice selectByOrderNo(@Param("orderNo") String orderNo);
}