package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.UserBank;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * UserBankMapper数据库操作接口类
 * 
 **/

public interface UserBankRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	UserBank  selectByPrimaryKey(@Param("id") String id);

	List<UserBank> selectUserBankList(Map<String, Object> map);
}