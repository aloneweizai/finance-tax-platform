package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFactionAllocation;
import com.abc12366.bangbang.model.question.bo.AllocationPointAwardBO;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationManageBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationsBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionFactionAllocationMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionAllocationRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionFactionAllocation selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionFactionAllocationBo> selectList(Map map);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    int selectAllocationCnt(Map map);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    int selectIntegral(Map map);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    int selectAwardPoint(Map map);

    /**
     *
     * 查询
     *
     **/
    List<QuestionFactionAllocationManageBo> selectAllocationManageList(Map map);

    /**
     *
     * 查询
     *
     **/
    List<QuestionFactionAllocationsBo> selectAllocationList(Map map);

    /**
     *
     * 查询积分分配列表
     *
     **/
    List<AllocationPointAwardBO> selectPointAwardListByIds(List<String> ids);

}