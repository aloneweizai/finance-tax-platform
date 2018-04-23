package com.abc12366.uc.service.order;

import com.abc12366.uc.model.order.UvipPrice;
import com.abc12366.uc.model.order.bo.UvipPriceBO;

import java.util.List;

/**
 * Created by MY on 2017-07-04.
 */
public interface UvipPriceService {

    List<UvipPriceBO> selectList(UvipPrice uvipPrice);

}
