package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.invoice.Express;
import org.apache.ibatis.annotations.Param;

/**
 * ExpressMapper数据库操作接口类
 **/

public interface ExpressMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(Express record);

    /**
     * 修改（根据主键ID修改）
     **/
    int update(Express record);

    int deleteByInvoiceOrderNo(@Param("invoiceOrderNo") String invoiceOrderNo);
}