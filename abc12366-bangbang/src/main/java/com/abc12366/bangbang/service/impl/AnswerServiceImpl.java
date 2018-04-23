package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.AnswerMapper;
import com.abc12366.bangbang.mapper.db1.AskMapper;
import com.abc12366.bangbang.mapper.db2.AnswerRoMapper;
import com.abc12366.bangbang.mapper.db2.AskRoMapper;
import com.abc12366.bangbang.model.Answer;
import com.abc12366.bangbang.model.Ask;
import com.abc12366.bangbang.model.bo.*;
import com.abc12366.bangbang.service.AnswerService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-12
 * Time: 11:42
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerServiceImpl.class);

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private AnswerRoMapper answerRoMapper;

    @Autowired
    private AskRoMapper askRoMapper;

    @Autowired
    private AskMapper askMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public AnswerBO insert(AnswerInsertBO answerInsertBO) {
        LOGGER.info("{}", answerInsertBO);
        AskBO askBO = askRoMapper.selectOne(answerInsertBO.getAskId());
        if (askBO == null) {
            LOGGER.warn("该问题已经不存在，不能回答！");
            throw new ServiceException(4101);
        }
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerInsertBO, answer);
        Date date = new Date();
        answer.setId(Utils.uuid());
        answer.setCreateTime(date);
        answer.setLastUpdate(date);
        //答案的类型「type」跟随问题类型
        answer.setType(askBO.getType());
        int result = answerMapper.insert(answer);
        if (result != 1) {
            return null;
        }
        AnswerBO answerBO = new AnswerBO();
        BeanUtils.copyProperties(answer, answerBO);
        return answerBO;
    }

    @Override
    public AnswerBO update(String id, AnswerUpdateBO answerUpdateBO, String userId) {
        LOGGER.info("{}:{}", id, answerUpdateBO);
        AnswerBO answerBO = answerRoMapper.selectOne(id);
        if (answerBO == null) {
            return null;
        }
        //更新操作权限控制：只有答案拥有者才能修改答案
        //TODO
        AskBO askBO = askRoMapper.selectOne(answerUpdateBO.getAskId());
        if (askBO == null) {
            LOGGER.warn("该问题已经不存在，不能回答！");
            throw new ServiceException(4101);
        }
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerUpdateBO, answer);
        answer.setId(id);
        answer.setLastUpdate(new Date());
        //答案的类型「type」跟随问题类型
        answer.setType(answerUpdateBO.getType());
        int result = answerMapper.update(answer);
        if (result != 1) {
            return null;
        }

        BeanUtils.copyProperties(answer, answerBO);
        return answerBO;
    }

    @Override
    public int delete(String id, String userId) {
        LOGGER.info("{}", id);
        AnswerBO answerBO = answerRoMapper.selectOne(id);
        if (answerBO == null) {
            return 0;
        }
        //1.更新操作权限控制：只有答案拥有者才能修改答案
        //TODO

        //2.用户在回答问题超过某一阈值（24小时）之后不能删除
        if (System.currentTimeMillis() > (answerBO.getCreateTime().getTime() + (24 * 3600 * 1000))) {
            LOGGER.warn("用户在回答问题超过某一阈值（24小时）之后不能删除");
            return 0;
        }

        int result = answerMapper.delete(id);
        if (result != 1) {
            return 0;
        }
        return 1;
    }

    @Override
    public List<AnswerBO> selectListForAdmin(AnswersQueryParamBO answersQueryParamBO) {
        LOGGER.info("{}", answersQueryParamBO);
        return answerRoMapper.selectListForAdmin(answersQueryParamBO);
    }

    @Override
    public List<AnswerBO> selectListForUser(AnswerQueryParamBO answerQueryParamBO) {
        LOGGER.info("{}", answerQueryParamBO);
        return answerRoMapper.selectListForUser(answerQueryParamBO);
    }

    @Override
    public int block(String id, String userId) {
        AnswerBO answerBO = answerRoMapper.selectOne(id);
        if (answerBO == null) {
            LOGGER.warn("更新失败，不存在可被更新的数据，参数:ID=", id);
            throw new ServiceException(4102);
        }
        //屏蔽权限,只有后台用户可以做屏蔽操作
        //TODO

        Answer answer = new Answer();
        BeanUtils.copyProperties(answerBO, answer);
        answer.setLastUpdate(new Date());
        answer.setStatus("2");
        int result = answerMapper.update(answer);
        if (result != 1) {
            LOGGER.warn("更新失败，参数：{}", answer);
            throw new ServiceException(4102);
        }
        return 1;
    }

    @Override
    public AnswerBO selectOne(String id) {
        LOGGER.info("{}", id);
        return answerRoMapper.selectOne(id);
    }

    @Transactional("db1TxManager")
    @Override
    public int accept(String id, String userId) throws IOException {
        // 1.提出问题的用户可以采纳某一个答案作为问题最好的回答，此时需要修改answer表中的isAccept字段、
        AnswerBO answerBO = answerRoMapper.selectOne(id);
        if (answerBO == null) {
            return 0;
        }
        //采纳答案权限控制，提出问题的用户才可以采纳某一个答案作为问题最好的回答
        //TODO

        Answer answer = new Answer();
        BeanUtils.copyProperties(answerBO, answer);
        answer.setIsAccept(true);
        answer.setLastUpdate(new Date());
        int answerUpdateResult = answerMapper.update(answer);
        if (answerUpdateResult != 1) {
            return 0;
        }
        // 2.ask表中的isSolve，
        AskBO askBO = askRoMapper.selectOne(answerBO.getAskId());
        if (askBO == null) {
            return 0;
        }
        Ask ask = new Ask();
        BeanUtils.copyProperties(askBO, ask);
        ask.setIsSolve(true);
        ask.setLastUpdate(new Date());
        // 3.如果ask表中的exp有悬赏积分的话，需要扣除提出问题用户的积分，
        if (askBO.getPoints() > 0) {
            int points = askBO.getPoints();
            // 4.相应地，作为采纳答案的回答者应加上悬赏积分
            String url = "http://api.abc12366.com/uc/user/" + askBO.getAskedUserId();
            //请求头设置
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(Constant.VERSION_HEAD, Constant.VERSION_1);
            httpHeaders.add("Content-Type", "application/json");
            //先查出回答者的当前积分
            ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            if (responseEntity == null || !responseEntity.getStatusCode().is2xxSuccessful() || !responseEntity
                    .hasBody()) {
                throw new ServiceException(4104);
            }
            UCUserBO ucUserBO = objectMapper.readValue(((String) responseEntity.getBody()).getBytes(), UCUserBO.class);
            //请求体
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("points", "" + ucUserBO.getPoints() + points);

            HttpEntity requestEntity = new HttpEntity(requestBody, httpHeaders);
            restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        }
        int askUpdateResult = askMapper.update(ask);
        if (askUpdateResult != 1) {
            LOGGER.warn("更新失败，参数：{}", answer);
            throw new ServiceException(4102);
        }
        return 0;
    }
}
