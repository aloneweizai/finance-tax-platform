package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.ModelItemMapper;
import com.abc12366.cms.mapper.db1.ModelMapper;
import com.abc12366.cms.mapper.db2.ModelRoMapper;
import com.abc12366.cms.model.Model;
import com.abc12366.cms.model.bo.ModelBo;
import com.abc12366.cms.model.bo.ModelListBo;
import com.abc12366.cms.service.ModelService;
import com.abc12366.gateway.exception.ServiceException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/5/8.
 */
@Service
public class ModelServiceImpl implements ModelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelItemMapper modelItemMapper;

    @Autowired
    private ModelRoMapper modelRoMapper;

    @Override
    public List<ModelBo> selectList() {
        List<ModelBo> modelBoList;
        try {
            //查询模型列表
            modelBoList = modelRoMapper.selectList();
        } catch (Exception e) {
            LOGGER.error("查询模型信息异常：{}", e);
            throw new ServiceException(4220);
        }
        return modelBoList;
    }

    @Override
    public ModelBo save(ModelBo modelBo) {
        try {
            JSONObject jsonStu = JSONObject.fromObject(modelBo);
            LOGGER.info("新增模型信息:{}", jsonStu.toString());
            //保存模型信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Model model = new Model();
            modelBo.setModelId(uuid);
            BeanUtils.copyProperties(modelBo, model);
            modelMapper.insert(model);
        } catch (Exception e) {
            LOGGER.error("新增模型信息异常：{}", e);
            throw new ServiceException(4222);
        }

        return modelBo;
    }

    @Override
    public ModelBo selectModel(String modelId) {
        ModelBo modelBo = new ModelBo();
        try {
            LOGGER.info("查询单个模型信息:{}", modelId);
            //查询模型信息
            Model model = modelRoMapper.selectByPrimaryKey(modelId);
            BeanUtils.copyProperties(model, modelBo);
        } catch (Exception e) {
            LOGGER.error("查询单个模型信息异常：{}", e);
            throw new ServiceException(4221);
        }
        return modelBo;
    }

    @Override
    public ModelBo update(ModelBo modelBo) {
        //更新模型信息
        Model model = new Model();
        try {
            JSONObject jsonStu = JSONObject.fromObject(modelBo);
            LOGGER.info("更新模型信息:{}", jsonStu.toString());
            BeanUtils.copyProperties(modelBo, model);
        } catch (Exception e) {
            LOGGER.error("更新模型信息异常：{}", e);
            throw new ServiceException(4223);
        }
        modelMapper.updateByPrimaryKeySelective(model);
        return modelBo;
    }

    @Override
    public ModelListBo updateList(ModelListBo modelListBo) {
        try {
            JSONObject jsonStu = JSONObject.fromObject(modelListBo);
            LOGGER.info("批量更新模型信息:{}", jsonStu.toString());
            //保存模型项列表
            List<ModelBo> list = modelListBo.getModelBoList();
            for (ModelBo modelBo : list) {
                Model model = new Model();
                BeanUtils.copyProperties(modelBo, model);
                modelMapper.updateByPrimaryKeySelective(model);
            }
        } catch (Exception e) {
            LOGGER.error("批量更新模型异常：{}", e);
            throw new ServiceException(4224);
        }
        return modelListBo;
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String modelId) {
        try {
            LOGGER.info("删除模型信息:{}", modelId);
            int con = modelRoMapper.selectConByModelId(modelId);
            int cha = modelRoMapper.selectChaByModelId(modelId);
            if (con != 0 || cha != 0) {
                throw new ServiceException(4227);
            }
            modelItemMapper.deleteBymodelId(modelId);
            modelMapper.deleteByPrimaryKey(modelId);
        } catch (Exception e) {
            LOGGER.error("删除模型异常：{}", e);
            throw new ServiceException(4225);
        }
        return "";
    }

    @Override
    public String deleteList(String[] modelIds) {
        try {
            JSONArray jsonArray = JSONArray.fromObject(modelIds);
            LOGGER.info("批量删除模型信息:{}", jsonArray.toString());
            //modelItemMapper.deleteListBymodelIds(modelIds);
            //int r = modelMapper.deleteList(modelIds);
            for (int i = 0; i < modelIds.length; i++) {
                this.delete(modelIds[i]);
            }
        } catch (Exception e) {
            LOGGER.error("批量删除模型异常：{}", e);
            throw new ServiceException(4226);
        }
        return "";
    }
}
