package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.admin.bo.OperateMessageBO;
import com.abc12366.uc.model.admin.bo.OperateMessageUpdateBO;
import com.abc12366.uc.model.admin.bo.YyxxLogListBO;
import com.abc12366.uc.service.admin.OperateMessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-18
 * Time: 10:49
 */
@RestController
@RequestMapping(path = "/operate/message", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class OperateMessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperateMessageController.class);

    @Autowired
    private OperateMessageService operateMessageService;

    /**
     * 新增运营消息任务接口
     *
     * @param operateMessageBO 运营消息任务参数体
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity insert(@RequestBody @Valid OperateMessageBO operateMessageBO) {
        LOGGER.info("新建运营任务消息：{}", operateMessageBO);
        OperateMessageBO messageBO = operateMessageService.insert(operateMessageBO);
        LOGGER.info("新建运营任务消息返回：{}", messageBO);
        return ResponseEntity.ok(Utils.kv("data", messageBO));
    }

    /**
     * 查询运营消息任务列表
     *
     * @param page 页码
     * @param size 每页数据量
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(required = false) String status,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String createTime,
                                    @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        LOGGER.info("查询运营消息列表：{}:{}:{}:{}:{}", status,name,createTime,page, size);
        List<OperateMessageBO> operateMessageBOList = operateMessageService.selectList(status, name,createTime,page, size);
        return (operateMessageBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) operateMessageBOList, "total", ((Page) operateMessageBOList).getTotal()));
    }

    /**
     * 修改运营消息任务接口
     *
     * @param operateMessageBO 入参
     * @return ResponseEntity
     */
    @PutMapping
    public ResponseEntity update(@RequestBody @Valid OperateMessageUpdateBO operateMessageBO) {
        LOGGER.info("更新运营消息任务：{}", operateMessageBO);
        OperateMessageBO messageBO = operateMessageService.update(operateMessageBO);
        return ResponseEntity.ok(Utils.kv("data", messageBO));
    }

    /**
     * 查询运营消息任务详情
     * @param id 页码
     * @return ResponseEntity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable String id) {
        LOGGER.info("查询运营消息详情：{}", id);
        OperateMessageBO operateMessageBO = operateMessageService.selectOne(id);
        return ResponseEntity.ok(Utils.kv("data",operateMessageBO));
    }

    /**
     * 删除运营消息任务
     * @param id 页码
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        LOGGER.info("删除运营消息：{}", id);
        operateMessageService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 复用运营消息任务
     * @param id 页码
     * @return ResponseEntity
     */
    @PostMapping(path = "/reuse/{id}")
    public ResponseEntity reuse(@PathVariable String id) {
        LOGGER.info("复用运营消息：{}", id);
        OperateMessageBO operateMessageBO = operateMessageService.reuse(id);
        return ResponseEntity.ok(Utils.kv("data",operateMessageBO));
    }

    /**
     * 运营消息发送情况查询接口
     * @param userId 用户id
     * @param nickName 用户昵称
     * @param messageId 运营消息id
     * @param page 页码
     * @param size 每页数据量
     * @return ResponseEntity
     */
    @GetMapping(path = "/log")
    public ResponseEntity operateMessageLog(@RequestParam(required = false) String userId,
                                            @RequestParam(required = false) String nickName,
                                            @RequestParam(required = false) String messageId,
                                            @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                            @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        LOGGER.info("运营消息日志，{}:{}:{}:{}:{}",userId,nickName,page,size,messageId);
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<YyxxLogListBO> listBOList = operateMessageService.operateMessageLog(userId,nickName,messageId);
        return (listBOList == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) listBOList, "total", ((Page) listBOList).getTotal()));
    }

    @GetMapping(path = "/shorturl/{surl}")
    public String shortUrl(@PathVariable String surl){
        return "http://uc.abc12366.com/login";
    }
}
