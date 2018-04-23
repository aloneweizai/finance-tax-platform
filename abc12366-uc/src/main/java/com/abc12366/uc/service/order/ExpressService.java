package com.abc12366.uc.service.order;

import com.abc12366.uc.model.invoice.Express;
import com.abc12366.uc.model.invoice.bo.ExpressBO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpressService {

    List<ExpressBO> selectList(ExpressBO expressBO);

    ExpressBO send(Express express);

    ExpressBO update(ExpressBO expressBO);

    Express selectExpress(String id);

    void delete(String id);

    ExpressBO importExpress(ExpressBO expressBO);
}
