package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.order.TradeLog;
import org.apache.ibatis.annotations.Param;

/**
 * TradeLogMapper数据库操作接口类
 **/

public interface TradeLogMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(TradeLog record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int update(TradeLog record);
    
    int insertTradeLog(TradeLog record);


}