package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.TagBO;
import com.abc12366.uc.model.bo.TagInsertBO;
import com.abc12366.uc.model.bo.TagSelectParamBO;
import com.abc12366.uc.model.bo.TagUpdateBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 10:36
 */
public interface TagService {
    List<TagBO> selectList(TagSelectParamBO tagSelectParamBO);

    TagBO insert(TagInsertBO tagInsertBO);

    TagBO selectOne(String id);

    TagBO update(TagUpdateBO tagUpdateBO, String id);

    int delete(String id);

    List<TagBO> selectListByUserId(String userId);

    void enableOrDisable(String id, String status);

    /**
     * 根据标签ID查询用户ID，标签ID、用户ID都可以为多个
     *
     * @param map 标签ID
     * @return 用户ID列表
     */
    List<String> selectUserIdsByTagIds(Map map);
}
