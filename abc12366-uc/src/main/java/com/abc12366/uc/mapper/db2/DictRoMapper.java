package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.Dict;
import com.abc12366.uc.model.bo.DictBO;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-24 3:12 PM
 * @since 1.0.0
 */
public interface DictRoMapper {
    List<Dict> selectList(Dict dict);

    List<Dict> selectFirstLevel();

    Dict selectOne(Dict dict);

    List<Dict> selectByDictId(String dictId);

    Dict selectById(String id);

    Dict selectByDictName(String dictName);

    List<DictBO> selectDictList(Dict dict);

    List<Dict> selectListByDictName(Dict dict);

    Dict selectDict(DictBO dictBO);

    DictBO selectByFieldValue(String id);

    List<DictBO> selectByParentDictId(String id);
}
