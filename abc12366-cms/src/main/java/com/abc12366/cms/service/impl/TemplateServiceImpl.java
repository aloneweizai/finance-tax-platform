package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.TemplateMapper;
import com.abc12366.cms.mapper.db2.TemplateRoMapper;
import com.abc12366.cms.model.Template;
import com.abc12366.cms.model.bo.TemplateBo;
import com.abc12366.cms.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/5/8.
 */
@Service
public class TemplateServiceImpl implements TemplateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private TemplateRoMapper templateRoMapper;

    @Override
    public List<TemplateBo> selectList(Map<String, Object> map) {
        //查询模型列表
        List<TemplateBo> templateBoList = templateRoMapper.selectList(map);
        return templateBoList;
    }

    @Override
    public TemplateBo save(TemplateBo templateBo) {
        //保存模型信息
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Template template = new Template();
        templateBo.setTemplateId(uuid);
        templateBo.setCreateTime(new Date());
        try {
            BeanUtils.copyProperties(templateBo, template);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        templateMapper.insert(template);
        return templateBo;
    }

    @Override
    public TemplateBo selectTemplate(String templateId) {
        //查询模型信息
        Template template = templateRoMapper.selectByPrimaryKey(templateId);
        TemplateBo templateBo = new TemplateBo();
        try {
            BeanUtils.copyProperties(template, templateBo);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        return templateBo;
    }

    @Override
    public TemplateBo update(TemplateBo templateBo) {
        //更新模型信息
        Template template = new Template();
        try {
            templateBo.setUpdateTime(new Date());
            BeanUtils.copyProperties(templateBo, template);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        templateMapper.updateByPrimaryKeySelective(template);
        return templateBo;
    }

    @Override
    public String delete(String templateId) {
        int r = templateMapper.deleteByPrimaryKey(templateId);
        LOGGER.info("{}", r);
        return "";
    }
}
