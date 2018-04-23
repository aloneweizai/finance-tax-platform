package com.abc12366.uc.web.admin;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.admin.bo.AdminBO;
import com.abc12366.uc.model.admin.bo.LoginInfoBO;
import com.abc12366.uc.service.admin.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 操作员登陆退出相关控制器
 *
 * @author lizhongwei
 * @create 2017-05-02 10:08 AM
 * @since 1.0.0
 */
@Controller
@RequestMapping(path = "/admin", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class LoginController {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AdminService adminService;


    /**
     * 操作员登陆
     *
     * @param adminBO AdminBO
     * @param request HttpServletRequest
     * @return ResponseEntity
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity loginPost(@Valid @RequestBody AdminBO adminBO, HttpServletRequest request) {
        String appId = (String) request.getAttribute(Constant.APP_ID);
        AdminBO user = adminService.login(adminBO, appId);
        LOGGER.info("{}", user);
        return ResponseEntity.ok(Utils.kv("data", user));
    }

    /**
     * 操作员退出
     *
     * @param token 令牌
     * @return ResponseEntity
     */
    @PostMapping(value = "/logout/{token}")
    public ResponseEntity logout(@PathVariable("token") String token) {
        LOGGER.info("登出");
        adminService.logout(token);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 查看LoginInfo信息
     *
     * @param token 令牌
     * @return ResponseEntity
     */
    @GetMapping(path = "/token/{token}")
    public ResponseEntity selectUser(@PathVariable("token") String token) {
        long start = System.currentTimeMillis();
        LOGGER.info("token:{}", token);
        LoginInfoBO loginInfo = adminService.selectLoginInfoByToken(token);
        LOGGER.info("loginInfo:{}", loginInfo);
        long end = System.currentTimeMillis();
        long res = end - start;
        LOGGER.info("响应用时:{}毫秒", res);
        return ResponseEntity.ok(Utils.kv("data", loginInfo));
    }
}
