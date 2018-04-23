package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.TemplateMapper;
import com.abc12366.uc.mapper.db2.TemplateRoMapper;
import com.abc12366.uc.mapper.db2.WxGzhRoMapper;
import com.abc12366.uc.model.weixin.BaseWxRespon;
import com.abc12366.uc.model.weixin.bo.template.QTemplateSendLog;
import com.abc12366.uc.model.weixin.bo.template.Template;
import com.abc12366.uc.model.weixin.bo.template.TemplateSendLog;
import com.abc12366.uc.model.weixin.bo.template.WxTemplates;
import com.abc12366.uc.service.IWxTemplateService;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxConnectFactory;
import com.abc12366.uc.util.wx.WxGzhClient;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("templateService")
public class WxTemplateServiceImpl implements IWxTemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxTemplateServiceImpl.class);
    @Autowired
    private TemplateRoMapper templateRoMapper;
    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private WxGzhRoMapper gzhRoMapper;

    @Transactional("db1TxManager")
    public boolean synchroTemplate() {
        LOGGER.info("开始同步微信模板消息数据.......");
        Map<String, String> headparamters = new HashMap<String, String>();
        headparamters.put("access_token", WxGzhClient.getInstanceToken());
        WxTemplates listRs = WxConnectFactory.get(WechatUrl.TEMPLATEMSG_LIST, headparamters, null, WxTemplates.class);
        if (listRs.getErrcode() != 0) {
            return false;
        } else {
        	templateMapper.deleteAll();
            for (int i = 0; i < listRs.getTemplate_list().size(); i++) {
                Template template = listRs.getTemplate_list().get(i);
                template.setLastupdate(new Timestamp(new Date().getTime()));
                templateMapper.insert(template);
            }
            return true;
        }

    }

    @Transactional("db1TxManager")
    public void delete(String id) {
        Map<String, String> headparamters = new HashMap<String, String>();
        headparamters.put("access_token", WxGzhClient.getInstanceToken());
        BaseWxRespon baseWxRespon = WxConnectFactory.post(WechatUrl.TEMPLATEMSG_DEL, headparamters, 
        		"{\"template_id\" : \""+id+"\"}",
                BaseWxRespon.class);
        if (baseWxRespon.getErrcode() != 0) {
            throw new ServiceException(9999,baseWxRespon.getErrmsg());
        }else{
        	templateMapper.delete(id);
        }
    }

    @Override
    public Template selectOne(String id) {
        Template info = new Template();
        try {
            LOGGER.info("查询单个模板消息:{}", id);
            info = templateRoMapper.selectOne(id);
        } catch (Exception e) {
            LOGGER.error("查询单个模板消息异常：{}", e);
            throw new ServiceException(9999,"查询单个模板消息异常");
        }
        return info;
    }

    @Override
    public List<Template> wxTemplateList(Template template, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<Template> templates = templateRoMapper.selectList(template);
        return templates;
    }
    
    
    public List<QTemplateSendLog> wxTemplateSendList(int page, int size,Map<String,Object> map){
    	 PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
    	 return templateRoMapper.selectLog(map);
    }

	@SuppressWarnings("rawtypes")
	public ResponseEntity templateSend(String temp_id, Map<String, String> dataList) {
		
		LOGGER.info("接收到微信模板消息信息:"+JSONObject.toJSONString(dataList));
		
		Template info=templateRoMapper.selectOne(temp_id);
		if(info==null){
			return ResponseEntity.ok(Utils.bodyStatus(9999, "没有找到对应的模板消息!"));
		}else{
			boolean canFs = false;
			//应要求添加校验
			String[] keys=StringUtils.substringsBetween(info.getContent(),"{{", ".DATA}}");
			for(String key:keys){
				String s = dataList.get(key);
				if((!"userId".equals(key) || !"openId".equals(key))
						&& StringUtils.isNotEmpty(s)){
					canFs = true;
				}
			}
			if(canFs){
				String msg=info.toSendJson(dataList);
				Map<String, String> headparamters = new HashMap<String, String>();
				headparamters.put("access_token", WxGzhClient.getInstanceToken());
				BaseWxRespon wxRespon=WxConnectFactory.post(WechatUrl.TEMPLATEMSG_SEND, headparamters,msg, BaseWxRespon.class);
				
				//记录微信模板消息发送日志
				Date now =new Date();
				templateMapper.insertLog(new TemplateSendLog(Utils.uuid(), temp_id, dataList.get("userId"), dataList.get("openId"), msg, wxRespon.getErrcode().toString(), wxRespon.getErrmsg(), now, now));
				
				if(wxRespon.getErrcode()!=0){
					return ResponseEntity.ok(Utils.bodyStatus(9999, wxRespon.getErrmsg()));
				}else{
					return ResponseEntity.ok(Utils.kv());
				}
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, "内容为空，拒绝发送!"));
			}
			
		}
		
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity templateSend(String templatemsg) {
		Map<String, String> headparamters = new HashMap<String, String>();
		headparamters.put("access_token", WxGzhClient.getInstanceToken());
		BaseWxRespon wxRespon=WxConnectFactory.post(WechatUrl.TEMPLATEMSG_SEND, headparamters,
				templatemsg, BaseWxRespon.class);
		if(wxRespon.getErrcode()!=0){
			return ResponseEntity.ok(Utils.bodyStatus(9999, wxRespon.getErrmsg()));
		}else{
			return ResponseEntity.ok(Utils.kv());
		}
	}

	
}
