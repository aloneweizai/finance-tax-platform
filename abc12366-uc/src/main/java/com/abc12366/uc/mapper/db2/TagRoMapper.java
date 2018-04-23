package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.TagBO;
import com.abc12366.uc.model.bo.TagSelectParamBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 11:03
 */
public interface TagRoMapper {

    TagBO selectOne(String id);

    List<TagBO> selectList(TagSelectParamBO tagSelectParamBO);

    List<TagBO> selectListByUserId(String userId);

    /**
     * 根据标签ID查询用户ID，标签ID、用户ID都可以为多个
     *
     * @param map 标签ID
     * @return 用户ID列表
     */
    List<String> selectUserIdsByTagIds(Map map);
}
