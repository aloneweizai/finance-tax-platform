package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.UserExtendBO;
import com.abc12366.uc.model.bo.UserExtendListBO;
import com.abc12366.uc.model.bo.UserExtendUpdateBO;
import com.abc12366.uc.service.RealNameValidationService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实名认证接口控制器
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-04
 * Time: 10:29
 */
@RestController
@RequestMapping(path = "/realnamevalidation", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class RealNameValidationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RealNameValidationController.class);

    @Autowired
    private RealNameValidationService realNameValidationService;

    /**
     * 查询用户实名认证列表
     *
     * @param username    用户名
     * @param status      用户状态
     * @param realName    姓名
     * @param phone       手机号码
     * @param validStatus 实名认证状态
     * @param page        页码
     * @param size        每页数据量
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String username,
                                     @RequestParam(required = false) Boolean status,
                                     @RequestParam(required = false) String realName,
                                     @RequestParam(required = false) String phone,
                                     @RequestParam(required = false) String validStatus,
                                     @RequestParam(required = false) String validType,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {

        Map<String, Object> map = new HashMap<>();
        if (!org.springframework.util.StringUtils.isEmpty(username)) {
            username = username.toLowerCase().trim();
        }
        map.put("username", username);
        map.put("status", status);
        map.put("realName", realName);
        map.put("phone", phone);
        map.put("validStatus", validStatus);
        map.put("validType", validType);
        LOGGER.info("{}:{}:{}", map, page, size);
        List<UserExtendListBO> userExtendBOList = realNameValidationService.selectList(map, page, size);
        PageInfo<UserExtendListBO> pageInfo = new PageInfo<>(userExtendBOList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 手动审核实名认证
     *
     * @param userId             用户ID
     * @param validStatus        认证状态
     * @param userExtendUpdateBO 用户扩展信息
     * @return 用户扩展信息
     * @throws ParseException 日期转换异常
     */
    @PutMapping(path = "/{userId}/{validStatus}")
    public ResponseEntity realNameValidate(@PathVariable String userId,
                                           @PathVariable String validStatus,
                                           @Valid @RequestBody(required = false) UserExtendUpdateBO userExtendUpdateBO)
            throws ParseException {
        LOGGER.info("{}:{}:{}", userId, validStatus, userExtendUpdateBO);
        UserExtendBO userExtendBO = realNameValidationService.validate(userId.trim(), validStatus.trim(),
                userExtendUpdateBO);
        return ResponseEntity.ok(Utils.kv("data", userExtendBO));
    }

}
