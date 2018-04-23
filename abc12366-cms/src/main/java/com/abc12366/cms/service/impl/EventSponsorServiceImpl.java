package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.EventSponsorMapper;
import com.abc12366.cms.mapper.db2.EventSponsorRoMapper;
import com.abc12366.cms.model.EventSponsor;
import com.abc12366.cms.model.bo.EventSponsorBo;
import com.abc12366.cms.service.EventSponsorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/5/8.
 */
@Service
public class EventSponsorServiceImpl implements EventSponsorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventSponsorServiceImpl.class);

    @Autowired
    private EventSponsorMapper eventSponsorMapper;

    @Autowired
    private EventSponsorRoMapper eventSponsorRoMapper;

    @Override
    public List<EventSponsorBo> selectList() {
        //查询主办方列表
        List<EventSponsorBo> eventSponsorBoList = eventSponsorRoMapper.selectList();
        return eventSponsorBoList;
    }

    @Override
    public EventSponsorBo save(EventSponsorBo eventSponsorBo) {
        //保存主办方信息
        String uuid = UUID.randomUUID().toString().replace("-", "");
        EventSponsor eventSponsor = new EventSponsor();
        eventSponsorBo.setSponsorId(uuid);
        try {
            BeanUtils.copyProperties(eventSponsorBo, eventSponsor);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        eventSponsorMapper.insert(eventSponsor);
        return eventSponsorBo;
    }

    @Override
    public EventSponsorBo selectEventSponsor(String topicId) {
        //查询主办方信息
        EventSponsor eventSponsor = eventSponsorRoMapper.selectByPrimaryKey(topicId);
        EventSponsorBo eventSponsorBo = new EventSponsorBo();
        try {
            if (eventSponsor != null) {
                BeanUtils.copyProperties(eventSponsor, eventSponsorBo);
            }

        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        return eventSponsorBo;
    }

    @Override
    public EventSponsorBo update(EventSponsorBo eventSponsorBo) {
        //更新主办方信息
        EventSponsor eventSponsor = new EventSponsor();
        try {
            BeanUtils.copyProperties(eventSponsorBo, eventSponsor);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        eventSponsorMapper.updateByPrimaryKeySelective(eventSponsor);
        return eventSponsorBo;
    }

    @Override
    public String delete(String sponsorId) {
        int r = eventSponsorMapper.deleteByPrimaryKey(sponsorId);
        LOGGER.info("{}", r);
        return "";
    }

}
