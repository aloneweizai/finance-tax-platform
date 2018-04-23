package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionDisableIp;
import com.abc12366.bangbang.model.question.bo.QuestionDisableIpBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/18 17:52
 */
public interface QuestionDisableIpRoMapper {

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    QuestionDisableIp selectByPrimaryKey(@Param("id") Long id);

    /**
     *
     * 列表查询
     *
     **/
    List<QuestionDisableIpBo> selectList(Map map);

    /**
     *
     *
     *查询IP是否被禁止
     **/
    int selectIpCnt(@Param("ip") String ip);

}
