package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.AccessLogtjListBo;
import com.abc12366.cms.model.questionnaire.AccessLog;
import com.abc12366.cms.model.questionnaire.bo.AccessLogBO;

import java.util.List;
import java.util.Map;

/**
 * 访问记录管理接口类
 *
 * @author lizhongwei
 * @create 2017-7-5
 * @since 1.0.0
 */
public interface AccessLogService {

    List<AccessLogBO> selectList(AccessLog accessLog);

    List<AccessLogBO> selectAccessLogStatis(AccessLog accessLog);

    AccessLogtjListBo selecttj(Map<String, Object> map);

    AccessLogBO insert(AccessLogBO accessLog);

    void delete(String questionId);
}
