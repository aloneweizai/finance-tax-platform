package com.abc12366.uc.service.invoice;

import com.abc12366.uc.model.invoice.Invoice;
import com.abc12366.uc.model.invoice.InvoiceBack;
import com.abc12366.uc.model.invoice.bo.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by MY on 2017-05-15.
 */
@Service
public interface InvoiceService {

    /**
     * 查询发票订单列表
     * @param invoice
     * @return
     */
    List<InvoiceBO> selectList(InvoiceBO invoice);

    /**
     * 查询发票订单详情
     * @param id
     * @return
     */
    InvoiceBO selectOne(String id);

    /**
     * 更新发票订单信息
     * @param invoiceBO
     * @return
     */
    InvoiceBO updateInvoice(InvoiceBO invoiceBO);

    /**
     * 删除发票信息
     * @param invoiceBO
     * @return
     */
    int deleteByIdAndUserId(InvoiceBO invoiceBO);

    /**
     * 新增发票订单
     * @param invoiceBO
     * @return
     */
    InvoiceBO addInvoice(InvoiceBO invoiceBO);

    /**
     * 发票信息导出
     */
    List<InvoiceExcel> selectInvoicePrintExcelList(InvoiceBO invoice);

    /**
     * 发票退票
     * @param invoiceBackBO
     * @return
     */
    InvoiceBackBO refund(InvoiceBackBO invoiceBackBO);

    /**
     * 发票退票审核
     * @param invoiceBack
     * @return
     */
    InvoiceBackBO refundCheck(InvoiceBack invoiceBack);

    /**
     * 查询发票退票信息
     * @param invoiceBackBO
     * @return
     */
    List<InvoiceBackBO> selectBOList(InvoiceBackBO invoiceBackBO);

    /**
     * 查询发票退票详情
     * @param id
     * @return
     */
    InvoiceBackBO selectBackOne(String id);

    /**
     * 前台用户查询发票信息
     * @param invoice
     * @return
     */
    InvoiceBO selectUserInvoice(Invoice invoice);

    /**
     * 管理员开票、拒绝开票
     * @param invoiceCheckBO
     * @param request
     */
    void billing(InvoiceCheckBO invoiceCheckBO, HttpServletRequest request);

    /**
     * 发票导出寄送信息
     * @param invoice
     * @return
     */
    List<InvoiceExpressExcel> selectInvoiceExpressExcelList(InvoiceBO invoice);

    /**
     * 发票导入寄送信息
     * @param expressExcelList
     * @param expressCompId
     * @param request
     */
    void insertInvoiceExpressExcelList(List<InvoiceExpressExcel> expressExcelList, String expressCompId, HttpServletRequest request);

    /**
     * 发票导入打印收的信息
     * @param invoiceList
     */
    void insertInvoicePrintExcelList(List<InvoiceExcel> invoiceList);

    /**
     * 确认收货
     */
    void confirmInvoice(Invoice invoice);

    /**
     * 自动确认收货
     */
    void automaticReceiptInvoice();

    /**
     * 状态为[待审批]发票申请数
     *
     * @return Integer
     */
    Integer selectTodoListCount();

    /**
     * 电子发票开票核对
     * @return 未通过核对的电子发票
     */
    void invoiceCheck();

    /**
     * 发票作废
     */
    void invalid(InvoiceInvalidBO invoiceInvalidBO, HttpServletRequest request);
}
