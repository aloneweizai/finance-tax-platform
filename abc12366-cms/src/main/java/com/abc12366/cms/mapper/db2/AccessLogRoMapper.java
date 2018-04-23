package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.bo.AccessLogRolltjBo;
import com.abc12366.cms.model.bo.AccessLogtjListBo;
import com.abc12366.cms.model.questionnaire.AccessLog;
import com.abc12366.cms.model.questionnaire.bo.AccessLogBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * AccessLogMapper数据库操作接口类
 **/

public interface AccessLogRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    AccessLog selectByPrimaryKey(@Param("id") String id);

    List<AccessLogBO> selectList(AccessLog accessLog);

    List<AccessLogBO> selectAccessLogStatis(AccessLog accessLog);

    /**
     * 查询（浏览统计）
     **/
    List<AccessLogRolltjBo> selectlltj(Map<String, Object> map);

    /**
     * 查询（浏览统计总数按时间）
     **/
    Integer selectlltjsbysj(Map<String, Object> map);

    /**
     * 查询（浏览统计总数）
     **/
    Integer selectlltjs(Map<String, Object> map);

    AccessLogtjListBo selectAccessLogtjListBo(Map<String, Object> map);
}