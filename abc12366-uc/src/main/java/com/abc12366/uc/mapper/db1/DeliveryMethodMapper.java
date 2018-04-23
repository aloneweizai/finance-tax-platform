package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.DeliveryMethod;
import org.apache.ibatis.annotations.Param;

/**
 * DeliveryMethodMapper数据库操作接口类
 **/

public interface DeliveryMethodMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(DeliveryMethod record);


    /**
     * 修改（根据主键ID修改）
     **/
    int update(DeliveryMethod record);

}