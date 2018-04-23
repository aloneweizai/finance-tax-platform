package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.SubjectTagBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 15:10
 */
public interface SubjectTagRoMapper {
    List<SubjectTagBO> selectList(Map map);

    List<SubjectTagBO> selectListByTagIdsAndSubjectIds(Map<String, String> map);

    List<SubjectTagBO> selectExist(Map<String, String> map);
    List<SubjectTagBO> getSubjectIdsCategorys(Map<String, String> map);
}
