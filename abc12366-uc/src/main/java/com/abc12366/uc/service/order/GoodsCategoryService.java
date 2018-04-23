package com.abc12366.uc.service.order;

import com.abc12366.uc.model.order.GoodsCategory;
import com.abc12366.uc.model.order.bo.GoodsCategoryBO;
import org.springframework.stereotype.Service;

/**
 * Created by MY on 2017-05-15.
 */
@Service
public interface GoodsCategoryService {

    GoodsCategoryBO selectList(GoodsCategory goodsCategory);

    GoodsCategory add(GoodsCategoryBO goodsCategoryBO);

    GoodsCategoryBO update(GoodsCategoryBO goodsCategoryBO);

    GoodsCategory selectGategory(String id);

    void delete(String id);
}
