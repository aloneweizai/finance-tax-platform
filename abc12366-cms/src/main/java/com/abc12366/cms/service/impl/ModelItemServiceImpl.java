package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.ModelItemMapper;
import com.abc12366.cms.mapper.db2.ModelItemRoMapper;
import com.abc12366.cms.model.ModelItem;
import com.abc12366.cms.model.bo.ModelItemBo;
import com.abc12366.cms.model.bo.ModelItemListBo;
import com.abc12366.cms.service.ModelItemService;
import com.abc12366.gateway.exception.ServiceException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
public class ModelItemServiceImpl implements ModelItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelItemServiceImpl.class);

    @Autowired
    private ModelItemMapper modelItemMapper;

    @Autowired
    private ModelItemRoMapper modelItemRoMapper;

    @Override
    public List<ModelItemBo> selectList(Map<String, Object> map) {
        //查询模型项列表
        List<ModelItemBo> modelItemBos;
        try {
            JSONObject jsonStu = JSONObject.fromObject(map);
            LOGGER.info("查询模型项信息:{}", jsonStu.toString());
            modelItemBos = modelItemRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询模型项异常：{}", e);
            throw new ServiceException(4230);
        }
        return modelItemBos;
    }

    @Override
    public ModelItemListBo updateList(ModelItemListBo modelItemListBo) {
        //保存模型项列表
        try {
            JSONObject jsonStu = JSONObject.fromObject(modelItemListBo);
            LOGGER.info("批量更新模型项信息:{}", jsonStu.toString());
            List<ModelItemBo> list = modelItemListBo.getModelItemBoList();
            for (ModelItemBo modelItemBo : list) {
                ModelItem modelItem = new ModelItem();
                BeanUtils.copyProperties(modelItemBo, modelItem);
                modelItemMapper.updateByPrimaryKeySelective(modelItem);
            }
        } catch (Exception e) {
            LOGGER.error("批量更新模型项异常：{}", e);
            throw new ServiceException(4231);
        }
        return modelItemListBo;
    }

    @Override
    public ModelItemListBo saveList(ModelItemListBo modelItemListBo) {
        //保存模型项列表
        try {
            JSONObject jsonStu = JSONObject.fromObject(modelItemListBo);
            LOGGER.info("批量新增模型项信息:{}", jsonStu.toString());
            List<ModelItemBo> list = modelItemListBo.getModelItemBoList();
            for (ModelItemBo modelItemBo : list) {
                ModelItem modelItem = new ModelItem();
                String uuid = UUID.randomUUID().toString().replace("-", "");
                modelItemBo.setModelitemId(uuid);
                BeanUtils.copyProperties(modelItemBo, modelItem);
                modelItemMapper.insert(modelItem);
            }
        } catch (Exception e) {
            LOGGER.error("批量新增模型项异常：{}", e);
            throw new ServiceException(4232);
        }
        return modelItemListBo;
    }

    @Override
    public ModelItemBo save(ModelItemBo modelItemBo) {
        //保存模型项信息
        try {
            JSONObject jsonStu = JSONObject.fromObject(modelItemBo);
            LOGGER.info("新增模型项信息:{}", jsonStu.toString());
            String uuid = UUID.randomUUID().toString().replace("-", "");
            ModelItem modelItem = new ModelItem();
            modelItemBo.setModelitemId(uuid);
            BeanUtils.copyProperties(modelItemBo, modelItem);
            modelItemMapper.insert(modelItem);
        } catch (Exception e) {
            LOGGER.error("新增模型项异常：{}", e);
            throw new ServiceException(4233);
        }
        return modelItemBo;
    }

    @Override
    public ModelItemBo selectModel(String modelitemId) {
        ModelItemBo modelItemBo = new ModelItemBo();
        try {
            LOGGER.info("查询单个模型项信息:{}", modelitemId);
            ModelItem modelItem = modelItemRoMapper.selectByPrimaryKey(modelitemId);
            BeanUtils.copyProperties(modelItem, modelItemBo);
        } catch (Exception e) {
            LOGGER.error("查询单个模型项异常：{}", e);
            throw new ServiceException(4234);
        }
        return modelItemBo;
    }

    @Override
    public ModelItemBo update(ModelItemBo modelItemBo) {
        //保存模型项信息
        ModelItem modelItem = new ModelItem();
        try {
            JSONObject jsonStu = JSONObject.fromObject(modelItemBo);
            LOGGER.info("更新模型项信息:{}", jsonStu.toString());
            BeanUtils.copyProperties(modelItemBo, modelItem);
            modelItemMapper.updateByPrimaryKeySelective(modelItem);
        } catch (Exception e) {
            LOGGER.error("更新模型项异常：{}", e);
            throw new ServiceException(4235);
        }
        return modelItemBo;
    }

    @Override
    public String delete(String modelitemId) {
        try {
            LOGGER.info("删除模型项信息:{}", modelitemId);
            modelItemMapper.deleteByPrimaryKey(modelitemId);
        } catch (Exception e) {
            LOGGER.error("删除模型项异常：{}", e);
            throw new ServiceException(4236);
        }
        return "";
    }

    @Override
    public String deleteList(String[] modelItemIds) {
        try {
            JSONArray jsonArray = JSONArray.fromObject(modelItemIds);
            LOGGER.info("批量删除模型项信息:{}", jsonArray.toString());
            modelItemMapper.deleteList(modelItemIds);
        } catch (Exception e) {
            LOGGER.error("批量删除模型项异常：{}", e);
            throw new ServiceException(4237);
        }
        return "";
    }


}
