package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.invoice.Express;
import com.abc12366.uc.model.invoice.bo.ExpressBO;
import com.abc12366.uc.model.order.ExpressComp;
import com.abc12366.uc.model.order.bo.ExpressCompBO;
import com.abc12366.uc.service.order.ExpressCompService;
import com.abc12366.uc.service.order.ExpressService;
import com.abc12366.uc.util.ExcelUtil;
import com.abc12366.uc.util.FileUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author lizhongwei
 *         物流公司控制类
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/express", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ExpressCompController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressCompController.class);

    @Autowired
    private ExpressService expressService;

    @Autowired
    private ExpressCompService expressCompService;

    @GetMapping
    public ResponseEntity selectExpressList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                            @RequestParam(value = "size", defaultValue = Constant.pageSize) int
                                                    pageSize,
                                            @RequestParam(value = "username", required = false) String username,
                                            @RequestParam(value = "phone", required = false) String phone,
                                            @RequestParam(value = "expressId", required = false) String expressId,
                                            @RequestParam(value = "startTime", defaultValue = "") String startTime,
                                            @RequestParam(value = "endTime", defaultValue = "") String endTime) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        ExpressBO expressBO = new ExpressBO();

        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        expressBO.setUser(user);
        expressBO.setExpressNo(expressId);
        if (startTime == null || "".equals(startTime)) {
            expressBO.setStartTime(DateUtils.getToday(new Date()));
        }
        if (endTime == null || "".equals(endTime)) {
            expressBO.setEndTime(DateUtils.getToday(new Date()));
        }
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<ExpressBO> expressList = expressService.selectList(expressBO);
        LOGGER.info("{}", expressList);
        return (expressList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) expressList, "total", ((Page) expressList).getTotal()));
    }

    /**
     * 新增物流公司
     *
     * @param express
     * @return
     */
    @PostMapping
    public ResponseEntity send(@Valid @RequestBody Express express) {
        LOGGER.info("{}", express);
        ExpressBO bo = expressService.send(express);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /*
     *采用spring提供的上传文件的方法
     */
    @PostMapping(path = "/excel")
    public ResponseEntity springUpload(HttpServletRequest request, String fileName) throws IllegalStateException,
            IOException {
        request.setCharacterEncoding("UTF-8");
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        multipartResolver.setDefaultEncoding("UTF-8");
        //检查form中是否有enctype="multipart/form-data"
        String path = null;
        List<ExpressBO> expressBOList = null;
        List<ExpressBO> orderNumList = null;
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    //filePath = new String(file.getOriginalFilename().getBytes(), "UTF-8");
                    path = FileUtils.getDefaultFolder() + "//" + fileName;
                    //上传
                    file.transferTo(new File(path));
                }
            }
            String keyValue = "订单号:invoiceOrderNo,运单号:expressNo";

            try {
                expressBOList = ExcelUtil.readXls(FileUtils.getDefaultFolder() + "//" + fileName, ExcelUtil.getMap
                        (keyValue), "com.abc12366.uc.model.invoice.Express");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (expressBOList != null) {
                ExpressBO bo = null;
                System.out.println(expressBOList.size());
                for (ExpressBO expressBO : expressBOList) {
                    bo = new ExpressBO();
                    System.out.println("订单号:" + expressBO.getInvoiceOrderNo() + "  运单号:" + expressBO.getExpressNo());
                    bo = expressService.importExpress(expressBO);
                    if (bo == null) {
                        orderNumList.add(expressBO);
                    }
                }
            }

        }
        return ResponseEntity.ok(Utils.kv("orderNumList", orderNumList, "num", expressBOList.size()));
    }


    /**
     * 获取物流公司列表
     *
     * @param pageNum
     * @param pageSize
     * @param compName
     * @return
     */
    @GetMapping(path = "/comp")
    public ResponseEntity selectCompList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                         @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                         @RequestParam(value = "compName", required = false) String compName) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        ExpressComp expressComp = new ExpressComp();
        expressComp.setCompName(compName);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<ExpressComp> compList = expressCompService.selectList(expressComp);
        LOGGER.info("{}", compList);
        return (compList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) compList, "total", ((Page) compList).getTotal()));
    }

    /**
     * 新增物流公司
     *
     * @param expressCompBO
     * @return
     */
    @PostMapping(path = "/comp")
    public ResponseEntity addComp(@Valid @RequestBody ExpressCompBO expressCompBO) {
        LOGGER.info("{}", expressCompBO);
        ExpressCompBO bo = expressCompService.add(expressCompBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 查询单个物流公司
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/comp/{id}")
    public ResponseEntity selectExpressComp(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        ExpressComp expressComp = expressCompService.selectExpressComp(id);
        LOGGER.info("{}", expressComp);
        return ResponseEntity.ok(Utils.kv("data", expressComp));
    }

    /**
     * 修改物流公司信息
     *
     * @param expressCompBO
     * @param id
     * @return
     */
    @PutMapping(path = "/comp/{id}")
    public ResponseEntity updateExpressComp(@Valid @RequestBody ExpressCompBO expressCompBO, @PathVariable("id")
    String id) {
        LOGGER.info("{}", expressCompBO);
        expressCompBO.setId(id);
        ExpressCompBO bo = expressCompService.update(expressCompBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", expressCompBO));
    }

    /**
     * 删除物流公司信息
     *
     * @return
     */
    @DeleteMapping(path = "/comp/{id}")
    public ResponseEntity deleteExpressComp(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        expressCompService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }


}
