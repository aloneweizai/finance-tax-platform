package com.abc12366.uc.web.order;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.TradeBillBO;
import com.abc12366.uc.model.order.TradeLog;
import com.abc12366.uc.model.order.bo.TradeLogBO;
import com.abc12366.uc.service.order.TradeLogService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 交易日志控制类
 *
 * @author lizhongwei
 * @create 2017-06-08
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/tradelog", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class TradeLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeLogController.class);

    @Autowired
    private TradeLogService tradeLogService;

    /**
     * 交易日志列表查询
     */
    @GetMapping()
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "orderNo", required = false) String orderNo) {
        LOGGER.info("{},{},{}", orderNo, pageNum, pageSize);
        TradeLog tradeLog = new TradeLog();

        tradeLog.setTradeNo(orderNo);
        List<TradeLogBO> orderLogList = tradeLogService.selectBOList(tradeLog, pageNum, pageSize);
        PageInfo<TradeLogBO> pageInfo = new PageInfo<>(orderLogList);

        ResponseEntity re = ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
        LOGGER.info("{}", re);
        return re;
    }

    /**
     * 批量对账单
     */
    @PutMapping("/bill")
    public ResponseEntity bill(@Valid @RequestBody List<TradeBillBO> dataList) {
        LOGGER.info("{}", dataList);

        List<TradeBillBO> undoneList = tradeLogService.bill(dataList);

        ResponseEntity re = ResponseEntity.ok(Utils.kv("dataList", undoneList));
        LOGGER.info("{}", re);
        return re;
    }

    /**
     * 手工对账成功
     */
    @PutMapping("/valid/{id}")
    public ResponseEntity valid(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        TradeLog log = new TradeLog();
        log.setTradeNo(id);
        log.setCompareStatus("3");
        TradeLog data = tradeLogService.updateCompare(log);

        ResponseEntity re = ResponseEntity.ok(Utils.kv("data", data));
        LOGGER.info("{}", re);
        return re;
    }

    /**
     * 手工对账成功
     */
    @PutMapping("/invalid/{id}")
    public ResponseEntity invalid(@PathVariable("id") String id) {
        LOGGER.info("{}", id);

        TradeLog log = new TradeLog();
        log.setTradeNo(id);
        log.setCompareStatus("2");
        TradeLog data = tradeLogService.updateCompare(log);

        ResponseEntity re = ResponseEntity.ok(Utils.kv("data", data));
        LOGGER.info("{}", re);
        return re;
    }
}
