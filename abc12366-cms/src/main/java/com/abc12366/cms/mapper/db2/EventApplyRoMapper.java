package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.EventApply;
import com.abc12366.cms.model.bo.EventApplyBo;
import com.abc12366.cms.model.bo.EventbmtjBo;
import com.abc12366.cms.model.bo.EventlltjBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * EventApplyMapper数据库操作接口类
 **/

public interface EventApplyRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    EventApply selectByPrimaryKey(@Param("applyId") String applyId);

    /**
     * 查询（根据主键ID查询）
     **/
    List<EventApplyBo> selectList(Map<String, Object> map);

    /**
     * 查询（根据主键ID查询）
     **/
    EventbmtjBo selectbmtj(Map<String, Object> map);

    /**
     * 查询（根据主键ID查询）日期
     **/
    List<EventlltjBo> selectlltj(Map<String, Object> map);

    /**
     * 查询（根据主键ID查询）月份
     **/
    List<EventlltjBo> selectlltjyue(Map<String, Object> map);

    /**
     * 查询（根据主键ID查询）年份
     **/
    List<EventlltjBo> selectlltjnian(Map<String, Object> map);


}