package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.invoice.InvoiceDistribute;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * InvoiceDistributeMapper数据库操作接口类
 * 
 **/

public interface InvoiceDistributeMapper{


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
	int insert(InvoiceDistribute record);


	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int update(InvoiceDistribute record);

}