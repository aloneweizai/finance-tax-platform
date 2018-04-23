package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.order.Goods;
import com.abc12366.uc.model.order.bo.GoodsBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GoodsMapper数据库操作接口类
 **/

public interface GoodsRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Goods selectByPrimaryKey(@Param("id") String id);

    List<GoodsBO> selectList(Goods goods);

    GoodsBO selectGoods(String id);

    List<Goods> selectGoodsList(Goods goods);

    List<GoodsBO> selectProductRepoList(GoodsBO goodsBO);

    GoodsBO selecGoodsByProductId(String id);

    GoodsBO selectUserGoods(String id);

    List<GoodsBO> selectGoodsBOList(Goods goods);

    List<GoodsBO> selectCurriculumGoods(String id);
}