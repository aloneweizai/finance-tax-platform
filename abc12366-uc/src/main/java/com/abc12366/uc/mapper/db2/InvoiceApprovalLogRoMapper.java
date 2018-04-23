package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.invoice.InvoiceApprovalLog;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * InvoiceApprovalLogMapper数据库操作接口类
 * 
 **/

public interface InvoiceApprovalLogRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	InvoiceApprovalLog  selectByPrimaryKey(@Param("id") String id);

}