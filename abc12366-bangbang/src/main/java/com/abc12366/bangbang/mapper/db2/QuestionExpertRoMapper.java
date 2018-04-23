package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.bo.QuestionExpertBO;
import com.abc12366.bangbang.model.question.bo.QuestionExpertParamBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/19 17:25
 */
public interface QuestionExpertRoMapper {

    /**
     *
     * 列表查询
     *
     **/
    List<QuestionExpertBO> selectList(QuestionExpertParamBo param);

    /**
     *
     * 列表查询
     *
     **/
    List<QuestionExpertBO> selectListDX(Map<String, Object> map);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    QuestionExpertBO selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 列表查询
     *
     **/
    List<QuestionExpertBO> selectListByUserId(@Param("userId") String userId);

}
