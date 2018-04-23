package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ContentPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ContentPictureMapper数据库操作接口类
 **/

public interface ContentPictureRoMapper {


    /**
     * 查询(根据主键ID查询)
     **/
    ContentPicture selectByPrimaryKey(@Param("id") Long id);

    /**
     * 查询(根据contentId查询)
     **/
    List<ContentPicture> selectContentPictureList(String contentId);

}