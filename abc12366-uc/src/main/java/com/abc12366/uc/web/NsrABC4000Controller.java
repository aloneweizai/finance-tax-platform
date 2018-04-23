package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.uc.model.abc4000.ABC4000CallbackBO;
import com.abc12366.uc.model.abc4000.NsrbindListParam;
import com.abc12366.uc.model.abc4000.ResponseForAbc4000;
import com.abc12366.uc.model.abc4000.ResponseForAbc4000Simple;
import com.abc12366.uc.service.NsrABC4000Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 查询纳税人绑定电子申报接口控制器
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 9:31
 */
@RestController
@RequestMapping(path = "/nsrbind/abc4000", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class NsrABC4000Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(NsrABC4000Controller.class);


    @Autowired
    private NsrABC4000Service nsrABC4000Service;

    /**
     * 查询用户所有纳税人绑定列表
     * @param data
     * @return
     */
    @PostMapping(path = "/list")
    public ResponseEntity selectList(@Valid @RequestBody NsrbindListParam data) {
        LOGGER.info("{}", data);
        ResponseForAbc4000 response = nsrABC4000Service.selectList(data.getUserId());
        return ResponseEntity.ok(response);
    }

    /**
     * 供外系统回调UC修改用户绑定关系数据
     * @param data
     * @return
     */
    @PostMapping(path = "/update")
    public ResponseEntity update(@Valid @RequestBody ABC4000CallbackBO data) {
        LOGGER.info("{}", data);
        ResponseForAbc4000Simple response = nsrABC4000Service.update(data);
        return ResponseEntity.ok(response);
    }
}
