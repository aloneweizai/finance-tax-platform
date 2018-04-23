package com.abc12366.uc.web.invoice;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.invoice.InvoiceDetail;
import com.abc12366.uc.model.invoice.InvoiceRepo;
import com.abc12366.uc.model.invoice.bo.InvoiceDetailBO;
import com.abc12366.uc.model.invoice.bo.InvoiceRepoBO;
import com.abc12366.uc.service.invoice.InvoiceRepoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 发票领用申请控制类
 *
 * @author lizhongwei
 * @create 2017-08-09
 * @since 2.0.0
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class InvoiceRepoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceRepoController.class);

    @Autowired
    private InvoiceRepoService invoiceRepoService;

    /**
     * 发票仓库列表
     *
     * @param pageNum
     * @param pageSize
     * @param invoiceCode
     * @return
     */
    @GetMapping(path = "/invoice/repo")
    public ResponseEntity selectInvoiceRepoList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                                @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                                @RequestParam(value = "status", required = false) String status,
                                                @RequestParam(value = "noStart", required = false) String noStart,
                                                @RequestParam(value = "noEnd", required = false) String noEnd,
                                                @RequestParam(value = "invoiceNoStart", required = false) String invoiceNoStart,
                                                @RequestParam(value = "invoiceNoEnd", required = false) String invoiceNoEnd,
                                                @RequestParam(value = "startTime", required = false) String startTime,
                                                @RequestParam(value = "endTime", required = false) String endTime,
                                                @RequestParam(value = "invoiceTypeCode", required = false) String invoiceTypeCode,
                                                @RequestParam(value = "invoiceCode", required = false) String invoiceCode) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        InvoiceRepoBO invoiceRepo = new InvoiceRepoBO();
        invoiceRepo.setStatus(status);
        invoiceRepo.setInvoiceCode(invoiceCode);
        invoiceRepo.setInvoiceTypeCode(invoiceTypeCode);
        invoiceRepo.setNoStart(noStart);
        invoiceRepo.setNoEnd(noEnd);
        invoiceRepo.setInvoiceNoStart(invoiceNoStart);
        invoiceRepo.setInvoiceNoEnd(invoiceNoEnd);

        if (startTime != null && !"".equals(startTime)) {
            invoiceRepo.setStartTime(DateUtils.strToDate(startTime));
        }
        if (endTime != null && !"".equals(endTime)) {
            invoiceRepo.setEndTime(DateUtils.strToDate(endTime));
        }
        //获取发票类型编码长度，用户排序
        if(StringUtils.isEmpty(invoiceTypeCode)){
            invoiceRepo.setCodeLength(5);
        }else {
            invoiceRepo.setCodeLength(invoiceTypeCode.length());
        }

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<InvoiceRepoBO> invoiceList = invoiceRepoService.selectList(invoiceRepo);
        LOGGER.info("{}", invoiceList);
        return (invoiceList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) invoiceList, "total", ((Page) invoiceList).getTotal()));
    }


    /**
     * 根据发票种类代码，获取发票仓库ID
     *
     * @return
     */
    @GetMapping(path = "/invoice/repo/code/{code}")
    public ResponseEntity selectRepoId(@PathVariable("code") String code) {
        InvoiceRepo repoId = invoiceRepoService.selectRepoId(code);
        LOGGER.info("{}", repoId);
        return ResponseEntity.ok(Utils.kv("data", repoId));
    }

    /**
     * 发票仓库详情查看
     *
     * @return
     */
    @GetMapping(path = "/invoice/repo/{id}")
    public ResponseEntity selectInvoiceRepo(@PathVariable("id") String id) {
        InvoiceRepo invoiceRepo = new InvoiceRepo();
        invoiceRepo.setId(id);
        InvoiceRepoBO bo = invoiceRepoService.selectInvoiceRepo(invoiceRepo);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 发票仓库-验证唯一性，根据no和code
     *
     * @return
     */
    @GetMapping(path = "/invoice/repo/validate")
    public ResponseEntity validateInvoice(@RequestParam(value = "id", required = false) String id,
                                          @RequestParam(value = "invoiceCode", required = false) String invoiceCode,
                                          @RequestParam(value = "invoiceNoStart", required = false) String invoiceNoStart,
                                          @RequestParam(value = "invoiceNoEnd", required = false) String invoiceNoEnd) {
        InvoiceRepo invoiceRepo = new InvoiceRepo();
        invoiceRepo.setId(id);
        invoiceRepo.setInvoiceCode(invoiceCode);
        invoiceRepo.setInvoiceNoStart(invoiceNoStart);
        invoiceRepo.setInvoiceNoEnd(invoiceNoEnd);
        boolean isVal = invoiceRepoService.validateInvoice(invoiceRepo);
        LOGGER.info("{}", isVal);
        return ResponseEntity.ok(Utils.kv("data", isVal));
    }

    /**
     * 发票仓库新增
     *
     * @return
     */
    @PostMapping(path = "/invoice/repo")
    public ResponseEntity addInvoiceRepo(@Valid @RequestBody InvoiceRepoBO invoiceRepoBO) {
        LOGGER.info("{}", invoiceRepoBO);
        InvoiceRepoBO bo = invoiceRepoService.add(invoiceRepoBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 发票仓库修改
     *
     * @return
     */
    @PutMapping(path = "/invoice/repo/{id}")
    public ResponseEntity updateInvoiceRepo(@Valid @RequestBody InvoiceRepoBO invoiceRepoBO,@PathVariable("id") String id) {
        LOGGER.info("{}", invoiceRepoBO);
        invoiceRepoBO.setId(id);
        InvoiceRepoBO bo = invoiceRepoService.update(invoiceRepoBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 发票仓库删除
     *
     * @return
     */
    @DeleteMapping(path = "/invoice/repo/{id}")
    public ResponseEntity deleteInvoiceRepo(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        invoiceRepoService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 发票仓库详情列表
     *
     * @param pageNum
     * @param pageSize
     * @param invoiceNo
     * @param invoiceCode
     * @param status
     * @return
     */
    @GetMapping(path = "/invoice/detail")
    public ResponseEntity selectInvoiceDetailList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                                  @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                                  @RequestParam(value = "invoiceNo", required = false) String invoiceNo,
                                                  @RequestParam(value = "invoiceCode", required = false) String invoiceCode,
                                                  @RequestParam(value = "status", required = false) String status,
                                                  @RequestParam(value = "invoiceRepoId", required = true) String invoiceRepoId) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setInvoiceNo(invoiceNo);
        invoiceDetail.setInvoiceCode(invoiceCode);
        invoiceDetail.setStatus(status);
        invoiceDetail.setInvoiceRepoId(invoiceRepoId);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<InvoiceDetail> invoiceList = invoiceRepoService.selectInvoiceDetailList(invoiceDetail);
        PageInfo<InvoiceDetail> pageInfo = new PageInfo<>(invoiceList);
        LOGGER.info("{}", invoiceList);
        return (invoiceList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 根据发票号码，获取发票仓库详情信息
     *
     * @param invoiceNo
     * @return
     */
    @GetMapping(path = "/invoice/detail/no")
    public ResponseEntity selectInvoiceDetailByInvoice(@RequestParam(value = "invoiceNo", required = true) String invoiceNo) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setInvoiceNo(invoiceNo);
        InvoiceDetail invoiceDetail1 = invoiceRepoService.selectInvoiceDetailByInvoice(invoiceDetail);
        LOGGER.info("{}", invoiceDetail1);
        return ResponseEntity.ok(Utils.kv("data",invoiceDetail1));
    }

    /**
     * 根据发票号码，获取发票仓库详情信息列表
     *
     * @param invoiceNo
     * @return
     */
    @GetMapping(path = "/invoice/detail/invoice")
    public ResponseEntity selectInvoiceDetailListByInvoice(@RequestParam(value = "page", defaultValue = Constant .pageNum) int pageNum,
                                                           @RequestParam(value = "size", defaultValue = Constant .pageSize) int pageSize,
                                                           @RequestParam(value = "invoiceNo", required = false) String invoiceNo) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setInvoiceNo(invoiceNo);
        invoiceDetail.setStatus("0");
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<InvoiceDetail> invoiceList = invoiceRepoService.selectInvoiceDetailListByInvoice(invoiceDetail);
        PageInfo<InvoiceDetail> pageInfo = new PageInfo<>(invoiceList);
        LOGGER.info("{}", invoiceList);
        return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
    }

    /**
     * 发票详情信息删除
     *
     * @return
     */
    @DeleteMapping(path = "/invoice/detail/del/{id}")
    public ResponseEntity deleteInvoiceDetail(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        invoiceRepoService.deleteInvoiceDetail(id);
        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 获取该管理员领用表中的一张发票
     *
     * @return
     */
    @GetMapping(path = "/invoice/detail/select")
    public ResponseEntity selectInvoiceDetail(@RequestParam(value = "invoiceTypeCode", required = false) String invoiceTypeCode) {
        InvoiceDetailBO invoiceDetailBO = invoiceRepoService.selectInvoiceDistributeByInv(invoiceTypeCode);
        LOGGER.info("{}", invoiceDetailBO);
        return ResponseEntity.ok(Utils.kv("data", invoiceDetailBO));
    }

    /**
     * 发票详情信息作废
     *
     * @return
     */
    @PutMapping(path = "/invoice/detail/invalid/{id}")
    public ResponseEntity invalidInvoiceDetail(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        invoiceRepoService.invalidInvoiceDetail(id);
        return ResponseEntity.ok(Utils.kv());
    }
}
