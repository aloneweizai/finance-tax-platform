package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileMapper数据库操作接口类
 **/

public interface FileRoMapper {


    /**
     * 查询(根据主键ID查询)
     **/
    File selectByPrimaryKey(@Param("id") Long id);

    /**
     * 查询(根据contentId查询)
     **/
    List<File> selectFileList(String contentId);

}