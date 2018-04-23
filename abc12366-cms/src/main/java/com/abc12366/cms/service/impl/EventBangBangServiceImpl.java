package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.EventBangBangUpdateMapper;
import com.abc12366.cms.mapper.db2.EventBangBangMapper;
import com.abc12366.cms.model.MessageSendBo;
import com.abc12366.cms.model.event.*;
import com.abc12366.cms.service.EventBangBangService;
import com.abc12366.cms.service.MessageSendUtil;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.Utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by stuy on 2017/9/13.
 */
@Service
public class EventBangBangServiceImpl implements EventBangBangService{

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventBangBangMapper eventMapper;

    @Autowired
    private EventBangBangUpdateMapper eventBangBangUpdateMapper;
    
    @Autowired
	private MessageSendUtil messageSendUtil;

    @Override
    public SingleEventBo singleEvent(Map map) {
        return eventMapper.singleEvent(map);
    }

    @Override
    public List<SingleEventBo> singleEventList(Map map) {
        return eventMapper.singleEventList(map);
    }


    @Transactional("db1TxManager")
    public EventRecordBbBo addEventRecord(EventRecordBbBo eventRecord) {
        try {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            eventRecord.setRecordId(uuid);
            eventRecord.setBrowsetime(new Date());
            eventBangBangUpdateMapper.saveEventRecord(eventRecord);
        } catch (Exception e) {
            LOGGER.debug("异常：{}"+e);
            throw new RuntimeException("异常：{}", e);
        }
        return eventRecord;

    }

    @Override
    public EventBrowseCountBo browseCount(Map<String, String> map) {
        EventBrowseCountBo browsecount = eventMapper.browseCount(map);
        return browsecount;
    }

    @Override
    public EventIdBo selectEventId(Map map) {
        EventIdBo event = eventMapper.selectEventId(map);
        map.put("sponsorid",event.getSponsorid());
        EventSponsorBbBo eventsponsor = eventMapper.selectEventSponsor(map);
        event.setEventSponsorBbBo(eventsponsor);
        List<EventApplyFieldBo> modelitem = eventMapper.selectModelItem(map);
        event.setEventApplyFieldBoList(modelitem);
        EventBrowseCountBo browse = eventMapper.browseCount(map);
        event.setEventBrowseCountBo(browse);
        List<EventIdBo> list = eventMapper.selectEventRelevant(map);
        event.setEventIdBoList(list);
        int count=eventMapper.selectEventApplyStatus(map);
        event.setIsApply(count);
        return event;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional("db1TxManager")
    public EventApplyBbBo saveEventApply(EventApplyBbBo eventApplyBbBo, HttpServletRequest request) {
        try {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            eventApplyBbBo.setApplyId(uuid);
            eventApplyBbBo.setApplytime(new Date());
            eventBangBangUpdateMapper.saveEventApply(eventApplyBbBo);
            List<EventApplyCentenBo> eventapplyCenten = eventApplyBbBo.getEventApplyCentenBo();
            for(EventApplyCentenBo eventApplyCentenBo : eventapplyCenten){
                eventApplyCentenBo.setApplyId(uuid);
                eventBangBangUpdateMapper.saveEventApplyAttr(eventApplyCentenBo);
            }
            
	        //2018-03-12
	        Map map=new HashMap();
	        map.put("eventid",eventApplyBbBo.getEventId());
	        map.put("userid",eventApplyBbBo.getUserid());
	    	EventIdBo eventIdBo = selectEventId(map);
	    	
	    	Map<String, String> dataList = new HashMap<>();
	    	dataList.put("first", "您已报名成功:");
	    	dataList.put("remark", "请于" + DateUtils.dateToStr(eventIdBo.getBegintime()) + "时间准时参加，感谢您的参与！");
	    	dataList.put("keyword1", Utils.getUserInfo() !=null?Utils.getUserInfo().getNickname():"");
	    	dataList.put("keyword2",
	    			(Utils.getUserInfo() !=null && StringUtils.isNotEmpty(Utils.getUserInfo().getPhone()))
							? new StringBuilder(Utils.getUserInfo().getPhone())
									.replace(3, Utils.getUserInfo().getPhone().length() - 4, "****").toString()
							: "");
	    	dataList.put("keyword3", "财税网活动");
			dataList.put("keyword4", eventIdBo.getTitle());
			dataList.put("keyword5", DateUtils.dateToStr(eventApplyBbBo.getApplytime()));
	         
	        MessageSendBo messageSendBo = new MessageSendBo();
			messageSendBo.setType(MessageConstant.USER_MESSAGE);
			messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ACTIVES);
			messageSendBo.setBusinessId(eventApplyBbBo.getEventId());
			messageSendBo.setWebMsg("您已报名成功"+eventIdBo.getTitle()+"活动，请于"+DateUtils.dateToStr(eventIdBo.getBegintime())+"准时参加，感谢您的参与");
			messageSendBo.setPhoneMsg("您已报名成功"+eventIdBo.getTitle()+"活动，请于"+DateUtils.dateToStr(eventIdBo.getBegintime())+"准时参加，感谢您的参与");
			messageSendBo.setTemplateid("-3PFGVQGFL9vH8mTKfYr_0YdAY-6uU924WP--4ZfG8A");
			messageSendBo.setDataList(dataList);
	         
	        List<String> userIds = new ArrayList<String>();
			userIds.add(eventApplyBbBo.getUserid());
			messageSendBo.setUserIds(userIds);
	
			messageSendUtil.sendMsgBySubscriptions(messageSendBo, request);
	   
        } catch (Exception e) {
            LOGGER.debug("异常：{}"+e);
            throw new RuntimeException("异常：{}", e);
        }
        return eventApplyBbBo;
    }

    @Override
    public int selectEventApplyStatus(Map map) {
        return eventMapper.selectEventApplyStatus(map);
    }
}
