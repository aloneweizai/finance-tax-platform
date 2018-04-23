package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.Curriculum;
import com.abc12366.bangbang.model.curriculum.bo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CurriculumMapper数据库操作接口类
 * 
 **/

public interface CurriculumRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	Curriculum  selectByPrimaryKey(@Param("curriculumId") String curriculumId);

    /**
     *
     * 查询是否已存在
     *
     **/
    int selectCurrCntByGoodsId(@Param("goodsId") String goodsId);

    /**
     *
     * 根据课程分类查询课程数
     *
     **/
    int selectCurrCntByClassify(@Param("classify") String classify);

    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumListBo> selectList(Map<String, Object> map);

    /**
     * 查询最新课程
     **/
    List<CurriculumListsyBo> selectListNew(Map<String, Object> map);

    /**
     * 查询会员专享课程
     **/
    List<CurriculumListsyBo> selectListVIP(Map<String, Object> map);

    /**
     * 查询相关课程
     **/
    List<CurriculumListsyBo> selectListxgNew(@Param("curriculumId") String curriculumId);

    /**
     * 查询最热课程
     **/
    List<CurriculumListsyBo> selectListWatch(Map<String, Object> map);

    /**
     * 查询推荐课程
     **/
    List<CurriculumListsyBo> selectRecommend(Map<String, Object> map);

    /**
     * 查询课程授课信息
     **/
    CurriculumSituationBo selectSituation(@Param("curriculumId") String curriculumId);

    /**
     * 查询课程信息(前端用)
     **/
    CurriculumsyBo selectCurriculum(@Param("curriculumId") String curriculumId);

    /**
     * 查询课程信息(查询下架课程)
     **/
    CurriculumsyBo selectCurriculum2(@Param("curriculumId") String curriculumId);

    /**
     * 查询课程信息(前端用)
     **/
    CurriculumEvaluateTjBo selectEvaluateTj(@Param("curriculumId") String curriculumId);

    /**
     * 查询课程学习历史
     **/
    List<CurrMyStudyBo> selectStudyHistory(Map<String, Object> map);

    /**
     * 查询收藏课程
     **/
    List<CurriculumListsyBo> selectListCollect(Map<String, Object> map);

    /**
     * 查询本月学习课程数
     **/
    int selectStudyNumByMonth(Map<String, Object> map);

    /**
     * 查询本年学习课程数
     **/
    int selectStudyNumByYear(Map<String, Object> map);

    /**
     * 查询学习勤奋榜
     **/
    String selectStudyQfb(Map<String, Object> map);


    /**
     *
     * 查询课程下是否有课件
     *
     **/
    int  selectCoursewareCnt(@Param("curriculumId") String curriculumId);

    /**
     * 查询课程标题是否重复
     **/
    int selectCurriculumCnt(Map<String, Object> map);

    /**
     * 根据知识库的标签查询
     * @param knowledgeId
     * @return
     */
    List<CurriculumListBo> selectByKnowledgeId(@Param("knowledgeId")String knowledgeId, @Param("num")int num);

}