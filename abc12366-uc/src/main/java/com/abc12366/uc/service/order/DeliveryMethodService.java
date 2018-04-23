package com.abc12366.uc.service.order;

import com.abc12366.uc.model.order.DeliveryMethod;
import com.abc12366.uc.model.order.bo.DeliveryMethodBO;
import com.abc12366.uc.model.order.bo.DeliveryMethodUpdateBO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 配送方式接口
 */
@Service
public interface DeliveryMethodService {

    List<DeliveryMethod> selectList(DeliveryMethod deliveryMethod);

    DeliveryMethodBO add(DeliveryMethodBO deliveryMethodBO);

    DeliveryMethodBO update(DeliveryMethodBO deliveryMethodBO);

    DeliveryMethodBO selectDeliveryMethod(String id);

    void delete(String id);

    void enable(DeliveryMethodUpdateBO deliveryMethodUpdateBO);
}
