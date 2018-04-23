package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.DeliveryMethod;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DeliveryMethodMapper数据库操作接口类
 **/

public interface DeliveryMethodRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    DeliveryMethod selectByPrimaryKey(@Param("id") String id);

    List<DeliveryMethod> selectList(DeliveryMethod deliveryMethod);

    DeliveryMethod selectByName(String name);
}