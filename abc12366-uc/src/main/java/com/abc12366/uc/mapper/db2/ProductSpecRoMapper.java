package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.ProductSpec;
import org.apache.ibatis.annotations.Param;

/**
 * ProductSpecMapper数据库操作接口类
 **/

public interface ProductSpecRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    ProductSpec selectByPrimaryKey(@Param("id") String id);


}