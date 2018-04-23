package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.UserHkj;
import com.abc12366.uc.model.bo.UserBO;
import com.abc12366.uc.service.AuthService;
import com.abc12366.uc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/userhkj", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserHkjController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserHkjController.class);

    @Autowired
    private UserService userService;


    @Autowired
    private AuthService authService;
    // 用户token校验,根据用户token获取用户并刷新token
    @GetMapping(path = "/token")
    public ResponseEntity authAndRefreshToken(@RequestParam(required = true) String apptoken,
                                              @RequestParam(required = false) String sign, HttpServletRequest request) {
        LOGGER.info("-----------------------------------{}", apptoken);

        // token校验
        authService.isAuthentication(apptoken, request);
        // 根据用户token获取用户
        UserBO userBO = userService.authAndRefreshToken(apptoken);
        UserHkj userHkj = new UserHkj();
        if(userBO != null) {
            LOGGER.info("{}", userBO);
            if(userBO.getId() != null) {
                User user =userService.selectUser(userBO.getId());
                userHkj.setMobile(user.getPhone());
                userHkj.setUsername(user.getUsername());
                userHkj.setIdentify(user.getId());
            }
        }
        return ResponseEntity.ok(Utils.kv("data", userHkj));
    }

}