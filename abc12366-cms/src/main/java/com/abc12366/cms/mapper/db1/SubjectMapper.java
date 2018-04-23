package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Subject;
import com.abc12366.cms.model.SubjectItem;
import com.abc12366.cms.model.bo.SubItemBo;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-08 3:25 PM
 * @since 1.0.0
 */
public interface SubjectMapper {
    void insertSubject(Subject subject);

    void deleteSubject(String voteId);

    void insertItem(SubjectItem item);

    void deleteItem(String subjectId);

    void updateStatus(SubItemBo subItemBo);
}
