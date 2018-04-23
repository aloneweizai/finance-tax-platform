package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.UserBsrl;
import org.apache.ibatis.annotations.Param;

/**
 * @author lizhongwei
 * @create 2017-01-02
 * UserBsrlMapper数据库操作接口类
 * 
 **/

public interface UserBsrlMapper{


	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int delete(@Param("calId") String calId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(UserBsrl record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int update(UserBsrl record);

}