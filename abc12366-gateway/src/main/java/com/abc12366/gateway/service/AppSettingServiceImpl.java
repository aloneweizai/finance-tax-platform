package com.abc12366.gateway.service;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.mapper.db1.AppSettingMapper;
import com.abc12366.gateway.mapper.db2.AppSettingRoMapper;
import com.abc12366.gateway.model.AppSetting;
import com.abc12366.gateway.model.bo.AppSettingBO;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-27 4:06 PM
 * @since 1.0.0
 */
@Service
public class AppSettingServiceImpl implements AppSettingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingServiceImpl.class);

    @Autowired
    private AppSettingRoMapper appSettingRoMapper;

    @Autowired
    private AppSettingMapper appSettingMapper;

    @Override
    public List<AppSettingBO> selectList(AppSettingBO appSettingBO) {
        return appSettingRoMapper.selectList(appSettingBO);
    }

    @Override
    public AppSetting update(AppSettingBO bo) {
        AppSetting appSetting = new AppSetting();
        BeanUtils.copyProperties(bo, appSetting);
        int update = appSettingMapper.update(appSetting);
        if (update != 1) {
            LOGGER.warn("修改失败，参数：{}", appSetting);
            throw new ServiceException(4102);
        }
        return appSetting;
    }

    @Override
    public AppSetting insert(AppSettingBO bo) {
        bo.setId(Utils.uuid());
        Date now = new Date();
        bo.setCreateTime(now);
        bo.setLastUpdate(now);
        AppSetting appSetting = new AppSetting();
        BeanUtils.copyProperties(bo, appSetting);
        int insert = appSettingMapper.insert(appSetting);
        if (insert != 1) {
            LOGGER.warn("插入失败，参数：{}", appSetting);
            throw new ServiceException(4101);
        }
        return appSetting;
    }

    @Override
    public void delete(String appId, String id) {
        appSettingMapper.delete(id);
    }

    @Override
    public AppSetting selectOne(String appId, String id) {
        AppSetting appSetting = new AppSetting();
        appSetting.setAppId(appId);
        appSetting.setId(id);

        return appSettingRoMapper.selectOne(appSetting);
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public List<AppSetting> insertList(String appId, List<AppSettingBO> appSettingBOList) {
        List<AppSetting> list = new ArrayList<>();
        //根据appId删除授权信息
        //appSettingMapper.deleteByAppId(appId);
        if (appSettingBOList != null && appSettingBOList.size() != 0) {
            if(appSettingBOList.size() > 100){
                LOGGER.warn("每次最多授权100个：{}", appSettingBOList.size());
                throw new ServiceException(4101,"每次最多授权100个");
            }
            for (AppSettingBO bo : appSettingBOList) {
                bo.setId(Utils.uuid());
                Date date = new Date();
                bo.setCreateTime(date);
                bo.setLastUpdate(date);
                AppSetting appSetting = new AppSetting();
                BeanUtils.copyProperties(bo, appSetting);
                list.add(appSetting);
            }
            //批量新增授权
            appSettingMapper.batchInsert(list);
        }
        return list;
    }
}
