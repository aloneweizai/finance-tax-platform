package com.abc12366.uc.service.order.impl;

import com.abc12366.uc.mapper.db2.UvipPriceRoMapper;
import com.abc12366.uc.model.order.UvipPrice;
import com.abc12366.uc.model.order.bo.UvipPriceBO;
import com.abc12366.uc.service.order.UvipPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @since 1.0.0
 */
@Service
public class UvipPriceServiceImpl implements UvipPriceService {

    @Autowired
    private UvipPriceRoMapper uvipPriceRoMapper;

    @Override
    public List<UvipPriceBO> selectList(UvipPrice uvipPrice) {
        return uvipPriceRoMapper.selectList(uvipPrice);
    }

}
