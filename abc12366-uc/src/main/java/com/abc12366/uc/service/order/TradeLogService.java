package com.abc12366.uc.service.order;

import com.abc12366.uc.model.bo.TradeBillBO;
import com.abc12366.uc.model.order.TradeLog;
import com.abc12366.uc.model.order.bo.TradeLogBO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TradeLogService {

    /**
     * 查询交易日志列表信息
     */
    List<TradeLog> selectList(TradeLog tradeLog, int page, int size);
    
    int insertTradeLog(TradeLog tradeLog);

    List<TradeBillBO> bill(List<TradeBillBO> dataList);

    TradeLog updateCompare(TradeLog log);

    TradeLog selectOne(TradeBillBO log);

    void update(TradeLog tradeLogUpdate);

    /**
     * 根据Ali交易流水号查询
     * @param tradeLog
     */
    TradeLogBO selectByAliNo(TradeLog tradeLog);

    /**
     * 查询交易日志列表信息
     * @param tradeLog 订单对象
     * @param pageNum 页数
     * @param pageSize 条数
     * @return
     */
    List<TradeLogBO> selectBOList(TradeLog tradeLog, int pageNum, int pageSize);
}
