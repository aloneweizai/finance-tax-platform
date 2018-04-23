package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.gift.UgiftLog;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * UgiftLogMapper数据库操作接口类
 * @author lizhongwei
 * @create 2017-12-18 3:09 PM
 * @since 1.0.0
 **/

public interface UgiftLogMapper{


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
	int insert(UgiftLog record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int update(UgiftLog record);

}