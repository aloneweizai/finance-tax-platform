package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.*;
import com.abc12366.cms.service.ChannelService;
import com.abc12366.cms.service.ContentService;
import com.abc12366.cms.service.ModelService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 内容管理模块
 *
 * @author xieyanmao
 * @create 2017-04-27
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/content", headers = Constant.VERSION_HEAD + "=1")
public class ContentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private ContentService contentService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ModelService modelService;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 查询内容列表信息
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "siteId", required = false) String siteId,
                                     @RequestParam(value = "topLevel", required = false) String topLevel,
                                     @RequestParam(value = "typeId", required = false) String typeId,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "status", required = false) String status,
                                     @RequestParam(value = "channelId", required = false) String channelId,
                                     @RequestParam(value = "recommendLevel", required = false) String recommendLevel) {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("siteId", siteId);//站点ID
        dataMap.put("title", title);//标题
        dataMap.put("topLevel", topLevel);//置顶级别
        dataMap.put("typeId", typeId);//内容类型
        dataMap.put("username", username);//作者
        dataMap.put("status", status);//状态
        dataMap.put("channelId", channelId);//栏目ID
        dataMap.put("recommendLevel", recommendLevel);//推荐级别

        // 分页插件的用法：加入下面一行代码之后，插件会将最近的select语句分页；下面的代码可以放在Controller或Service中.
        // 当Service中有多条select语句时，建议放在Service中，这时需要将Page对象传递到Service实现方法，返回对象也是Page对象。
        // 将List对象强制转成Page可以获取Page的相关属性。如：((Page)dataList).getTotal()，总记录数统一使用total返回。
        // 代码解释：
        // count=true(第一个),默认值为false，是查询总记录数
        // pageSizeZero=true,默认值为 false，当该参数设置为 true 时，如果 pageSize=0 或者 pageNum = 0 就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是
        // Page 类型）
        // reasonable=true,分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        //查询内容列表
        List<ContentListBo> dataList = contentService.selectList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 查询内容列表信息(搜索)
     */
    @GetMapping(path = "/selectListSearch")
    public ResponseEntity selectListSearch(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                     @RequestParam(value = "siteId", required = false) String siteId,
                                     @RequestParam(value = "channelId", required = false) String channelId,
                                     @RequestParam(value = "text", required = false) String text) {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("siteId", siteId);//站点ID
        dataMap.put("channelId", channelId);//栏目ID
        dataMap.put("text", text);//搜索内容
        dataMap.put("text1", text+"*");//搜索内容

        // 分页插件的用法：加入下面一行代码之后，插件会将最近的select语句分页；下面的代码可以放在Controller或Service中.
        // 当Service中有多条select语句时，建议放在Service中，这时需要将Page对象传递到Service实现方法，返回对象也是Page对象。
        // 将List对象强制转成Page可以获取Page的相关属性。如：((Page)dataList).getTotal()，总记录数统一使用total返回。
        // 代码解释：
        // count=true(第一个),默认值为false，是查询总记录数
        // pageSizeZero=true,默认值为 false，当该参数设置为 true 时，如果 pageSize=0 或者 pageNum = 0 就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是
        // Page 类型）
        // reasonable=true,分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        //查询内容列表
        List<ContentListBo> dataList = contentService.selectListSearch(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 查询内容列表信息按访问量
     */
    @GetMapping(path = "/selectListByviews")
    public ResponseEntity selectListByviews(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                            @RequestParam(value = "tagId", required = false) String tagId,
                                            @RequestParam(value = "siteId", required = false) String siteId,
                                            @RequestParam(value = "channelId", required = false) String channelId,
                                            @RequestParam(value = "exceptChannelId", required = false) String exceptChannelId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tagId", tagId);//标签
        dataMap.put("channelId", channelId);//栏目ID
        dataMap.put("siteId", siteId);//站点ID
        dataMap.put("exceptChannelId", exceptChannelId);//过滤掉的栏目ID

        // 分页插件的用法：加入下面一行代码之后，插件会将最近的select语句分页；下面的代码可以放在Controller或Service中.
        // 当Service中有多条select语句时，建议放在Service中，这时需要将Page对象传递到Service实现方法，返回对象也是Page对象。
        // 将List对象强制转成Page可以获取Page的相关属性。如：((Page)dataList).getTotal()，总记录数统一使用total返回。
        // 代码解释：
        // count=true(第一个),默认值为false，是查询总记录数
        // pageSizeZero=true,默认值为 false，当该参数设置为 true 时，如果 pageSize=0 或者 pageNum = 0 就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是
        // Page 类型）
        // reasonable=true,分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        //查询内容列表
        List<ContentViewListBo> dataList = contentService.selectListByviews(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 根据内容标签查询内容列表信息，过滤掉指定栏目（exceptChannelId）的信息
     */
    @GetMapping(path = "/selectListByContentType")
    public ResponseEntity selectListByContentType(@RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                              page,
                                                  @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                          size,
                                                  @RequestParam(value = "siteId", required = false) String siteId,
                                                  @RequestParam(value = "tagId", required = false) String tagId,
                                                  @RequestParam(value = "channelId", required = false) String channelId,
                                                  @RequestParam(value = "exceptChannelId", required = false) String exceptChannelId,
                                                  @RequestParam(value = "startTime", required = false) String startTime,
                                                  @RequestParam(value = "endTime", required = false) String endTime) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tagId", tagId);//内容类型(标签)
        dataMap.put("channelId", channelId);//栏目ID
        dataMap.put("exceptChannelId", exceptChannelId);//过滤掉的栏目ID
        dataMap.put("siteId", siteId);//站点ID
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startTime != null && !"".equals(startTime)) {
                Date startTime1 = sdf.parse(startTime);
                dataMap.put("startTime", startTime1.getTime() / 1000);
            }
        } catch (ParseException e) {
            LOGGER.error("时间类转换异常：{}", e);
            throw new RuntimeException("时间类型转换异常：{}", e);
        }

//        dataMap.put("endTime", endTime);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        //查询内容列表
        List<ContentsListBo> dataList = contentService.selectListByContentType(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 根据内容标签名称查询内容列表信息
     */
    @GetMapping(path = "/selectListByTagName")
    public ResponseEntity selectListByTagName(@RequestParam(value = "page", defaultValue = Constant.pageNum) int
                                                          page,
                                                  @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                          size,
                                                  @RequestParam(value = "siteName", required = false) String siteName,
                                                  @RequestParam(value = "tagName", required = false) String tagName,
                                                  @RequestParam(value = "channelName", required = false) String channelName) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tagName", tagName);//内容类型(标签)
        dataMap.put("channelName", channelName);//栏目ID
        dataMap.put("siteName", siteName);//站点ID
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        //查询内容列表
        List<ContentsListBo> dataList = contentService.selectListByTagName(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 根据内容标签分组，获取内容标签列表
     */
    @GetMapping(path = "/selectContentType")
    public ResponseEntity selectContentType(@RequestParam(value = "siteId", required = false) String siteId,
                                            @RequestParam(value = "channelId", required = false) String channelId) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("channelId", channelId);//栏目ID
        dataMap.put("siteId", siteId);//栏目ID
        //查询内容列表
        List<ContenttagidBo> dataList = contentService.selectContentType(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 根据栏目ID查询内容列表信息
     */
    @GetMapping(path = "/selectListByChannelId")
    public ResponseEntity selectListByChannelId(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                @RequestParam(value = "size", defaultValue = Constant.pageSize) int size,
                                                @RequestParam(value = "typeId", required = false) String typeId,
                                                @RequestParam(value = "status", required = false) String status,
                                                @RequestParam(value = "channelId", required = false) String channelId,
                                                @RequestParam(value = "exceptChannelId", required = false) String exceptChannelId,
                                                @RequestParam(value = "topicId", required = false) String topicId,
                                                @RequestParam(value = "channelName", required = false) String
                                                            channelName,
                                                @RequestParam(value = "startTime", required = false) String startTime,
                                                @RequestParam(value = "endTime", required = false) String endTime,
                                                @RequestParam(value = "tplContent", required = false) String
                                                            tplContent) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("topicId", topicId);//专题ID
        dataMap.put("typeId", typeId);//内容类型
        dataMap.put("status", status);//状态
        dataMap.put("channelId", channelId);//栏目ID
        dataMap.put("channelName", channelName);//栏目名称
        dataMap.put("exceptChannelId", exceptChannelId);//过滤掉的栏目ID
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startTime != null && !"".equals(startTime)) {
                Date startTime1 = sdf.parse(startTime);
                dataMap.put("startTime", startTime1.getTime() / 1000);
            }
        } catch (ParseException e) {
            LOGGER.error("时间类转换异常：{}", e);
            throw new RuntimeException("时间类型转换异常：{}", e);
        }

//        dataMap.put("endTime", endTime);
        dataMap.put("tplContent", tplContent);

        int cnt = contentService.selectCntByChannelId(dataMap);
        if (cnt > 0) {
            //该栏目或者专题下存在未生成静态页的内容信息，请先生成内容静态页
            throw new ServiceException(4255);
        }


        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        //查询内容列表
        List<ContentsListBo> dataList = contentService.selectListByChannelId(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }

    /**
     * 财税咨询网
     */
    @GetMapping(path = "/selectListcszxw")
    public ResponseEntity selectListcszxw(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                                @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("siteId", "3ef33a7ece264f859a4c4af37ba458c9");//站点ID
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        //查询内容列表
        List<ContentsListBo> dataList = contentService.selectListcszxw(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }
    
    @GetMapping(path = "/selectListcszxwForqt")
    public ResponseEntity selectListcszxwForqt() {
    	if(redisTemplate.hasKey("CMS_SelectListcszxwFqt")){
    		List<ContentsListBo> dataList = JSONArray.parseArray(redisTemplate.opsForValue().get("CMS_SelectListcszxwFqt"),ContentsListBo.class);
    		LOGGER.info("从Redis获取数据:"+JSONArray.toJSONString(dataList));
    		return ResponseEntity.ok(Utils.kv("dataList", dataList, "total", dataList.size()));
    	}else{
    		Map<String, Object> dataMap = new HashMap<>();
	        dataMap.put("siteId", "3ef33a7ece264f859a4c4af37ba458c9");//站点ID
	        PageHelper.startPage(1, 7, true).pageSizeZero(true).reasonable(true);
	        //查询内容列表
	        List<ContentsListBo> dataList = contentService.selectListcszxw(dataMap);
	        redisTemplate.opsForValue().set("CMS_SelectListcszxwFqt",JSONArray.toJSONString(dataList),RedisConstant.DAY_1, TimeUnit.DAYS);
	        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    	}
    }
    

    /**
     * 内容初始化
     */
    @GetMapping(path = "/init")
    public ResponseEntity init(@RequestParam(value = "modelId", required = false) String modelId) {
        //查询模型项
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("modelId", modelId);
        dataMap.put("isChannel", 0);
        ContentInitBo dataList = new ContentInitBo();
        List<ModelItemBo> modelItems = contentService.selectModeList(dataMap);
        List<ChannelBo> channels = channelService.selectList();
        ModelBo modelBo = modelService.selectModel(modelId);
        dataList.setChannels(channels);
        dataList.setModelItems(modelItems);
        dataList.setTplPrefix(modelBo.getTplContentPrefix());
        LOGGER.info("{}", dataList);
        redisTemplate.delete("CMS_SelectListcszxwFqt");
        return ResponseEntity.ok(Utils.kv("data", dataList));
    }

    /**
     * 新增内容
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody ContentSaveBo contentSaveBo) {
        LOGGER.info("{}", contentSaveBo);
        //新增内容信息
        contentSaveBo = contentService.save(contentSaveBo);
        LOGGER.info("{}", contentSaveBo);

        redisTemplate.delete("CMS_SelectListcszxwFqt");
        return ResponseEntity.ok(Utils.kv("data", contentSaveBo));
    }

    /**
     * 查询单个内容
     */
    @GetMapping(path = "/{contentId}")
    public ResponseEntity selectOne(@PathVariable String contentId) {
        LOGGER.info("{}", contentId);
        //根据内容ID查询内容信息
        ContentSaveBo contentSaveBo = contentService.selectContent(contentId);
        LOGGER.info("{}", contentSaveBo);
        return ResponseEntity.ok(Utils.kv("data", contentSaveBo));
    }

    /**
     * 查询单个内容(前端用)
     */
    @GetMapping(path = "/selectContent")
    public ResponseEntity selectContent(@RequestParam(value = "contentId", required = true) String contentId) {
        LOGGER.info("{}", contentId);
        //根据内容ID查询内容信息
        ContentSaveBo contentSaveBo = contentService.selectContent(contentId);
        LOGGER.info("{}", contentSaveBo);
        return ResponseEntity.ok(Utils.kv("data", contentSaveBo));
    }

    /**
     * 根据栏目ID查找内容信息
     */
    @GetMapping(path = "/contentList")
    public ResponseEntity contentList(@RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime,
                                      @RequestParam(value = "tplContent", required = false) String tplContent,
                                      @RequestParam(value = "status", required = false) String status,
                                      @RequestParam(value = "channelId", required = false) String channelId) {
        //查询模型项
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tplContent", tplContent);
        dataMap.put("channelId", channelId);
        dataMap.put("status", status);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startTime != null && !"".equals(startTime)) {
                Date startTime1 = sdf.parse(startTime);
                dataMap.put("startTime", startTime1.getTime() / 1000);
            }
//            else {
//                dataMap.put("needRegenerate", "0");
//            }
        } catch (ParseException e) {
            LOGGER.error("时间类转换异常：{}", e);
            throw new RuntimeException("时间类型转换异常：{}", e);
        }
        List<ContentsListBo> contentBoList = contentService.selectListByTplContent(dataMap);
        List<ContentSaveBo> dataList = new ArrayList<ContentSaveBo>();
        for (ContentsListBo contentBo : contentBoList) {
            ContentSaveBo contentSaveBo = contentService.selectContent(contentBo.getContentId());
            dataList.add(contentSaveBo);
        }
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 根据专题ID查找内容信息
     */
    @GetMapping(path = "/selectListBytopicId")
    public ResponseEntity selectListBytopicId(@RequestParam(value = "channelName", required = false) String channelName,
                                              @RequestParam(value = "topicId", required = false) String topicId) {
        //查询模型项
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("channelName", channelName);
        dataMap.put("topicId", topicId);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            if (startTime != null && !"".equals(startTime)) {
//                Date startTime1 = sdf.parse(startTime);
//                dataMap.put("startTime", startTime1.getTime() / 1000);
//            } else {
//                dataMap.put("needRegenerate", "0");
//            }
//        } catch (ParseException e) {
//            LOGGER.error("时间类转换异常：{}", e);
//            throw new RuntimeException("时间类型转换异常：{}", e);
//        }
        List<ContentsListBo> contentBoList = contentService.selectListBytopicId(dataMap);
//        List<ContentSaveBo> dataList = new ArrayList<ContentSaveBo>();
//        for (ContentsListBo contentBo : contentBoList) {
//            ContentSaveBo contentSaveBo = contentService.selectContent(contentBo.getContentId());
//            dataList.add(contentSaveBo);
//        }
//        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", contentBoList));
    }

    /**
     * 根据多个内容ID查找内容信息
     */
    @GetMapping(path = "/contentListByContentids")
    public ResponseEntity contentListByContentids(
            @RequestParam(value = "contentIds", required = false) String contentIds) {
        List<ContentSaveBo> dataList = new ArrayList<ContentSaveBo>();
        if (contentIds != null && !"".equals(contentIds)) {
            String[] contentIdstr = contentIds.split(",");
            for (int i = 0; i < contentIdstr.length; i++) {
                ContentSaveBo contentSaveBo = contentService.selectContent(contentIdstr[i]);
                dataList.add(contentSaveBo);
            }
        }
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 更新内容
     */
    @PutMapping(path = "/{contentId}")
    public ResponseEntity update(@PathVariable String contentId,
                                 @Valid @RequestBody ContentSaveBo contentSaveBo) {

        LOGGER.info("{}", contentSaveBo);
        //更新内容信息
        contentSaveBo = contentService.update(contentSaveBo);
        LOGGER.info("{}", contentSaveBo);
        redisTemplate.delete("CMS_SelectListcszxwFqt");
        return ResponseEntity.ok(Utils.kv("data", contentSaveBo));
    }

    /**
     * 删除内容
     */
    @DeleteMapping(path = "/{contentId}")
    public ResponseEntity delete(@PathVariable String contentId) {
        LOGGER.info("{}", contentId);
        //删除内容信息
        String rtn = contentService.delete(contentId);
        LOGGER.info("{}", rtn);
        redisTemplate.delete("CMS_SelectListcszxwFqt");
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 批量删除内容
     */
    @PostMapping(path = "/deletelist")
    public ResponseEntity deleteList(@RequestBody IdsBo idsBo) {
        LOGGER.info("{}", idsBo);
//        String[] contentIdStr = contentIds.split(",");
        //批量删除内容信息
        String rtn = contentService.deleteList(idsBo.getIds());
        LOGGER.info("{}", rtn);
        redisTemplate.delete("CMS_SelectListcszxwFqt");
        return ResponseEntity.ok(Utils.kv("data", idsBo));
    }

    /**
     * 批量退回内容
     */
    @PostMapping(path = "/thlist")
    public ResponseEntity thList(@RequestBody IdsBo idsBo) {
        LOGGER.info("{}", idsBo);
        //批量退回内容信息
        String rtn = contentService.updateStatusList(idsBo.getIds());
        List<ContentSaveBo> dataList = new ArrayList<ContentSaveBo>();
        String[] contentIdstr = idsBo.getIds();
        for (int i = 0; i < contentIdstr.length; i++) {
            ContentSaveBo contentSaveBo = contentService.selectContent(contentIdstr[i]);
            dataList.add(contentSaveBo);
        }
        redisTemplate.delete("CMS_SelectListcszxwFqt");
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 批量更新内容已生成静态页字段
     */
    @PostMapping(path = "/updatRegenerateList")
    public ResponseEntity updatRegenerateList(@RequestBody IdsBo idsBo) {
        LOGGER.info("{}", idsBo);
        //批量更新内容已生成静态页字段
        String rtn = contentService.updatRegenerateList(idsBo.getIds());
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", idsBo));
    }

    /**
     * 批量更新内容
     */
    @PutMapping(path = "/updateList")
    public ResponseEntity updateList(@RequestBody ContentUpdateListBo contentUpdateListBo) {

        LOGGER.info("{}", contentUpdateListBo);
        //更新内容信息
        contentUpdateListBo = contentService.updateList(contentUpdateListBo);
        LOGGER.info("{}", contentUpdateListBo);
        redisTemplate.delete("CMS_SelectListcszxwFqt");
        return ResponseEntity.ok(Utils.kv("data", contentUpdateListBo));
    }

    /**
     * 批量更新内容(专题)
     */
    @PutMapping(path = "/updatetopicList")
    public ResponseEntity updatetopicList(@RequestBody ContentTopicListBo topicListBo) {

        LOGGER.info("{}", topicListBo);
        //更新内容信息
        topicListBo = contentService.updatetopicList(topicListBo);
        LOGGER.info("{}", topicListBo);
        redisTemplate.delete("CMS_SelectListcszxwFqt");
        return ResponseEntity.ok(Utils.kv("data", topicListBo));
    }


    /**
     * 查询内容的上下篇
     */
    @GetMapping(path = "/selectContentudList")
    public ResponseEntity selectContentudList(@RequestParam(value = "channelId", required = false) String channelId,
                                              @RequestParam(value = "releaseDate", required = false) String
                                                      releaseDate) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("releaseDate", releaseDate);//发布时间
        dataMap.put("channelId", channelId);//栏目ID
        //查询内容列表
        List<ContentudBo> dataList = contentService.selectContentudList(dataMap);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 更新浏览量
     */
    @PutMapping(path = "/updateViewsDay/{contentId}")
    public ResponseEntity updateViewsDay(@PathVariable String contentId) {
        LOGGER.info("{}", contentId);
        //更新浏览量信息
        String rtn = contentService.updateViewsDay(contentId);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 更新浏览量
     */
    @PutMapping(path = "/updateViewsDayjf/{contentId}")
    public ResponseEntity updateViewsDayjf(@PathVariable String contentId, HttpServletRequest request) {
        LOGGER.info("{}", contentId);
        //更新浏览量信息
        String rtn = contentService.updateViewsDayjf(contentId,request);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }

    /**
     * 文章发布
     */
    @PutMapping(path = "/updateStatus2/{contentId}")
    public ResponseEntity updateStatus2(@PathVariable String contentId) {
        LOGGER.info("{}", contentId);
        //文章发布
        String rtn = contentService.updateStatus2(contentId);
        LOGGER.info("{}", rtn);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }


}
