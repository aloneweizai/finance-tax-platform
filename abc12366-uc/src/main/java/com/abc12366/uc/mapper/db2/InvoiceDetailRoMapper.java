package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.invoice.InvoiceDetail;
import com.abc12366.uc.model.invoice.InvoiceDistribute;
import com.abc12366.uc.model.invoice.bo.InvoiceDetailBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * InvoiceDetailMapper数据库操作接口类
 **/

public interface InvoiceDetailRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    InvoiceDetail selectByPrimaryKey(@Param("id") String id);


    List<InvoiceDetail> selectInvoiceDetailList(InvoiceDetail invoiceDetail);

    InvoiceDetail selectByInvoiceNo(String invoiceNo);

    InvoiceDetail selectInvoiceRepo(String status);

    List<InvoiceDetail> selectByIdAndStatus(String id);

    InvoiceDetailBO selectInvoiceDetail(InvoiceDistribute invoiceDistribute);

    List<InvoiceDetail> selectInvoiceDetailListByInvoice(InvoiceDetail invoiceDetail);

    InvoiceDetail selectByInvoiceNoAndCode(InvoiceDetail tail);

    InvoiceDetail selectByInvoiceNoAndStatus(String invoiceNo);

    InvoiceDetail selectInvoiceDetailByInvoice(InvoiceDetail invoiceDetail);
}