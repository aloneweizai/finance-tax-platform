package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.invoice.InvoiceUseApply;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * InvoiceUseApplyMapper数据库操作接口类
 * 
 **/

public interface InvoiceUseApplyMapper{



	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int delete(@Param("id") String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(InvoiceUseApply record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int update(InvoiceUseApply record);


	int updateByUseId(InvoiceUseApply invoiceUseApply);
}