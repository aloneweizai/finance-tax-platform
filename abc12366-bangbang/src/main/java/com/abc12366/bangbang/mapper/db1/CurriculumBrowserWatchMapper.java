package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumBrowserWatch;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 *
 * CurriculumBrowserWatchMapper数据库操作接口类
 *
 **/
public interface CurriculumBrowserWatchMapper {

    /**
     *
     * 添加
     *
     **/
    int insert(CurriculumBrowserWatch record);

    /**
     *
     * 修改
     *
     **/
    int updateBrowseNum(@Param("curriculumId") String curriculumId);

    /**
     *
     * 修改
     *
     **/
    int updateWatchNum(@Param("curriculumId") String curriculumId);
}
