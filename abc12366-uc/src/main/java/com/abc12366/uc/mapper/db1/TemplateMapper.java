package com.abc12366.uc.mapper.db1;


import com.abc12366.uc.model.weixin.bo.template.Template;
import com.abc12366.uc.model.weixin.bo.template.TemplateSendLog;

public interface TemplateMapper {

    void insert(Template template);

    void delete(String id);

    void deleteAll();

	void insertLog(TemplateSendLog log);

}
