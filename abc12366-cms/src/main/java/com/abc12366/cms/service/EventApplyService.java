package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.EventApplyBo;
import com.abc12366.cms.model.bo.EventApplySaveBo;
import com.abc12366.cms.model.bo.EventbmtjBo;
import com.abc12366.cms.model.bo.EventlltjListBo;

import java.util.List;
import java.util.Map;

public interface EventApplyService {

    List<EventApplyBo> selectList(Map<String, Object> map);

    EventbmtjBo selectbmtj(Map<String, Object> map);

    EventlltjListBo selectlltj(Map<String, Object> map,String type);

    EventApplySaveBo save(EventApplySaveBo eventApplySaveBo);

    EventApplySaveBo selectEventApply(String applyId);

    EventApplySaveBo update(EventApplySaveBo eventApplySaveBo);

    String delete(String applyId);

    String deleteList(String[] applyIds);

    String updateStatusList(String[] applyIds);

    String updateStatusNoList(String[] applyIds,String text);

}
