package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.KnowledgeTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * KnowledgeTagMapper数据库操作接口类
 **/

public interface KnowledgeTagMapper {

    /**
     * 查询关联问题最多的标签名
     **/
    List<String> selectHotTag(Integer num);

    /**
     * 查询（根据主键ID查询）
     **/
    KnowledgeTag selectByPrimaryKey(@Param("id") String id);

    /**
     * 查询（根据标签名称查询）
     **/
    KnowledgeTag selectByName(@Param("name") String name);

    /**
     * 查询列表
     **/
    List<KnowledgeTag> selectList(@Param("keywords") String keywords, @Param("status") Boolean status, @Param("tagType") String tagType);

    /**
     * 查询相关联的标签
     **/
    List<KnowledgeTag> selectRelatedTags(@Param("knowledgeId") String knowledgeId);

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 删除（根据主键IDS批量删除）
     **/
    int deleteByPrimaryKeys(List<String> ids);

    /**
     * 添加
     **/
    int  insertBatch(List<KnowledgeTag> records);

    /**
     * 添加
     **/
    int  batchUpdateTypeByName(List<KnowledgeTag> records);

    /**
     * 添加
     **/
    int insert(KnowledgeTag record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(KnowledgeTag record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(KnowledgeTag record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(KnowledgeTag record);

}