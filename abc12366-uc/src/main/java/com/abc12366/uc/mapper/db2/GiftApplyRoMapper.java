package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.gift.GiftApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GiftApplyMapper数据库操作接口类
 *
 * @author lizhongwei
 * @create 2017-12-18 3:09 PM
 * @since 1.0.0
 **/

public interface GiftApplyRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    GiftApply selectByPrimaryKey(@Param("id") String id);

    /**
     * 根据applayId查找
     *
     * @param applyId
     * @return
     */
    GiftApply selectByApplyId(String applyId);

    /**
     * 根据giftId查找
     * @param giftId
     * @return
     */
    List<GiftApply> selectByGiftId(String giftId);
}