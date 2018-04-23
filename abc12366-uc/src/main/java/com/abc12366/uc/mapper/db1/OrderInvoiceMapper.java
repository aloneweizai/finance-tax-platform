package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.OrderInvoice;
import org.apache.ibatis.annotations.Param;

/**
 * OrderInvoiceMapper数据库操作接口类
 **/

public interface OrderInvoiceMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int delete(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(OrderInvoice record);

    /**
     * 修改（根据主键ID修改）
     **/
    int update(OrderInvoice record);

    int deleteByInvoiceId(@Param("invoiceId") String invoiceId);

    int updateByInvoiceId(@Param("invoiceId") String invoiceId);
}