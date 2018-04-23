package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.GoodsLog;
import com.abc12366.uc.model.order.bo.GoodsLogBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * GoodsLogMapper数据库操作接口类
 * 
 **/

public interface GoodsLogRoMapper {


	/**
	 *
	 * 查询（根据主键ID查询）
	 *
	 **/
	GoodsLog select ( @Param("id") String id );

    List<GoodsLogBO> selectGoodsLogList(@Param("goodsId") String goodsId);
}