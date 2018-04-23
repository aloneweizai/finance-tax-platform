package com.abc12366.uc.service.order;

import com.abc12366.uc.model.order.Goods;
import com.abc12366.uc.model.order.bo.GoodsBO;
import com.abc12366.uc.model.order.bo.GoodsCheckBO;
import com.abc12366.uc.model.order.bo.GoodsLogBO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MY on 2017-05-15.
 */
@Service
public interface GoodsService {

    /**
     * 商品后台管理查询
     *
     * @param goods 商品对象
     * @return 商品列表信息
     */
    List<GoodsBO> selectList(Goods goods);

    /**
     * 商品单个查询
     *
     * @param id 商品ID
     * @return 商品对象
     */
    GoodsBO selectOne(String id);

    /**
     * 新增商品信息
     *
     * @param goodsBO 商品对象
     * @return 商品对象
     */
    GoodsBO add(GoodsBO goodsBO);

    /**
     * 修改商品
     *
     * @param goodsBO 商品对象
     * @return 商品对象
     */
    GoodsBO update(GoodsBO goodsBO);

    /**
     * 根据商品名称查询商品
     *
     * @param goods 商品对象
     * @return 商品列表对象
     */
    List<Goods> selectGoodsList(Goods goods);

    /**
     * 查询单个商品
     *
     * @param id 商品ID
     * @return 商品对象
     */
    GoodsBO selectGoods(String id);

    /**
     * 审核商品信息
     *
     * @param goodsCheckBO 审核对象
     */
    void checkGoods(GoodsCheckBO goodsCheckBO);

    /**
     * 删除商品
     *
     * @param id 商品ID
     */
    void deleteGoods(String id);

    List<GoodsBO> selectProductRepoList(GoodsBO goods);

    /**
     * 前台用户查询单个商品
     *
     * @param id 商品ID
     * @return 商品对象
     */
    GoodsBO selectUserGoods(String id);

    /**
     * 商品前台列表查询
     *
     * @param goods 商品对象
     * @return 商品对象列表
     */
    List<GoodsBO> selectGoodsBOList(Goods goods);

    /**
     * 商品操作日志
     *
     * @param id 商品ID
     * @return 商品日志列表
     */
    List<GoodsLogBO> selectGoodsLogList(String id);

    /**
     * 前台查询单个课程商品
     *
     * @param id 商品ID
     * @return 商品列表对象
     */
    List<GoodsBO> selectCurriculumGoods(String id);
}
