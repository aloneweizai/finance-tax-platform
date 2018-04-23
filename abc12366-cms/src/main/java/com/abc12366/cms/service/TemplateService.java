package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.TemplateBo;

import java.util.List;
import java.util.Map;

public interface TemplateService {

    List<TemplateBo> selectList(Map<String, Object> map);

    TemplateBo save(TemplateBo templateBo);

    TemplateBo selectTemplate(String templateId);

    TemplateBo update(TemplateBo templateBo);

    String delete(String templateId);

}
