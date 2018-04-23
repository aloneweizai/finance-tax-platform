package com.abc12366.uc.web;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.UserExtendBO;
import com.abc12366.uc.model.bo.UserExtendUpdateBO;
import com.abc12366.uc.service.IWxGzhService;
import com.abc12366.uc.service.UserExtendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 用户扩展信息控制器
 *
 * @author liuguiyao
 * @create 2017-05-05 10:08 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/user/extend", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserExtendController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserExtendController.class);

    @Autowired
    private UserExtendService userExtendService;
    @Autowired
    private IWxGzhService iWxGzhService;

    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        UserExtendBO user_extend = userExtendService.selectOne(id);
        LOGGER.info("{}", user_extend);
        return ResponseEntity.ok(Utils.kv("data", user_extend));
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody UserExtendBO userExtendBO, HttpServletRequest request) throws IOException {
        LOGGER.info("{}", userExtendBO);
        UserExtendBO user_extend = userExtendService.insert(userExtendBO, request);
        LOGGER.info("{}", user_extend);
        return ResponseEntity.ok(Utils.kv("data", user_extend));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("{}", id);
        userExtendService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity update(@Valid @RequestBody UserExtendUpdateBO userExtendUpdateBO, @PathVariable String
            userId, HttpServletRequest request) throws IOException {
        LOGGER.info("用户更新扩展信息，{}:{}:{}", userExtendUpdateBO, userId, request);
        if (!userId.trim().equals(Utils.getUserId(request))) {
            throw new ServiceException(4190);
        }
        userExtendUpdateBO.setUserId(userId.trim());
        UserExtendBO user_extend = userExtendService.update(userExtendUpdateBO,request);
        LOGGER.info("用户更新扩展信息成功，{}", user_extend);
        return ResponseEntity.ok(Utils.kv("data", user_extend));
    }
    
    /**
     * 微信实名认证审核
     * @param userExtendUpdateBO
     * @param userId
     * @param request
     * @return
     */
    @PutMapping(path = "/wx/{userId}")
    public ResponseEntity updateWx(@Valid @RequestBody UserExtendUpdateBO userExtendUpdateBO, @PathVariable String
            userId, HttpServletRequest request) throws IOException {
        LOGGER.info("{}:{}:{}", userExtendUpdateBO, userId, request);
        
        userExtendUpdateBO.setUserId(userId);
        String filePath1=iWxGzhService.getWxDownFilePath(userExtendUpdateBO.getUserId(), userExtendUpdateBO.getFrontImage(),request);
        userExtendUpdateBO.setFrontImage(filePath1);
        
        String filePath2=iWxGzhService.getWxDownFilePath(userExtendUpdateBO.getUserId(), userExtendUpdateBO.getBackImage(),request);
        userExtendUpdateBO.setBackImage(filePath2);
        
        if (!userId.trim().equals(Utils.getUserId(request))) {
            throw new ServiceException(4190);
        }
        userExtendUpdateBO.setUserId(userId.trim());
        UserExtendBO user_extend = userExtendService.update(userExtendUpdateBO, request);
        LOGGER.info("{}", user_extend);
        return ResponseEntity.ok(Utils.kv("data", user_extend));
    }
}
