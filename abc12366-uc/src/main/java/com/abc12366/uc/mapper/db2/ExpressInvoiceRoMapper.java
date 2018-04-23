package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.invoice.ExpressInvoice;
import org.apache.ibatis.annotations.Param;

/**
 * ExpressInvoiceMapper数据库操作接口类
 **/

public interface ExpressInvoiceRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    ExpressInvoice selectByPrimaryKey(@Param("id") String id);


}