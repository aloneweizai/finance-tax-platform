package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.KnowledgeAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * KnowledgeAttachmentMapper数据库操作接口类
 * 
 **/

public interface KnowledgeAttachmentMapper{

	/**
	 * 
	 * 删除（根据知识库ID删除）
	 * 
	 **/
	int deleteByKnowledgeId(@Param("id") String id);

	/**
	 *
	 * 删除（根据知识库ID删除）
	 *
	 **/
	int deleteByKnowledgeIds(List<String> ids);

	/**
	 *
	 * 添加
	 *
	 **/
	int insertBatch(List<KnowledgeAttachment> records);


}