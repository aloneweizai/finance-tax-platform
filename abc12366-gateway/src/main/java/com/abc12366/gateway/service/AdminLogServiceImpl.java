package com.abc12366.gateway.service;

import com.abc12366.gateway.mapper.db1.AdminLogMapper;
import com.abc12366.gateway.mapper.db2.AdminLogRoMapper;
import com.abc12366.gateway.model.AdminLog;
import com.abc12366.gateway.model.bo.AdminLogBO;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-05 11:48 AM
 * @since 1.0.0
 */
@Service
public class AdminLogServiceImpl implements AdminLogService {

    @Autowired
    private AdminLogMapper adminLogMapper;

    @Autowired
    private AdminLogRoMapper adminLogRoMapper;

    @Async
    @Override
    public CompletableFuture<AdminLog> insert(AdminLogBO bo) {
        Date now = new Date();
        AdminLog adminLog = new AdminLog.Builder()
                .id(Utils.uuid())
                .userId(bo.getAdminId())
                .createTime(now)
                .businessUri(bo.getBusinessUri())
                .businessName(bo.getBusinessName())
                .businessData((StringUtils.isEmpty(bo.getBusinessData())||bo.getBusinessData().length()>2000)?" ":bo.getBusinessData())
                .method(bo.getMethod())
                .remark(bo.getRemark())
                .build();
        adminLog.setYyyyMMdd(DateUtils.getDateFormat(now, "yyyyMMdd"));
        adminLog.setYyyyMM(DateUtils.getDateFormat(now, "yyyyMM"));

        adminLogMapper.create(adminLog);
        adminLogMapper.insert(adminLog);
        return CompletableFuture.completedFuture(adminLog);
    }

    @Override
    public List<AdminLog> selectList(int page, int size, AdminLog adminLog) {
        Date now = new Date();
        adminLog.setYyyyMM(DateUtils.getDateFormat(now, "yyyyMM"));

        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        return adminLogRoMapper.selectList(adminLog);
    }
    
}
