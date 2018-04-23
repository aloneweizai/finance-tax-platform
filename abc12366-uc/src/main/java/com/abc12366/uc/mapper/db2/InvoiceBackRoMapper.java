package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.invoice.InvoiceBack;
import com.abc12366.uc.model.invoice.bo.InvoiceBackBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * InvoiceBackMapper数据库操作接口类
 **/

public interface InvoiceBackRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    InvoiceBack selectById(@Param("id") String id);


    List<InvoiceBackBO> selectBOList(InvoiceBackBO invoiceBackBO);

    InvoiceBackBO selectBackOne(String id);
}