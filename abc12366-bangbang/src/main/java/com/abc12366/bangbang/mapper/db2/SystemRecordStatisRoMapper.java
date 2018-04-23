package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.SystemRecordStatis;
import com.abc12366.bangbang.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * SystemRecordStatisMapper数据库操作接口类
 * 
 **/

public interface SystemRecordStatisRoMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	SystemRecordStatis  selectByPrimaryKey(@Param("id") String id);


	/**
	 * 获取统计数据
	 * @param map
	 * @return
	 */
	List<SystemRecordStatis> selectRecordStatisList(Map<String, Object> map);

	/**
	 * 软件用户行为统计
	 * @param map
	 * @return
	 */
	List<SystemRecordStatis> statisList(Map<String, Object> map);

	/**
	 * 根据时间查询数据
	 * @param createTime
	 * @return
	 */
	int selectByDateCount(@Param("createTime") Date createTime);

	/**
	 * 软件用户行为统计-用户列表
	 * @param map
	 * @return
	 */
	List<User> statisRecordUserList(Map<String, Object> map);
}