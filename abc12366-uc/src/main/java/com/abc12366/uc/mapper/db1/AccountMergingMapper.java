package com.abc12366.uc.mapper.db1;

import java.util.Map;

public interface AccountMergingMapper {

	int updateHngs(Map<String, String> map);

	int updateHnds(Map<String, String> map);

	int updateDzsb(Map<String, String> map);

	int updateOrder(Map<String, String> map);

	int updateInvoice(Map<String, String> map);

	int updateClassorder(Map<String, String> map);

}
