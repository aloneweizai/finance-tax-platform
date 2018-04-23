package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.EventMapper;
import com.abc12366.cms.mapper.db1.EventModelItemMapper;
import com.abc12366.cms.mapper.db2.EventModelItemRoMapper;
import com.abc12366.cms.mapper.db2.EventRoMapper;
import com.abc12366.cms.model.Event;
import com.abc12366.cms.model.EventModelItem;
import com.abc12366.cms.model.bo.EventBo;
import com.abc12366.cms.model.bo.EventListBo;
import com.abc12366.cms.model.bo.EventModelItemBo;
import com.abc12366.cms.model.bo.EventSaveBo;
import com.abc12366.cms.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by xieyanmao on 2017/5/8.
 */
@Service
public class EventServiceImpl implements EventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventRoMapper eventRoMapper;

    @Autowired
    private EventModelItemMapper eventModelItemMapper;

    @Autowired
    private EventModelItemRoMapper eventModelItemRoMapper;

    @Override
    public List<EventListBo> selectList(Map<String, Object> map) {
        //查询活动列表
        eventMapper.updateStatus();
        List<EventListBo> dataList = eventRoMapper.selectList(map);
        return dataList;
    }

    @Transactional("db1TxManager")
    @Override
    public EventSaveBo save(EventSaveBo eventSaveBo) {
        //保存活动信息
        String uuid = UUID.randomUUID().toString().replace("-", "");
        EventBo eventBo = eventSaveBo.getEvent();
        Event event = new Event();
        eventBo.setEventId(uuid);
        eventBo.setCreatetime(new Date());
        if ("2".equals(eventBo.getStatus())) {
            eventBo.setUpdatetime(new Date());
        }
        try {
            BeanUtils.copyProperties(eventBo, event);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        eventMapper.insert(event);
        List<EventModelItemBo> eventModelItemBoList = eventSaveBo.getModelItemList();
        if (eventModelItemBoList != null) {
            for (EventModelItemBo eventModelItemBo : eventModelItemBoList) {
                eventModelItemBo.setEventId(uuid);
                eventModelItemBo.setModelitemId(UUID.randomUUID().toString().replace("-", ""));
                EventModelItem eventModelItem = new EventModelItem();
                try {
                    BeanUtils.copyProperties(eventModelItemBo, eventModelItem);
                } catch (Exception e) {
                    LOGGER.error("类转换异常：{}", e);
                    throw new RuntimeException("类型转换异常：{}", e);
                }
                eventModelItemMapper.insert(eventModelItem);
            }
        }

        return eventSaveBo;
    }

    @Override
    public EventSaveBo selectEvent(String eventId) {
        //查询主办方信息
        EventSaveBo eventSaveBo = new EventSaveBo();
        Event event = eventRoMapper.selectByPrimaryKey(eventId);
        EventBo eventBo = new EventBo();
        try {
            if (event != null) {
                BeanUtils.copyProperties(event, eventBo);
                eventSaveBo.setEvent(eventBo);
            }
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        List<EventModelItemBo> eventModelItemBoList = new ArrayList<EventModelItemBo>();
        List<EventModelItem> eventModelItemList = eventModelItemRoMapper.selectByEventId(eventId);
        if (eventModelItemList != null) {
            for (EventModelItem eventModelItem : eventModelItemList) {
                EventModelItemBo eventModelItemBo = new EventModelItemBo();
                try {
                    BeanUtils.copyProperties(eventModelItem, eventModelItemBo);
                } catch (Exception e) {
                    LOGGER.error("类转换异常：{}", e);
                    throw new RuntimeException("类型转换异常：{}", e);
                }
                eventModelItemBoList.add(eventModelItemBo);
            }
        }
        eventSaveBo.setModelItemList(eventModelItemBoList);
        return eventSaveBo;
    }


    @Override
    public EventSaveBo selecttopone(String category) {
        //查询主办方信息
        EventSaveBo eventSaveBo = new EventSaveBo();
        Event event = eventRoMapper.selecttopone(category);
        EventBo eventBo = new EventBo();
        try {
            if (event != null) {
                BeanUtils.copyProperties(event, eventBo);
                eventSaveBo.setEvent(eventBo);
                List<EventModelItemBo> eventModelItemBoList = new ArrayList<EventModelItemBo>();
                List<EventModelItem> eventModelItemList = eventModelItemRoMapper.selectByEventId(event.getEventId());
                if (eventModelItemList != null) {
                    for (EventModelItem eventModelItem : eventModelItemList) {
                        EventModelItemBo eventModelItemBo = new EventModelItemBo();
                        try {
                            BeanUtils.copyProperties(eventModelItem, eventModelItemBo);
                        } catch (Exception e) {
                            LOGGER.error("类转换异常：{}", e);
                            throw new RuntimeException("类型转换异常：{}", e);
                        }
                        eventModelItemBoList.add(eventModelItemBo);
                    }
                }
                eventSaveBo.setModelItemList(eventModelItemBoList);
            }
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }

        return eventSaveBo;
    }

    @Transactional("db1TxManager")
    @Override
    public EventSaveBo update(EventSaveBo eventSaveBo) {
        //更新活动信息
        EventBo eventBo = eventSaveBo.getEvent();
        Event event = new Event();
        if ("2".equals(eventBo.getStatus())) {
            eventBo.setUpdatetime(new Date());
        }
        try {
            BeanUtils.copyProperties(eventBo, event);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        eventMapper.updateByPrimaryKeySelective(event);
        eventModelItemMapper.deleteByEventId(eventBo.getEventId());
        List<EventModelItemBo> eventModelItemBoList = eventSaveBo.getModelItemList();
        if (eventModelItemBoList != null) {
            for (EventModelItemBo eventModelItemBo : eventModelItemBoList) {
                eventModelItemBo.setEventId(eventBo.getEventId());
                eventModelItemBo.setModelitemId(UUID.randomUUID().toString().replace("-", ""));
                EventModelItem eventModelItem = new EventModelItem();
                try {
                    BeanUtils.copyProperties(eventModelItemBo, eventModelItem);
                } catch (Exception e) {
                    LOGGER.error("类转换异常：{}", e);
                    throw new RuntimeException("类型转换异常：{}", e);
                }
                eventModelItemMapper.insert(eventModelItem);
            }
        }
        return eventSaveBo;
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String eventId) {
        eventModelItemMapper.deleteByEventId(eventId);
        int r = eventMapper.deleteByPrimaryKey(eventId);
        LOGGER.info("{}", r);
        return "";
    }

}
