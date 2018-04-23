package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.invoice.InvoiceRepo;
import com.abc12366.uc.model.invoice.bo.InvoiceRepoBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * InvoiceRepoMapper数据库操作接口类
 **/

public interface InvoiceRepoRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    InvoiceRepo selectByPrimaryKey(@Param("id") String id);

    List<InvoiceRepo> selectInvoiceRepoList(InvoiceRepo invoiceRepo);

    List<InvoiceRepoBO> selectList(InvoiceRepoBO invoiceRepoBO);

    InvoiceRepoBO selectInvoiceRepo(String id);

    InvoiceRepo selectRepoId(Map invoiceTypeCode);

    InvoiceRepoBO selectInvoiceRepoNum(String code);
}