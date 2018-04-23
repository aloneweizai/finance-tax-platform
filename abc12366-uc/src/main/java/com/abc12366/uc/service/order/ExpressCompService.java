package com.abc12366.uc.service.order;

import com.abc12366.uc.model.order.ExpressComp;
import com.abc12366.uc.model.order.bo.ExpressCompBO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物流公司接口类
 */
@Service
public interface ExpressCompService {

    List<ExpressComp> selectList(ExpressComp expressComp);

    ExpressCompBO add(ExpressCompBO expressCompBO);

    ExpressCompBO update(ExpressCompBO expressCompBO);

    ExpressComp selectExpressComp(String id);

    void delete(String id);
}
