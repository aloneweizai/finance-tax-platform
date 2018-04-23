package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.AdminModifyUphoneLogList;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-15
 * Time: 15:20
 */
public interface AdminOperationRoMapper {
    List<AdminModifyUphoneLogList> selectList(Map map);
}
