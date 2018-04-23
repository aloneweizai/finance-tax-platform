package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.KnowledgeCategory;
import com.abc12366.bangbang.model.bo.SortBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * KnowledgeCategoryMapper数据库操作接口类
 **/

public interface KnowledgeCategoryMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    KnowledgeCategory selectByPrimaryKey(@Param("id") String id);


    /**
     * 查询（根据CODE查询）
     **/
    KnowledgeCategory selectByCode(@Param("code") String code);

    /**
     * 列表查询
     *
     * @return
     */
    List<KnowledgeCategory> selectAll();

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(KnowledgeCategory record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(KnowledgeCategory record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(KnowledgeCategory record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(KnowledgeCategory record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int batchUpdateSort(List<SortBO> list);

}