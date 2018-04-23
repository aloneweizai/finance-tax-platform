package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.OrderLog;
import org.apache.ibatis.annotations.Param;

/**
 * OrderLogMapper数据库操作接口类
 **/

public interface OrderLogMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(OrderLog record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int update(OrderLog record);


}