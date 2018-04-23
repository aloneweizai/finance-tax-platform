package com.abc12366.gateway.web;

import com.abc12366.gateway.model.bo.AppBO;
import com.abc12366.gateway.service.AppService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 应用控制器
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-05 12:59 PM
 * @since 1.0.0
 */
@Controller
@RequestMapping(path = "/app", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;

    /**
     * APP注册
     *
     * @param appBO appBO对象
     * @return ResponseEntity
     * @throws Exception
     */
    @PostMapping(path = "/register")
    public ResponseEntity register(@Valid @RequestBody AppBO appBO) throws Exception {
        LOGGER.info("{}", appBO);
        AppBO app = appService.register(appBO);
        LOGGER.info("{}", app);
        return ResponseEntity.ok(Utils.kv("data", app));
    }

    /**
     * APP登录
     *
     * @param appBO appBO对象
     * @return ResponseEntity
     * @throws Exception
     */
    @PostMapping(path = "/login")
    public ResponseEntity login(@Valid @RequestBody AppBO appBO) throws Exception {
        LOGGER.info("{}", appBO);
        String token = appService.login(appBO);
        return token != null ? ResponseEntity.ok(
                Utils.kv("token", token, "expires_in", Constant.APP_TOKEN_VALID_SECONDS))
                : new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST);
    }

    /**
     * APP列表查询
     *
     * @param pageNum   当前页
     * @param pageSize  每页大小
     * @param name      app名称
     * @param startTime 开始日期
     * @param endTime   截止日期
     * @param status    应用状态
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(required = false, value = "name") String name,
                                     @RequestParam(required = false, value = "accessToken") String accessToken,
                                     @RequestParam(required = false, value = "startTime") String startTime,
                                     @RequestParam(required = false, value = "endTime") String endTime,
                                     @RequestParam(required = false, value = "status") Boolean status
    ) {
        if (name != null && StringUtils.isEmpty(name)) {
            name = null;
        }
        AppBO appBO = new AppBO();
        appBO.setName(name);
        if (startTime != null && !"".equals(startTime)) {
            appBO.setStartTime(DateUtils.strToDate(startTime));
        } else {
            appBO.setStartTime(null);
        }
        if (endTime != null && !"".equals(endTime)) {
            appBO.setEndTime(DateUtils.strToDate(endTime));
        } else {
            appBO.setEndTime(null);
        }
        appBO.setAccessToken(accessToken);
        appBO.setStatus(status);
        LOGGER.info("{},{},{}", pageNum, pageSize, appBO);

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<AppBO> appList = appService.selectList(appBO);
        LOGGER.info("{}", appList);
        return (appList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) appList, "total", ((Page) appList).getTotal()));
    }

    /**
     * APP详情查询
     *
     * @param id PK
     * @return ResponseEntity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        AppBO app = appService.selectById(id);
        LOGGER.info("{}", app);
        return ResponseEntity.ok(Utils.kv("data", app));
    }

    /**
     * APP修改
     *
     * @param appUpdateBO appUpdateBO对象
     * @param id          PK
     * @return ResponseEntity
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@RequestBody AppBO appUpdateBO, @PathVariable("id") String id) {
        LOGGER.info("{}", appUpdateBO);
        appUpdateBO.setId(id);
        AppBO app = appService.update(appUpdateBO);
        LOGGER.info("{}", app);
        return ResponseEntity.ok(Utils.kv("data", app));
    }
}
