package com.abc12366.uc.web.wx;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.weixin.bo.message.FileContent;
import com.abc12366.uc.model.weixin.bo.message.News;
import com.abc12366.uc.model.weixin.bo.message.ReturnMsg;
import com.abc12366.uc.model.weixin.bo.message.WxNews;
import com.abc12366.uc.service.IWxMsgService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 微信消息管理
 *
 * @author zhushuai 2017-7-31
 */
@Controller
public class WxMsgController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(WxMsgController.class);

    @Autowired
    private IWxMsgService iWxMsgService;

    /**
     * 微信图片永久图片素材接口
     * @param fileContent  素材内容
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PostMapping("/wximage/add")
    public ResponseEntity wximageCreat(
            @Valid @RequestBody FileContent fileContent) {
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data",
                iWxMsgService.add_img(fileContent)));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 微信上传图文消息内的图片
     * @param fileContent 图片内容
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PostMapping("/wxnews/imgupload")
    public ResponseEntity imgupload(@Valid @RequestBody FileContent fileContent) {
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data",
                iWxMsgService.uploadWxImag(fileContent)));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 微信永久图文消息
     * @param news 图文
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PostMapping("/wxnews/add")
    public ResponseEntity wxnewsCreat(@Valid @RequestBody WxNews news) {
        LOGGER.info("{}", news);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data",
                iWxMsgService.add_news(news)));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 图文消息创建
     * @param news  图文
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PostMapping("/wxnews/db/creat")
    public ResponseEntity wxnewsCreat(@Valid @RequestBody News news) {
        LOGGER.info("{}", news);

        News v = iWxMsgService.insertNews(news);

        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 图文消息修改
     * @param id 图文id
     * @param news 图文
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PutMapping("/wxnews/db/{id}")
    public ResponseEntity wxnewsEdit(@PathVariable("id") String id,
                                     @Valid @RequestBody News news) {
        LOGGER.info("{},{}", id, news);

        news.setId(id);
        News v = iWxMsgService.updateNews(news);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 图文信息查看 
     * @param id 图文ID
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxnews/db/{id}")
    public ResponseEntity wxnewsInfo(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        News news = iWxMsgService.selectOne(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", news));

        LOGGER.info("{}", responseEntity);
        return responseEntity;

    }


    /**
     *  图文消息查看
     * @param news 图文
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxnews/db/get")
    public ResponseEntity getWxnews(News news) {
        List<News> selectNews = iWxMsgService.getWxnews(news);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList",
                selectNews));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 删除图文消息
     * @param id 图文
     * @return
     */
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/wxnews/db/{id}")
    public ResponseEntity wxmenuDel(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        iWxMsgService.deleteNews(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    // --------------自动回复消息设置----------
    /**
     * 自动回复消息创建
     * @param returnMsg  消息
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PostMapping("/wxremsg/db/creat")
    public ResponseEntity wxrenewsCreat(@Valid @RequestBody ReturnMsg returnMsg) {
        LOGGER.info("{}", returnMsg);

        ReturnMsg v = iWxMsgService.insertReNews(returnMsg);

        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 自动回复消息修改
     * @param id  消息ID
     * @param returnMsg 消息
     * @return
     */
    @SuppressWarnings("rawtypes")
    @PutMapping("/wxremsg/db/{id}")
    public ResponseEntity wxremsgEdit(@PathVariable("id") String id,
                                      @Valid @RequestBody ReturnMsg returnMsg) {
        LOGGER.info("{},{}", id, returnMsg);

        returnMsg.setId(id);
        ReturnMsg v = iWxMsgService.updateReMsg(returnMsg);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 自动回复消息查询 
     * @param setting 0：被关注 1：自动回复
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxremsg/db/get")
    public ResponseEntity wxremsgGet(@RequestParam("setting") String setting) {
        ReturnMsg v = null;
        if ("0".equals(setting) || "1".equals(setting)) {
            v = iWxMsgService.getReMsgOneBysetting(setting);
        }
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
    
    /**
     * 按照ID查询消息
     * @param id 主键
     * @return
     */
    @SuppressWarnings("rawtypes")
	@GetMapping("/wxremsg/db/get/{id}")
    public ResponseEntity wxremsgGetId(@PathVariable("id") String id) {
        ReturnMsg v  = iWxMsgService.selectOneWxremsg(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data", v));
        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }
    
    

    /**
     * 关键字消息列表 
     * @param returnMsg 消息
     * @param page 页数
     * @param size 大小
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxremsg/db/keyList")
    public ResponseEntity wxremsgKeyList(
            ReturnMsg returnMsg,
            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("{},{},{}", returnMsg, page, size);
        returnMsg.setSetting("2");
        List<ReturnMsg> dataList = iWxMsgService.selectkeyList(returnMsg, page,
                size);

        PageInfo<ReturnMsg> pageInfo = new PageInfo<ReturnMsg>(dataList);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("dataList",
                pageInfo.getList(), "total", pageInfo.getTotal()));

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 删除自动回复消息
     * @param id 消息ID
     * @return
     */
    @SuppressWarnings("rawtypes")
    @DeleteMapping("/wxremsg/db/{id}")
    public ResponseEntity wxremsgDel(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        iWxMsgService.deleteWxremsg(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv());

        LOGGER.info("{}", responseEntity);
        return responseEntity;
    }

    /**
     * 从数据库获取自动回复消息
     * @param id 消息id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @GetMapping("/wxremsg/db/{id}")
    public ResponseEntity wxmenudbInfo(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        ReturnMsg returnMsg = iWxMsgService.selectOneWxremsg(id);
        ResponseEntity responseEntity = ResponseEntity.ok(Utils.kv("data",
                returnMsg));

        LOGGER.info("{}", responseEntity);
        return responseEntity;

    }

}
