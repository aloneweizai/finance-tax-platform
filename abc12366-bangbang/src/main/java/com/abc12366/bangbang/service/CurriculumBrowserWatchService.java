package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.curriculum.CurriculumBrowserWatch;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumListBo;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2018/1/30 16:31
 */
public interface CurriculumBrowserWatchService {

    /*列表查询*/
    List<CurriculumListBo> selectList(Map<String,Object> map);

    /*月表数据查询*/
    List<CurriculumListBo> selectMonthList(Map<String,Object> map);

    /*今日浏览观看数*/
    CurriculumBrowserWatch selectTodayNum();

    /*本月浏览观看数*/
    CurriculumBrowserWatch selectCurrentMonthNum();

    /*修改*/
    void updateBrowserNum(String curriculumId);

    /*修改*/
    void updateWatchNum(String curriculumId);
}
