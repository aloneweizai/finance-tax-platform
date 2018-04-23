package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.AdPageBO;
import com.abc12366.cms.service.AdPageService;
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
 *
 * @author yuanluo <ljun51@outlook.com>
 * @create 2017-07-26 4:02 PM
 * @since 1.0.0
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AdPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdPageController.class);
    @Autowired
    private AdPageService adPageService;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/adpage")
    public ResponseEntity selectList(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{}", name, page, size);

        AdPageBO adPage = new AdPageBO();
        adPage.setName(name);
        List<AdPageBO> dataList = adPageService.selectList(adPage, page, size);

        PageInfo<AdPageBO> pageInfo = new PageInfo<AdPageBO>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                "total", pageInfo.getTotal()));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/adpages")
    public ResponseEntity selectListForqt(@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                          @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        /*LOGGER.info("{},{},{}", name, page, size);

        AdPageBO adPage = new AdPageBO();
        adPage.setName(name);
        List<AdPageBO> dataList = adPageService.selectListForqt(adPage, page, size);

        PageInfo<AdPageBO> pageInfo = new PageInfo<AdPageBO>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
                "total", pageInfo.getTotal()));

        LOGGER.info("{}", responseEntity);*/
    	//redisTemplate.delete("CMS_AdpageListFqt");
    	if(redisTemplate.hasKey("CMS_AdpageListFqt")){
    		List<AdPageBO> dataList = JSONArray.parseArray(redisTemplate.opsForValue().get("CMS_AdpageListFqt"),AdPageBO.class);
    		LOGGER.info("从Redis获取数据:"+JSONArray.toJSONString(dataList));
    		return ResponseEntity.ok(Utils.kv("dataList", dataList, "total", dataList.size()));
    	}else{
    		AdPageBO adPage = new AdPageBO();
	        adPage.setName(name);
	        List<AdPageBO> dataList = adPageService.selectListForqt(adPage, 0, 0);
	        redisTemplate.opsForValue().set("CMS_AdpageListFqt",JSONArray.toJSONString(dataList),RedisConstant.DAY_1, TimeUnit.DAYS);
	        PageInfo<AdPageBO> pageInfo = new PageInfo<AdPageBO>(dataList);
	        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(),
	                "total", pageInfo.getTotal()));
    	}
    }
   
    
    

    @PostMapping("/adpage")
    public ResponseEntity insert(@Valid @RequestBody AdPageBO adPage) {
        LOGGER.info("{}", adPage);

        AdPageBO v = adPageService.insert(adPage);

        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));
        LOGGER.info("{}", responseEntity);
        redisTemplate.delete("CMS_AdpageListFqt");
        return responseEntity;
    }

    @GetMapping("/adpage/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        AdPageBO adPage = adPageService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", adPage));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    @GetMapping("/adpages/{id}")
    public ResponseEntity selectOneForqt(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        AdPageBO adPage = adPageService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", adPage));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    @PutMapping("/adpage/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @Valid @RequestBody AdPageBO adPage) {
        LOGGER.info("{},{}", id, adPage);

        adPage.setId(id);
        AdPageBO v = adPageService.update(adPage);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));

        LOGGER.info("{}", responseEntity);
        redisTemplate.delete("CMS_AdpageListFqt");
        return responseEntity;
    }

    @DeleteMapping("/adpage/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        adPageService.delete(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        redisTemplate.delete("CMS_AdpageListFqt");
        return responseEntity;
    }

}
