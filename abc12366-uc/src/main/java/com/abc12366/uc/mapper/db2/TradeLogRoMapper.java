package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.TradeBillBO;
import com.abc12366.uc.model.order.TradeLog;
import com.abc12366.uc.model.order.bo.TradeLogBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TradeLogMapper数据库操作接口类
 **/

public interface TradeLogRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    TradeLog selectByPrimaryKey(@Param("id") String id);

    List<TradeLog> selectList(TradeLog tradeLog);

    TradeLog selectOne(TradeBillBO data);

    TradeLog selectByOrderNo(String OrderNo);

    /**
     * 根据Ali交易流水号查询
     * @param tradeLog
     * @return
     */
    TradeLogBO selectByAliNo(TradeLog tradeLog);

    /**
     * 查询订单交易流水记录列表
     * @param tradeLog
     * @return
     */
    List<TradeLogBO> selectBOList(TradeLog tradeLog);

    TradeLog selectTradeLog(TradeLog log);
}