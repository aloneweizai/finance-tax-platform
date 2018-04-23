package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.GoodsCategoryMapper;
import com.abc12366.uc.mapper.db2.GoodsCategoryRoMapper;
import com.abc12366.uc.mapper.db2.GoodsRoMapper;
import com.abc12366.uc.model.order.Goods;
import com.abc12366.uc.model.order.GoodsCategory;
import com.abc12366.uc.model.order.bo.GoodsBO;
import com.abc12366.uc.model.order.bo.GoodsCategoryBO;
import com.abc12366.uc.service.order.GoodsCategoryService;
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
 * @date  2017-06-01
 * @since 1.0.0
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);

    @Autowired
    private GoodsCategoryRoMapper goodsCategoryRoMapper;

    @Autowired
    private GoodsRoMapper goodsRoMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public GoodsCategoryBO selectList(GoodsCategory goodsCategory) {
        //查询所有父节点
        GoodsCategoryBO bo;
        if (goodsCategory != null && goodsCategory.getCategory() != null && !"".equals(goodsCategory.getCategory())) {
            bo = goodsCategoryRoMapper.selectByName(goodsCategory.getCategory());
        } else {
            bo = goodsCategoryRoMapper.selectParentCategory();
        }
        GoodsCategoryBO categoryBO = new GoodsCategoryBO();
        if (bo != null) {
            categoryBO = recursiveTree(bo.getId());
        }
        return categoryBO;
    }

    /**
     * 递归算法解析成树形结构
     *
     * @param id id
     */
    public GoodsCategoryBO recursiveTree(String id) {
        GoodsCategoryBO node = goodsCategoryRoMapper.selectById(id);
        List<GoodsCategoryBO> treeNodes = goodsCategoryRoMapper.selectByParentId(id);
        //遍历子节点
        for (GoodsCategoryBO child : treeNodes) {
            GoodsCategoryBO n = recursiveTree(child.getId()); //递归
            node.getNodes().add(n);
        }
        return node;
    }

    @Transactional("db1TxManager")
    @Override
    public GoodsCategory add(GoodsCategoryBO goodsCategoryBO) {
        GoodsCategory goodsCategory = new GoodsCategory();
        BeanUtils.copyProperties(goodsCategoryBO, goodsCategory);
        goodsCategory.setId(Utils.uuid());
        Date date = new Date();
        goodsCategory.setCreateTime(date);
        goodsCategory.setLastUpdate(date);
        if (goodsCategoryBO.getParentId() == null || "".equals(goodsCategory.getParentId())) {
            LOGGER.info("请选择分类的父节点：{}", goodsCategoryBO);
            throw new ServiceException(4153);
        }
        //不能加入名称相同的分类
        GoodsCategoryBO bo = goodsCategoryRoMapper.selectByName(goodsCategoryBO.getCategory());
        if (bo != null) {
            LOGGER.info("不能加入名称相同的分类：{}", goodsCategoryBO);
            throw new ServiceException(4152);
        }
        int insert = goodsCategoryMapper.insert(goodsCategory);
        if (insert != 1) {
            LOGGER.info("新增失败：{}", goodsCategory);
            throw new ServiceException(4101);
        }
        return goodsCategory;
    }

    @Transactional("db1TxManager")
    @Override
    public GoodsCategoryBO update(GoodsCategoryBO goodsCategoryBO) {
        String id = goodsCategoryBO.getId();
        String parentId = goodsCategoryBO.getParentId();
        //判断节点是否是自己本身
        if (parentId != null && !"".equals(parentId)) {
            if (id.equals(parentId)) {
                throw new ServiceException(4118);
            }
        } else {
            LOGGER.info("请选择分类的父节点：{}", goodsCategoryBO);
            throw new ServiceException(4153);
        }
        GoodsCategory category = new GoodsCategory();
        BeanUtils.copyProperties(goodsCategoryBO, category);
        category.setLastUpdate(new Date());
        int upd = goodsCategoryMapper.update(category);
        if (upd != 1) {
            throw new ServiceException(4102);
        }
        GoodsCategoryBO bo = new GoodsCategoryBO();
        BeanUtils.copyProperties(category, bo);
        return bo;
    }

    @Override
    public GoodsCategory selectGategory(String id) {
        return goodsCategoryRoMapper.selectByPrimaryKey(id);
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(String id) {
        Goods goods = new Goods();
        goods.setCategoryId(id);
        List<GoodsBO> goodsBOs = goodsRoMapper.selectGoodsBOList(goods);
        if(goodsBOs != null && goodsBOs.size() > 0){
            LOGGER.info("该商品分类下存在商品，不能删除：{}", goodsBOs);
            throw new ServiceException(4917);
        }
        deleteTree(id);
    }

    /**
     * 递归算法删除树节点
     *
     * @param id id
     */
    public void deleteTree(String id) {
        List<GoodsCategoryBO> treeNodes = goodsCategoryRoMapper.selectByParentId(id);
        //遍历子节点
        for (GoodsCategoryBO child : treeNodes) {
            deleteTree(child.getId()); //递归
        }
        goodsCategoryMapper.deleteByPrimaryKey(id);
    }
}
