package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.bo.QcBangUserBo;
import com.abc12366.bangbang.model.question.bo.QcTitleBo;
import com.abc12366.bangbang.service.QcUserService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 帮邦用户管理
 * User: xieyanmao
 * Date: 2017-10-09
 * Time: 17:23
 */
@RestController
@RequestMapping(path = "/qcuser", headers = Constant.VERSION_HEAD + "=1")
public class QcUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QcUserController.class);

    @Autowired
    private QcUserService qcUserService;

    /**
     * 查询用户列表
     */
    @GetMapping(path = "/selectList")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<QcBangUserBo> userBoList = qcUserService.selectList();
        if(userBoList != null){
            for(QcBangUserBo qcBangUserBo : userBoList){
                List<QcTitleBo> qcTitleBoList = qcUserService.selectQuestionList(qcBangUserBo.getUserId());
                qcBangUserBo.setQcTitleBoList(qcTitleBoList);
            }
        }
        return (userBoList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) userBoList, "total", ((Page) userBoList).getTotal
                        ()));
    }

}
