package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.curriculum.bo.CurriculumCollectBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CurrCollectService {

    CurriculumCollectBo insert(String curriculumId, HttpServletRequest request);

    void delete(String curriculumId, HttpServletRequest request);

    List<CurriculumCollectBo> selectList(String userId);

    String selectExist(String curriculumId,HttpServletRequest request);

}
