package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.order.Goods;
import com.abc12366.uc.model.order.GoodsCategory;
import com.abc12366.uc.model.order.bo.GoodsBO;
import com.abc12366.uc.model.order.bo.GoodsCategoryBO;
import com.abc12366.uc.model.order.bo.GoodsCheckBO;
import com.abc12366.uc.model.order.bo.GoodsLogBO;
import com.abc12366.uc.service.order.GoodsCategoryService;
import com.abc12366.uc.service.order.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lizhongwei
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/goods", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class GoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 商品后台管理查询
     *
     * @param pageNum       页数
     * @param pageSize      条数
     * @param name          商品名称
     * @param status        状态
     * @param categoryId    分类ID
     * @param recommendType 推荐类型
     * @param goodsType     商品类型
     * @return 商品列表信息
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "status", required = false) Boolean status,
                                     @RequestParam(value = "categoryId", required = false) String categoryId,
                                     @RequestParam(value = "recommendType", required = false) String recommendType,
                                     @RequestParam(value = "goodsType", required = false) String goodsType
    ) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        Goods goods = new Goods();
        goods.setStatus(status);
        goods.setName(name);
        goods.setCategoryId(categoryId);
        goods.setRecommendType(recommendType);
        goods.setGoodsType(goodsType);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<GoodsBO> goodsList = goodsService.selectList(goods);
        LOGGER.info("{}", goodsList);
        return (goodsList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) goodsList, "total", ((Page) goodsList).getTotal()));
    }

    /**
     * 根据商品名称查询商品
     *
     * @param name 商品名称
     * @return 商品信息
     */
    @GetMapping(path = "/name")
    public ResponseEntity selectListByName(@RequestParam(value = "name", required = false) String name) {
        LOGGER.info("{}", name);
        Goods goods = new Goods();
        goods.setName(name);
        List<Goods> goodsList = goodsService.selectGoodsList(goods);
        LOGGER.info("{}", goodsList);
        return ResponseEntity.ok(Utils.kv("dataList", goodsList));
    }


    /**
     * 商品前台列表查询
     *
     * @param pageNum       页数
     * @param pageSize      条数
     * @param name          商品名称
     * @param tradeMethod   交易方式
     * @param categoryId    分类ID
     * @param goodsType     商品类型
     * @param recommendType 推荐类型
     * @return 商品列表信息
     */
    @GetMapping(path = "/user")
    public ResponseEntity selectGoodsList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                          @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "tradeMethod", required = false) String tradeMethod,
                                          @RequestParam(value = "categoryId", required = false) String categoryId,
                                          @RequestParam(value = "goodsType", required = false) String goodsType,
                                          @RequestParam(value = "recommendType", required = false) String
                                                  recommendType) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        Goods goods = new Goods();
        goods.setName(name);
        goods.setGoodsType(goodsType);
        goods.setCategoryId(categoryId);
        goods.setRecommendType(recommendType);
        goods.setTradeMethod(tradeMethod);
        //已发布状态
        goods.setStatus(true);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<GoodsBO> goodsList = goodsService.selectGoodsBOList(goods);
        LOGGER.info("{}", goodsList);
        return (goodsList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) goodsList, "total", ((Page) goodsList).getTotal()));
    }

    /**
     * 新增商品
     *
     * @param goodsBO 商品对象
     * @return 商品信息
     */
    @PostMapping
    public ResponseEntity addGoods(@Valid @RequestBody GoodsBO goodsBO) {
        LOGGER.info("{}", goodsBO);
        GoodsBO bo = goodsService.add(goodsBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 查询单个商品
     *
     * @param id 商品ID
     * @return 商品信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectGoods(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        GoodsBO goodsBO = goodsService.selectGoods(id);
        LOGGER.info("{}", goodsBO);
        return ResponseEntity.ok(Utils.kv("data", goodsBO));
    }

    /**
     * 前台用户查询单个商品
     *
     * @param id 商品ID
     * @return 商品信息
     */
    @GetMapping(path = "/user/{id}")
    public ResponseEntity selectUserGoods(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        GoodsBO goodsBO = goodsService.selectUserGoods(id);
        LOGGER.info("{}", goodsBO);
        return ResponseEntity.ok(Utils.kv("data", goodsBO));
    }

    /**
     * 前台查询单个课程商品
     *
     * @param id 商品ID
     * @return 商品信息
     */
    @GetMapping(path = "/curriculum/{id}")
    public ResponseEntity selectCurriculumGoods(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        List<GoodsBO> goodsBOs = goodsService.selectCurriculumGoods(id);
        LOGGER.info("{}", goodsBOs);
        return ResponseEntity.ok(Utils.kv("data", goodsBOs.get(0)));
    }


    /**
     * 修改商品信息
     *
     * @param goodsBO 商品信息
     * @param id      商品ID
     * @return 商品信息
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity updateGoods(@Valid @RequestBody GoodsBO goodsBO, @PathVariable("id") String id) {
        LOGGER.info("{}", goodsBO);
        goodsBO.setId(id);
        GoodsBO bo = goodsService.update(goodsBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 删除商品信息
     *
     * @param id 商品ID
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteGoods(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        goodsService.deleteGoods(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 审核商品信息
     *
     * @param goodsCheckBO 商品审核信息
     * @return
     */
    @PutMapping(path = "/check")
    public ResponseEntity checkGoods(@Valid @RequestBody GoodsCheckBO goodsCheckBO) {
        LOGGER.info("{}", goodsCheckBO);
        goodsService.checkGoods(goodsCheckBO);
        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 产品分类列表
     *
     * @param category 类别名称
     * @return 类别信息
     */
    @GetMapping(path = "/category")
    public ResponseEntity selectGategoryList(
            @RequestParam(value = "category", required = false) String category) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setCategory(category);
        GoodsCategoryBO categoryBO = goodsCategoryService.selectList(goodsCategory);
        LOGGER.info("{}", categoryBO);
        return (categoryBO == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("data", categoryBO));
    }


    /**
     * 查询单个产品分类
     *
     * @param id 类别ID
     * @return 类别信息
     */
    @GetMapping(path = "/category/{id}")
    public ResponseEntity selectGategory(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        GoodsCategory goodsCategory = goodsCategoryService.selectGategory(id);
        LOGGER.info("{}", goodsCategory);
        return ResponseEntity.ok(Utils.kv("data", goodsCategory));
    }

    /**
     * 新增产品分类
     *
     * @param goodsCategoryBO 商品类别对象
     * @return 类别信息
     */
    @PostMapping(path = "/category")
    public ResponseEntity addGategory(@Valid @RequestBody GoodsCategoryBO goodsCategoryBO) {
        LOGGER.info("{}", goodsCategoryBO);
        GoodsCategory bo = goodsCategoryService.add(goodsCategoryBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 查找商品分类详情
     *
     * @param goodsCategoryBO 商品类别对象
     * @param id              商品类别ID
     * @return 类别信息
     */
    @PutMapping(path = "/category/{id}")
    public ResponseEntity updateGategory(@Valid @RequestBody GoodsCategoryBO goodsCategoryBO, @PathVariable("id")
    String id) {
        LOGGER.info("{}", goodsCategoryBO);
        goodsCategoryBO.setId(id);
        GoodsCategoryBO bo = goodsCategoryService.update(goodsCategoryBO);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 删除商品分类
     *
     * @param id 商品类别ID
     * @return
     */
    @DeleteMapping(path = "/category/{id}")
    public ResponseEntity deleteGategory(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        goodsCategoryService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 商品操作日志
     *
     * @param pageNum  页数
     * @param pageSize 条数
     * @param id       日志 ID
     * @return 日志列表
     */
    @GetMapping(path = "/log")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "id", required = true) String id) {
        LOGGER.info("{}:{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<GoodsLogBO> goodsList = goodsService.selectGoodsLogList(id);
        LOGGER.info("{}", goodsList);
        return (goodsList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4104), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) goodsList, "total", ((Page) goodsList).getTotal()));
    }
}
