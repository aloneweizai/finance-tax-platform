package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.SubjectTag;
import com.abc12366.uc.model.bo.SubjectTagBO;

import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 15:10
 */
public interface SubjectTagMapper {
    int insert(SubjectTag subjectTag);

    int delete(Map map);

    int update(SubjectTagBO subjectTagBO);
    int deleteByTagId(String tagId);
    int deleteById(String id);
}
