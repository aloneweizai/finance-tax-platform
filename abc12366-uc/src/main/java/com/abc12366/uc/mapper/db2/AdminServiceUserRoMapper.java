package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.admin.AdminServiceUser;
import com.abc12366.uc.model.admin.bo.AdminServiceUserBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * AdminServiceUserMapper数据库操作接口类
 * 
 **/

public interface AdminServiceUserRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	AdminServiceUser selectByPrimaryKey(@Param("id") String id);


    List<AdminServiceUserBo> selectList(Map<String, Object> map);

}