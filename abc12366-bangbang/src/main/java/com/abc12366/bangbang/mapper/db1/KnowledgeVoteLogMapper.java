package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.KnowledgeVoteLog;
import com.abc12366.bangbang.model.bo.KnowledgeBaseParamBO;
import com.abc12366.bangbang.model.bo.KnowledgeVoteLogBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * KnowledgeVoteLogMapper数据库操作接口类
 *
 **/

public interface KnowledgeVoteLogMapper{

	/**
	 *
	 * 列表查询
	 *
	 */
	List<KnowledgeVoteLogBO> selectList(KnowledgeBaseParamBO param);



	/**
	 *
	 * 查询（根据主键ID查询）
	 *
	 **/
	KnowledgeVoteLog  selectByPrimaryKey ( @Param("id") String id );


	/**
	 *
	 * 查询（用户投票过的数据）
	 *
	 **/
	KnowledgeVoteLog  selectByUserVotedKnowledge ( @Param("userId") String userId, @Param("knowledgeId") String knowledgeId );

	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey ( @Param("id") String id );
	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKeys (List<String> ids );

	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByKnowledgeIds (List<String> knowledgeIds);

	/**
	 *
	 * 添加
	 *
	 **/
	int insert( KnowledgeVoteLog record );

	/**
	 *
	 * 添加 （匹配有值的字段）
	 *
	 **/
	int insertSelective( KnowledgeVoteLog record );

	/**
	 *
	 * 修改 （匹配有值的字段）
	 *
	 **/
	int updateByPrimaryKeySelective( KnowledgeVoteLog record );

	/**
	 *
	 * 修改（根据主键ID修改）
	 *
	 **/
	int updateByPrimaryKey ( KnowledgeVoteLog record );

}