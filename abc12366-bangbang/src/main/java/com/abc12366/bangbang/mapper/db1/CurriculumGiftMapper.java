package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumGift;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumGiftMapper数据库操作接口类
 * 
 **/

public interface CurriculumGiftMapper{

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int delete(@Param("id") String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(CurriculumGift record);


	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int update(CurriculumGift record);

	int deleteByGiftId(@Param("giftId") String giftId);
}