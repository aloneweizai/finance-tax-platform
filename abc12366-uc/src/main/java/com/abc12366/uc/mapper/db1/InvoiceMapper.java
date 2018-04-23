package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.invoice.Invoice;
import org.apache.ibatis.annotations.Param;

/**
 * InvoiceMapper数据库操作接口类
 **/

public interface InvoiceMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int delete(@Param("id") Long id);

    /**
     * 添加
     **/
    int insert(Invoice record);


    /**
     * 修改（根据主键ID修改）
     **/
    int update(Invoice record);

    int deleteByIdAndUserId(Invoice invoice);
}