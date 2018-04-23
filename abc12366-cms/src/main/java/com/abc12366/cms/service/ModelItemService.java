package com.abc12366.cms.service;

import com.abc12366.cms.model.bo.ModelItemBo;
import com.abc12366.cms.model.bo.ModelItemListBo;

import java.util.List;
import java.util.Map;

public interface ModelItemService {
    List<ModelItemBo> selectList(Map<String, Object> map);

    ModelItemListBo updateList(ModelItemListBo modelItemListBo);

    ModelItemListBo saveList(ModelItemListBo modelItemListBo);

    ModelItemBo save(ModelItemBo modelItemBo);

    ModelItemBo selectModel(String modelItemId);

    ModelItemBo update(ModelItemBo modelItemBo);

    String delete(String modelItemId);

    String deleteList(String[] modelIds);

}
