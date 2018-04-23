package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.*;
import com.abc12366.uc.mapper.db2.GoodsLogRoMapper;
import com.abc12366.uc.mapper.db2.GoodsRoMapper;
import com.abc12366.uc.mapper.db2.OrderProductRoMapper;
import com.abc12366.uc.mapper.db2.ProductRoMapper;
import com.abc12366.uc.model.bo.DictBO;
import com.abc12366.uc.model.order.*;
import com.abc12366.uc.model.order.bo.GoodsBO;
import com.abc12366.uc.model.order.bo.GoodsCheckBO;
import com.abc12366.uc.model.order.bo.GoodsLogBO;
import com.abc12366.uc.model.order.bo.ProductBO;
import com.abc12366.uc.service.order.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @since 1.0.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsRoMapper goodsRoMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ProductRoMapper productRoMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepoMapper productRepoMapper;

    @Autowired
    private OrderProductRoMapper orderProductRoMapper;

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Autowired
    private UvipPriceMapper uvipPriceMapper;


    @Override
    public List<GoodsBO> selectList(Goods goods) {
        return goodsRoMapper.selectList(goods);
    }

    @Override
    public GoodsBO selectOne(String id) {
        return null;
    }

    @Autowired
    private GoodsLogRoMapper goodsLogRoMapper;

    @Autowired
    private GoodsLogMapper goodsLogMapper;

    @Transactional("db1TxManager")
    @Override
    public GoodsBO add(GoodsBO goodsBO) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsBO, goods);
        //新增商品信息
        String goodsId = Utils.uuid();
        goods.setId(goodsId);
        Date date = new Date();
        goods.setCreateTime(date);
        goods.setLastUpdate(date);
        int gInsert = goodsMapper.insert(goods);
        if (gInsert != 1) {
            LOGGER.info("新增产品失败：{}", goods);
            throw new ServiceException(4101);
        }
        GoodsBO bo = new GoodsBO();
        BeanUtils.copyProperties(goods, bo);
        //加入产品参数
        List<ProductBO> productList = goodsBO.getProductBOList();
        List<ProductBO> productBOs = new ArrayList<>();
        for (ProductBO pBO : productList) {
            ProductBO productBO = new ProductBO();
            Product product = new Product();
            BeanUtils.copyProperties(pBO, product);
            String productId = Utils.uuid();
            product.setId(productId);
            product.setGoodsId(goodsId);
            product.setCreateTime(date);
            product.setLastUpdate(date);
            product.setStock(0);
            //新增产品参数
            int pInsert = productMapper.insert(product);
            if (pInsert != 1) {
                LOGGER.info("新增产品参数失败：{}", product);
                throw new ServiceException(4101);
            }
            BeanUtils.copyProperties(product, productBO);
            //新增规格信息与产品对应关系
            List<DictBO> dictList = pBO.getDictList();
            for (DictBO dictBO : dictList) {
                ProductSpec productSpec = new ProductSpec();
                productSpec.setId(Utils.uuid());
                productSpec.setProductId(productId);
                productSpec.setSpecId(dictBO.getId());
                int sInsert = productSpecMapper.insert(productSpec);
                if (sInsert != 1) {
                    LOGGER.info("新增产品规格信息失败：{}", productSpec);
                    throw new ServiceException(4160);
                }
            }
            //新增产品会员价格
            List<UvipPrice> uvipPriceList = pBO.getUvipPriceList();
            List<UvipPrice> uvipList = new ArrayList<>();
            for (UvipPrice uvipPrice : uvipPriceList) {
                String uvipPriceId = Utils.uuid();
                uvipPrice.setId(uvipPriceId);
                uvipPrice.setProductId(productId);
                uvipPrice.setCreateTime(date);
                uvipPrice.setLastUpdate(date);
                int sInsert = uvipPriceMapper.insert(uvipPrice);
                if (sInsert != 1) {
                    LOGGER.info("新增产品单个规格会员价格失败：{}", uvipPrice);
                    throw new ServiceException(4161);
                }
                uvipList.add(uvipPrice);
            }
            productBO.setUvipPriceList(uvipList);
            productBOs.add(productBO);
        }
        bo.setProductBOList(productBOs);
        insertGoodsLog(bo.getId(), goodsBO.toString(), "新增商品");
        return bo;
    }

    @Transactional("db1TxManager")
    private void insertGoodsLog(String goodsId, String remark, String action) {
        GoodsLog goodsLog = new GoodsLog();
        goodsLog.setId(Utils.uuid());
        goodsLog.setGoodsId(goodsId);
        goodsLog.setCreateUser(Utils.getAdminId());
        goodsLog.setCreateTime(new Date());
        goodsLog.setAction(action);
        goodsLog.setRemark(remark);
        goodsLogMapper.insert(goodsLog);
    }


    @Transactional("db1TxManager")
    @Override
    public GoodsBO update(GoodsBO goodsBO) {
        String goodsId = goodsBO.getId();
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsBO, goods);
        //先判断商品是否有被卖出，被卖出不能修改
        /*List<Product> pBOList = productRoMapper.selectByGoodsId(goodsBO.getId());
        for (Product product : pBOList) {
            OrderProduct orderProduct = orderProductRoMapper.selectByProductId(product.getId());
            if (orderProduct != null) {
                LOGGER.info("商品有被卖出，被卖出不能修改：{}", product);
                throw new ServiceException(4162);
            }
        }*/
        //修改产品信息
        Date date = new Date();
        goods.setLastUpdate(date);
        int gUpdate = goodsMapper.update(goods);
        if (gUpdate != 1) {
            LOGGER.info("修改产品失败：{}", goods);
            throw new ServiceException(4102);
        }
        GoodsBO bo = new GoodsBO();
        BeanUtils.copyProperties(goods, bo);
        //先删除该产品所有产品参数
        //productMapper.deleteByGoodsId(goodsId);
        //查询所有商品规格
        List<ProductBO> tempProductList = productRoMapper.selectBOListByGoodsId(goodsId);
        //获取原有的ProductId
        List<String> oldIds = new ArrayList<>();
        if (tempProductList != null && tempProductList.size() > 0) {
            oldIds.addAll(tempProductList.stream().map(ProductBO::getId).collect(Collectors.toList()));
        }
        //获取新的ProductId
        List<ProductBO> productList = goodsBO.getProductBOList();
        List<String> newIds = new ArrayList<>();
        if (productList != null && productList.size() > 0) {
            newIds.addAll(productList.stream().map(ProductBO::getId).collect(Collectors.toList()));
            //获取老的ID，并且新ID中不存在的
            List<String> delIds = new ArrayList<>();
            Collection<String> fExists = new ArrayList<>(oldIds);
            fExists.removeAll(newIds);
            delIds.addAll(fExists);

            List<ProductBO> productBOs = new ArrayList<>();
            int pInsert;
            for (ProductBO pBO : productList) {
                if (pBO.getId() == null || "".equals(pBO.getId())) { //新增产品
                    ProductBO productBO = new ProductBO();
                    Product product = new Product();
                    BeanUtils.copyProperties(pBO, product);
                    String productId = Utils.uuid();
                    product.setId(productId);
                    product.setGoodsId(goodsId);
                    product.setCreateTime(date);
                    product.setLastUpdate(date);
                    product.setStock(0);
                    //新增产品参数
                    pInsert = productMapper.insert(product);
                    if (pInsert != 1) {
                        LOGGER.info("新增产品参数失败：{}", product);
                        throw new ServiceException(4101);
                    }
                    BeanUtils.copyProperties(product, productBO);
                    //新增规格信息与产品对应关系
                    List<DictBO> dictList = pBO.getDictList();
                    for (DictBO dictBO : dictList) {
                        ProductSpec productSpec = new ProductSpec();
                        productSpec.setId(Utils.uuid());
                        productSpec.setProductId(productId);
                        productSpec.setSpecId(dictBO.getId());
                        int sInsert = productSpecMapper.insert(productSpec);
                        if (sInsert != 1) {
                            LOGGER.info("新增产品规格信息失败：{}", productSpec);
                            throw new ServiceException(4160);
                        }
                    }
                    //新增产品会员价格
                    List<UvipPrice> uvipPriceList = pBO.getUvipPriceList();
                    List<UvipPrice> uvipList = new ArrayList<>();
                    for (UvipPrice uvipPrice : uvipPriceList) {
                        String uvipPriceId = Utils.uuid();
                        uvipPrice.setId(uvipPriceId);
                        uvipPrice.setProductId(productId);
                        uvipPrice.setCreateTime(date);
                        uvipPrice.setLastUpdate(date);
                        int sInsert = uvipPriceMapper.insert(uvipPrice);
                        if (sInsert != 1) {
                            LOGGER.info("新增产品单个规格会员价格失败：{}", uvipPrice);
                            throw new ServiceException(4161);
                        }
                        uvipList.add(uvipPrice);
                    }
                    productBO.setUvipPriceList(uvipList);
                    productBOs.add(productBO);
                } else {
                    ProductBO productBO = new ProductBO();
                    Product product = new Product();
                    BeanUtils.copyProperties(pBO, product);
                    String productId = pBO.getId();
                    product.setId(productId);
                    product.setGoodsId(goodsId);
                    product.setCreateTime(date);
                    product.setLastUpdate(date);
                    product.setStock(null);
                    //新增产品参数
                    int update = productMapper.update(product);
                    if (update != 1) {
                        LOGGER.info("修改产品参数失败：{}", product);
                        throw new ServiceException(4102);
                    }
                    BeanUtils.copyProperties(product, productBO);
                    //删除规格信息与产品对应关系
                    productSpecMapper.deleteByProductId(productId);
                    //新增规格信息与产品对应关系
                    List<DictBO> dictList = pBO.getDictList();
                    for (DictBO dictBO : dictList) {
                        ProductSpec productSpec = new ProductSpec();
                        productSpec.setId(Utils.uuid());
                        productSpec.setProductId(productId);
                        productSpec.setSpecId(dictBO.getId());
                        int sInsert = productSpecMapper.insert(productSpec);
                        if (sInsert != 1) {
                            LOGGER.info("新增产品规格信息失败：{}", productSpec);
                            throw new ServiceException(4160);
                        }
                    }

                    //删除规格信息与产品对应关系
                    uvipPriceMapper.deleteByProductId(productId);
                    //新增产品会员价格
                    List<UvipPrice> uvipPriceList = pBO.getUvipPriceList();
                    List<UvipPrice> uvipList = new ArrayList<>();
                    for (UvipPrice uvipPrice : uvipPriceList) {
                        String uvipPriceId = Utils.uuid();
                        uvipPrice.setId(uvipPriceId);
                        uvipPrice.setProductId(productId);
                        uvipPrice.setCreateTime(date);
                        uvipPrice.setLastUpdate(date);
                        int sInsert = uvipPriceMapper.insert(uvipPrice);
                        if (sInsert != 1) {
                            LOGGER.info("新增产品单个规格会员价格失败：{}", uvipPrice);
                            throw new ServiceException(4161);
                        }
                        uvipList.add(uvipPrice);
                    }
                    productBO.setUvipPriceList(uvipList);
                    productBOs.add(productBO);
                }
            }

            for (String delId : delIds) {//需要删除的数据
                productMapper.deleteById(delId);
                productSpecMapper.deleteByProductId(delId);
                uvipPriceMapper.deleteByProductId(delId);
                productRepoMapper.deleteByProductId(delId);
            }

            bo.setProductBOList(productBOs);
            insertGoodsLog(bo.getId(), goodsBO.toString(), "修改商品");
        }
        return bo;
    }

    @Override
    public List<Goods> selectGoodsList(Goods goods) {
        return goodsRoMapper.selectGoodsList(goods);
    }

    @Override
    public GoodsBO selectGoods(String id) {
        return goodsRoMapper.selectGoods(id);
    }

    @Transactional("db1TxManager")
    @Override
    public void checkGoods(GoodsCheckBO goodsCheckBO) {
        String goodsIds = goodsCheckBO.getGoodsIds();
        String[] ids = goodsIds.split(",");
        for (String goodsId : ids) {
            Goods goods = new Goods();
            goods.setId(goodsId);
            goods.setStatus(goodsCheckBO.getStatus());
            int upd = goodsMapper.update(goods);
            if (upd != 1) {
                LOGGER.info("修改产品参数失败：{}", goods);
                throw new ServiceException(4119);
            }
        }
    }

    @Transactional("db1TxManager")
    @Override
    public void deleteGoods(String id) {
        //先判断商品是否有被卖出，被卖出不能删除
        List<Product> pBOList = productRoMapper.selectByGoodsId(id);
        for (Product prod : pBOList) {
            OrderProduct orderProduct = orderProductRoMapper.selectByProductId(id);
            if (orderProduct != null) {
                LOGGER.info("商品有被卖出，不能删除：{}", prod);
                throw new ServiceException(4163);
            }
        }
        Goods goods = goodsRoMapper.selectByPrimaryKey(id);
        //商品类型是会员服务=4，不能删除
       /* if (goods != null && "4".equals(goods.getGoodsType())) {
            LOGGER.info("商品类型是会员服务，不能删除：{}", goods);
            throw new ServiceException(4902);
        }*/
        //删除产品信息
        int gDelete = goodsMapper.deleteByPrimaryKey(id);
        if (gDelete != 1) {
            LOGGER.info("删除产品失败：{}", id);
            throw new ServiceException(4103);
        }
        for (Product prod : pBOList) {
            productMapper.deleteById(prod.getId());
            productSpecMapper.deleteByProductId(prod.getId());
            uvipPriceMapper.deleteByProductId(prod.getId());
            productRepoMapper.deleteByProductId(prod.getId());
        }

        //删除商品库存
        //productRepoMapper.deleteByGoodsId(id);
        insertGoodsLog(goods.getId(), goods.toString(), "删除商品");
    }

    @Override
    public List<GoodsBO> selectProductRepoList(GoodsBO goodsBO) {
        return goodsRoMapper.selectProductRepoList(goodsBO);
    }

    @Override
    public GoodsBO selectUserGoods(String id) {
        return goodsRoMapper.selectUserGoods(id);
    }

    @Override
    public List<GoodsBO> selectGoodsBOList(Goods goods) {
        return goodsRoMapper.selectGoodsBOList(goods);
    }

    @Override
    public List<GoodsLogBO> selectGoodsLogList(String id) {
        return goodsLogRoMapper.selectGoodsLogList(id);
    }

    @Override
    public List<GoodsBO> selectCurriculumGoods(String id) {
        return goodsRoMapper.selectCurriculumGoods(id);
    }
}
