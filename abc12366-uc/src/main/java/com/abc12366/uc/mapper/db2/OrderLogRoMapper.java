package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.OrderLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OrderLogMapper数据库操作接口类
 **/

public interface OrderLogRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    OrderLog selectByPrimaryKey(@Param("id") String id);

    List<OrderLog> selectList(OrderLog orderLog);
}