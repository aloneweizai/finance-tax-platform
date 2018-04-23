package com.abc12366.gateway.web;

import com.abc12366.gateway.model.AppSetting;
import com.abc12366.gateway.model.bo.AppSettingBO;
import com.abc12366.gateway.service.AppSettingService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * APP设置控制器
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-27 3:29 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AppSettingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppSettingService appSettingService;

    /**
     * APP设置列表查询
     *
     * @param appId    应用ID
     * @param name     应用名称
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @return ResponseEntity
     */
    @GetMapping(path = "/appsetting")
    public ResponseEntity selectList(@RequestParam(value = "appId") String appId,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "uri", required = false) String uri,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize) throws IOException {
        AppSettingBO appSettingBO = new AppSettingBO();
        appSettingBO.setAppId(appId);
        appSettingBO.setName(name);
        if (uri != null && !"".equals(uri)) {
            appSettingBO.setUri(new String(new BASE64Decoder().decodeBuffer(uri)));
        }
        LOGGER.info("{},{},{}", pageNum, pageSize, appSettingBO);

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<AppSettingBO> dataList = appSettingService.selectList(appSettingBO);
        PageInfo<AppSettingBO> pageInfo = new PageInfo<>(dataList);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * APP设置详细查询
     *
     * @param appId 应用ID
     * @param id    PK
     * @return ResponseEntity
     */
    @GetMapping(path = "/appsetting/{appId}/{id}")
    public ResponseEntity selectOne(@PathVariable("appId") String appId,
                                    @PathVariable("id") String id) {
        LOGGER.info("{}/{}", appId, id);
        AppSetting appSetting = appSettingService.selectOne(appId, id);
        LOGGER.info("{}", appSetting);
        return ResponseEntity.ok(Utils.kv("data", appSetting));
    }

    /**
     * APP设置批量授权
     *
     * @param appId            应用ID
     * @param appSettingBOList 应用设置列表
     * @return ResponseEntity
     */
    @PostMapping(path = "/appsetting/list/{appId}")
    public ResponseEntity insertList(@PathVariable("appId") String appId,
                                     @Valid @RequestBody List<AppSettingBO> appSettingBOList) {
        LOGGER.info("{}: {}", appId, appSettingBOList);
        List<AppSetting> appSettingList = appSettingService.insertList(appId, appSettingBOList);
        LOGGER.info("{}", appSettingList);
        return ResponseEntity.ok(Utils.kv("data", appSettingList));
    }

    /**
     * APP设置单个授权
     *
     * @param appId        应用ID
     * @param appSettingBO AppSettingBO
     * @return ResponseEntity
     */
    @PostMapping(path = "/appsetting/{appId}")
    public ResponseEntity insert(@PathVariable("appId") String appId,
                                 @Valid @RequestBody AppSettingBO appSettingBO) {
        LOGGER.info("{}: {}", appId, appSettingBO);
        if (StringUtils.isEmpty(appSettingBO.getAppId())) {
            appSettingBO.setAppId(appId);
        }
        AppSetting appSetting = appSettingService.insert(appSettingBO);
        LOGGER.info("{}", appSetting);
        return ResponseEntity.ok(Utils.kv("data", appSetting));
    }

    /**
     * APP设置修改
     *
     * @param appId        应用ID
     * @param id           PK
     * @param appSettingBO AppSettingBO
     * @return ResponseEntity
     */
    @PutMapping(path = "/appsetting/{appId}/{id}")
    public ResponseEntity update(@PathVariable("appId") String appId,
                                 @PathVariable("id") String id,
                                 @Valid @RequestBody AppSettingBO appSettingBO) {
        LOGGER.info("{}/{}", appId, id);
        appSettingBO.setAppId(appId);
        appSettingBO.setId(id);
        AppSetting appSetting = appSettingService.update(appSettingBO);
        LOGGER.info("{}", appSetting);
        return ResponseEntity.ok(Utils.kv("data", appSetting));
    }

    /**
     * APP设置删除
     *
     * @param appId 应用ID
     * @param id PK
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/appsetting/{appId}/{id}")
    public ResponseEntity delete(@PathVariable("appId") String appId,
                                 @PathVariable("id") String id) {
        LOGGER.info("{}/{}", appId, id);
        appSettingService.delete(appId, id);
        return ResponseEntity.ok(Utils.kv());
    }
}
