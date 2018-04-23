package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Subject;
import com.abc12366.cms.model.SubjectItem;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-08 3:55 PM
 * @since 1.0.0
 */
public interface SubjectRoMapper {
    List<Subject> selectSubjectList(String voteId);

    List<SubjectItem> selectItemList(String subjectId);
}
