package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFactionMember;
import com.abc12366.bangbang.model.question.QuestionMemberHonor;
import com.abc12366.bangbang.model.question.bo.QuestionFactionMemberBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionFactionMemberMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionMemberRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionFactionMember selectByPrimaryKey(@Param("memberId") String memberId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionFactionMemberBo> selectList(Map<String, Object> map);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionFactionMemberBo> selectListTj(Map<String, Object> map);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    int selectSplendidNum(Map<String, Object> map);

    /**
     *
     * 查询用户是否已申请
     *
     **/
    int selectMemberCnt(Map<String, Object> map);

    /**
     *
     * 查询已通过的用户数
     *
     **/
    int selectPassMemberCnt(@Param("factionId") String factionId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionMemberHonor> selectMemberHonorList(@Param("factionId") String factionId);

    /**
     *
     * 查询职位数量
     *
     **/
    int selectMemberDutyCnt(Map<String, Object> map);

    /**
     *
     * 查询加入的邦派分类是否重复
     *
     **/
    int selectClassifyCnt(Map<String, Object> map);

    /**
     *
     * 查询帮主
     *
     **/
    QuestionFactionMember selectFactionLeader(@Param("factionId") String factionId);
}