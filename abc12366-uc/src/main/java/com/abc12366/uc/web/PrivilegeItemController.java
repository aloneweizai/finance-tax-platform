package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.PrivilegeItem;
import com.abc12366.uc.model.bo.PrivilegeItemBO;
import com.abc12366.uc.service.PrivilegeItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-22
 * Time: 10:18
 */
@RestController
@RequestMapping(path = "/privilege/item", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class PrivilegeItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeItemController.class);

    @Autowired
    private PrivilegeItemService privilegeItemService;

    @GetMapping(path = "/{levelId}")
    public ResponseEntity selecOne(@PathVariable("levelId") String levelId) {
        LOGGER.info("{}", levelId);
        PrivilegeItem privilegeItem = privilegeItemService.selectOne(levelId);
        return ResponseEntity.ok(Utils.kv("data", privilegeItem));
    }

    @PutMapping()
    public ResponseEntity update(@Valid @RequestBody PrivilegeItemBO privilegeItemBO) {
        LOGGER.info("{}", privilegeItemBO);
        PrivilegeItem privilegeItem = privilegeItemService.update(privilegeItemBO);
        return ResponseEntity.ok(Utils.kv("data", privilegeItem));
    }

    @PostMapping()
    public ResponseEntity insert(@Valid @RequestBody PrivilegeItemBO privilegeItemBO) {
        LOGGER.info("{}", privilegeItemBO);
        PrivilegeItem privilegeItem = privilegeItemService.insert(privilegeItemBO);
        return ResponseEntity.ok(Utils.kv("data", privilegeItem));
    }

    @DeleteMapping(path = "/{levelId}")
    public ResponseEntity delete(@PathVariable("levelId") String levelId) {
        LOGGER.info("{}", levelId);
        int i = privilegeItemService.delete(levelId);
        return ResponseEntity.ok(Utils.kv("data", i));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity selecOneByUser(@PathVariable("userId") String userId) {
        LOGGER.info("{}", userId);
        PrivilegeItem privilegeItem = privilegeItemService.selecOneByUser(userId);
        return ResponseEntity.ok(Utils.kv("data", privilegeItem));
    }
}
