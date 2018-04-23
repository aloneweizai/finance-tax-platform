package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.BatchTagInsertBO;
import com.abc12366.uc.model.bo.SubjectTagBO;
import com.abc12366.uc.model.bo.TagList;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-15
 * Time: 15:07
 */
public interface SubjectTagService {
    SubjectTagBO insert(@RequestParam("subject") String subject, @RequestParam("id") String id, @RequestParam
            ("tagId") String tagId, HttpServletRequest request);

    List<SubjectTagBO> batchInsert(@RequestParam("subject") String subject, @RequestParam("id") String id, TagList
            tagList, HttpServletRequest request);

    List<SubjectTagBO> selectList(@RequestParam("subject") String subject, @RequestParam("id") String id);

    int delete(@RequestParam("subject") String subject, @RequestParam("id") String id, @RequestParam("tagId") String
            tagId);

    int deleteByTagId(String id);

    List<SubjectTagBO> batchUserInsert(String subject, String tagId, String subjectIds, HttpServletRequest request);

    List<SubjectTagBO> batchInsert2(String subject, BatchTagInsertBO batchTagInsertBO, HttpServletRequest request);
}
