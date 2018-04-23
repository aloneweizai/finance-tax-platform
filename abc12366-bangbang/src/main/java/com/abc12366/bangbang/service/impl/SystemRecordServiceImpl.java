package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.SystemRecordCompanyMapper;
import com.abc12366.bangbang.mapper.db1.SystemRecordMapper;
import com.abc12366.bangbang.mapper.db1.SystemRecordStatisMapper;
import com.abc12366.bangbang.mapper.db2.SystemRecordCompanyRoMapper;
import com.abc12366.bangbang.mapper.db2.SystemRecordRoMapper;
import com.abc12366.bangbang.mapper.db2.SystemRecordStatisRoMapper;
import com.abc12366.bangbang.model.*;
import com.abc12366.bangbang.model.bo.SystemRecordBO;
import com.abc12366.bangbang.model.bo.SystemRecordInsertBO;
import com.abc12366.bangbang.service.SystemRecordService;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.bo.TableBO;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author lingsuzhi <554600654@qq.com.com>
 * @create 2017-08-16
 */
@Service("systemRecordService")
public class SystemRecordServiceImpl implements SystemRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemRecordServiceImpl.class);

    @Autowired
    private SystemRecordRoMapper systemRecordRoMapper;

    @Autowired
    private SystemRecordMapper systemRecordMapper;

    @Autowired
    private SystemRecordStatisRoMapper systemRecordStatisRoMapper;

    @Autowired
    private SystemRecordStatisMapper systemRecordStatisMapper;

    @Autowired
    private SystemRecordCompanyRoMapper systemRecordCompanyRoMapper;

    @Autowired
    private SystemRecordCompanyMapper systemRecordCompanyMapper;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public List<SystemRecordBO> selectList(Map<String, String> map, int page, int size) {
        // 如果不存在表，创建当天的用户日志表
        TableBO tableBO = new TableBO.Builder().yyyyMMdd(map.get("yyyyMMdd")).build();
        systemRecordMapper.create(tableBO);

        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        return systemRecordRoMapper.selectList(map);
    }

    @Override
    public SystemRecordBO selectOne(SystemRecord systemRecord) {
        return systemRecordRoMapper.selectOne(systemRecord);
    }

    /**
     * 异步新增；如果规则代码有效，则新增用户经验值
     *
     * @param systemRecordInsertBO 日志BO
     * @return CompletableFuture 系统日志对象
     */
    @Async
    @Override
    public CompletableFuture<SystemRecordBO> insert(SystemRecordInsertBO systemRecordInsertBO, HttpServletRequest
            request) {
        if (systemRecordInsertBO == null) {
            LOGGER.warn("新增失败，参数：" + null);
            throw new ServiceException(4101);
        }

        // 如果不存在表，创建当天的用户日志表
        String today = DateUtils.getDataString();
        TableBO tableBO = new TableBO.Builder().yyyyMMdd(today).build();
        systemRecordMapper.create(tableBO);

        SystemRecord systemRecord = new SystemRecord();
        BeanUtils.copyProperties(systemRecordInsertBO, systemRecord);
        Date date = new Date();
        systemRecord.setYyyyMMdd(today);
        systemRecord.setId(Utils.uuid());
        systemRecord.setCreateTime(date);
        systemRecordSetDate(systemRecord);
        if (systemRecord.getUserId() != null && systemRecord.getSessionId() != null) {
            try {
                //  要搜索一个 sessionId 相等 UserId相等  browseDate 要当天
                List<SystemRecordBO> list = systemRecordRoMapper.findStay(systemRecord);
                if (list != null && list.size() > 0) {
                    SystemRecordBO systemRecordBO = list.get(0);
                    systemRecordBO.setYyyyMMdd(today);
                    Long oldTime = systemRecordBO.getCreateTime().getTime();
                    Long newTime = date.getTime();
                    systemRecordBO.setStayLong((int) (newTime - oldTime));
                    systemRecordMapper.updateStay(systemRecordBO);
                }
            } catch (Exception e) {
                LOGGER.error("错误：{}", e);
            }
        }
        if (StringUtils.isEmpty(systemRecord.getPageTitle())) {
            String pageTitle = "";
            if (!StringUtils.isEmpty(systemRecord.getMenua())) {
                pageTitle = systemRecord.getMenua();
            }
            if (!StringUtils.isEmpty(systemRecord.getMenub())) {
                pageTitle += "-" + systemRecord.getMenub();
            }
            if (!StringUtils.isEmpty(systemRecord.getMenuc())) {
                pageTitle += "-" + systemRecord.getMenuc();
            }
            systemRecord.setPageTitle(pageTitle);
        }
        int result = systemRecordMapper.insert(systemRecord);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + systemRecord);
            throw new ServiceException(4101);
        }

        // 如果规则代码有效，则新增用户经验值
        if (!StringUtils.isEmpty(systemRecord.getRuleCode()) && !StringUtils.isEmpty(systemRecord.getUserId())) {
            LOGGER.info("调用UC计算用户经验值接口，用户ID{}，经验值编码：{}", systemRecord.getUserId(), systemRecord.getRuleCode());
            String expCalculateUrl = SpringCtxHolder.getProperty("abc12366.uc.url") + "/experience/calculate";
            Map<String, String> map = new HashMap<>();
            map.put("userId", systemRecord.getUserId());
            map.put("ruleCode", systemRecord.getRuleCode());
            try {
                restTemplateUtil.exchange(expCalculateUrl, HttpMethod.POST, map, request);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("错误：{}", e);
            }
        }

        SystemRecordBO systemRecordBOReturn = new SystemRecordBO();
        BeanUtils.copyProperties(systemRecord, systemRecordBOReturn);

        return CompletableFuture.completedFuture(systemRecordBOReturn);
    }


    @Override
    public List<SystemRecordStatis> statisList(Map<String, Object> map) {
        //查询这个时间段每天的数据有没有做统计
        List<Date> datelist = DateUtils.findDates((Date) map.get("startTime"), (Date) map.get("endTime"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date current = new Date();
        if(datelist != null && datelist.size() > 7){
            LOGGER.warn("起止时间不能超过7天");
            throw new ServiceException(6393,"起止时间不能超过7天");
        }
        for (Date date : datelist) {
            if(date != null &&  date.compareTo(current) > 0){
                LOGGER.warn("起止日期不能大于当前日期");
                throw new ServiceException(6393);
            }
            //查询当天有没有数据
            int count = systemRecordStatisRoMapper.selectByDateCount(date);
            if(count == 0){
                Map<String,Object> objectMap = new HashMap<>();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                objectMap.put("yyyyMMdd", df.format(date));
                //统计数据
                autoRecordStatis(objectMap);
            }
        }
        //查询子节点
//        List<String> list = GeneralTree.t.getChild((String) map.get("menu"));
//        map.put("list",list);
        List<SystemRecordStatis> dataList = systemRecordStatisRoMapper.statisList(map);
        return dataList;
    }

    @Override
    public void autoRecordStatis(Map<String, Object> map) {
        //查询统计数据
        try{
            List<SystemRecordStatis> list = systemRecordStatisRoMapper.selectRecordStatisList(map);
            //新增统计数据
            for (SystemRecordStatis statis:list){
                SystemRecordStatis data = new SystemRecordStatis();
                data.setId(Utils.uuid());
                data.setAmount(statis.getAmount());
                data.setCreateTime(statis.getCreateTime());
                data.setMenu(statis.getMenu());
                systemRecordStatisMapper.insert(data);
            }
        }catch (Exception e){
            LOGGER.warn("查询异常：" + e);
//            e.printStackTrace();
        }
    }

    @Override
    public List<SystemRecordCompany> statisCompanyList(Map<String, Object> map) {
        //查询这个时间段每天的数据有没有做统计
        List<Date> datelist = DateUtils.findDates((Date) map.get("startTime"), (Date) map.get("endTime"));
        for (Date date : datelist) {
            if(date.compareTo(date) > 0){
                LOGGER.warn("起止日期不能大于当前日期");
                throw new ServiceException(6393);
            }
            //查询当天有没有数据
            int count = systemRecordCompanyRoMapper.selectByDateCount(date);
            if(count == 0){
                Map<String,Object> objectMap = new HashMap<>();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                objectMap.put("yyyyMMdd", df.format(date));
                //统计数据
                autoRecordCompany(objectMap);
            }
        }
        //查询子节点
//        List<String> list = GeneralTree.t.getChild((String) map.get("menu"));
//        map.put("list",list);
        LOGGER.info("查询企业用户");
        List<SystemRecordCompany> dataList = systemRecordCompanyRoMapper.statisList(map);
        return dataList;
    }

    @Override
    public void autoRecordCompany(Map<String, Object> map) {
        //查询统计数据
        try{
            List<SystemRecordCompany> list = systemRecordCompanyRoMapper.selectRecordCompanyList(map);
            //新增统计数据
            for (SystemRecordCompany statis:list){
                SystemRecordCompany data = new SystemRecordCompany();
                data.setId(Utils.uuid());
                data.setAmount(statis.getAmount());
                data.setNsrsbh(statis.getNsrsbh());
                data.setCreateTime(statis.getCreateTime());
                data.setMenu(statis.getMenu());
                systemRecordCompanyMapper.insert(data);
            }
        }catch (Exception e){
            LOGGER.warn("查询异常：" + e);
        }
    }

    @Override
    public List<User> statisRecordUserList(Map<String, Object> map, int page, int size) {
        List<Date> datelist = DateUtils.findDates((Date) map.get("startTime"), (Date) map.get("endTime"));
        List<String> list = new ArrayList<>();
        //格式化时间条件
        for (Date date : datelist) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String dfDate = df.format(date);
            //查询当天是否有数据
            int count;
            try {
                count = systemRecordRoMapper.selectRecordCount(dfDate);
            }catch (Exception e){
                count = -1;
            }
            if(count != -1){
                list.add(dfDate);
            }
        }
        map.put("list",list);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<User> userList;
        try {
            userList = systemRecordStatisRoMapper.statisRecordUserList(map);
        }catch (Exception e){
            LOGGER.warn("查询SQL异常：" + e);
            throw new ServiceException(6393,"2017-12-12之前的数据不做统计");
        }
        return userList;
    }

    @Override
    public List<DzsbHngs> statisRecordCompanyList(Map<String, Object> map, int page, int size) {
        List<Date> datelist = DateUtils.findDates((Date) map.get("startTime"), (Date) map.get("endTime"));
        List<String> list = new ArrayList<>();
        for (Date date : datelist) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            String dfDate = df.format(date);
            //查询当天是否有数据
            int count;
            try {
                count = systemRecordRoMapper.selectRecordCount(dfDate);
            }catch (Exception e){
                count = -1;
            }
            if(count != -1){
                list.add(dfDate);
            }
        }
        map.put("list",list);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<DzsbHngs> dzsbHngsList;
        try {
            dzsbHngsList = systemRecordCompanyRoMapper.statisRecordCompanyList(map);
        }catch (Exception e){
            LOGGER.warn("查询SQL异常：" + e);
            throw new ServiceException(6393,"2017-12-12之前的数据不做统计");
        }
        return dzsbHngsList;
    }

    /**
     * 年、月、日。。。是根据浏览日期算出来的，新增的时候不需要用户传过来
     */
    private void systemRecordSetDate(SystemRecord systemRecord) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        systemRecord.setWeek(String.valueOf(now.get(Calendar.DAY_OF_WEEK) - 1));
        systemRecord.setYear(String.valueOf(now.get(Calendar.YEAR)));
        systemRecord.setMonth(String.valueOf(now.get(Calendar.MONTH) + 1));
        systemRecord.setDay(String.valueOf(now.get(Calendar.DAY_OF_MONTH)));
        systemRecord.setHour(String.valueOf(now.get(Calendar.HOUR_OF_DAY)));
        systemRecord.setMinute(String.valueOf(now.get(Calendar.MINUTE)));
    }
}
