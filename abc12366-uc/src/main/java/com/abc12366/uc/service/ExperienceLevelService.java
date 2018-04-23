package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.ExperienceLevelBO;
import com.abc12366.uc.model.bo.ExperienceLevelInsertAndUpdateBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 15:50
 */
public interface ExperienceLevelService {
    List<ExperienceLevelBO> selectList(Map<String, Object> map);

    ExperienceLevelBO selectOne(String id);

    /**
     * 根据经验值查询对象信息
     *
     * @param exp 经验值
     * @return 经验值对象信息
     */
    ExperienceLevelBO selectOne(int exp);

    ExperienceLevelBO insert(ExperienceLevelInsertAndUpdateBO experienceLevelInsertBO);

    ExperienceLevelBO update(ExperienceLevelInsertAndUpdateBO experienceLevelUpdateBO, String id);

    int delete(String id);

    void enableOrDisable(String id, String status);
}
