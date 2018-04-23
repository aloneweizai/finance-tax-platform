package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.Trade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * TradeMapper数据库操作接口类
 * 
 **/

public interface TradeRoMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	Trade  selectById(@Param("tradeNo") String tradeNo);

	/**
	 * 根据订单号查询
	 */
	Trade  selectOrderNo(@Param("orderNo") String orderNo);

	List<Trade> selectList(@Param("orderNo") String orderNo);
}