package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.UserBank;
import com.abc12366.uc.model.bo.UserBankBO;
import com.abc12366.uc.service.UserBankService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 银行卡控制器
 *
 * @author lizhongwei
 * @create 2017-04-24 2:51 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/user/bank", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserBankController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBankController.class);

    @Autowired
    private UserBankService userBankService;

    /**
     * 银行卡列表查询
     */
    /*@GetMapping("/user")
    public ResponseEntity selectUserBankList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                             @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                             @RequestParam(value = "bankName", required = false) String bankName) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<UserBank> UserBankList = userBankService.selectUserBankList(map);
        LOGGER.info("{}", UserBankList);
        return (UserBankList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) UserBankList, "total", ((Page) UserBankList).getTotal()));
    }*/

    /**
     * 银行卡列表查询
     */
    @GetMapping("/user")
    public ResponseEntity selectUserBankList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "id") String id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<UserBank> userBankList = userBankService.selectUserBankList(map);
        LOGGER.info("{}", userBankList);
        return (userBankList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) userBankList, "total", ((Page) userBankList).getTotal()));
    }
    
    /**
     * 查看银行卡详情
     *
     * @param id 主键ID
     * @return ResponseEntity {@linkplain UserBank UserBank}响应实体
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable("id") String id) {
        UserBank userBank = userBankService.selectById(id);
        LOGGER.info("{}", userBank);
        return ResponseEntity.ok(Utils.kv("data", userBank));
    }

    /**
     * 银行卡删除
     *
     * @param id 银行卡ID
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        userBankService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 银行卡修改
     * @param id           银行卡ID
     * @return ResponseEntity {@linkplain UserBank UserBank}响应实体
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody UserBankBO userBank, @PathVariable("id") String id) {
        LOGGER.info("{}", userBank, id);
        userBank.setId(id);
        UserBankBO userBankBO = userBankService.update(userBank);
        LOGGER.info("{}", userBankBO);
        return ResponseEntity.ok(Utils.kv("data", userBankBO));
    }

    /**
     * 银行卡新增
     *
     * @return ResponseEntity UserBankBO实体
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody UserBankBO bo) {
        LOGGER.info("{}", bo);
        UserBankBO userBankBO = userBankService.insert(bo);
        LOGGER.info("{}", userBankBO);
        return ResponseEntity.ok(Utils.kv("data", userBankBO));
    }
}
