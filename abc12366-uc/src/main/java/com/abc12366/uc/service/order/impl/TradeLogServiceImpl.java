package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.uc.mapper.db1.TradeLogMapper;
import com.abc12366.uc.mapper.db2.TradeLogRoMapper;
import com.abc12366.uc.model.bo.TradeBillBO;
import com.abc12366.uc.model.order.TradeLog;
import com.abc12366.uc.model.order.bo.TradeLogBO;
import com.abc12366.uc.service.order.TradeLogService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @since 1.0.0
 */
@Service
public class TradeLogServiceImpl implements TradeLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeLogServiceImpl.class);

    @Autowired
    private TradeLogRoMapper tradeLogRoMapper;
    
    @Autowired
    private TradeLogMapper tradeLogMapper;

    @Override
    public List<TradeLog> selectList(TradeLog tradeLog, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        return tradeLogRoMapper.selectList(tradeLog);
    }

	public int insertTradeLog(TradeLog tradeLog) {
		return tradeLogMapper.insertTradeLog(tradeLog);
	}

    public TradeLog selectOne(TradeBillBO data) {
        return tradeLogRoMapper.selectOne(data);
    }

    @Override
    public void update(TradeLog tradeLogUpdate) {
        tradeLogMapper.update(tradeLogUpdate);
    }

    @Override
    public TradeLogBO selectByAliNo(TradeLog tradeLog) {
        return tradeLogRoMapper.selectByAliNo(tradeLog);
    }

    @Override
    public List<TradeLogBO> selectBOList(TradeLog tradeLog, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        return tradeLogRoMapper.selectBOList(tradeLog);
    }

    @Override
    public List<TradeBillBO> bill(List<TradeBillBO> dataList) {
        try {
			List<TradeBillBO> undoneList = new ArrayList<>();
			if (dataList.size() > 0) {
			    for (TradeBillBO data: dataList) {
			        TradeLog log = selectOne(data);
                    if(log!=null){
                        if (log.getTradeNo().equals(data.getTradeNo())
                                && log.getAmount().equals(data.getAmount())) {
                            log.setCompareStatus("1");
                        } else {
                            log.setCompareStatus("0");
                            undoneList.add(data);
                        }
                        log.setCompareTime(new Date());
                        tradeLogMapper.update(log);
                    }
			    }
			}
			return undoneList;
		} catch (Exception e) {
			LOGGER.error("自动对账异常", e);
            throw new ServiceException(9999,"对账数据异常!");
		}
    }

    @Override
    public TradeLog updateCompare(TradeLog log) {
//        TradeLog tradeLog = tradeLogRoMapper.selectByPrimaryKey(log.getTradeNo());
//        tradeLog.setCompareStatus(log.getCompareStatus());
//        tradeLog.setCompareTime(new Date());
        log.setCreateTime(new Date());
        int update = tradeLogMapper.update(log);
        if(update != 1){
            LOGGER.error("自动对账异常", log);
            throw new ServiceException(9999,"对账数组不存在!");
        }
        return log;
    }
}
