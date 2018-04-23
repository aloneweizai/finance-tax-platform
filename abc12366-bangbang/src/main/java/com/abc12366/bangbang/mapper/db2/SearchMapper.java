package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.CheatsSearchBo;
import com.abc12366.bangbang.model.question.QuestionSearchBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by stuy on 2017/11/9.
 */
public interface SearchMapper {

    List<QuestionSearchBo> queryQuestionSearch(@Param("text") String text);

    Map<String,String> queryUser(@Param("userid") String userid);

    Integer queryLike(@Param("questionid") String questionid);

    Integer queryTread(@Param("questionid") String questionid);

    Integer queryComment(@Param("questionid") String questionid);

    Map<String,Object> queryAnswer(@Param("questionid") String questionid);

    List<CheatsSearchBo> queryCheats(@Param("text") String text);
}
