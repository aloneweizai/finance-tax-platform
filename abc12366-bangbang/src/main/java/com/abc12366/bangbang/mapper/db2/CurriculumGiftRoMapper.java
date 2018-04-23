package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumGift;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumGiftMapper数据库操作接口类
 * 
 **/

public interface CurriculumGiftRoMapper {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CurriculumGift selectByPrimaryKey(@Param("id") String id);


}