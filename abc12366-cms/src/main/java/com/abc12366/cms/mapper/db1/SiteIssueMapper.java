package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.SiteIssue;
import org.apache.ibatis.annotations.Param;

/**
 * SiteIssueMapper数据库操作接口类
 **/

public interface SiteIssueMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("issueId") String issueId);

    /**
     * 批量删除（根据主键ID删除）
     **/
    int deleteList(@Param("issueIds") String[] issueIds);

    /**
     * 添加
     **/
    int insert(SiteIssue record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(SiteIssue record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(SiteIssue record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(SiteIssue record);

}