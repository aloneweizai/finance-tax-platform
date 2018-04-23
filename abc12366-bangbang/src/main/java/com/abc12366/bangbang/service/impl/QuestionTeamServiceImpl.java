package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.model.question.QuestionTeam;
import com.abc12366.bangbang.service.QuestionTeamService;
import com.abc12366.gateway.util.Utils;
import org.springframework.stereotype.Service;

/**
 * @Author liuQi
 * @Date 2017/9/15 11:47
 */
@Service
public class QuestionTeamServiceImpl implements QuestionTeamService{

    @Override
    public void add(QuestionTeam questionTeam) {
        //先校验是否有资格




    }



    private void validAddTeam(QuestionTeam questionTeam){
        String userId = Utils.getUserId();


    }
}
