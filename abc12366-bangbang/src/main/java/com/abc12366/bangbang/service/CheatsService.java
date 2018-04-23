package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.CheatsBo;
import com.abc12366.bangbang.model.question.bo.CheatsTagBo;
import com.abc12366.bangbang.model.question.bo.CheatstjydBo;

import java.util.List;
import java.util.Map;

public interface CheatsService {

    List<CheatsBo> selectList(Map<String, Object> map);

    List<CheatsBo> selectListByBrowseNum(Map<String, Object> map);

    List<CheatsBo> selectListRecommend(Map<String, Object> map);

    List<CheatstjydBo> selectListRecommendTitle(Map<String, Object> map);

    List<CheatstjydBo> selectListByTag(Map<String, Object> map);

    List<CheatsBo> selectMyCheatsList(Map<String, Object> map);

    CheatsBo save(CheatsBo cheatsBo);

    CheatsBo selectCheats(String id);

    CheatsBo update(CheatsBo cheatsBo);

    String updateStatus(String id, String status);

    String delete(String id);

    List<CheatsTagBo> selectTagList();

    String updateBrowseNum(String id);

    void recommend(String id, Boolean isRecommend, CheatsBo cheatsBo);

    /**
     * 查询秘籍和话题总数
     */
    int selectCheatsAndQuestionCount();

}
