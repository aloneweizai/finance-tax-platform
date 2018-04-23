package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.EventMapper;
import com.abc12366.bangbang.model.MessageSendBo;
import com.abc12366.bangbang.model.event.*;
import com.abc12366.bangbang.service.EventService;
import com.abc12366.bangbang.service.MessageSendUtil;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stuy on 2017/9/13.
 */
@Service
public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    
    @Autowired
	private MessageSendUtil messageSendUtil;

    @Override
    public SingleEventBo singleEvent(HttpServletRequest request, String category) {
        String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/cms/bangbang/event/singleevent";
        if(category!=null && !"".equals(category)){
            url += "?category="+category;
        }
        //String url = "http://118.118.116.132:9400/cms/bangbang/event/singleevent";
        String str = restTemplateUtil.send(url, HttpMethod.GET, request);
        SingleEventOneBo dataList = JSON.parseObject(str,SingleEventOneBo.class);
        return dataList.getData();
    }

    @Override
    public List<SingleEventBo> singleEventList(HttpServletRequest request, String category) {
        //String url = "http://118.118.116.132:9400/cms/bangbang/event/singleeventlist";
        String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/cms/bangbang/event/singleeventlist";
        if(category!=null && !"".equals(category)){
            url += "?category="+category;
        }
        String str = restTemplateUtil.send(url, HttpMethod.GET, request);
        SingleEventListBo dataList = JSON.parseObject(str,SingleEventListBo.class);
        return dataList.getDataList();
    }

    @Override
    public EventIdBo saveeventrecord(HttpServletRequest request, String eventid,String userid) {
        //String url = "http://118.118.116.132:9400/cms/bangbang/event/details?eventid="+eventid+"&userid="+userid;
        EventIdDataBo data= null;
        try {
            String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/cms/bangbang/event/details/"+eventid+"?userid="+userid;
            String str = restTemplateUtil.send(url, HttpMethod.GET, request);
            data = JSON.parseObject(str,EventIdDataBo.class);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        return data.getData();
    }

    @Override
    public EventRecordBbBo addEventRecord(HttpServletRequest request, EventRecordBbBo eventRecordBbBo) {
        //String url = "http://118.118.116.132:9400/cms/bangbang/event/saveeventrecord";
        EventRecordBbDataBo data= null;
        try {
            Map map=new BeanMap(eventRecordBbBo);
            String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/cms/bangbang/event/saveeventrecord";
            String str = restTemplateUtil.exchange(url, HttpMethod.POST,map, request);
            data = JSON.parseObject(str,EventRecordBbDataBo.class);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        return data.getData();
    }

    @Override
    public EventApplyBbBo saveEventApply(HttpServletRequest request, EventApplyBbBo eventApplyBbBo) {
        //String url = "http://118.118.116.132:9400/cms/bangbang/event/saveEventApply";
        EventApplyBbDataBo data= null;
        try {
            Map map=new BeanMap(eventApplyBbBo);
            String url = SpringCtxHolder.getProperty("abc12366.api.url") + "/cms/bangbang/event/saveEventApply";
            String str = restTemplateUtil.exchange(url, HttpMethod.POST,map, request);
            data = JSON.parseObject(str,EventApplyBbDataBo.class);
            
            
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        return data.getData();
    }
}
