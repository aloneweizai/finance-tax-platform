package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ContentAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ContentAttrMapper数据库操作接口类
 **/

public interface ContentAttrMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 添加
     **/
    int insert(ContentAttr record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(ContentAttr record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(ContentAttr record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(ContentAttr record);


    /**
     * 查找指定 contentId、attrName 的记录
     * @param record
     * @return
     */
    List<ContentAttr> selectContentAttr(ContentAttr record);
}