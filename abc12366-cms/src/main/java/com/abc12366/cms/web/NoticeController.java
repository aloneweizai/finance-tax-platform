package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.NoticeBO;
import com.abc12366.cms.model.bo.NoticeForqtBO;
import com.abc12366.cms.service.NoticeService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ֪ͨ������������
 *
 * @author yuanluo <ljun51@outlook.com>
 * @create 2017-07-26 4:02 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class NoticeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);
    @Autowired
    private NoticeService noticeService;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/notice")
    public ResponseEntity selectList(@RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "status", required = false) String status,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{}", title, page, size);

        NoticeBO notice = new NoticeBO();
        notice.setTitle(title);
        notice.setStatus(status);
        List<NoticeBO> dataList = noticeService.selectList(notice, page, size);

        PageInfo<NoticeBO> pageInfo = new PageInfo<NoticeBO>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                "total", pageInfo.getTotal()));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/notices")
    public ResponseEntity selectListForqt(@RequestParam(value = "title", required = false) String title,
                                          @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                          @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{}", title, page, size);
        	 NoticeForqtBO notice = new NoticeForqtBO();
             notice.setTitle(title);
             List<NoticeForqtBO> dataList = noticeService.selectListForqt(notice, page, size);
             PageInfo<NoticeForqtBO> pageInfo = new PageInfo<NoticeForqtBO>(dataList);
             ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                     "total", pageInfo.getTotal()));

             LOGGER.info("{}", responseEntity);
             return responseEntity;
    }
    
    @GetMapping("/noticesForqt")
    public ResponseEntity selectListForqtCszj(
                @RequestParam(value = "page", defaultValue = "1") int page,
                @RequestParam(value = "size", defaultValue = "9") int size) {
        
        if(redisTemplate.hasKey("CMS_NoticeListFqt")){
        	List<NoticeForqtBO> dataList = JSONArray.parseArray(redisTemplate.opsForValue().get("CMS_NoticeListFqt"),NoticeForqtBO.class);
    		LOGGER.info("从Redis获取数据:"+JSONArray.toJSONString(dataList));
    		return ResponseEntity.ok(Utils.kv("dataList", dataList, "total", dataList.size()));
        }else{
        	 NoticeForqtBO notice = new NoticeForqtBO();
             List<NoticeForqtBO> dataList = noticeService.selectListForqt(notice, page, size);
             redisTemplate.opsForValue().set("CMS_NoticeListFqt",JSONArray.toJSONString(dataList),RedisConstant.DAY_1, TimeUnit.DAYS);
             PageInfo<NoticeForqtBO> pageInfo = new PageInfo<NoticeForqtBO>(dataList);
             ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                     "total", pageInfo.getTotal()));
             LOGGER.info("{}", responseEntity);
             return responseEntity;
        } 
    }
    
    

    @PostMapping("/notice")
    public ResponseEntity insert(@Valid @RequestBody NoticeBO notice) {
        LOGGER.info("{}", notice);

        NoticeBO v = noticeService.insert(notice);

        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));
        LOGGER.info("{}", responseEntity);
        redisTemplate.delete("CMS_NoticeListFqt");
        return responseEntity;
    }

    @GetMapping("/notice/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        NoticeBO notice = noticeService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", notice));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/notices/{id}")
    public ResponseEntity selectOneForqt(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        NoticeBO notice = noticeService.selectOneForqt(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", notice));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    @PutMapping("/notice/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @Valid @RequestBody NoticeBO notice) {
        LOGGER.info("{},{}", id, notice);

        notice.setId(id);
        NoticeBO v = noticeService.update(notice);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));

        LOGGER.info("{}", responseEntity);
        redisTemplate.delete("CMS_NoticeListFqt");
        return responseEntity;
    }

    @DeleteMapping("/notice/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        noticeService.delete(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        redisTemplate.delete("CMS_NoticeListFqt");
        return responseEntity;
    }

}
