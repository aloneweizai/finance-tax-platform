package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.TopicMapper;
import com.abc12366.cms.mapper.db2.TopicRoMapper;
import com.abc12366.cms.model.Topic;
import com.abc12366.cms.model.bo.TopicBo;
import com.abc12366.cms.model.bo.TopicListBo;
import com.abc12366.cms.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/5/8.
 */
@Service
public class TopicServiceImpl implements TopicService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicRoMapper topicRoMapper;

    @Override
    public List<TopicBo> selectList(Map<String, Object> map) {
        //查询模型列表
        List<TopicBo> topicBoList = topicRoMapper.selectList(map);
        return topicBoList;
    }

    @Override
    public List<TopicBo> selectListBytplContent(String tplContent) {
        //查询模型列表
        List<TopicBo> topicBoList = topicRoMapper.selectListBytplContent(tplContent);
        return topicBoList;
    }


    @Override
    public TopicBo save(TopicBo topicBo) {
        //保存模型信息
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Topic topic = new Topic();
        topicBo.setTopicId(uuid);
        try {
            BeanUtils.copyProperties(topicBo, topic);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        topicMapper.insert(topic);
        return topicBo;
    }

    @Override
    public TopicBo selectTopic(String topicId) {
        //查询模型信息
        Topic topic = topicRoMapper.selectByPrimaryKey(topicId);
        TopicBo topicBo = new TopicBo();
        try {
            BeanUtils.copyProperties(topic, topicBo);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        return topicBo;
    }

    @Override
    public TopicBo update(TopicBo topicBo) {
        //更新模型信息
        Topic topic = new Topic();
        try {
            BeanUtils.copyProperties(topicBo, topic);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        topicMapper.updateByPrimaryKeySelective(topic);
        return topicBo;
    }

    @Override
    public TopicListBo updateList(TopicListBo topicListBo) {
        //保存模型项列表
        List<TopicBo> list = topicListBo.getTopicBoList();
        for (TopicBo topicBo : list) {
            Topic topic = new Topic();
            try {
                BeanUtils.copyProperties(topicBo, topic);
            } catch (Exception e) {
                LOGGER.error("类转换异常：{}", e);
                throw new RuntimeException("类型转换异常：{}", e);
            }
            topicMapper.updateByPrimaryKeySelective(topic);
        }
        return topicListBo;
    }

    @Override
    public String delete(String topicId) {
        int r = topicMapper.deleteByPrimaryKey(topicId);
        LOGGER.info("{}", r);
        return "";
    }

    @Override
    public String deleteList(String[] topicIds) {
        int r = topicMapper.deleteList(topicIds);
        LOGGER.info("{}", r);
        return "";
    }
}
