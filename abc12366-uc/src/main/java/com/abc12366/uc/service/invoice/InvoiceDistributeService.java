package com.abc12366.uc.service.invoice;

import com.abc12366.uc.model.invoice.InvoiceDistribute;
import com.abc12366.uc.model.invoice.bo.InvoiceDistributeBO;
import com.abc12366.uc.model.invoice.bo.InvoiceUseCheckBO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MY on 2017-08-09.
 */
@Service
public interface InvoiceDistributeService {

    List<InvoiceDistributeBO> selectList(InvoiceDistributeBO invoiceDistributeBO);

    InvoiceDistributeBO selectInvoiceDistribute(InvoiceDistribute invoiceDistribute);

    void delete(String id);

    InvoiceDistributeBO add(InvoiceDistributeBO invoiceDistributeBO);

    InvoiceDistributeBO update(InvoiceDistributeBO invoiceDistributeBO);

    void checkUseApplay(InvoiceUseCheckBO invoiceUseCheckBO);

    void distributeUseApply(InvoiceUseCheckBO invoiceUseCheckBO);
}
