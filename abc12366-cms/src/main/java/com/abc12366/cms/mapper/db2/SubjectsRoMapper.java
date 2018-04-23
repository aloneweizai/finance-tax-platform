package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.bo.SubjectsdtxxtjBo;
import com.abc12366.cms.model.questionnaire.Subjects;
import com.abc12366.cms.model.questionnaire.bo.SubjectsBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * SubjectsMapper数据库操作接口类
 **/

public interface SubjectsRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Subjects selectByPrimaryKey(@Param("id") String id);

    List<SubjectsBO> selectList(Subjects subjects);

    SubjectsBO selectOne(String id);

    List<SubjectsBO> selectSubjectsByPages(Subjects subjects);

    List<SubjectsdtxxtjBo> selectListdttj(Map<String, Object> map);
}