package com.abc12366.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc12366.cms.mapper.db1.ProductSpreadMapper;
import com.abc12366.cms.mapper.db2.ProductSpreadRoMapper;
import com.abc12366.cms.model.ProductImg;
import com.abc12366.cms.model.ProductSpread;
import com.abc12366.cms.service.IProductSpreadService;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.PageHelper;

@Service
public class ProductSpreadServiceImpl implements IProductSpreadService {

	@Autowired
	private ProductSpreadRoMapper productSpreadRoMapper;

	@Autowired
	private ProductSpreadMapper productSpreadMapper;

	@Override
	public List<ProductSpread> list(Map<String, Object> map, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
		return productSpreadRoMapper.list(map);
	}

	@Override
	public ProductSpread selectone(String id) {
		return productSpreadRoMapper.selectone(id);
	}

	@Transactional("db1TxManager")
	public void save(ProductSpread productSpread) {
		productSpreadMapper.saveProductSpread(productSpread);
		for (ProductImg productImg : productSpread.getProductImgs()) {
			productImg.setId(Utils.uuid());
			productImg.setProductId(productSpread.getId());
			productSpreadMapper.saveImg(productImg);
		}
	}

	@Transactional("db1TxManager")
	public void put(ProductSpread productSpread) {
		productSpreadMapper.delImg(productSpread.getId());
		productSpreadMapper.putProductSpread(productSpread);
		for (ProductImg productImg : productSpread.getProductImgs()) {
			productImg.setId(Utils.uuid());
			productImg.setProductId(productSpread.getId());
			productSpreadMapper.saveImg(productImg);
		}
	}

	@Transactional("db1TxManager")
	public void del(String id) {
		productSpreadMapper.delProductSpread(id);
		productSpreadMapper.delImg(id);
	}

}
