package com.abc12366.uc.service;

import com.abc12366.uc.model.weixin.bo.template.QTemplateSendLog;
import com.abc12366.uc.model.weixin.bo.template.Template;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IWxTemplateService {

	/**
	 * 微信模板消息同步
	 * @return
	 */
    boolean synchroTemplate();

    /**
     * 删除模板
     * @param id 模板id
     */
    public void delete(String id);

    /**
     * 丛数据库查询模板
     * @param id 模板id
     * @return
     */
    Template selectOne(String id);

    /**
     * 模板列表
     * @param template  条件
     * @param page 页数
     * @param size 大小
     * @return
     */
    List<Template> wxTemplateList(Template template, int page, int size);

    /**
     * 发送模板消息
     * @param temp_id  模板ID
     * @param dataList  内容
     * @return
     */
	@SuppressWarnings("rawtypes")
	ResponseEntity templateSend(String temp_id, Map<String, String> dataList);
    /**
     * 发送模板消息
     * @param templatemsg 内容
     * @return
     */
	ResponseEntity templateSend(String templatemsg);
	
	/**
	 * 获取微信模板消息发送日志
	 * @param page 页数
	 * @param size 大小
	 * @param map 参数
	 * @return
	 */
	public List<QTemplateSendLog> wxTemplateSendList(int page, int size,Map<String,Object> map);

}
