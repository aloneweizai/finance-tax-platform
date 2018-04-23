package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionSysBlock;
import com.abc12366.bangbang.model.question.bo.QuestionSysBlockBo;
import com.abc12366.bangbang.model.question.bo.QuestionSysBlockParamBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/17 17:07
 */
public interface QuestionSysBlockRoMapper {

    /**
     *
     * 列表查询
     *
     **/
    List<QuestionSysBlockBo> selectList(QuestionSysBlockParamBo Param);


    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    QuestionSysBlock selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 根据状态查询总数
     *
     **/
    Long selectCntByStatus(@Param("status")String status);
}
