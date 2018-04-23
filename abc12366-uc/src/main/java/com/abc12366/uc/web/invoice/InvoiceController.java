package com.abc12366.uc.web.invoice;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.invoice.Invoice;
import com.abc12366.uc.model.invoice.InvoiceBack;
import com.abc12366.uc.model.invoice.bo.*;
import com.abc12366.uc.service.invoice.InvoiceService;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 发票控制类
 *
 * @author lizhongwei
 * @create 2017-05-16
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/invoice", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);


    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发票列表管理
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "consignee", required = false) String consignee,
                                     @RequestParam(value = "id", required = false) String id,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "invoiceNo", required = false) String invoiceNo,
                                     @RequestParam(value = "property", required = false) String property,
                                     @RequestParam(value = "waybillNum", required = false) String waybillNum,
                                     @RequestParam(value = "startTime", required = false) String startTime,
                                     @RequestParam(value = "endTime", required = false) String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        InvoiceBO invoice = new InvoiceBO();
        invoice.setConsignee(consignee);
        invoice.setId(id);
        invoice.setUsername(username);
        invoice.setInvoiceNo(invoiceNo);
        invoice.setProperty(property);
        invoice.setWaybillNum(waybillNum);
        if (startTime != null && !"".equals(startTime)) {
            invoice.setStartTime(DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            invoice.setEndTime(DateUtils.strToDate(endTime));
        }

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<InvoiceBO> invoiceList = invoiceService.selectList(invoice);
        LOGGER.info("{}", invoiceList);
        return (invoiceList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) invoiceList, "total", ((Page) invoiceList).getTotal()));
    }

    /**
     * 前台，历史发票列表管理
     */
    @GetMapping(path = "/history")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "userId", required = true) String userId,
                                     @RequestParam(value = "status", required = false) String status) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        InvoiceBO invoice = new InvoiceBO();
        invoice.setStatus(status);
        invoice.setUserId(userId);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<InvoiceBO> invoiceList = invoiceService.selectList(invoice);
        LOGGER.info("{}", invoiceList);
        return (invoiceList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) invoiceList, "total", ((Page) invoiceList).getTotal()));
    }
    
    /**
     * 前端-我的历史发票缓存查询
     * @param userId 用户ID
     * @return 发票列表
     */
    @GetMapping(path = "/historyForqt")
    public ResponseEntity selectListForqt(@RequestParam(value = "userId", required = true) String userId) {
    	if(redisTemplate.hasKey(userId+"_historyForqt")){
    		 List<InvoiceBO> invoiceList = JSONArray.parseArray(redisTemplate.opsForValue().get(userId+"_historyForqt"),InvoiceBO.class);
    		 LOGGER.info("从Redis获取数据:"+JSONArray.toJSONString(invoiceList));
    		 return (invoiceList == null) ?
                     new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                     ResponseEntity.ok(Utils.kv("dataList", invoiceList, "total", invoiceList.size())); 
    	}else{
    		InvoiceBO invoice = new InvoiceBO();
            invoice.setUserId(userId);
            PageHelper.startPage(0, 0, true).pageSizeZero(true).reasonable(true);
            List<InvoiceBO> invoiceList = invoiceService.selectList(invoice);
            LOGGER.info("{}", invoiceList);
            redisTemplate.opsForValue().set(userId+"_historyForqt", JSONArray.toJSONString(invoiceList),RedisConstant.DAY_1, TimeUnit.DAYS);
            return (invoiceList == null) ?
                    new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                    ResponseEntity.ok(Utils.kv("dataList", (Page) invoiceList, "total", ((Page) invoiceList).getTotal()));
    	} 
    }
    
    

    /**
     * 前台，发票详情查看
     */
    @GetMapping(path = "/user/{invoiceId}/{userId}")
    public ResponseEntity userInvoice(@PathVariable String invoiceId, @PathVariable String
            userId) {
        LOGGER.info("{}", invoiceId);
        Invoice invoice = new Invoice();
        invoice.setUserId(userId);
        invoice.setId(invoiceId);
        InvoiceBO bo = invoiceService.selectUserInvoice(invoice);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 发票详情查看
     *
     * @param invoiceId 发票ID
     * @return 发票对象
     */
    @GetMapping(path = "/{invoiceId}")
    public ResponseEntity selectInvoice(@PathVariable("invoiceId") String invoiceId) {
        LOGGER.info("{}", invoiceId);
        InvoiceBO bo = invoiceService.selectOne(invoiceId);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 索要发票
     *
     * @param userId 用户ID
     * @return 发票对象
     */
    @PostMapping(path = "/{userId}")
    public ResponseEntity addInvoice(@PathVariable String userId, @Valid @RequestBody InvoiceBO invoiceBO) {
        invoiceBO.setUserId(userId);
        InvoiceBO bo = invoiceService.addInvoice(invoiceBO);

        LOGGER.info("{}", bo);
        
        redisTemplate.delete(userId+"_historyForqt");
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 管理员开票、拒绝开票
     */
    @PostMapping(path = "/billing")
    public ResponseEntity billing(@Valid @RequestBody InvoiceCheckBO invoiceCheckBO,HttpServletRequest request) {
        LOGGER.info("{}", invoiceCheckBO);
        invoiceService.billing(invoiceCheckBO,request);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 运营管理系统-发票作废
     */
    @PutMapping(path = "/invalid")
    public ResponseEntity invalid(@Valid @RequestBody InvoiceInvalidBO invoiceInvalidBO,HttpServletRequest request) {
        LOGGER.info("{}", invoiceInvalidBO);
        invoiceService.invalid(invoiceInvalidBO,request);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 发票信息修改
     *
     * @param userId 用户ID
     */
    @PutMapping(path = "/{userId}/{id}")
    public ResponseEntity updateInvoice(@PathVariable String userId, @PathVariable String id,
                                        @Valid @RequestBody InvoiceBO invoiceBO) {
        invoiceBO.setId(id);
        invoiceBO.setUserId(userId);
        InvoiceBO bo = invoiceService.updateInvoice(invoiceBO);
        redisTemplate.delete(userId+"_historyForqt");
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 发票信息删除
     *
     * @param userId 用户ID
     * @param id ID
     */
    @DeleteMapping(path = "/{userId}/{id}")
    public ResponseEntity delete(@PathVariable String userId, @PathVariable String id) {
        InvoiceBO invoiceBO = new InvoiceBO();
        invoiceBO.setId(id);
        invoiceBO.setUserId(userId);
        int bo = invoiceService.deleteByIdAndUserId(invoiceBO);
        LOGGER.info("{}", bo);
        redisTemplate.delete(userId+"_historyForqt");
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 发票确认收货
     */
    @PostMapping(path = "/confirm/{userId}/{id}")
    public ResponseEntity confirm(@PathVariable String userId, @PathVariable String id) {
        LOGGER.info("{}{}", userId,id);
        Invoice invoiceBO = new Invoice();
        invoiceBO.setId(id);
        invoiceBO.setUserId(userId);
        invoiceBO.setStatus("5");
        invoiceService.confirmInvoice(invoiceBO);
        redisTemplate.delete(userId+"_historyForqt");
        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 发票导出打印机列表
     *
     * @return
     */
    @GetMapping(path = "/export/print")
    public ResponseEntity exportInvoicePrint() {
        InvoiceBO invoice = new InvoiceBO();
        invoice.setStatus("2");
        List<InvoiceExcel> invoiceList = invoiceService.selectInvoicePrintExcelList(invoice);
        LOGGER.info("{}", invoiceList);
        return (invoiceList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", invoiceList));
    }

    /**
     * 发票导入打印收的信息
     */
    @PostMapping(path = "/import/print")
    public ResponseEntity insertInvoicePrintExcelList(@Valid @RequestBody List<InvoiceExcel> invoiceList) {
        LOGGER.info("{}", invoiceList);
        invoiceService.insertInvoicePrintExcelList(invoiceList);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 发票导出寄送信息
     */
    @GetMapping(path = "/export/express")
    public ResponseEntity exportInvoice() {
        InvoiceBO invoice = new InvoiceBO();
        invoice.setStatus("7");
        List<InvoiceExpressExcel> invoiceList = invoiceService.selectInvoiceExpressExcelList(invoice);
        LOGGER.info("{}", invoiceList);
        return (invoiceList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", invoiceList));
    }

    /**
     * 发票导入寄送信息
     *
     */
    @PostMapping(path = "/import/express/{expressCompId}")
    public ResponseEntity insertInvoiceExpressExcelList(@Valid @RequestBody List<InvoiceExpressExcel> expressExcelList,
                                                        @PathVariable("expressCompId") String expressCompId,
                                                        HttpServletRequest request) {
        LOGGER.info("{}", expressExcelList);
        invoiceService.insertInvoiceExpressExcelList(expressExcelList,expressCompId,request);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 退票管理列表
     *
     */
    @GetMapping(path = "/back")
    public ResponseEntity selectBackList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                         @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                         @RequestParam(value = "expressNo", required = false) String expressNo,
                                         @RequestParam(value = "id", required = false) String id,
                                         @RequestParam(value = "invoiceNo", required = false) String invoiceNo,
                                         @RequestParam(value = "sendExpressNo", required = false) String
                                                     sendExpressNo) {
        InvoiceBackBO invoiceBackBO = new InvoiceBackBO();
        invoiceBackBO.setId(id);
        invoiceBackBO.setExpressNo(expressNo);
        InvoiceBO invoiceBO = new InvoiceBO();
        invoiceBO.setInvoiceNo(invoiceNo);
        invoiceBackBO.setInvoiceBO(invoiceBO);

        invoiceBackBO.setSendExpressNo(sendExpressNo);

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<InvoiceBackBO> invoiceList = invoiceService.selectBOList(invoiceBackBO);
        PageInfo<InvoiceBackBO> pageInfo = new PageInfo<>(invoiceList);
        LOGGER.info("{}", invoiceList);
        return (invoiceList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 退票详情查看
     *
     * @return
     */
    @GetMapping(path = "/back/{id}")
    public ResponseEntity selectBackOne(@PathVariable("id") String id) {

        InvoiceBackBO invoiceBackBO = invoiceService.selectBackOne(id);
        LOGGER.info("{}", invoiceBackBO);
        return ResponseEntity.ok(Utils.kv("data", invoiceBackBO));
    }

    /**
     * 发票退票
     *
     * @param expressId
     * @return
     */
    @PostMapping(path = "/back/{expressId}")
    public ResponseEntity refund(@PathVariable("expressId") String expressId, @Valid @RequestBody InvoiceBackBO
            invoiceBackBO) {
        invoiceBackBO.setExpressId(expressId);
        InvoiceBackBO bo = invoiceService.refund(invoiceBackBO);

        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 发票退票审核
     *
     * @param expressId
     * @return
     */
    @PostMapping(path = "/back/check/{expressId}/{id}")
    public ResponseEntity refundCheck(@PathVariable("expressId") String expressId, @PathVariable("id") String id,
                                      @Valid @RequestBody InvoiceBack invoiceBack) {
        invoiceBack.setId(id);
        invoiceBack.setExpressId(expressId);
        InvoiceBackBO bo = invoiceService.refundCheck(invoiceBack);

        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 电子发票核对
     * @return 未通过核对的电子发票
     */
    @PostMapping(path = "/dzfp/check")
    public ResponseEntity refundCheck() {
        LOGGER.info("refundCheck:{}");
        invoiceService.invoiceCheck();
        return ResponseEntity.ok(Utils.kv());
    }

}
