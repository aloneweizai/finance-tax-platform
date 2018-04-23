package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.GoodsCategory;
import com.abc12366.uc.model.order.bo.GoodsCategoryBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GoodsCategoryMapper数据库操作接口类
 **/

public interface GoodsCategoryRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    GoodsCategory selectByPrimaryKey(@Param("id") String id);

    GoodsCategoryBO selectById(String id);

    List<GoodsCategoryBO> selectByParentId(String id);

    List<GoodsCategoryBO> selectList(GoodsCategory goodsCategory);

    GoodsCategoryBO selectByName(@Param("category") String category);

    GoodsCategoryBO selectParentCategory();

}