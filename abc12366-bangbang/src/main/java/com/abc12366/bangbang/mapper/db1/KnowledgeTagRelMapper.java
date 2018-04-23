package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.KnowledgeTagRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * KnowledgeTagRelMapper数据库操作接口类
 **/

public interface KnowledgeTagRelMapper {

    /**
     * 查询标签名称
     * @param knowledgeId
     * @return
     */
    List<String> selectTagNamesByKnowledgeId(String knowledgeId);


    /**
     * 查询（根据主键ID查询）
     **/
    KnowledgeTagRel selectByPrimaryKey(@Param("id") String id);

    /**
     * 查询
     **/
    KnowledgeTagRel selectByKnowledgeIdAndTagId(@Param("knowledgeId") String knowledgeId, @Param("tagId") String tagId );

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);


    /**
     * 删除（根据KnowledgeID删除）
     **/
    int deleteByKnowledgeId(@Param("knowledgeId") String KnowledgeId);

    /**
     * 删除（根据KnowledgeIds批量删除）
     **/
    int deleteByKnowledgeIds(List<String> ids);

    /**
     * 删除（根据tagId删除）
     **/
    int deleteByTagId(@Param("tagId") String tagId);

    /**
     * 删除（根据tagIds批量删除）
     **/
    int deleteByTagIds(List<String> tagIds);

    /**
     * 添加
     **/
    int insert(KnowledgeTagRel record);

    /**
     * 批量添加
     **/
    int insertBatch(List<KnowledgeTagRel> list);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(KnowledgeTagRel record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(KnowledgeTagRel record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(KnowledgeTagRel record);

}