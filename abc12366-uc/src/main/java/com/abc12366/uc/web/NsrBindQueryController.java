package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.NsrBindQueryBO;
import com.abc12366.uc.model.bo.UserDzsbBO;
import com.abc12366.uc.model.bo.UserHndsBO;
import com.abc12366.uc.model.bo.UserHngsBO;
import com.abc12366.uc.service.NsrBindQueryService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询所有绑定关系接口控制器
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-16
 * Time: 11:35
 */
@RestController
@RequestMapping(path = "/nsrbindquery", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class NsrBindQueryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NsrBindQueryController.class);

    @Autowired
    private NsrBindQueryService nsrBindQueryService;

    /**
     * 查询纳税人绑定关系列表
     *
     * @param username 用户名
     * @param nsrsbh   纳税人识别号
     * @param status   绑定状态
     * @param type     查询类型：dzsb、hngs、hnds，默认为dzsb
     * @param page     当前页
     * @param size     每页大小
     * @return 绑定信息列表
     */
    @GetMapping()
    public ResponseEntity selectList(@RequestParam(required = false) String username,
                                     @RequestParam(required = false) String nsrsbh,
                                     @RequestParam(required = false) Boolean status,
                                     @RequestParam(required = false, defaultValue = "dzsb") String type,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", username);
        map.put("nsrsbh", nsrsbh);
        map.put("status", status);
        map.put("type", type);
        LOGGER.info("{}:{}:{}", map, page, size);

        List<NsrBindQueryBO> dataList = nsrBindQueryService.selectList(map, page, size);
        LOGGER.info("{}", dataList);
        PageInfo<NsrBindQueryBO> pageInfo = new PageInfo<>(dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));

    }

    /**
     * 查询一条电子申报绑定
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/dzsb/{id}")
    public ResponseEntity selectDzsb(@PathVariable String id) {
        LOGGER.info("{}", id);
        UserDzsbBO userDzsbBO = nsrBindQueryService.selectDzsb(id);
        return ResponseEntity.ok(Utils.kv("data", userDzsbBO));
    }

    /**
     * 查询一条湖南地税绑定
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/hnds/{id}")
    public ResponseEntity selectHnds(@PathVariable String id) {
        LOGGER.info("{}", id);
        UserHndsBO userHndsBO = nsrBindQueryService.selectHnds(id);
        return ResponseEntity.ok(Utils.kv("data", userHndsBO));
    }

    /**
     * 查询一条湖南国税绑定
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/hngs/{id}")
    public ResponseEntity selectHngs(@PathVariable String id) {
        LOGGER.info("{}", id);
        UserHngsBO userHngsBO = nsrBindQueryService.selectHngs(id);
        return ResponseEntity.ok(Utils.kv("data", userHngsBO));
    }
}
