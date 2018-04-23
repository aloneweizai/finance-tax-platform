package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.invoice.InvoiceLog;
import org.apache.ibatis.annotations.Param;

/**
 * InvoiceMapper数据库操作接口类
 **/

public interface InvoiceLogRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    InvoiceLog selectById(@Param("id") String id);

}