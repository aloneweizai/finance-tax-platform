package com.abc12366.uc.service.invoice;

import com.abc12366.uc.model.invoice.InvoiceDetail;
import com.abc12366.uc.model.invoice.InvoiceRepo;
import com.abc12366.uc.model.invoice.bo.InvoiceDetailBO;
import com.abc12366.uc.model.invoice.bo.InvoiceRepoBO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizhongwei
 * Created by MY on 2017-08-10.
 */
@Service
public interface InvoiceRepoService {

    List<InvoiceRepoBO> selectList(InvoiceRepoBO invoiceRepoBO);

    InvoiceRepoBO selectInvoiceRepo(InvoiceRepo invoiceRepo);

    void delete(String id);

    InvoiceRepoBO add(InvoiceRepoBO invoiceRepoBO);

    InvoiceRepoBO update(InvoiceRepoBO invoiceRepoBO);

    InvoiceRepo selectRepoId(String invoiceTypeCode);

    List<InvoiceDetail> selectInvoiceDetailList(InvoiceDetail invoiceDetail);

    List<InvoiceDetail> selectInvoiceDetailListByInvoice(InvoiceDetail invoiceDetail);

    void deleteInvoiceDetail(String id);

    InvoiceDetailBO selectInvoiceDistributeByInv(String invoiceDistribute);

    void invalidInvoiceDetail(String id);

    InvoiceRepoBO selectInvoiceRepoNum(String code);

    InvoiceDetail selectInvoiceDetailByInvoice(InvoiceDetail invoiceDetail);

    boolean validateInvoice(InvoiceRepo invoiceRepo);
}
