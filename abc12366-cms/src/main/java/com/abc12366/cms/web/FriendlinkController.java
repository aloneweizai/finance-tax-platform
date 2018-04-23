package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.FriendlinkBo;
import com.abc12366.cms.service.FriendlinkService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
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
 * 友情链接管理模块
 *
 * @author xieyanmao
 * @create 2017-06-08
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/link", headers = Constant.VERSION_HEAD + "=1")
public class FriendlinkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FriendlinkController.class);
    @Autowired
    private FriendlinkService friendlinkService;

    /**
     * 查询友情链接列表信息
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<FriendlinkBo> dataList = friendlinkService.selectList();
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 查询单个友情链接信息
     */
    @GetMapping(path = "/{friendlinkId}")
    public ResponseEntity selectOneById(@PathVariable("friendlinkId") String friendlinkId) {
        LOGGER.info("{}", friendlinkId);
        FriendlinkBo friendlinkBo = friendlinkService.selectOneById(friendlinkId);
        LOGGER.info("{}", friendlinkBo);
        return ResponseEntity.ok(Utils.kv("data", friendlinkBo));
    }

    /**
     * 新增友情链接信息
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody FriendlinkBo friendlinkBo) {
        LOGGER.info("{}", friendlinkBo);
        friendlinkBo = friendlinkService.save(friendlinkBo);
        LOGGER.info("{}", friendlinkBo);
        return ResponseEntity.ok(Utils.kv("data", friendlinkBo));
    }

    /**
     * 更新友情链接信息
     */
    @PutMapping(path = "/{friendlinkId}")
    public ResponseEntity update(@Valid @RequestBody FriendlinkBo friendlinkBo, @PathVariable("friendlinkId") String
            friendlinkId) {
        LOGGER.info("{}", friendlinkBo);
        friendlinkBo = friendlinkService.update(friendlinkBo);
        LOGGER.info("{}", friendlinkBo);
        return ResponseEntity.ok(Utils.kv("data", friendlinkBo));
    }

    /**
     * 删除友情链接信息
     */
    @DeleteMapping(path = "/{friendlinkId}")
    public ResponseEntity delete(@PathVariable String friendlinkId) {
        LOGGER.info("{}", friendlinkId);
        //删除友情链接信息
        String rtn = friendlinkService.delete(friendlinkId);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }


}
