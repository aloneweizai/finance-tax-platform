package com.abc12366.cms.mapper.db2;

import java.util.List;
import java.util.Map;

import com.abc12366.cms.model.ProductSpread;

public interface ProductSpreadRoMapper {
     public List<ProductSpread> list(Map<String, Object> map);

	 public ProductSpread selectone(String id);
}
