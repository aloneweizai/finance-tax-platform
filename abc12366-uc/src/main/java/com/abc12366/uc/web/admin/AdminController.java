package com.abc12366.uc.web.admin;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.admin.AdminExtend;
import com.abc12366.uc.model.admin.bo.AdminBO;
import com.abc12366.uc.model.admin.bo.AdminUpdateBO;
import com.abc12366.uc.model.admin.bo.UserPasswordBO;
import com.abc12366.uc.service.admin.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 操作员控制器
 *
 * @author lizhongwei
 * @create 2017-05-02 10:08 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/admin/user", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    /**
     * 查询操作员列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @param username 用户名
     * @param nickname 姓名
     * @param phone    手机号
     * @param orgId    部门ID
     * @param status   用户状态
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "nickname", required = false) String nickname,
                                     @RequestParam(value = "phone", required = false) String phone,
                                     @RequestParam(value = "orgId", required = false) String orgId,
                                     @RequestParam(value = "status", required = false) Boolean status) {
        AdminBO admin = new AdminBO();
        admin.setUsername(username);
        admin.setNickname(nickname);
        admin.setStatus(status);
        admin.setOrganizationId(orgId);
        admin.setPhone(phone);
        LOGGER.info("{}", admin);

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<AdminBO> userList = adminService.selectList(admin);
        LOGGER.info("userList:{}", userList);
        return userList == null ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", userList, "total", ((Page) userList).getTotal()));
    }

    /**
     * 详情查询
     *
     * @param id PK
     * @return ResponseEntity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id) {
        AdminBO adminBO = adminService.selectOne(id);
        LOGGER.info("adminBO:{}", adminBO);
        return ResponseEntity.ok(Utils.kv("data", adminBO));
    }


    /**
     * 新增用户
     *
     * @param adminBO AdminBO
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody AdminBO adminBO) {
        AdminBO bo = adminService.addUser(adminBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 修改用户信息
     *
     * @param adminUpdateBO AdminUpdateBO
     * @param id            PK
     * @return ResponseEntity
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody AdminUpdateBO adminUpdateBO, @PathVariable("id") String id) {
        LOGGER.info("id:{}", id);
        adminUpdateBO.setId(id);
        AdminUpdateBO bo = adminService.updateUser(adminUpdateBO);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 启用、禁用
     *
     * @param adminUpdateBO AdminUpdateBO
     * @return ResponseEntity
     */
    @PutMapping(path = "/enable")
    public ResponseEntity enable(@Valid @RequestBody AdminUpdateBO adminUpdateBO) {
        LOGGER.info("{}", adminUpdateBO);
        adminService.enable(adminUpdateBO);
        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 修改用户密码
     *
     * @param userPasswordBO UserPasswordBO
     * @return ResponseEntity
     */
    @PutMapping(path = "/password")
    public ResponseEntity updateUserPwd(@Valid @RequestBody UserPasswordBO userPasswordBO) {
        LOGGER.info("userPasswordBO:{}", userPasswordBO);
        int upd = adminService.updateUserPwd(userPasswordBO);
        LOGGER.info("upd:{}", upd);
        return ResponseEntity.ok(Utils.kv("data", upd));
    }

    /**
     * 重置用户密码
     *
     * @param id PK
     * @return ResponseEntity
     */
    @PutMapping(path = "/password/{id}")
    public ResponseEntity resetUserPwd(@PathVariable("id") String id) {
        LOGGER.info("id:{}", id);
        int upd = adminService.resetUserPwd(id);
        LOGGER.info("upd:{}", upd);
        return ResponseEntity.ok(Utils.kv("data", upd));
    }

    /**
     * 删除
     *
     * @param id PK
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") String id) {
        LOGGER.info("id{}", id);
        int del = adminService.deleteUserById(id);
        LOGGER.info("del{}", del);
        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 查看用户扩展详情
     *
     * @param id PK
     * @return ResponseEntity
     */
    @GetMapping(path = "/extend/{id}")
    public ResponseEntity selectUserExtend(@PathVariable("id") String id) {
        LOGGER.info("id:{}", id);
        AdminExtend adminExtend = adminService.selectUserExtendByUserId(id);
        LOGGER.info("adminExtend:{}", adminExtend);
        return ResponseEntity.ok(Utils.kv("data", adminExtend));
    }
}
