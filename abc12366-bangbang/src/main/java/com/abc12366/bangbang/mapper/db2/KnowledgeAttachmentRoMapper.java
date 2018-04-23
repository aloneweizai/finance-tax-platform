package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.KnowledgeAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * KnowledgeAttachmentMapper数据库操作接口类
 * 
 **/

public interface KnowledgeAttachmentRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	KnowledgeAttachment  selectByPrimaryKey(@Param("id") String id);

	/**
	 *
	 * 查询列表（根据知识库ID查询）
	 *
	 **/
	List<KnowledgeAttachment> selectListByKnowledgeId(@Param("id") String id);

}