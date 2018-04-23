package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumOrder;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumOrderBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CurriculumOrderMapper数据库操作接口类
 * 
 **/

public interface CurriculumOrderRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CurriculumOrder  selectByPrimaryKey(@Param("orderId") String orderId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumOrderBo> selectList(Map<String, Object> map);


}