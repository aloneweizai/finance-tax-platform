package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumBrowserWatch;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumListBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * CurriculumBrowserWatchRoMapper数据库操作接口类
 */
public interface CurriculumBrowserWatchRoMapper {

    int selectCntToday(@Param("curriculumId") String curriculumId);

    /*今日浏览观看数*/
    CurriculumBrowserWatch selectTodayNum();

    /*本月浏览观看数*/
    CurriculumBrowserWatch selectCurrentMonthNum();

    /*列表查询*/
    List<CurriculumListBo> selectList(Map<String, Object> map);

    /*月表数据列表查询*/
    List<CurriculumListBo> selectMonthList(Map<String, Object> map);
}
