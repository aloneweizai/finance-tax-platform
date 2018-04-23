package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.OrderBack;
import com.abc12366.uc.model.order.bo.OrderBackBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OrderBackMapper数据库操作接口类
 **/

public interface OrderBackRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    OrderBack selectByPrimaryKey(@Param("id") String id);

    List<OrderBackBO> selectOrderBackList(OrderBackBO orderBackBO);
}