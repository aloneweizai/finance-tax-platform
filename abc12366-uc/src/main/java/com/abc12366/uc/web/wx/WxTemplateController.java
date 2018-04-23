package com.abc12366.uc.web.wx;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.weixin.bo.template.QTemplateSendLog;
import com.abc12366.uc.model.weixin.bo.template.Template;
import com.abc12366.uc.service.IWxTemplateService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信模板消息
 *
 * @author zhushuai 2017-8-3
 */
@Controller
public class WxTemplateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxTemplateController.class);

    @Autowired
    private IWxTemplateService templateService;

    /**
     * 同步微信模板消息到数据库
     * @param request  上下文
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/wxTemplate/synchro")
    public ResponseEntity synchroTemplate(HttpServletRequest request) {
        if (templateService.synchroTemplate()) {
            return ResponseEntity.ok(Utils.kv());
        } else {
            throw new ServiceException(4117);
        }
    }

    /**
     * 从数据库获取模板消息
     * @param template  模板
     * @param page  页数
     * @param size 大小
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxTemplate/list")
    public ResponseEntity wxTemplateList(Template template,
                                         @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                         @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{}", template, page, size);
        List<Template> dataList = templateService.wxTemplateList(template, page, size);

        PageInfo<Template> pageInfo = new PageInfo<Template>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                "total", pageInfo.getTotal()));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 从数据库获取单个菜单信息
     * @param id  模板id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxTemplate/{id}")
    public ResponseEntity wxTemplateInfo(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        Template template = templateService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", template));

        LOGGER.info("{}", responseEntity);
        return responseEntity;

    }

    /**
     * 删除数据库以及微信端模板消息
     * @param id 模板id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/wxTemplate/{id}")
    public ResponseEntity templateDel(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        templateService.delete(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 模板消息发送
     * @param temp_id 模板ID
     * @param dataList 模板内容
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PostMapping("/wxTemplate/send/{temp_id}")
    public ResponseEntity templateSend(@PathVariable("temp_id") String temp_id,
    		@RequestBody Map<String,String> dataList) {
        return templateService.templateSend(temp_id,dataList);
    }
    
    /**
     * 模板消息发送
     * @param templatemsg  模板内容
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PostMapping("/wxTemplate/sendstr/")
    public ResponseEntity templateSendstr(@RequestBody String templatemsg) {
        return templateService.templateSend(templatemsg);
    }
    
    /**
     * 微信模板发送日志查询
     * @param nickname  昵称
     * @param username 用户名
     * @param beginTime 开始时间 yyyy-MM-dd
     * @param endTime 结束时间 yyyy-MM-dd
     * @param page 页数
     * @param size 大小
     * @return
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("/wxtemplatesendlog")
    public ResponseEntity wxTemplateSendList(
    		@RequestParam(value = "nickname", required = false) String nickname,
    		@RequestParam(value = "username", required = false) String username,
    		@RequestParam(value = "beginTime", required = false) String beginTime,
    		@RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
    	
		LOGGER.info("{},{},{},{},{},{}", nickname, username, beginTime,endTime,page,size);
		
		Map<String,Object> map=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(nickname)){
			map.put("nickname", nickname);
		}
		if(!StringUtils.isEmpty(username)){
			map.put("username", username);
		}
		if(!StringUtils.isEmpty(beginTime)){
			map.put("beginTime", DateUtils.strToDate(beginTime));
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", DateUtils.strToDate(endTime));
		}
		
		List<QTemplateSendLog> dataList = templateService.wxTemplateSendList(page, size,map);
		
		PageInfo<QTemplateSendLog> pageInfo = new PageInfo<QTemplateSendLog>(dataList);
		ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
		"total", pageInfo.getTotal()));
		
		LOGGER.info("{}", responseEntity);
		return responseEntity;
	}
   
}
