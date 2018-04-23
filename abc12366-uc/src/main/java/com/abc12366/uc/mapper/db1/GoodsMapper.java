package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * GoodsMapper数据库操作接口类
 **/

public interface GoodsMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insert(Goods record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int update(Goods record);


}