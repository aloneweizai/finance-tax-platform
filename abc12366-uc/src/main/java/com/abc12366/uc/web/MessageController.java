package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.bo.MessageBO;
import com.abc12366.uc.model.bo.MessageListBO;
import com.abc12366.uc.service.MessageService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * UC发送业务消息接口控制器
 * 接口已过期，改为调用帮帮子系统中的BusinessMsgController
 *
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-09
 * Time: 11:39
 */
@Deprecated
@RestController
@RequestMapping(path = "/message", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class MessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取当前用户消息列表
     *
     * @param page 当前页
     * @param size 每页大小
     * @return ResponseEntity
     */
    @GetMapping()
    public ResponseEntity selectList(@RequestParam(required = false) String type,
                                     @RequestParam(required = false) String busiType,
                                     @RequestParam(required = false) String status,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     HttpServletRequest request) throws IOException {
        LOGGER.info("{},{}:{}", type, page, size);

        // request USER_ID为空
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.bodyStatus(4193));
        String userId = (String) request.getAttribute(Constant.USER_ID);

        request.setAttribute("page", page);
        request.setAttribute("size", size);
        if (type != null && "".equals(type)) {
            type = null;
        }
        if (busiType != null && "".equals(busiType)) {
            busiType = null;
        }
        if (status != null && "".equals(status)) {
            status = null;
        }

        if (!StringUtils.isEmpty(userId)) {
            MessageListBO data = messageService.selectList(type,busiType,status, page, size, request);
            responseEntity = ResponseEntity.ok(data);
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
    
    /**
     * 获取当前用户消息列表缓存
     *
     * @return ResponseEntity
     */
    @GetMapping(path = "/forqt")
    public ResponseEntity selectListForqt(HttpServletRequest request) throws IOException {

        // request USER_ID为空
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.bodyStatus(4193));
        String userId = (String) request.getAttribute(Constant.USER_ID);
        request.setAttribute("page", 0);
        request.setAttribute("size", 0);
        if(redisTemplate.hasKey(userId+"_MessageForqt")){
        	 MessageListBO data = JSONObject.parseObject(redisTemplate.opsForValue().get(userId+"_MessageForqt"),MessageListBO.class);
        	 responseEntity = ResponseEntity.ok(data);
        	 LOGGER.info("从Redis获取数据:"+JSONObject.toJSONString(data));
        }else{
        	if (!StringUtils.isEmpty(userId)) {
                MessageListBO data = messageService.selectList("1",null,null, 0, 0, request);
                responseEntity = ResponseEntity.ok(data);
                redisTemplate.opsForValue().set(userId+"_MessageForqt", JSONObject.toJSONString(data),RedisConstant.DAY_1, TimeUnit.DAYS);
            }
        }

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 发送用户消息
     *
     * @param message
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody Message message, HttpServletRequest request) throws IOException {
        LOGGER.info("{}", message);

        MessageBO messageBO = messageService.insert(message, request);
        ResponseEntity responseEntity = ResponseEntity.ok(messageBO);
        redisTemplate.delete(message.getUserId()+"_MessageForqt");
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 用户查看消息，如果消息状态为'未读'，则将消息状态置为'已读'
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id, HttpServletRequest request) throws IOException {
        LOGGER.info("{}", id);

        MessageBO messageBO = messageService.selectOne(id, request);
        ResponseEntity responseEntity = ResponseEntity.ok(messageBO);

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 直接将'未读'消息置为'已读'，不需要进入消息
     *
     * @param id
     * @return ResponseEntity
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable String id, HttpServletRequest request) throws IOException {
        LOGGER.info("{}", id);

        MessageBO messageBO = messageService.update(id, request);
        ResponseEntity responseEntity = ResponseEntity.ok(messageBO);

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 直接将'未读'消息置为'已读'，不需要进入消息
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id, HttpServletRequest request) throws IOException {
        LOGGER.info("{}", id);

        MessageBO messageBO = messageService.delete(id, request);
        ResponseEntity responseEntity = ResponseEntity.ok(messageBO);

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
}
