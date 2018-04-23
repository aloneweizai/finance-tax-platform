package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.gift.Gift;
import com.abc12366.uc.model.gift.bo.GiftBO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * GiftMapper数据库操作接口类
 * @author lizhongwei
 * @create 2017-12-18 3:09 PM
 * @since 1.0.0
 **/

public interface GiftRoMapper {


	/**
	 * 查询（根据主键ID查询）
	 **/
	Gift selectByPrimaryKey(@Param("id") String id);

	/**
	 * 礼物列表查询
	 * @param gift
	 * @return
	 */
	List<Gift> selectList(Gift gift);

    /**
     * 查看单个礼物实体
     * @param gift
     * @return
     */
    GiftBO selectOne(Gift gift);

    /**
     * 根据ID查找用户已领取礼物详情
     * @param map
     * @return
     */
    Gift selectGiftByGiftId(Map<String, Object> map);

    /**
     * 查询自动收货信息
     * @param date
     * @return
     */
    List<Gift> selectReceiptGiftByDate(@Param("date")Date date);

    /**
     * 后台-礼物列表查询
     */
    List<Gift> selectAdminList(Gift gift);
}