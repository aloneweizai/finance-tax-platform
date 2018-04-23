package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.curriculum.bo.*;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CurriculumService {

    List<CurriculumListBo> selectList(Map<String, Object> map);

    List<CurriculumListsyBo> selectListNew(Map<String, Object> map);

    List<CurriculumListsyBo> selectListVIP(Map<String, Object> map);

    List<CurriculumListsyBo> selectListWatch(Map<String, Object> map);

    List<CurriculumListsyBo> selectRecommend(Map<String, Object> map);

    List<CurriculumLabelBo> selectLabelList();

    CurriculumSituationBo selectSituation(String curriculumId);

    int selectCurrCntByGoodsId(String goodsId);

    CurriculumBo save(CurriculumBo curriculumBo);

    CurriculumBo selectCurriculum(String curriculumId);

    CurriculumsyBo selectCurriculumsy(String curriculumId);

    CurriculumsyBo selectCurriculumsy2(String curriculumId);

    CurriculumEvaluateTjBo selectEvaluateTj(String curriculumId);

    CurriculumBo update(CurriculumBo curriculumBo,HttpServletRequest request);

    String updateStatus(String curriculumId, String status, HttpServletRequest request);

    String delete(String curriculumId);

    String deleteList(String[] curriculumIds);

    List<CurrMyStudyBo> selectStudyHistory(Map<String, Object> map);

    List<CurriculumListsyBo> selectListCollect(Map<String, Object> map);

    CurrMyStudyNumBo selectStudyNum(Map<String, Object> map);

    String updateBrowsesDay(String curriculumId);

    List<CurriculumListBo> selectByKnowledgeId(String knowledgeId, int num);

    boolean isOptional(List<CurriculumGiftBo> curriculumGiftBoList);
}
