package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.UserPointsApply;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * UserPointsApplyMapper数据库操作接口类
 * 
 **/

public interface UserPointsApplyRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	UserPointsApply  selectByPrimaryKey(@Param("id") String id);

}