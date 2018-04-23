package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.dzfp.Einvocie;
import com.abc12366.uc.model.invoice.InvoiceDetail;
import org.apache.ibatis.annotations.Param;

/**
 * InvoiceDetailMapper数据库操作接口类
 **/

public interface InvoiceDetailMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int delete(@Param("id") String id);

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByInvoiceRepoId(@Param("invoiceRepoId") String invoiceRepoId);

    /**
     * 添加
     **/
    int insert(InvoiceDetail record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int update(InvoiceDetail record);

    int updateByRepoId(InvoiceDetail invoiceDetail);

    int updateDZFP(Einvocie einvocie);
}