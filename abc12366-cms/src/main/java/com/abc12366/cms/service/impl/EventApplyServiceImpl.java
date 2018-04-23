package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.EventApplyAttrMapper;
import com.abc12366.cms.mapper.db1.EventApplyMapper;
import com.abc12366.cms.mapper.db2.EventApplyAttrRoMapper;
import com.abc12366.cms.mapper.db2.EventApplyRoMapper;
import com.abc12366.cms.model.EventApply;
import com.abc12366.cms.model.EventApplyAttr;
import com.abc12366.cms.model.bo.*;
import com.abc12366.cms.service.EventApplyService;
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
public class EventApplyServiceImpl implements EventApplyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventApplyServiceImpl.class);

    @Autowired
    private EventApplyMapper eventApplyMapper;

    @Autowired
    private EventApplyRoMapper eventApplyRoMapper;

    @Autowired
    private EventApplyAttrMapper eventApplyAttrMapper;

    @Autowired
    private EventApplyAttrRoMapper eventApplyAttrRoMapper;

    @Override
    public List<EventApplyBo> selectList(Map<String, Object> map) {
        //查询活动列表
        List<EventApplyBo> dataList = eventApplyRoMapper.selectList(map);
        return dataList;
    }

    @Override
    public EventbmtjBo selectbmtj(Map<String, Object> map) {
        //查询活动列表
        EventbmtjBo eventbmtjBo = eventApplyRoMapper.selectbmtj(map);
        return eventbmtjBo;
    }

    @Override
    public EventlltjListBo selectlltj(Map<String, Object> map,String type) {
        EventlltjListBo eventlltjListBo = new EventlltjListBo();
        if("1".equals(type)){
            //总浏览次数
            List<EventlltjBo> list = eventApplyRoMapper.selectlltj(map);
            map.put("source", "PC");
            List<EventlltjBo> pclist = eventApplyRoMapper.selectlltj(map);
            map.put("source", "MobileWeb");
            List<EventlltjBo> mobileWeblist = eventApplyRoMapper.selectlltj(map);
            map.put("source", "WeChat");
            List<EventlltjBo> weChatlist = eventApplyRoMapper.selectlltj(map);
            Map<String, List<EventlltjBo>> lltj = new HashMap<String, List<EventlltjBo>>();
            lltj.put("total", list);
            lltj.put("PC", pclist);
            lltj.put("MobileWeb", mobileWeblist);
            lltj.put("WeChat", weChatlist);
            eventlltjListBo.setLltj(lltj);
        }else if("2".equals(type)){
            //总浏览次数
            List<EventlltjBo> list = eventApplyRoMapper.selectlltjyue(map);
            map.put("source", "PC");
            List<EventlltjBo> pclist = eventApplyRoMapper.selectlltjyue(map);
            map.put("source", "MobileWeb");
            List<EventlltjBo> mobileWeblist = eventApplyRoMapper.selectlltjyue(map);
            map.put("source", "WeChat");
            List<EventlltjBo> weChatlist = eventApplyRoMapper.selectlltjyue(map);
            Map<String, List<EventlltjBo>> lltj = new HashMap<String, List<EventlltjBo>>();
            lltj.put("total", list);
            lltj.put("PC", pclist);
            lltj.put("MobileWeb", mobileWeblist);
            lltj.put("WeChat", weChatlist);
            eventlltjListBo.setLltj(lltj);
        }else if("3".equals(type)){
            //总浏览次数
            List<EventlltjBo> list = eventApplyRoMapper.selectlltjnian(map);
            map.put("source", "PC");
            List<EventlltjBo> pclist = eventApplyRoMapper.selectlltjnian(map);
            map.put("source", "MobileWeb");
            List<EventlltjBo> mobileWeblist = eventApplyRoMapper.selectlltjnian(map);
            map.put("source", "WeChat");
            List<EventlltjBo> weChatlist = eventApplyRoMapper.selectlltjnian(map);
            Map<String, List<EventlltjBo>> lltj = new HashMap<String, List<EventlltjBo>>();
            lltj.put("total", list);
            lltj.put("PC", pclist);
            lltj.put("MobileWeb", mobileWeblist);
            lltj.put("WeChat", weChatlist);
            eventlltjListBo.setLltj(lltj);
        }
        return eventlltjListBo;
    }

    @Transactional("db1TxManager")
    @Override
    public EventApplySaveBo save(EventApplySaveBo eventApplySaveBo) {
        //保存活动信息
        String uuid = UUID.randomUUID().toString().replace("-", "");
        EventApplyBo eventApplyBo = eventApplySaveBo.getEventApply();
        EventApply eventApply = new EventApply();
        eventApplyBo.setApplyId(uuid);
        eventApplyBo.setApplytime(new Date());
        try {
            BeanUtils.copyProperties(eventApplyBo, eventApply);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        eventApplyMapper.insert(eventApply);
        List<EventApplyAttrBo> eventApplyAttrBoList = eventApplySaveBo.getApplyAttrList();
        if (eventApplyAttrBoList != null) {
            for (EventApplyAttrBo eventApplyAttrBo : eventApplyAttrBoList) {
                eventApplyAttrBo.setApplyId(uuid);
                EventApplyAttr eventApplyAttr = new EventApplyAttr();
                try {
                    BeanUtils.copyProperties(eventApplyAttrBo, eventApplyAttr);
                } catch (Exception e) {
                    LOGGER.error("类转换异常：{}", e);
                    throw new RuntimeException("类型转换异常：{}", e);
                }
                eventApplyAttrMapper.insert(eventApplyAttr);
            }
        }

        return eventApplySaveBo;
    }

    @Override
    public EventApplySaveBo selectEventApply(String applyId) {
        //查询主办方信息
        EventApplySaveBo eventApplySaveBo = new EventApplySaveBo();
        EventApply eventApply = eventApplyRoMapper.selectByPrimaryKey(applyId);
        EventApplyBo eventApplyBo = new EventApplyBo();
        try {
            BeanUtils.copyProperties(eventApply, eventApplyBo);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        eventApplySaveBo.setEventApply(eventApplyBo);
        List<EventApplyAttrBo> eventApplyAttrBoList = new ArrayList<EventApplyAttrBo>();
        List<EventApplyAttr> eventApplyAttrList = eventApplyAttrRoMapper.selectByPrimaryKey(applyId);
        if (eventApplyAttrList != null) {
            for (EventApplyAttr eventApplyAttr : eventApplyAttrList) {
                EventApplyAttrBo eventApplyAttrBo = new EventApplyAttrBo();
                try {
                    BeanUtils.copyProperties(eventApplyAttr, eventApplyAttrBo);
                } catch (Exception e) {
                    LOGGER.error("类转换异常：{}", e);
                    throw new RuntimeException("类型转换异常：{}", e);
                }
                eventApplyAttrBoList.add(eventApplyAttrBo);
            }
        }
        eventApplySaveBo.setApplyAttrList(eventApplyAttrBoList);
        return eventApplySaveBo;
    }

    @Transactional("db1TxManager")
    @Override
    public EventApplySaveBo update(EventApplySaveBo eventApplySaveBo) {
        //更新活动信息
        EventApplyBo eventApplyBo = eventApplySaveBo.getEventApply();
        EventApply eventApply = new EventApply();
        try {
            BeanUtils.copyProperties(eventApplyBo, eventApply);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        eventApplyMapper.insert(eventApply);
        eventApplyAttrMapper.deleteByPrimaryKey(eventApplyBo.getApplyId());
        List<EventApplyAttrBo> eventApplyAttrBoList = eventApplySaveBo.getApplyAttrList();
        if (eventApplyAttrBoList != null) {
            for (EventApplyAttrBo eventApplyAttrBo : eventApplyAttrBoList) {
                eventApplyAttrBo.setApplyId(eventApplyBo.getApplyId());
                EventApplyAttr eventApplyAttr = new EventApplyAttr();
                try {
                    BeanUtils.copyProperties(eventApplyAttrBo, eventApplyAttr);
                } catch (Exception e) {
                    LOGGER.error("类转换异常：{}", e);
                    throw new RuntimeException("类型转换异常：{}", e);
                }
                eventApplyAttrMapper.insert(eventApplyAttr);
            }
        }

        return eventApplySaveBo;
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String applyId) {
        eventApplyAttrMapper.deleteByPrimaryKey(applyId);
        int r = eventApplyMapper.deleteByPrimaryKey(applyId);
        LOGGER.info("{}", r);
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String deleteList(String[] applyIds) {
        //删除评论信息
        eventApplyAttrMapper.deleteList(applyIds);
        //删除评论扩展信息
        int r = eventApplyMapper.deleteList(applyIds);
        LOGGER.info("{}", r);
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String updateStatusList(String[] applyIds) {
        //批量审批评论信息
        int r = eventApplyMapper.updateStatusList(applyIds);
        LOGGER.info("{}", r);
        return "";
    }


    @Transactional("db1TxManager")
    @Override
    public String updateStatusNoList(String[] applyIds,String text) {
        //批量审批评论信息
        int r = eventApplyMapper.updateStatusNoList(applyIds,text);
        LOGGER.info("{}", r);
        return "";
    }


}
