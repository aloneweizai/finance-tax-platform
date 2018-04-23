package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.Cheats;
import com.abc12366.bangbang.model.question.bo.CheatsBo;
import com.abc12366.bangbang.model.question.bo.CheatstjydBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * CheatsMapper数据库操作接口类
 * 
 **/

public interface CheatsRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	Cheats selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    CheatsBo selectCheats(@Param("id") String id);

    /**
     * 查询最新问题
     **/
    List<CheatsBo> selectList(Map<String, Object> map);

    /**
     * 查询热门问题
     **/
    List<CheatsBo> selectListByBrowseNum(Map<String, Object> map);

    /**
     * 查询推荐问题
     **/
    List<CheatsBo> selectListRecommend(Map<String, Object> map);

    /**
     * 查询推荐阅读(标题)
     **/
    List<CheatstjydBo> selectListRecommendTitle(Map<String, Object> map);

    /**
     * 查询你可能感兴趣的文章(标题)
     **/
    List<CheatstjydBo> selectListByTag(Map<String, Object> map);

    /**
     * 查询我的文章
     **/
    List<CheatsBo> selectMyCheatsList(Map<String, Object> map);

    /**
     * 查询分类代码
     **/
    String selectclassifyCode(String cheatsId);

    /**
     * 查询邦派ID
     **/
    String selectfactionId(Map<String, Object> map);

    int selectCheatsAndQuestionCount();

}