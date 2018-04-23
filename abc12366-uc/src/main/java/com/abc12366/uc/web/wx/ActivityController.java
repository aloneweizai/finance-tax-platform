package com.abc12366.uc.web.wx;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.weixin.WxActivity;
import com.abc12366.uc.model.weixin.bo.redpack.ActivityBO;
import com.abc12366.uc.service.IActivityService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 微信红包活动管理
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-14 10:30 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/wx/activity", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ActivityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private IActivityService iActivityService;

    /**
     * 查询微信红包活动列表-管理端
     *
     * @param name 活动名称
     * @param page 当前页
     * @param size 每页大小
     * @return ResponseEntity
     */
    @GetMapping()
    public ResponseEntity selectList(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{}", name, page, size);
        WxActivity activity = new WxActivity.Builder().name(name).build();
        List<WxActivity> dataList = iActivityService.selectList(activity, page, size);

        PageInfo<WxActivity> pageInfo = new PageInfo<>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                "total", pageInfo.getTotal()));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 查询微信红包活动列表-微信端
     *
     * @param page 当前页
     * @param size 每页大小
     * @return ResponseEntity
     */
    @GetMapping("/redpack")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{}", page, size);
        List<ActivityBO> dataList = iActivityService.selectSimpleList(page, size);

        PageInfo<ActivityBO> pageInfo = new PageInfo<>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                "total", pageInfo.getTotal()));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 查看微信红包活动
     *
     * @param id 活动ID
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id) {

        LOGGER.info("{}", id);
        WxActivity data = iActivityService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 新增微信红包活动
     *
     * @param activity WxActivity
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody WxActivity activity) {
        LOGGER.info("{}", activity);
        WxActivity data = iActivityService.insert(activity);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 更新微信红包活动
     *
     * @param id       活动ID
     * @param activity WxActivity
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @Valid @RequestBody WxActivity activity) {

        activity.setId(id);
        LOGGER.info("{}", activity);
        WxActivity data = iActivityService.update(activity);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", data));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 删除微信红包活动
     *
     * @param id 活动ID
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {

        LOGGER.info("{}", id);
        iActivityService.delete(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
}
