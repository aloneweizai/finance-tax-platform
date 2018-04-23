package com.abc12366.uc.web.wx;


import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.weixin.bo.person.WxPerson;
import com.abc12366.uc.service.IWxPersonService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 微信用户接口
 *
 * @author zhushuai 2017-7-28
 */
@Controller
public class WxUserController {


    private static final Logger LOGGER = LoggerFactory.getLogger(WxUserController.class);
    @Autowired
    private IWxPersonService iWxPersonService;

    /**
     * 同步微信用户到本地数据库
     * @param request 上下文
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PostMapping("/wxuser/synchro")
    public ResponseEntity synchroUser(HttpServletRequest request) {
        if (iWxPersonService.startUsersynchro()) {
            return ResponseEntity.ok(Utils.kv());
        } else {
        	return ResponseEntity.ok(Utils.bodyStatus(9999, "同步微信用户失败"));
        }
    }

    /**
     * 数据库获取微信用户列表
     * @param person  用户
     * @param page 页数
     * @param size 大小
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxuser/list")
    public ResponseEntity selectList(WxPerson person,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{}", person, page, size);
        List<WxPerson> dataList = iWxPersonService.selectList(person, page, size);

        PageInfo<WxPerson> pageInfo = new PageInfo<WxPerson>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                "total", pageInfo.getTotal()));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 查看单个用户详细信息
     * @param openid 微信id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxuser/{openid}")
    public ResponseEntity select(@PathVariable("openid") String openid) {
        LOGGER.info("{}", openid);

        WxPerson person = iWxPersonService.selectOne(openid);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", person));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 从微信服务器同步单个用户信息
     * @param openid 微信id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PutMapping("/wxuser/synchro/{openid}")
    public ResponseEntity synchroOne(@PathVariable("openid") String openid) {
        LOGGER.info("{}", openid);

        WxPerson person = iWxPersonService.synchroOne(openid);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", person));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
    
    /**
     * 从微信获取用户信息
     * @param openid 微信ID
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxuser/getOneFromWx/{openid}")
    public ResponseEntity getOneFromWx(@PathVariable("openid") String openid) {
        LOGGER.info("{}", openid);

        WxPerson person = iWxPersonService.getOneFromWx(openid);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", person));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    
    }

}
