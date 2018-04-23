package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.gift.UgiftApply;
import com.abc12366.uc.model.gift.bo.UgiftApplyBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * UgiftApplyMapper数据库操作接口类
 *
 * @author lizhongwei
 * @create 2017-12-18 3:09 PM
 * @since 1.0.0
 **/

public interface UgiftApplyRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    UgiftApply selectByPrimaryKey(@Param("id") String id);


    /**
     * 根据applyId查找
     *
     * @param applyId
     * @return
     */
    UgiftApplyBO selectByApplyId(String applyId);

    /**
     * 前台-用户的礼物申请列表
     *
     * @param map
     * @return
     */
    List<UgiftApplyBO> selectUgiftApplyList(Map<String, Object> map);

    /**
     * 查询会员礼包申请详情
     * @param map
     * @return
     */
    UgiftApplyBO selectUgiftApplyBO(Map<String, Object> map);

    Integer getGiftStatusCount(@Param("status")String status);

}