package com.abc12366.uc.web;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.service.AuthService;
import com.abc12366.uc.service.IWxGzhService;
import com.abc12366.uc.service.UserService;
import com.abc12366.uc.util.StringUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User测试控制器类，包含CRUD接口；以常规JSON形式返回数据
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-20 3:18 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/user", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private IWxGzhService iWxGzhService;

    @SuppressWarnings("rawtypes")
    @PutMapping("/changeheadbywx/{userid}/{mediaid}")
    public ResponseEntity changeHeadByWx(@PathVariable("userid") String userid, @PathVariable("mediaid") String mediaid
            , HttpServletRequest request) {
        String filePath = iWxGzhService.getWxDownFilePath(userid, mediaid, request);

        UserUpdateBO userUpdateDTO = new UserUpdateBO();
        userUpdateDTO.setId(userid);
        userUpdateDTO.setUserPicturePath(filePath);
        UserBO user = userService.update(userUpdateDTO);
        LOGGER.info("{}", user);
        return ResponseEntity.ok(Utils.kv("data", user));
    }


    /**
     * 查询用户列表，支持多标签查询
     *
     * @param username   用户名
     * @param phone      手机号
     * @param nickname   昵称
     * @param status     用户状态
     * @param tagId      标签ID
     * @param realName   真实姓名
     * @param pointsOper 积分操作符 小于等于：lte，等于：equals，大于等于：gte
     * @param points     积分
     * @param expOper    经验值操作符 小于等于：lte，等于：equals，大于等于：gte
     * @param exp        经验值
     * @param vipLevel   会员等级
     * @param medal      用户等级
     * @param startDate  注册开始日期, 默认为最近30天
     * @param endDate    注册结束日期, 默认为当天
     * @param page       当前页
     * @param size       每页大小
     * @return List 用户列表
     */
    @GetMapping
    public ResponseEntity selectList(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) String tagId,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) String pointsOper,
            @RequestParam(required = false) String points,
            @RequestParam(required = false) String expOper,
            @RequestParam(required = false) String exp,
            @RequestParam(required = false) String vipLevel,
            @RequestParam(required = false) String medal,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("medal", medal);
        map.put("vipLevel", vipLevel);
        map.put("expOper", StringUtil.operReplace(expOper));
        map.put("exp", exp);
        map.put("pointsOper", StringUtil.operReplace(pointsOper));
        map.put("points", points);
        map.put("realName", realName);
        if (!StringUtils.isEmpty(username)) {
            username = username.toLowerCase().trim();
        }
        map.put("username", username);
        map.put("phone", phone);
        map.put("nickname", nickname);
        map.put("status", status);
        map.put("tagId", tagId);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        LOGGER.info("{}:{}:{}", map, page, size);

        List<UserListBO> userList = userService.selectList(map, page, size);
        PageInfo<UserListBO> pageInfo = new PageInfo<>(userList);
        LOGGER.info("{}", userList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 根据用户ID查询用户
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        Map map = userService.selectOne(id);
        LOGGER.info("{}", map);
        return ResponseEntity.ok(Utils.kv("user", map.get("user"), "user_extend", map.get("user_extend")));
    }

    /**
     * 根据用户ID查询用户(后台管理系统使用，敏感信息不做模糊化处理)
     */
    @GetMapping(path = "/admin/{id}")
    public ResponseEntity<?> selectOneForAdmin(@PathVariable String id) {
        LOGGER.info("{}", id);
        Map map = userService.selectOneForAdmin(id);
        LOGGER.info("{}", map);
        return ResponseEntity.ok(Utils.kv("user", map.get("user"), "user_extend", map.get("user_extend")));
    }

    @GetMapping(path = "/wx/{id}")
    public ResponseEntity<?> selectOneByWx(@PathVariable String id) {
        LOGGER.info("{}", id);
        Map map = userService.selectOne(id);
        LOGGER.info("{}", map);
        return (map == null) ?
                ResponseEntity.ok(Utils.kv("user", null)) :
                ResponseEntity.ok(Utils.kv("user", map.get("user")));
    }

    @GetMapping(path = "/wx/openid/{openid}")
    public ResponseEntity<?> selectByopenid(@PathVariable String openid) {
        UserBO user = userService.selectByopenid(openid);
        if (user == null) {
            throw new ServiceException(4018);
        }
        LOGGER.info("{}", user);
        return ResponseEntity.ok(Utils.kv("data", user));
    }

    //根据用户名或者电话查询用户
    @GetMapping(path = "/u/{usernameOrPhone}")
    public ResponseEntity selectByUsernameOrPhone(@PathVariable String usernameOrPhone) {
        LOGGER.info("{}", usernameOrPhone);
        UserBO user = userService.selectByUsernameOrPhone(usernameOrPhone);
        if (user == null) {
            throw new ServiceException(4018);
        }
        LOGGER.info("{}", user);
        return ResponseEntity.ok(Utils.kv("data", user));
    }
    
    //根据用户名或者电话查询用户简单信息
    @GetMapping(path = "/u/jyxx/{usernameOrPhone}")
    public ResponseEntity selectByUnameOrPhone(@PathVariable String usernameOrPhone) {
        LOGGER.info("{}", usernameOrPhone);
        UserJyxx jyxx = userService.selectByUnameOrPhone(usernameOrPhone);
        if (jyxx == null) {
            throw new ServiceException(4018);
        }
        LOGGER.info("{}", jyxx);
        return ResponseEntity.ok(Utils.kv("data", jyxx));
    }
    
    

    //用户更换手机号码
    @PutMapping(path = "/phone/{id}")
    public ResponseEntity updatePhone(@Valid @RequestBody UserPhoneBO bo, @PathVariable String id) {
        bo.setId(id);
        LOGGER.info("{}", bo);

        UserBO user = userService.updatePhone(bo);
        LOGGER.info("{}", user);
        return ResponseEntity.ok(Utils.kv("data", user));
    }

    //更新用户信息
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody UserUpdateBO userUpdateDTO, @PathVariable String id) {
        LOGGER.info("修改用户信息：{}", userUpdateDTO);
        userUpdateDTO.setId(id);
        UserBO user = userService.update(userUpdateDTO);
        LOGGER.info("{}", user);
        return ResponseEntity.ok(Utils.kv("data", user));
    }

    //更新用户信息
    @PutMapping(path = "/wx/{id}")
    public ResponseEntity wxupdate(@Valid @RequestBody UserUpdateBO userUpdateDTO, @PathVariable String id) {
        LOGGER.info("{}", userUpdateDTO);
        userUpdateDTO.setId(id);
        UserBO user = userService.update(userUpdateDTO);
        LOGGER.info("{}", user);
        return ResponseEntity.ok(Utils.kv("data", user));
    }

    //删除用户
    @DeleteMapping(path = "/{userId}")
    public ResponseEntity delete(@PathVariable String userId) {
        LOGGER.info("{}", userId);
        userService.delete(userId);
        return ResponseEntity.ok(Utils.kv());
    }

    // 用户token校验,根据用户token获取用户并刷新token
    @GetMapping(path = "/token/{token}")
    public ResponseEntity authAndRefreshToken(@PathVariable String token, HttpServletRequest request) {
        LOGGER.info("{}", token);

        // token校验
        boolean isAuth = authService.isAuthentication(token, request);
        if (!isAuth) {
            return ResponseEntity.ok().build();
        }
        // 根据用户token获取用户
        UserBO userBO = userService.authAndRefreshToken(token);
        LOGGER.info("{}", userBO);
        return ResponseEntity.ok(Utils.kv("data", userBO));
    }

    //用户修改密码
    @PutMapping(path = "/password")
    public ResponseEntity updatePassword(@Valid @RequestBody PasswordUpdateBO passwordUpdateBO, HttpServletRequest
            request) {
        LOGGER.info("用户修改密码：{}", passwordUpdateBO);
        Boolean message = userService.updatePassword(passwordUpdateBO, request);
        LOGGER.info("{}", message);
        LOGGER.info("用户修改密码结果：{}", message);
        return ResponseEntity.ok(Utils.kv("data", message));
    }

    /**
     * 启用、禁用
     */
    @PutMapping(path = "/{id}/{status}")
    public ResponseEntity enableOrDisable(@PathVariable String id, @PathVariable String status) {
        LOGGER.info("{}:{}", id, status);
        userService.enableOrDisable(id, status);
        return ResponseEntity.ok(Utils.kv());
    }

    //用户未登录状态下根据用户ID查询用户简单信息：用户编号，用户昵称，用户头像，擅长领域
    @GetMapping(path = "/notoken/simple/{userId}")
    public ResponseEntity<?> selectSimple(@PathVariable String userId) {
        LOGGER.info("{}", userId);
        UserSimpleInfoBO user = userService.selectSimple(userId);
        LOGGER.info("查询到用户简单信息为：{}", user);
        return ResponseEntity.ok(Utils.kv("user", user));
    }

    //用户登录后发送短信接口
    @PostMapping(path = "/verifycode")
    public ResponseEntity loginedSendCode(@Valid @RequestBody LoginedSendCodeBO sendCodeBO) {
        userService.loginedSendCode(sendCodeBO);
        return ResponseEntity.ok(Utils.kv());
    }

    //用户登录后校验短信验证码接口
    @PostMapping(path = "/verify")
    public ResponseEntity loginedVerifyCode(@Valid @RequestBody LoginedVerifyCodeBO verifyCodeBO) {
        userService.loginedVerifyCode(verifyCodeBO);
        return ResponseEntity.ok(Utils.kv());
    }

    //用户绑定手机
    @PutMapping(path = "/phone")
    public ResponseEntity bindPhone(@Valid @RequestBody BindPhoneBO bindPhoneBO) {
        LOGGER.info("绑定手机入参：{}", bindPhoneBO.toString());
        userService.bindPhone(bindPhoneBO);
        return ResponseEntity.ok(Utils.kv());
    }

    //用户手机+验证码登录专用发送短信接口
    @PostMapping(path = "/phonelogin/code")
    public ResponseEntity phoneLoginSendCode(@Valid @RequestBody SendPhoneCodeParam sendCodeBO) {
        userService.phoneLoginSendCode(sendCodeBO);
        return ResponseEntity.ok(Utils.kv());
    }

    //旧的手机号码是否有效校验
    @PostMapping(path = "/oldphone")
    public ResponseEntity verifyOldPhone(@Valid @RequestBody oldPhoneBO oldPhone) {
        userService.verifyOldPhone(oldPhone);
        return ResponseEntity.ok(Utils.kv());
    }

    @GetMapping(path = "/realname")
    public ResponseEntity isRealName() {
        IsRealNameBO isRealName = userService.isRealName();
        return ResponseEntity.ok(Utils.kv("data", isRealName));
    }

    /**
     * 修改微信绑定信息
     *
     * @return
     */
    @PostMapping(path = "/wx/changeWxBdxx")
    public ResponseEntity changeWxBdxx(@RequestBody UserUpdateBO userUpdateDTO) {
        int status = userService.changeWxBdxx(userUpdateDTO);
        return ResponseEntity.ok(Utils.kv("data", status));
    }

    //根据手机号码查询用户
    @GetMapping(path = "/u/phone/{phone}")
    public ResponseEntity selectOneByPhone(@PathVariable String phone) {
        LOGGER.info("根据手机号码查看用户：{}", phone);
        UserBO user = userService.selectOneByPhone(phone);
        LOGGER.info("根据手机号码查看用户结果：{}", user);
        return ResponseEntity.ok(Utils.kv("data", user));
    }

    /**
     * 重置用户密码接口
     * @param resetPwdBO
     * @return ResponseEntity
     */
    @PutMapping(path = "/resetpwd")
    public ResponseEntity resetPassword(@Valid @RequestBody UserResetPwdBO resetPwdBO){
        LOGGER.info("重置用户密码：{}",resetPwdBO);
        userService.resetPassword(resetPwdBO);
        return ResponseEntity.ok(Utils.kv());
    }
}
