package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionDisableUser;
import com.abc12366.bangbang.model.question.bo.QuestionDisableUserBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/18 15:40
 */
public interface QuestionDisableUserRoMapper {

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    QuestionDisableUser selectByPrimaryKey(@Param("id") Long id);


    /**
     *
     * 列表查询
     *
     **/
    List<QuestionDisableUserBo> selectList(Map map);

    /**
     *
     *
     *查询用户是否被禁言
     **/
    int selectUserCnt(@Param("userId") String userId);

}
