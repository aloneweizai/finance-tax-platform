package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.dzfp.Einvocie;

import java.util.List;

public interface DzfpRoMapper {

	Einvocie selectOne(String fPQQLSH);

	List<Einvocie> selectList(Einvocie einvocie);

	Einvocie selectEinvoice(Einvocie einvocie);
}
