package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.ProductMapper;
import com.abc12366.uc.mapper.db1.ProductRepoMapper;
import com.abc12366.uc.mapper.db2.ProductRepoRoMapper;
import com.abc12366.uc.model.order.Product;
import com.abc12366.uc.model.order.ProductRepo;
import com.abc12366.uc.model.order.bo.ProductRepoBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author lizhongwei
 * @date 2017-07-01 11:25 AM
 * @since 1.0.0
 */
@Service
public class ProductRepoServiceImpl implements ProductRepoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepoServiceImpl.class);

    @Autowired
    private ProductRepoRoMapper productRepoRoMapper;

    @Autowired
    private ProductRepoMapper productRepoMapper;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductRepoBO> selectList(ProductRepoBO productRepoBO) {
        return productRepoRoMapper.selectList(productRepoBO);
    }

    @Override
    public ProductRepoBO selectById(String id) {
        return productRepoRoMapper.selectById(id);
    }

    @Transactional("db1TxManager")
    @Override
    public ProductRepoBO income(ProductRepoBO productRepoBO) {
        productRepoBO.setId(Utils.uuid());
        int stock;
        ProductRepoBO temp = productRepoRoMapper.selectByGoodsId(productRepoBO);
        if (temp == null) {
            stock = productRepoBO.getIncome();
        } else {
            stock = temp.getStock() + productRepoBO.getIncome();
        }
        Date date = new Date();
        productRepoBO.setCreateTime(date);
        productRepoBO.setLastUpdate(date);
        productRepoBO.setStock(stock);
        productRepoBO.setRemark(Utils.getAdminInfo().getNickname()+"-操作入库");
        productRepoBO.setOptionUser(Utils.getAdminId());
        ProductRepo productRepo = new ProductRepo();
        BeanUtils.copyProperties(productRepoBO, productRepo);
        int insert = productRepoMapper.insert(productRepo);
        if (insert != 1) {
            LOGGER.info("商品入库失败:{}", productRepo);
            throw new ServiceException(4158);
        }
        //入库成功后，修改产品表的库存信息
        updateProductStock(productRepoBO, stock);
        return productRepoBO;
    }


    @Transactional("db1TxManager")
    @Override
    public ProductRepoBO outcome(ProductRepoBO productRepoBO) {
        productRepoBO.setId(Utils.uuid());
        int stock;
        ProductRepoBO temp = productRepoRoMapper.selectByGoodsId(productRepoBO);
        if (temp == null) {
            stock = 0 - productRepoBO.getOutcome();
        } else {
            stock = temp.getStock() - productRepoBO.getOutcome();
        }
        if(stock < 0){
            LOGGER.info("库存不能为负数:{}", stock);
            throw new ServiceException(4914);
        }
        Date date = new Date();
        productRepoBO.setCreateTime(date);
        productRepoBO.setLastUpdate(date);
        productRepoBO.setStock(stock);
        productRepoBO.setRemark(Utils.getAdminInfo().getNickname()+"-操作出库");
        productRepoBO.setOptionUser(Utils.getAdminId());
        ProductRepo productRepo = new ProductRepo();
        BeanUtils.copyProperties(productRepoBO, productRepo);
        int insert = productRepoMapper.insert(productRepo);
        if (insert != 1) {
            LOGGER.info("商品出库失败:{}", productRepo);
            throw new ServiceException(4159);
        }
        //出库成功后，修改产品表的库存信息
        updateProductStock(productRepoBO, stock);
        return productRepoBO;
    }

    @Override
    public List<ProductRepoBO> selectProductRepoDetail(ProductRepo productRepo) {
        return productRepoRoMapper.selectProductRepoDetail(productRepo);
    }

    private void updateProductStock(ProductRepoBO productRepoBO, int stock) {
        Product product = new Product();
        product.setGoodsId(productRepoBO.getGoodsId());
        product.setId(productRepoBO.getProductId());
        product.setStock(stock);
        product.setLastUpdate(new Date());
        int pUpdate = productMapper.update(product);
        if (pUpdate != 1) {
            LOGGER.info("产品库存修改失败:{}", product);
            throw new ServiceException(4164);
        }
    }
}
