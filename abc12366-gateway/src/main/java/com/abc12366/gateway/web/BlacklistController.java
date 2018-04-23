package com.abc12366.gateway.web;

import com.abc12366.gateway.model.Blacklist;
import com.abc12366.gateway.model.bo.BlacklistBO;
import com.abc12366.gateway.service.BlacklistService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 黑名单控制器
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-28 11:06 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/blacklist", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class BlacklistController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private BlacklistService blacklistService;

    /**
     * 黑名单列表查询
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<Blacklist> dataList = blacklistService.selectList();
        PageInfo<Blacklist> pageInfo = new PageInfo<>(dataList);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 查询单条黑名单记录
     *
     * @param id PK
     * @return ResponseEntity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id) {
        LOGGER.info(id);
        Blacklist blacklist = blacklistService.selectOne(id);
        LOGGER.info("{}", blacklist);
        return ResponseEntity.ok(Utils.kv("data", blacklist));
    }

    /**
     * 新增黑名单
     *
     * @param bo BlacklistBO
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody BlacklistBO bo) {
        LOGGER.info("{}", bo);
        Blacklist blacklist = blacklistService.insert(bo);
        LOGGER.info("{}", blacklist);
        return ResponseEntity.ok(Utils.kv("data", blacklist));
    }

    /**
     * 更新黑名单
     *
     * @param id PK
     * @param bo BlacklistBO
     * @return ResponseEntity
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") String id,
                                 @Valid @RequestBody BlacklistBO bo) {
        bo.setId(id);
        LOGGER.info("{}", bo);
        Blacklist blacklist = blacklistService.update(bo);
        LOGGER.info("{}", blacklist);
        return ResponseEntity.ok(Utils.kv("data", blacklist));
    }

    /**
     * 删除黑名单
     *
     * @param id PK
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info(id);
        blacklistService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }
}
