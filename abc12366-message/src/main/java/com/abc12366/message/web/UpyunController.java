package com.abc12366.message.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.model.UpyunTemplate;
import com.abc12366.message.service.UpyunService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 又拍云相关接口控制器
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-24
 * Time: 15:58
 */
@RestController
@RequestMapping(path = "/upyun", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UpyunController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpyunController.class);

    @Autowired
    private UpyunService upyunService;

    /**
     * 查询又拍云短信内容模板列表接口
     *
     * @param status
     * @param page
     * @param size
     * @return ResponseEntity {@linkplain com.abc12366.message.model.UpyunTemplate}
     */
    @GetMapping(path = "/templates")
    public ResponseEntity selectList(@RequestParam(value = "status", required = false) String status,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        LOGGER.info("查询又拍云短信模板列表");
        Map<String, String> map = new HashMap<>();
        map.put("status", status == null ? null : status.trim());
        List<UpyunTemplate> teplateList = upyunService.selectList(map);
        LOGGER.info("查询到的又拍云短信内容模板有：{}", teplateList);
        return (teplateList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) teplateList, "total", ((Page) teplateList).getTotal()));
    }

    /**
     * 同步又拍云配置的短信模板到本地
     *
     * @return ResponseEntity
     */
    @PostMapping(path = "/synchronize/template")
    public ResponseEntity synchronizeTemplate() {
        LOGGER.info("同步又拍云配置的短信模板到本地");
        upyunService.synchronizeTemplate();
        LOGGER.info("同步又拍云配置的短信模板到本地成功");
        return ResponseEntity.ok(Utils.kv());
    }
}
