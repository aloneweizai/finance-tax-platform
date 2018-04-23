package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.OrderGift;
import com.abc12366.uc.model.order.bo.OrderBO;
import com.abc12366.uc.model.order.bo.OrderGiftBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * OrderGiftMapper数据库操作接口类
 * 
 **/

public interface OrderGiftRoMapper {

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	OrderGift selectById(@Param("id") String id);

	List<OrderGiftBO> selectByOrderNo(@Param("giftId")String orderNo);

	OrderGift selectCurriculumGift(String id);
}