package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.FollowLecturer;

/**
 * 
 * FollowLecturerMapper数据库操作接口类
 * 
 **/

public interface FollowLecturerMapper{

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(FollowLecturer record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int update(FollowLecturer record);

}