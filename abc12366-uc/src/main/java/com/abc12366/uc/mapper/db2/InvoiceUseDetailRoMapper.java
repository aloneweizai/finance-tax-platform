package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.invoice.InvoiceUseDetail;
import com.abc12366.uc.model.invoice.bo.InvoiceUseDetailBO;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * InvoiceUseDetailMapper数据库操作接口类
 * 
 **/

public interface InvoiceUseDetailRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	InvoiceUseDetail  selectByPrimaryKey(@Param("id") String id);


	InvoiceUseDetailBO selectInvoiceRepoNum(@Param("invoiceTypeCode")String invoiceTypeCode);
}