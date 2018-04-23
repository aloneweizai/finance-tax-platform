package com.abc12366.cms.service;

import java.util.List;
import java.util.Map;

import com.abc12366.cms.model.ProductSpread;

/**
 * 产品宣传页
 * @author Administrator
 *
 */
public interface IProductSpreadService {

	List<ProductSpread> list(Map<String, Object> map, int pageNum, int pageSize);

	ProductSpread selectone(String id);

	void save(ProductSpread productSpread);

	void put(ProductSpread productSpread);

	void del(String id);

}
