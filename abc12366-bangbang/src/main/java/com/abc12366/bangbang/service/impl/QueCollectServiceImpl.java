package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionCollectMapper;
import com.abc12366.bangbang.mapper.db1.QuestionMapper;
import com.abc12366.bangbang.mapper.db2.QuestionCollectRoMapper;
import com.abc12366.bangbang.model.question.Question;
import com.abc12366.bangbang.model.question.QuestionCollect;
import com.abc12366.bangbang.model.question.bo.QuestionBo;
import com.abc12366.bangbang.service.QueCollectService;
import com.abc12366.bangbang.util.BangBangDtLogUtil;
import com.abc12366.bangbang.util.MapUtil;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/9/15.
 */
@Service
public class QueCollectServiceImpl implements QueCollectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueCollectServiceImpl.class);

    @Autowired
    private QuestionMapper quesionMapper;

    @Autowired
    private QuestionCollectMapper collectMapper;

    @Autowired
    private QuestionCollectRoMapper collectRoMapper;

    @Autowired
    private BangBangDtLogUtil bangBangDtLogUtil;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public String insert(String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String userId = Utils.getUserId();


                QuestionCollect collect = new QuestionCollect();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        collect.setUserId(userId);
        collect.setCollectId(uuid);
        collect.setQuestionId(id);
        collect.setCollectTime(new Date());

        Map map = MapUtil.kv("questionId", id, "userId", userId);
        int cnt =  collectRoMapper.selectExist(map);

        if(cnt >0){
            throw new ServiceException(6116);
        }

        int collectCnt = collectRoMapper.selectCollectCnt(id)+1;

        Question question = new Question();
        question.setId(id);
        question.setCollectNum(collectCnt);

        quesionMapper.updateByPrimaryKeySelective(question);

        int result = collectMapper.insert(collect);

        //帮邦日志记录表
        //日志类型,问题或者秘籍ID,回复ID,来源ID,用户ID,被关注用户ID
//        bangBangDtLogUtil.insertLog(4, collect.getQuestionId(), "", collect.getCollectId(), collect.getUserId(), "");


        String url = SpringCtxHolder.getProperty("abc12366.uc.url") + "/todo/task/do/award/{userId}/{taskCode}";
        String sysTaskId = TaskConstant.SYS_TASK_MRWDSC_CODE;
        restTemplateUtil.send(url, HttpMethod.POST, request, userId, sysTaskId);

        return collectCnt+"";
    }

    @Override
    public String delete(String id, HttpServletRequest request) {
        LOGGER.info("{}:{}", id, request);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("questionId", id, "userId", userId);

        int collectCnt = collectRoMapper.selectCollectCnt(id)-1;

        Question question = new Question();
        question.setId(id);
        question.setCollectNum(collectCnt);

        quesionMapper.updateByPrimaryKeySelective(question);

        collectMapper.delete(map);


        return collectCnt+"";
    }

    @Override
    public List<QuestionBo> selectList(String userId) {
        LOGGER.info("{}", userId);
        return collectRoMapper.selectList(userId);
    }

    @Override
    public String selectExist(String id, HttpServletRequest request) {
        LOGGER.info("{}", id);
        String userId = Utils.getUserId(request);
        Map map = MapUtil.kv("questionId", id, "userId", userId);
        String cnt = collectRoMapper.selectExist(map)+"";
        return cnt;
    }

}
