package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.Check;
import com.abc12366.uc.model.CheckRank;
import com.abc12366.uc.model.bo.CheckListParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-21
 * Time: 14:43
 */
public interface CheckRoMapper {
    List<Check> selectByOrder(Check check);

    List<Check> selectRecheck(Check checkTmp);

    List<CheckRank> selectRankList(Map<String, Object> map);

    //List<CheckRank> selectOneRank(Map<String, String> map);

    List<Check> selectIsRecheck(Check check);

    List<Check> selectCheckList(CheckListParam c);

    Integer checkTotal(@Param("userId") String userId);
}
