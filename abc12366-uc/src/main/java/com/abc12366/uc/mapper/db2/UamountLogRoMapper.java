package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.gift.UamountLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * UamountLogMapper数据库操作接口类
 * @author lizhongwei
 * @create 2017-12-18 3:09 PM
 * @since 1.0.0
 **/

public interface UamountLogRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	UamountLog selectByPrimaryKey(@Param("id") String id);


	/**
	 * 根据用户ID查找礼包金额记录
	 * @param map
	 * @return
	 */
	List<UamountLog> selectUamountLogList(Map<String, Object> map);
}