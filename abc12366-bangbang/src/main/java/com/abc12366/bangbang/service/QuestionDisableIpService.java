package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.question.QuestionDisableIp;
import com.abc12366.bangbang.model.question.bo.QuestionDisableIpBo;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/18 15:48
 */
public interface QuestionDisableIpService {

    List<QuestionDisableIpBo> selectList(Map map);

    /*禁言*/
    void disable(QuestionDisableIp questionDisableUser);

    /*撤销禁言*/
    void enable(String ip);

}
