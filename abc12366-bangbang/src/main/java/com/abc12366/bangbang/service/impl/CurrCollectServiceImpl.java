package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CurriculumCollectMapper;
import com.abc12366.bangbang.mapper.db2.CurriculumCollectRoMapper;
import com.abc12366.bangbang.model.curriculum.CurriculumCollect;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumCollectBo;
import com.abc12366.bangbang.service.CurrCollectService;
import com.abc12366.bangbang.util.MapUtil;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/8/11.
 */
@Service
public class CurrCollectServiceImpl implements CurrCollectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrCollectServiceImpl.class);

    @Autowired
    private CurriculumCollectMapper collectMapper;

    @Autowired
    private CurriculumCollectRoMapper collectRoMapper;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public CurriculumCollectBo insert(String curriculumId, HttpServletRequest request) {
        LOGGER.info("{}:{}", curriculumId, request);



        CurriculumCollect collect = new CurriculumCollect();

        String userId = Utils.getUserId();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        collect.setCollectId(uuid);
        collect.setCurriculumId(curriculumId);
        collect.setUserId(userId);
        collect.setCollectTime(new Date());

        Map map = MapUtil.kv("curriculumId", curriculumId, "userId", userId);
        int cnt =  collectRoMapper.selectExist(map);

        if(cnt >0){
            throw new ServiceException(4325);
        }

        int result = collectMapper.insert(collect);

        String url = SpringCtxHolder.getProperty("abc12366.uc.url") + "/todo/task/do/award/{userId}/{taskCode}";
        String responseStr;
        String sysTaskId = TaskConstant.SYS_TASK_COURSE_COLLECT_CODE;
        responseStr = restTemplateUtil.send(url, HttpMethod.POST, request,userId,sysTaskId);
//        System.out.println(responseStr);


        CurriculumCollectBo collectBO = new CurriculumCollectBo();
        BeanUtils.copyProperties(collect, collectBO);
        return collectBO;
    }

    @Override
    public void delete(String curriculumId, HttpServletRequest request) {
        LOGGER.info("{}:{}", curriculumId, request);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("curriculumId", curriculumId, "userId", userId);
        collectMapper.delete(map);
    }

    @Override
    public List<CurriculumCollectBo> selectList(String curriculumId) {
        LOGGER.info("{}", curriculumId);
        return collectRoMapper.selectList(curriculumId);
    }

    @Override
    public String selectExist(String curriculumId, HttpServletRequest request) {
        LOGGER.info("{}", curriculumId);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("curriculumId", curriculumId, "userId", userId);
        String cnt = collectRoMapper.selectExist(map)+"";
        return cnt;
    }

}
