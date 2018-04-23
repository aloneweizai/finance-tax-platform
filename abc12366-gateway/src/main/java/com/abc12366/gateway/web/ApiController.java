package com.abc12366.gateway.web;

import com.abc12366.gateway.model.Api;
import com.abc12366.gateway.model.bo.ApiBO;
import com.abc12366.gateway.service.ApiService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * API接口控制器
 *
 * @create 2017-04-27 10:21 AM
 * @since 1.0.0
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private ApiService apiService;

    /**
     * API列表
     *
     * @param name     接口名称
     * @param status   状态
     * @param dictId   字典ID
     * @param url      接口地址
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @return ResponseEntity
     * @throws IOException IO异常
     */
    @GetMapping(path = "/api")
    public ResponseEntity selectList(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "status", required = false) Boolean status,
                                     @RequestParam(value = "dictId", required = false) String dictId,
                                     @RequestParam(value = "url", required = false) String url,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize)
            throws IOException {
        Api api = new Api();
        api.setName(name);
        api.setStatus(status);
        api.setDictId(dictId);
        if (url != null && !"".equals(url)) {
            api.setUri(new String(new BASE64Decoder().decodeBuffer(url)));
        }
        LOGGER.info("{}", api);

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<ApiBO> apiList = apiService.selectList(api);
        PageInfo<ApiBO> pageInfo = new PageInfo<>(apiList);
        LOGGER.info("{}", apiList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 通过AppId查找未授权的API
     *
     * @param appId
     * @return
     */
    @GetMapping(path = "/api/apisetting/{appId}")
    public ResponseEntity selectBySettingList(@PathVariable("appId") String appId) {
        LOGGER.info("{appId}", appId);
        List<ApiBO> apiList = apiService.selectBySettingList(appId);
        LOGGER.info("{}", apiList);
        return ResponseEntity.ok(Utils.kv("dataList", apiList));
    }

    /**
     * API单个查询
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/api/{id}")
    public ResponseEntity selectApi(@PathVariable("id") String id) {
        LOGGER.info(id);
        ApiBO api = apiService.selectOne(id);
        LOGGER.info("{}", api);
        return ResponseEntity.ok(Utils.kv("data", api));
    }

    /**
     * API新增
     *
     * @param apiBO
     * @return
     */
    @PostMapping(path = "/api")
    public ResponseEntity insert(@Valid @RequestBody ApiBO apiBO) {
        LOGGER.info("{}", apiBO);
        Api api = apiService.insert(apiBO);
        LOGGER.info("{}", api);
        return ResponseEntity.ok(Utils.kv("data", api));
    }

    /**
     * API修改
     *
     * @param id
     * @param apiBO
     * @return
     */
    @PutMapping(path = "/api/{id}")
    public ResponseEntity update(@PathVariable("id") String id,
                                 @Valid @RequestBody ApiBO apiBO) {
        apiBO.setId(id);
        LOGGER.info("{}", apiBO);
        Api api = apiService.update(apiBO);
        LOGGER.info("{}", api);
        return ResponseEntity.ok(Utils.kv("data", api));
    }

    /**
     * API删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/api/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        apiService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }
}
