package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumCollect;

import java.util.Map;

/**
 * 
 * CurriculumCollectMapper数据库操作接口类
 * 
 **/

public interface CurriculumCollectMapper{

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(CurriculumCollect record);

    void delete(Map map);


}