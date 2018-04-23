package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.admin.AdminService;
import com.abc12366.uc.model.admin.bo.AdminServiceBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * AdminServiceMapper数据库操作接口类
 * 
 **/

public interface AdminServiceRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	AdminService selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<AdminServiceBo> selectList(Map<String, Object> map);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    int selectCnt(Map<String, Object> map);


}