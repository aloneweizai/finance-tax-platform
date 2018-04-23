package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.QuestionTeam;
import com.abc12366.bangbang.service.QuestionTeamService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liuQi
 * @Date 2017/9/15 11:41
 * 帮邦团队接口
 */
@RestController
@RequestMapping(path = "/questionTeam", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class QuestionTeamController {

    @Autowired
    private QuestionTeamService questionTeamService;

    /* 专家添加 */
    @PostMapping(path = "/add")
    public ResponseEntity add(@RequestBody QuestionTeam questionTeam) {
        questionTeamService.add(questionTeam);
        return ResponseEntity.ok(Utils.kv("data", questionTeam));
    }





}
