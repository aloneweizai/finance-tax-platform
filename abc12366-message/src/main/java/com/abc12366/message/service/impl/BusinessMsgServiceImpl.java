package com.abc12366.message.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.mapper.db1.BusinessMsgMapper;
import com.abc12366.message.mapper.db2.BusinessMsgRoMapper;
import com.abc12366.message.model.BusinessBatchMessage;
import com.abc12366.message.model.BusinessMessage;
import com.abc12366.message.model.bo.BatchUpdateMsgToReadBO;
import com.abc12366.message.model.bo.BusinessMessageAdmin;
import com.abc12366.message.model.bo.UserSimple;
import com.abc12366.message.service.BusinessMsgService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户消息服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 2:36 PM
 * @since 1.0.0
 */
@Service
public class BusinessMsgServiceImpl implements BusinessMsgService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessMsgServiceImpl.class);

    @Autowired
    private BusinessMsgMapper businessMsgMapper;

    @Autowired
    private BusinessMsgRoMapper businessMsgRoMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 业务消息redis未读消息key
     */
    private final String BUSINESS_MSG_KEY = "_BusinessMsgUnread";

    @Override
    public List<BusinessMessage> selectList(BusinessMessage data, int page, int size) {
    	if(StringUtils.isNotEmpty(data.getDateStr())){
    	   businessMsgMapper.createTable(data.getDateStr());
    	}
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        return businessMsgRoMapper.selectList(data);
    }

    @Override
    public BusinessMessage insert(BusinessMessage data) {  	
        if (data != null) {
        	Timestamp now = new Timestamp(System.currentTimeMillis());
        	if(StringUtils.isEmpty(data.getDateStr())){
        		data.setDateStr(DateUtils.getDateFormat(now, "yyyyMM"));
        	}
        	businessMsgMapper.createTable(data.getDateStr());
        	
            data.setId(Utils.uuid());
            data.setStatus("1");
            data.setCreateTime(now);
            data.setLastUpdate(now);
            businessMsgMapper.insert(data);

            String key = data.getUserId() + BUSINESS_MSG_KEY;
            if (redisTemplate.hasKey(key)) {
                redisTemplate.delete(key);
            }
        }
        return data;
    }

    @Override
    public List<BusinessMessage> insert(BusinessBatchMessage data) {
    	Timestamp now = new Timestamp(System.currentTimeMillis());
    	businessMsgMapper.createTable(DateUtils.getDateFormat(now, "yyyyMM"));
    	  	
        List<BusinessMessage> dataList = null;
        if (data != null && data.getUserIds().size() > 0) {
            dataList = new ArrayList<>();
            for (String userId : data.getUserIds()) {
                
                BusinessMessage bm = new BusinessMessage.Builder()
                        .id(Utils.uuid())
                        .userId(userId)
                        .businessId(data.getBusinessId())
                        .content(data.getContent())
                        .status("1")
                        .createTime(now)
                        .lastUpdate(now)
                        .type(data.getType())
                        .url(data.getUrl())
                        .build();
                bm.setDateStr(DateUtils.getDateFormat(now, "yyyyMM"));
                dataList.add(bm);

                String key = userId + BUSINESS_MSG_KEY;
                if (redisTemplate.hasKey(key)) {
                    redisTemplate.delete(key);
                }
            }
            
            businessMsgMapper.batchInsert(dataList,DateUtils.getDateFormat(now, "yyyyMM"));
        }
        return dataList;
    }

    //    @KafkaListener(topics = "business_message_topic")
    public void handleUserMessage(ConsumerRecord<String, String> record) {
        LOGGER.info("business_message_topic: " + record.value());
        BusinessMessage data = JSON.parseObject(record.value(), BusinessMessage.class);
        this.insert(data);
    }

    @Override
    public BusinessMessage update(BusinessMessage data) {
    	
    	businessMsgMapper.createTable(DateUtils.getDateFormat(new Date(), "yyyyMM"));
    	data.setDateStr(DateUtils.getDateFormat(new Date(), "yyyyMM"));
        BusinessMessage bm = businessMsgRoMapper.selectOne(data);
        if (bm != null) {
            bm.setStatus(data.getStatus());
            bm.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            bm.setDateStr(DateUtils.getDateFormat(new Date(), "yyyyMM"));
            businessMsgMapper.update(bm);

            String key = bm.getUserId() + BUSINESS_MSG_KEY;
            if (redisTemplate.hasKey(key)) {
                redisTemplate.delete(key);
            }
        }
        return bm;
    }

    @Override
    public BusinessMessage selectOne(String id) {
    	
    	businessMsgMapper.createTable(DateUtils.getDateFormat(new Date(), "yyyyMM"));
    	BusinessMessage data = new BusinessMessage();
    	data.setId(id);
    	data.setDateStr(DateUtils.getDateFormat(new Date(), "yyyyMM"));
        data = businessMsgRoMapper.selectOne(data);
        // 如果消息未读，置为已读
        if ("1".equals(data.getStatus())) {
            data.setStatus("2");
            data.setDateStr(DateUtils.getDateFormat(new Date(), "yyyyMM"));
            this.update(data);

            String key = data.getUserId() + BUSINESS_MSG_KEY;
            if (redisTemplate.hasKey(key)) {
                redisTemplate.delete(key);
            }
        }
        return data;
    }

    @Override
    public BodyStatus delete(BusinessMessage data) {
    	businessMsgMapper.createTable(DateUtils.getDateFormat(new Date(), "yyyyMM"));
        BusinessMessage bm = this.selectOne(data.getId());
        // 是否为本人操作
        if (data.getUserId().equals(bm.getUserId())) {
            bm.setStatus("0");
            this.update(bm);

            String key = bm.getUserId() + BUSINESS_MSG_KEY;
            if (redisTemplate.hasKey(key)) {
                redisTemplate.delete(key);
            }
            return Utils.bodyStatus(2000);
        } else {
            return Utils.bodyStatus(4024);
        }
    }

    @Override
    public List<BusinessMessageAdmin> selectListByUsername(Map<String, Object> map, int page, int size) {
    	if(StringUtils.isNotEmpty(map.get("dateStr").toString())){
    	   businessMsgMapper.createTable(map.get("dateStr").toString());
    	}
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<BusinessMessageAdmin> list = businessMsgRoMapper.selectListByUsername(map);
        for(BusinessMessageAdmin messageAdmin:list){
            UserSimple userSimple = businessMsgRoMapper.selectUserById(messageAdmin.getUserId());
            messageAdmin.setUsername(userSimple.getUsername());
            messageAdmin.setUserPicturePath(userSimple.getUserPicturePath());
        }
        return list;
    }

    @Override
    public List<BusinessMessage> selectUnreadList(BusinessMessage bm) {
    	if(StringUtils.isNotEmpty(bm.getDateStr())){
    	 businessMsgMapper.createTable(bm.getDateStr());
    	}
    	bm.setDateStr(DateUtils.getDateFormat(new Date(), "yyyyMM"));
        String key = Utils.getUserId() + BUSINESS_MSG_KEY;
        List<BusinessMessage> dataList;
        if (redisTemplate.hasKey(key)) {
            LOGGER.info("From redis read: " + key);
            dataList = JSONArray.parseArray(redisTemplate.opsForValue().get(key), BusinessMessage.class);
        } else {
            dataList = businessMsgRoMapper.selectUnreadList(bm);
            redisTemplate.opsForValue().set(key,
                    JSONObject.toJSONString(dataList),
                    RedisConstant.DAY_1,
                    TimeUnit.DAYS);
        }
        return dataList;
    }

    @Override
    public void batchUpdateToRead(BatchUpdateMsgToReadBO bo) {
    	businessMsgMapper.createTable(DateUtils.getDateFormat(new Date(), "yyyyMM"));
    	bo.setDateStr(DateUtils.getDateFormat(new Date(), "yyyyMM"));
        if (bo == null || bo.getIds() == null || bo.getIds().size() < 1) {
            return;
        }
        if (bo.getIds().size() > 100) {
            throw new ServiceException(4206);
        }
        businessMsgMapper.updateBatch(bo);

        String key = Utils.getUserId() + BUSINESS_MSG_KEY;
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
    }
}
