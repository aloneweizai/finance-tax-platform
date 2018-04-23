package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.uc.mapper.db1.ProductMapper;
import com.abc12366.uc.mapper.db2.ProductRoMapper;
import com.abc12366.uc.model.bo.DictBO;
import com.abc12366.uc.model.order.Product;
import com.abc12366.uc.model.order.bo.GoodsBO;
import com.abc12366.uc.model.order.bo.ProductBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @since 1.0.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRoMapper productRoMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> selectList(Product product) {
        return productRoMapper.selectList(product);
    }

    @Transactional("db1TxManager")
    @Override
    public ProductBO add(ProductBO productBO) {
        Product product = new Product();
        BeanUtils.copyProperties(productBO, product);
//        product.setId(Utils.uuid());
        Date date = new Date();
        product.setCreateTime(date);
        product.setLastUpdate(date);
        int insert = productMapper.insert(product);
        if (insert != 1) {
            throw new ServiceException(4101);
        } else {
            ProductBO temp = new ProductBO();
            BeanUtils.copyProperties(product, temp);
            return temp;
        }
    }

    @Transactional("db1TxManager")
    @Override
    public ProductBO update(ProductBO productBO) {
        Product product = new Product();
        BeanUtils.copyProperties(productBO, product);

        Date date = new Date();
        product.setLastUpdate(date);
        int update = productMapper.update(product);
        if (update != 1) {
            throw new ServiceException(4102);
        } else {
            ProductBO temp = new ProductBO();
            BeanUtils.copyProperties(product, temp);
            return temp;
        }
    }

    @Override
    public List<ProductBO> selectByGoodsId(ProductBO product) {
        return productRoMapper.selectBOListByGoodsId(product.getGoodsId());
    }

    @Override
    public List<GoodsBO> selectBOList(ProductBO productBO) {
        return productRoMapper.selectBOList(productBO);
    }

    @Override
    public List<DictBO> selectSpecByGoodsId(ProductBO product) {
        return productRoMapper.selectSpecByGoodsId(product);
    }
}
