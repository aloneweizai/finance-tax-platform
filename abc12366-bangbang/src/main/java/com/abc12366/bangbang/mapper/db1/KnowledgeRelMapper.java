package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.KnowledgeRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * KnowledgeRelMapper数据库操作接口类
 **/

public interface KnowledgeRelMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    KnowledgeRel selectByPrimaryKey(@Param("id") Long id);

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") Long id);


    /**
     * 删除（根据KnowledgeID删除）
     **/
    int deleteByKnowledgeId(@Param("knowledgeId") String KnowledgeId);

    /**
     * 删除（根据KnowledgeID删除）
     **/
    int deleteByKnowledgeIds(List<String> knowledgeIds);

    /**
     * 删除（根据KnowledgeID删除）
     **/
    int deleteByRelKnowledgeIds(List<String> relKnowledgeIds);

    /**
     * 添加
     **/
    int insert(KnowledgeRel record);


    /**
     * 批量添加
     **/
    int insertBatch(List<KnowledgeRel> list);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(KnowledgeRel record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(KnowledgeRel record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(KnowledgeRel record);

}