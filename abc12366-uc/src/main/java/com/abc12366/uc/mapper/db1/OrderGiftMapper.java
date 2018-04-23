package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.OrderGift;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * OrderGiftMapper数据库操作接口类
 * 
 **/

public interface OrderGiftMapper {

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
	int insert(OrderGift record);


	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int update(OrderGift record);

	int deleteByGiftId(@Param("giftId") String giftId);
}