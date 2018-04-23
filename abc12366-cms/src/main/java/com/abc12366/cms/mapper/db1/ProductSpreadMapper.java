package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ProductImg;
import com.abc12366.cms.model.ProductSpread;

public interface ProductSpreadMapper {

	public void saveProductSpread(ProductSpread productSpread);

	public void saveImg(ProductImg productImg);

	public void putProductSpread(ProductSpread productSpread);

	public void delImg(String id);

	public void delProductSpread(String id);

}
