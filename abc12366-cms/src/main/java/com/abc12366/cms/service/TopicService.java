package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.TopicBo;
import com.abc12366.cms.model.bo.TopicListBo;

import java.util.List;
import java.util.Map;

public interface TopicService {

    List<TopicBo> selectList(Map<String, Object> map);

    List<TopicBo> selectListBytplContent(String tplContent);

    TopicBo save(TopicBo topicBo);

    TopicBo selectTopic(String topicId);

    TopicBo update(TopicBo topicBo);

    String delete(String topicId);

    String deleteList(String[] topicIds);

    TopicListBo updateList(TopicListBo topicListBo);

}
