package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.order.UserAddress;
import com.abc12366.uc.model.order.bo.UserAddressBO;
import com.abc12366.uc.model.order.bo.UserAddressUpdateBO;
import com.abc12366.uc.service.order.UserAddressService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 发票控制类
 *
 * @author lizhongwei
 * @create 2017-05-16
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/address", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserAddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAddressController.class);

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 用户地址列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity selectList(@PathVariable("userId") String userId, @RequestParam(value = "page",
            defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<UserAddressBO> userAddressList = userAddressService.selectBOList(userId);
        LOGGER.info("{}", userAddressList);
        return (userAddressList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) userAddressList, "total", ((Page) userAddressList)
                        .getTotal()));
    }

    @GetMapping(path = "/{userId}/{id}")
    public ResponseEntity selectOne(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(id);
        userAddress.setUserId(userId);
        UserAddressBO userAddressBO = userAddressService.selectOne(userAddress);
        LOGGER.info("{}", userAddressBO);
        return ResponseEntity.ok(Utils.kv("data", userAddressBO));
    }

    /**
     * 用户地址新增
     *
     * @param userId
     * @return
     */
    @PostMapping(path = "/{userId}")
    public ResponseEntity addUserAddress(@PathVariable("userId") String userId, @Valid @RequestBody UserAddressBO
            userAddressBO) {
        userAddressBO.setUserId(userId);
        UserAddressBO bo = userAddressService.addUserAddress(userAddressBO);

        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 用户地址修改
     *
     * @param userId
     * @return
     */
    @PutMapping(path = "/{userId}/{id}")
    public ResponseEntity updateUserAddress(@PathVariable("userId") String userId, @PathVariable("id") String id,
                                            @Valid @RequestBody UserAddressBO userAddressBO) {
        userAddressBO.setId(id);
        userAddressBO.setUserId(userId);
        UserAddressBO bo = userAddressService.updateUserAddress(userAddressBO);

        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 用户地址信息删除
     *
     * @param userId
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{userId}/{id}")
    public ResponseEntity update(@PathVariable("userId") String userId, @PathVariable("id") String id) {
        UserAddressBO userAddressBO = new UserAddressBO();
        userAddressBO.setId(id);
        userAddressBO.setUserId(userId);
        int bo = userAddressService.deleteByIdAndUserId(userAddressBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 用户设置默认地址
     *
     * @param userId
     * @return
     */
    @PutMapping(path = "/default/{userId}/{id}")
    public ResponseEntity setDefaultAddrees(@PathVariable("userId") String userId, @PathVariable("id") String id,
                                            @Valid @RequestBody UserAddressUpdateBO userAddressBO) {
        userAddressBO.setId(id);
        userAddressBO.setUserId(userId);
        UserAddressUpdateBO bo = userAddressService.setDefaultAddrees(userAddressBO);

        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

}
