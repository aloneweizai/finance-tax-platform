package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.OrderProductSpec;
import org.apache.ibatis.annotations.Param;

/**
 * OrderProductspecMapper数据库操作接口类
 **/

public interface OrderProductspecRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    OrderProductSpec selectByPrimaryKey(@Param("id") String id);


}