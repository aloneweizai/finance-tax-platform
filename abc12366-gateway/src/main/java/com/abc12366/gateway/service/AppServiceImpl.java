package com.abc12366.gateway.service;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.mapper.db1.AppMapper;
import com.abc12366.gateway.mapper.db2.AppRoMapper;
import com.abc12366.gateway.mapper.db2.AppSettingRoMapper;
import com.abc12366.gateway.model.ApiLog;
import com.abc12366.gateway.model.App;
import com.abc12366.gateway.model.bo.AppBO;
import com.abc12366.gateway.model.bo.AppSettingBO;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @date 2017-04-05 1:12 PM
 * @since 1.0.0
 */
@Service("appService")
public class AppServiceImpl implements AppService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private AppRoMapper appRoMapper;

    @Autowired
    private ApiLogService apiLogService;

    @Autowired
    private AppSettingRoMapper appSettingRoMapper;

    @Override
    public AppBO register(AppBO bo) throws Exception {
        LOGGER.info("{}", bo);
        bo.setId(Utils.uuid());
        Date date = new Date();
        bo.setPassword(bo.getPassword());
        bo.setStartTime(date);
        bo.setCreateTime(date);
        bo.setLastUpdate(date);
        bo.setEndTime(DateUtils.addYears(date, Constant.APP_VALID_YEARS));
        App app = new App();
        BeanUtils.copyProperties(bo, app);
        //根据名称查询app
        App temp = appRoMapper.selectByName(app.getName());
        if (temp != null) {
            LOGGER.warn("APP应用名称已存在{}", app);
            throw new ServiceException(4095);
        }
        int insert = appMapper.insert(app);
        if (insert != 1) {
            LOGGER.warn("插入失败，参数：{}", app);
            throw new ServiceException(4101);
        }
        return bo;
    }

    @Override
    public String login(AppBO bo) throws Exception {
        LOGGER.info("{}", bo);
        App app = new App();
        app.setName(bo.getName());
        app.setStatus(true);
        app = appRoMapper.selectByName(bo.getName());
        if (app == null) {
            LOGGER.warn("APP用户名不存在：{}", bo.getName());
            throw new ServiceException(4094);
        }
        if (!Utils.md5(app.getPassword()).equals(bo.getPassword())) {
            LOGGER.warn("APP密码错误：{}", app);
            throw new ServiceException(4093);
        }
        // 第一次登录或token过期，需要设置token
        boolean token = StringUtils.isEmpty(app.getAccessToken()) ||
                StringUtils.isEmpty(app.getLastResetTokenTime()) ||
                new Date().after(app.getLastResetTokenTime());
        if (token) {
            app.setAccessToken(Utils.token());
            Long lastResetTokenTime = System.currentTimeMillis() + Constant.APP_TOKEN_VALID_SECONDS * 1000;
            app.setLastResetTokenTime(DateUtils.getLongToDate(lastResetTokenTime));
            app.setLastUpdate(new Date());
            appMapper.update(app);
        }
        return app.getAccessToken();
    }

    @Override
    public boolean isAuthentization(HttpServletRequest request) {
        // 1.获取最佳匹配地址
        // path: /uc
        String bestMatchingPattern = (String) request.
                getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");

        // 2.查询接口表中是否存在对应的接口
        String accessToken = request.getHeader(Constant.APP_TOKEN_HEAD);
        App app = new App();
        app.setAccessToken(accessToken);
        app.setStatus(true);
        app = appMapper.selectOne(app);
        //判断app是否正常
        if (app == null) {
            LOGGER.warn("APP不存在或APP未启用, accessToken: {}", accessToken);
            throw new ServiceException(4035);
        }
        //判断app登录是否已过期
        long lastTime = DateUtils.getDateStringToLong(app.getLastResetTokenTime());
        long currentTime = System.currentTimeMillis();
        if (currentTime > lastTime) {
            LOGGER.warn("APP登录已过期，请重新登录：{}", app);
            throw new ServiceException(4025);
        }
        //判断app是否已过期
        long endTime = DateUtils.getDateStringToLong(app.getEndTime());
        if (currentTime > endTime) {
            LOGGER.warn("APP已过期，请重新续费：{}", app);
            throw new ServiceException(4026);
        }
        String appId = app.getId();
        String appName = app.getName();
        request.setAttribute(Constant.APP_ID, appId);
        request.setAttribute(Constant.APP_NAME, appName);

        String method = request.getMethod().toUpperCase();
        LOGGER.info("API METHOD:" + method);
        String version = request.getHeader(Constant.VERSION_HEAD);
        AppSettingBO appSettingBO = new AppSettingBO();
        appSettingBO.setAppId(appId);
        appSettingBO.setUri(bestMatchingPattern);
        appSettingBO.setMethod(method);

        AppSettingBO bo = appSettingRoMapper.selectByAppId(appSettingBO);
        if (bo == null) {
            LOGGER.warn("API不存在或未授权：{}, {}", request.getRequestURI(), appId);
            throw new ServiceException(4027);
        }
        if (!method.equals(bo.getMethod())) {
            LOGGER.warn("API方法不正确：{}", method);
            throw new ServiceException(4028);
        }
        if (version != null && !version.equals(bo.getVersion())) {
            LOGGER.warn("API版本不正确：{}", version);
            throw new ServiceException(4029);
        }
        // 非公司应用查询访问次数
        if (!app.getName().contains(Constant.ABC)) {
            //查询每分钟访问的次数
            ApiLog apiLog = new ApiLog();
            currentTime = System.currentTimeMillis();
            apiLog.setUri(bestMatchingPattern);
            apiLog.setStartTime(currentTime - (60 * 1000));
            apiLog.setEndTime(currentTime);
            apiLog.setYyyyMMdd(DateUtils.getDataString());
            apiLog.setAppId(appId);
            apiLog.setMethod(method);
            //查询每分钟访问的次数
            if (bo.getTimesPerMinute() != 0) {
                int minuteCount = apiLogService.selectApiLogCount(apiLog);
                if (minuteCount > bo.getTimesPerMinute()) {
                    LOGGER.warn("API接口每分钟访问次数已超出，请稍后访问：{}", app);
                    throw new ServiceException(4031);
                }
            }
            //查询每小时访问的次数
            apiLog.setStartTime(currentTime - (60 * 1000 * 60));
            if (bo.getTimesPerHour() != 0) {
                int hourCount = apiLogService.selectApiLogCount(apiLog);
                if (hourCount > bo.getTimesPerHour()) {
                    LOGGER.warn("API接口每小时访问次数已超出，请稍后访问：{}", app);
                    throw new ServiceException(4032);
                }
            }
            //查询每天访问的次数
            apiLog.setStartTime(currentTime - (60 * 1000 * 60 * 24));
            if (bo.getTimesPerDay() != 0) {
                int dayCount = apiLogService.selectApiLogCount(apiLog);
                if (dayCount > bo.getTimesPerDay()) {
                    LOGGER.warn("API接口每天访问次数已超出，请稍后访问：{}", app);
                    throw new ServiceException(4033);
                }
            }
        }
        return true;
    }

    @Override
    public List<AppBO> selectList(AppBO appBO) {
        List<AppBO> apps = appRoMapper.selectList(appBO);
        LOGGER.info("{}", apps);
        return apps;
    }

    @Override
    public AppBO update(AppBO appBO) {
        LOGGER.info("{}", appBO);
        try {
            appBO.setPassword(appBO.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        App app = new App();
        BeanUtils.copyProperties(appBO, app);
        app.setLastUpdate(new Date());

        //根据名称查询app
        App temp = appRoMapper.selectByName(app.getName());
        if (temp != null && !temp.getId().equals(app.getId())) {
            LOGGER.warn("APP应用名称已存在{}", app);
            throw new ServiceException(4095);
        }

        int update = appMapper.update(app);
        if (update != 1) {
            LOGGER.warn("修改异常：{}", app);
            throw new ServiceException(4102);
        }
        return appBO;
    }

    @Override
    public AppBO selectById(String id) {
        LOGGER.info("{}", id);
        App app = appRoMapper.selectById(id);
        if (app == null) {
            LOGGER.warn("APP用户名不存在：{}", id);
            throw new ServiceException(4094);
        }
        AppBO appBO = new AppBO();
        BeanUtils.copyProperties(app, appBO);
        return appBO;
    }

    @Override
    public AppBO selectByName(String name) {
        App app = appRoMapper.selectByName(name);
        AppBO appBO = new AppBO();
        BeanUtils.copyProperties(app, appBO);
        return appBO;
    }
}
