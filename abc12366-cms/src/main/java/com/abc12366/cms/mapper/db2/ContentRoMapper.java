package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Content;
import com.abc12366.cms.model.bo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ContentMapper数据库操作接口类
 **/

public interface ContentRoMapper {


    /**
     * 查询(根据主键ID查询)
     **/
    Content selectByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 查询(根据主键ID查询)
     **/
    Long selectByChannelId(@Param("channelId") String channelId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<ContentListBo> selectList(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)按访问量排序
     **/
    List<ContentViewListBo> selectListByviews(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<ContentsListBo> selectListByContentType(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<ContentsListBo> selectListByTagName(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<ContenttagidBo> selectContentType(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<ContentsListBo> selectListByChannelId(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<ContentsListBo> selectListcszxw(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    Integer selectCntByChannelId(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<ContentsListBo> selectListBytopicId(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<ContentsListBo> selectListByTplContent(Map<String, Object> map);


    /**
     * 查询(根据contentId查询)
     **/
    Content selectByContentId(@Param("contentId") String contentId);

    /**
     * 查询(根据ReleaseDate查询下一篇)
     **/
    ContentudBo selectByReleaseDateAsc(Map<String, Object> map);

    /**
     * 查询(根据ReleaseDate查询上一篇)
     **/
    ContentudBo selectByReleaseDateDesc(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<ContentListBo> selectListSearch(Map<String, Object> map);

}