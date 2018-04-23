package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.AccessLogMapper;
import com.abc12366.cms.mapper.db2.AccessLogRoMapper;
import com.abc12366.cms.model.bo.AccessLogRolltjBo;
import com.abc12366.cms.model.bo.AccessLogtjListBo;
import com.abc12366.cms.model.questionnaire.AccessLog;
import com.abc12366.cms.model.questionnaire.bo.AccessLogBO;
import com.abc12366.cms.service.AccessLogService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lizhongwei
 * @create 2017-06-16 4:21 PM
 * @since 1.0.0
 */
@Service
public class AccessLogServiceImpl implements AccessLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogServiceImpl.class);
    @Autowired
    private AccessLogRoMapper accessLogRoMapper;

    @Autowired
    private AccessLogMapper accessLogMapper;

    @Override
    public List<AccessLogBO> selectList(AccessLog accessLog) {
        return accessLogRoMapper.selectList(accessLog);
    }

    @Override
    public List<AccessLogBO> selectAccessLogStatis(AccessLog accessLog) {
        return accessLogRoMapper.selectAccessLogStatis(accessLog);
    }

    @Override
    public AccessLogtjListBo selecttj(Map<String, Object> map) {
        AccessLogtjListBo accessLogtjListBo = new AccessLogtjListBo();
        //浏览统计总数
        Integer llcnt = accessLogRoMapper.selectlltjs(map);
        accessLogtjListBo.setLlcnt(llcnt);
        //浏览统计总数按时间
        Integer llcnts = accessLogRoMapper.selectlltjsbysj(map);
        accessLogtjListBo.setLlcnts(llcnts);
        //浏览统计浏览统计
        List<AccessLogRolltjBo> list = accessLogRoMapper.selectlltj(map);
        accessLogtjListBo.setList(list);
        //pc浏览统计浏览统计
        map.put("accessTerminal", "PC");
        List<AccessLogRolltjBo> pclist = accessLogRoMapper.selectlltj(map);
        accessLogtjListBo.setPclist(pclist);
        //mobileWeb浏览统计
        map.put("accessTerminal", "MobileWeb");
        List<AccessLogRolltjBo> mobileWeblist = accessLogRoMapper.selectlltj(map);
        accessLogtjListBo.setMobileWeblist(mobileWeblist);
        return accessLogtjListBo;
//        return accessLogRoMapper.selectAccessLogtjListBo(map);
    }

    @Override
    public AccessLogBO insert(AccessLogBO accessLogBO) {
        accessLogBO.setId(Utils.uuid());
        accessLogBO.setCreateTime(new Date());
        AccessLog log = new AccessLog();
        BeanUtils.copyProperties(accessLogBO, log);
        int insert = accessLogMapper.insert(log);
        if (insert != 1) {
            LOGGER.info("{新增问卷访问记录失败}", log);
            throw new ServiceException(4423);
        }
        return accessLogBO;
    }

    @Override
    public void delete(String questionId) {
        accessLogMapper.deleteByQuestionId(questionId);
    }
}
