package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.invoice.Invoice;
import com.abc12366.uc.model.invoice.bo.InvoiceBO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * InvoiceMapper数据库操作接口类
 **/

public interface InvoiceRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    InvoiceBO selectById(@Param("id") String id);


    List<InvoiceBO> selectList(InvoiceBO invoice);

    Invoice selectByInvoiceOrderNo(Invoice invoice);

    Invoice selectAvailableInvoice();

    InvoiceBO selectUserInvoice(Invoice invoice);

    InvoiceBO selectInvoice(Invoice ce);

    Invoice selectByIdAndUserId(Invoice id);

    List<Invoice> selectReceiptInvoiceByDate(@Param("date")Date date);

    /**
     * 状态为[待审批]发票申请数
     *
     * @return Integer
     */
    Integer selectTodoListCount();

    /**
     * 根据invoiceNo和invoiceCode查询
     */
    InvoiceBO selectByInvoiceNo(Map<String, Object> map);
}