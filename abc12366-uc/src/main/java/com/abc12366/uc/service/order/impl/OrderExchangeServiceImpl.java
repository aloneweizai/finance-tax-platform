package com.abc12366.uc.service.order.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.*;
import com.abc12366.uc.mapper.db1.*;
import com.abc12366.uc.mapper.db2.*;
import com.abc12366.uc.model.Dict;
import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.MessageSendBo;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.PointsLogBO;
import com.abc12366.uc.model.bo.PointsRuleBO;
import com.abc12366.uc.model.bo.SfExportBO;
import com.abc12366.uc.model.bo.SfImportBO;
import com.abc12366.uc.model.dzfp.DzfpGetReq;
import com.abc12366.uc.model.dzfp.Einvocie;
import com.abc12366.uc.model.dzfp.InvoiceXm;
import com.abc12366.uc.model.invoice.InvoiceDetail;
import com.abc12366.uc.model.order.*;
import com.abc12366.uc.model.order.bo.*;
import com.abc12366.uc.model.pay.RefundRes;
import com.abc12366.uc.model.pay.WxRefund;
import com.abc12366.uc.model.pay.WxRefundRsp;
import com.abc12366.uc.model.pay.bo.AliRefund;
import com.abc12366.uc.service.IMsgSendV2service;
import com.abc12366.uc.service.MessageSendUtil;
import com.abc12366.uc.service.PointsLogService;
import com.abc12366.uc.service.PointsRuleService;
import com.abc12366.uc.service.order.OrderExchangeService;
import com.abc12366.uc.service.order.TradeLogService;
import com.abc12366.uc.util.AliPayConfig;
import com.abc12366.uc.util.wx.SignUtil;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxMchConnectFactory;
import com.abc12366.uc.webservice.DzfpClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lijun <ljun51@outlook.com>
 * @date 2017-08-10 10:27 AM
 * @since 1.0.0
 */
@Service("orderExchangeService")
public class OrderExchangeServiceImpl implements OrderExchangeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderExchangeServiceImpl.class);
    @Autowired
    private OrderExchangeMapper orderExchangeMapper;

    @Autowired
    private OrderExchangeRoMapper orderExchangeRoMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Autowired
    private DictRoMapper dictRoMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRoMapper orderRoMapper;

    @Autowired
    private TradeLogRoMapper tradeLogRoMapper;

    @Autowired
    private InvoiceDetailMapper invoiceDetailMapper;

    @Autowired
    private InvoiceDetailRoMapper invoiceDetailRoMapper;

    @Autowired
    private PointsLogService pointsLogService;
    @Autowired
    private TradeLogService tradeLogService;

    @Autowired
    private ExpressCompRoMapper expressCompRoMapper;

    @Autowired
    private MessageSendUtil messageSendUtil;

    @Autowired
    private PointsRuleService pointsRuleService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private TradeRoMapper tradeRoMapper;
    
    @Autowired
	private IMsgSendV2service msgSendV2Service;

    @Transactional("db1TxManager")
    @Override
    public OrderExchange insert(ExchangeApplicationBO ra) {
        exchangeCheck(ra);
        OrderExchange data = new OrderExchange();
        BeanUtils.copyProperties(ra, data);
        List<OrderExchange> dataList = selectUndoneList(data.getOrderNo());
        if (dataList != null && dataList.size() > 0) {
            throw new ServiceException(4950);
        } else {
            String userId = Utils.getUserId();
            String id = Utils.uuid();
            data.setId(id);
            data.setUserId(userId);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            data.setCreateTime(now);
            data.setLastUpdate(now);
            data.setStatus("1");
            orderExchangeMapper.insert(data);

            // 插入订单日志
            insertLog(data.getOrderNo(), "1", userId, data.getUserRemark(), "1", id);
            // 更新订单状态
            //changeOrderStatus(data.getOrderNo());
        }
        return data;
    }

    /*private void changeOrderStatus(String orderNo) {
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setOrderStatus("6");
        order = orderRoMapper.selectOne(order);
        if (order != null) {
            order.setOrderStatus("10");
            order.setLastUpdate(new Date());
            orderMapper.update(order);

            //insertLog(order.getOrderNo(), "orderStatus", "10", Utils.getUserId(), "用户提交退换货申请","1");
        }
    }*/

    private void exchangeCheck(ExchangeApplicationBO ra) {
        ExchangeCompletedOrderBO bo = orderExchangeRoMapper.selectCompletedOrder(ra.getOrderNo());
        if (bo == null) { // 是否为已完成订单
            throw new ServiceException(4951);
        }
        if (!Utils.getUserId().equals(bo.getUserId())) {
            throw new ServiceException(4961);
        }
        if ("1".equals(ra.getType())) { // 换货
            if ("1".equals(bo.getIsExchange())) { // 是否为实物商品换货
                throw new ServiceException(4952, "订单中<" + bo.getName() + ">商品，不能换货");
            }
            // 是否在换货日期之内
            if (new Date().after(DateUtils.addDays(new Date(bo.getLastUpdate().getTime()), Constant
                    .ORDER_EXCHANGE_DAYS))) {
                throw new ServiceException(4953, "您的订单已超过" + Constant.ORDER_EXCHANGE_DAYS + "天的换货日，不支持换货");
            }
        } else { // 退货
            if ("1".equals(bo.getIsReturn())) { // 虚拟商品暂时不支持退货
                throw new ServiceException(4953, "订单中<" + bo.getName() + ">商品，不能退货");
            }
            // 是否在退货日期之内
            if (new Date().after(DateUtils.addDays(new Date(bo.getLastUpdate().getTime()), Constant.ORDER_BACK_DAYS))) {
                throw new ServiceException(4953, "您的订单已超过" + Constant.ORDER_BACK_DAYS + "天的退货日，不支持退货");
            }
            // 用户现有积分是否达到购买是赠送的积分
            if (bo.getPoints() != null && bo.getGiftPoints() != null && bo.getPoints() < bo.getGiftPoints()) {
                throw new ServiceException(4960);
            }
            // 查询发票申请是否结束状态
            List<ExchangeOrderInvoiceBO> dataList = orderExchangeRoMapper.selectOrderInvoice(ra.getOrderNo());
            if (dataList.size() > 0) {
                throw new ServiceException(4955);
            }
        }
    }

    @Transactional("db1TxManager")
    @Override
    public OrderExchange update(ExchangeApplicationBO data) {
        exchangeCheck(data);
        OrderExchange oe = orderExchangeRoMapper.selectById(data.getId());

        if (oe != null && "5".equals(oe.getStatus())) {
            //查询地址信息
            oe.setReason(data.getReason());
            oe.setUserRemark(data.getUserRemark());
            oe.setType(data.getType());
            oe.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            oe.setStatus("1");
            oe.setAdminRemark("");
            oe.setExpressNo("");
            oe.setExpressComp("");
            orderExchangeMapper.update(oe);

            // 插入订单日志
            insertLog(data.getOrderNo(), "1", Utils.getUserId(), data.getUserRemark(), "1", oe.getId());
        } else {
            throw new ServiceException(4962);
        }
        return oe;
    }

    @Override
    public List<SfExportBO> export(OrderExchangeExportBO data) {
        return orderExchangeRoMapper.export(data);
    }

    @Transactional("db1TxManager")
    @Override
    public void importJson(List<SfImportBO> dataList, HttpServletRequest request) {
        if (dataList.size() > 0) {
            for (SfImportBO data : dataList) {
                //查询退换货记录，2：审核通过
                OrderExchange exchange = orderExchangeRoMapper.selectByOrderNoAndStatus(data.getOrderNo());
                if (exchange != null) {
                    OrderExchange oe = new OrderExchange.Builder()
                            .orderNo(data.getOrderNo())
                            .status("3")
                            .build();
                    oe.setId(exchange.getId());
                    oe.setToExpressComp(data.getExpressComp());
                    oe.setToExpressNo(data.getExpressNo());
                    orderExchangeMapper.update(oe);
                    // 插入订单日志
                    insertLog(oe.getOrderNo(), "3", Utils.getAdminId(), oe.getAdminRemark(), "1", oe.getId());


                    //发送消息
                    Order order = orderRoMapper.selectByPrimaryKey(oe.getOrderNo());
                    if (order == null) {
                        LOGGER.warn("订单信息查询失败：{}", oe.getOrderNo());
                        throw new ServiceException(4102, "订单信息查询失败");
                    }
                    ExpressComp expressComp = expressCompRoMapper.selectByPrimaryKey(data.getExpressComp());
                    if (expressComp == null) {
                        LOGGER.warn("物流公司查询失败：{}", data.getExpressComp());
                        throw new ServiceException(4102, "物流公司查询失败");
                    }

                    //User user = userMapper.selectOne(order.getUserId());
                    //Message message = new Message();
                    //message.setBusinessId(order.getOrderNo());
                    //message.setType("SPDD");
                    String content = RemindConstant.EXCHANGE_DELIVER_GOODS_PREFIX.replaceAll("\\{#DATA.ORDER\\}", order.getOrderNo()).replaceAll("\\{#DATA.COMP\\}", expressComp.getCompName()).replaceAll("\\{#DATA.EXPRESSNO\\}", data.getExpressNo());
                    //message.setUrl("<a href=\"" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/orderback/exchange/" + oe.getId() + "/" + order.getOrderNo() + "\">" + MessageConstant.VIEW_DETAILS + "</a>");
                    //message.setContent(content);
                    //message.setUserId(order.getUserId());

                    Map<String, String> map = new HashMap<>();
                    //map.put("userId", user.getId());
                    //map.put("openId", user.getWxopenid());
                    map.put("first", "您好，您的订单已送出，请保持手机畅通，以便快递及时联系您！");
                    map.put("remark", "感谢您的使用。");
                    map.put("keyword1", order.getConsignee());
                    map.put("keyword2", order.getContactNumber());
                    map.put("keyword3", expressComp.getCompName());
                    map.put("keyword4", data.getExpressNo());
                    map.put("keyword5", order.getOrderNo());
                    String templateId = "lPhC6mRjGPBGSTq14Gwimpu61tvUA25OfmpxO4L8tas";
                    //messageSendUtil.sendMsg(request, user, message,map,templateId);
                    
                    MessageSendBo messageSendBo =new MessageSendBo();
                    messageSendBo.setType(MessageConstant.SYS_MESSAGE);
                    messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
                    messageSendBo.setBusinessId(order.getOrderNo());
                    messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/orderback/exchange/" + oe.getId() + "/" + order.getOrderNo() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
                    messageSendBo.setWebMsg(content);
                    messageSendBo.setPhoneMsg(content);
                    messageSendBo.setTemplateid(templateId);
                    messageSendBo.setDataList(map);
                    messageSendBo.setWxNoChargeVip(true);
                    messageSendBo.setMoblieNoChargeVip(true);
                    
                    List<String> userIds =new ArrayList<String>();
                    userIds.add(order.getUserId());
                    messageSendBo.setUserIds(userIds);
                    
                    msgSendV2Service.sendMsgV2(messageSendBo);
                    
                }else {
                    LOGGER.warn("只有审批通过的数据才能进行导入：{}", data.getOrderNo());
                    throw new ServiceException(4102, "只有审批通过的数据才能进行导入");
                }
            }
        }
    }

    @Transactional("db1TxManager")
    @Override
    public OrderExchange back(ExchangeBackBO data, HttpServletRequest request) throws Exception {
        OrderExchange oe = orderExchangeRoMapper.selectById(data.getId());

        if (oe != null) {
            // 更新退货状态：确认退货
            oe.setExpressNo(data.getExpressNo());
            oe.setExpressComp(data.getExpressComp());
            oe.setAdminRemark(data.getAdminRemark());
            oe.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            oe.setStatus("7");
            oe.setAdminConfirmRemark(data.getAdminConfirmRemark());
            orderExchangeMapper.update(oe);

            // 更新订单状态：已退单

            // 发票作废
            List<ExchangeOrderInvoiceBO> orderInvoiceBOList = orderExchangeRoMapper.selectInvoice(oe.getOrderNo());

            if (orderInvoiceBOList.size() > 0) {

                DzfpGetReq req = new DzfpGetReq();
                List<InvoiceXm> dataList = new ArrayList<>();
                for (ExchangeOrderInvoiceBO eoi : orderInvoiceBOList) {

                    if ("1".equals(eoi.getProperty())) { // 纸质发票
                        InvoiceDetail invoiceDetail = invoiceDetailRoMapper.selectByInvoiceNo(eoi.getInvoiceNo());
                        if (invoiceDetail != null) {
                            // 更新发票状态：3-已作废
                            invoiceDetail.setStatus("3");
                            invoiceDetail.setLastUpdate(new Date());
                            invoiceDetailMapper.update(invoiceDetail);
                        }
                    } else if ("2".equals(eoi.getProperty())) { // 电子发票
                        if ("1".equals(eoi.getName())) { // 个人发票
                            req.setGmf_mc(eoi.getNsrmc());
                        } else {
                            req.setGmf_mc(eoi.getNsrmc());
                            req.setGmf_nsrsbh(eoi.getNsrsbh());
                            req.setGmf_dzdh(eoi.getAddress());
                            req.setGmf_yhzh(eoi.getBank());
                            req.setGmf_sjh(eoi.getPhone());
                        }
                        req.setFpqqlsh(DateUtils.getFPQQLSH());
                        req.setYfp_dm(eoi.getInvoiceCode());
                        req.setYfp_hm(eoi.getInvoiceNo());
                        req.setKplx("1");
                        req.setZsfs("0");
                        req.setKpr(Utils.getAdminInfo().getNickname());
                        req.setHylx("0");

                        InvoiceXm xm = new InvoiceXm();
                        xm.setFphxz("0");
                        xm.setXmmc(selectFieldValue("invoicecontent", eoi.getContent()));
                        xm.setXmsl(-1.00);
                        xm.setTotalAmt(-eoi.getAmount());
                        dataList.add(xm);

                        req.setInvoiceXms(dataList);
                    }
                }
                Einvocie einvocie = (Einvocie) DzfpClient.doSender("DFXJ1001", req.tosendXml(), Einvocie.class);

                if ("0000".equals(einvocie.getReturnCode())) { // 更新作废状态
                    InvoiceDetail id = invoiceDetailRoMapper.selectByInvoiceNo(orderInvoiceBOList.get(0).getInvoiceNo());
                    if(id == null){
                        throw new ServiceException(4102,"查找发票详情错误");
                    }
                    id.setStatus("3");
                    id.setLastUpdate(new Date());
                    id.setSpUrl(einvocie.getSP_URL());
                    id.setPdfUrl(einvocie.getPDF_URL());
                    invoiceDetailMapper.update(id);
                } else {
                    throw new ServiceException(einvocie.getReturnCode(), einvocie.getReturnMessage());
                }
            }
            // 插入订单日志
            insertLog(oe.getOrderNo(), "7", Utils.getAdminId(), oe.getAdminConfirmRemark(), "1", oe.getId());
        }
        return oe;
    }

    @Transactional("db1TxManager")
    @Override
    public ResponseEntity refund(ExchangeRefundBO data, HttpServletRequest httpServletRequest) {
        OrderExchange oe = orderExchangeRoMapper.selectById(data.getId());
        // 退积分
//        ExchangeCompletedOrderBO eco = orderExchangeRoMapper.selectCompletedOrder(oe.getOrderNo());
//        if (eco != null && eco.getGiftPoints() < eco.getPoints()) {
//            PointsLogBO pointsLog = new PointsLogBO();
//            pointsLog.setRuleId(UCConstant.POINT_RULE_ORDER_RETURN_ID);
//            pointsLog.setOutgo(eco.getGiftPoints());
//            pointsLog.setUserId(eco.getUserId());
//            pointsLog.setRemark("用户退单");
//            pointsLog.setLogType("ORDER_BACK");
//            pointsLogService.insert(pointsLog);
//        } else {
//            throw new ServiceException(4960);
//        }

        //查询订单信息
        Order order = orderRoMapper.selectByPrimaryKey(oe.getOrderNo());
        //判断是RMB、积分
        if(order != null){
            if (data.getAmount() > order.getTotalPrice()) {
                LOGGER.info("退款金额不能大于订单成交总金额，{}", data.getAmount());
                throw new ServiceException(7150);
            }
            if ("RMB".equals(order.getTradeMethod())) {
                // 查询交易日志中支付成功的订单
                Trade tr = tradeRoMapper.selectOrderNo(order.getOrderNo());
                if(tr == null){
                    LOGGER.info("交易记录无法找到：{}",order.getOrderNo());
                    throw new ServiceException(4102,"交易记录无法找到");
                }
                if ("ALIPAY".equals(order.getPayMethod())) {
                    TradeLog log = new TradeLog();
                    log.setTradeNo(oe.getOrderNo());
                    log.setTradeStatus("1");
                    log.setPayMethod("ALIPAY");
                    List<TradeLog> logList = tradeLogRoMapper.selectList(log);

                    if (logList.size() > 0) {
                        for (int i = 0; i < logList.size(); i++) {
                            if (logList.get(i).getAmount() >= data.getAmount()) {
                                AliRefund refund = new AliRefund();
                                refund.setOut_trade_no(logList.get(i).getTradeNo());
                                refund.setTrade_no(logList.get(i).getAliTrandeNo());
                                refund.setRefund_amount(String.valueOf(data.getAmount()));
                                refund.setRefund_reason(data.getAdminRemark());
                                String out_request_no = log.getTradeNo() + "_" + logList.size();
                                refund.setOut_request_no(out_request_no);

                                try {

                                    //扣除订单获得的积分
                                    if(order.getGiftPoints() != null && order.getGiftPoints() != 0){
                                        insertPoints(order,0d,order.getGiftPoints());
                                    }

                                    // 插入订单日志-已完成
                                    insertLog(oe.getOrderNo(), "8", Utils.getAdminId(), "已完成退款", "1", oe.getId());

                                    //将订单状态改成已结束
                                    order.setOrderStatus("7");
                                    orderMapper.update(order);

                                    //支付宝退款
                                    AlipayClient alipayClient = AliPayConfig.getInstance();
                                    AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
                                    request.setBizContent(AliPayConfig.toCharsetJsonStr(refund));
                                    AlipayTradeRefundResponse response = alipayClient.execute(request);
                                    LOGGER.info("支付宝退款支付宝返回信息{}", JSON.toJSONString(response));
                                    if (response.isSuccess()) {

                                        JSONObject object = JSON.parseObject(response.getBody());
                                        RefundRes refundRes = JSON.parseObject(object.getString("alipay_trade_refund_response"), RefundRes.class);

                                        LOGGER.info("支付宝退款成功,插入退款流水记录");
                                        String tradeNo = DateUtils.getJYLSH();
                                        Trade trade = new Trade();
                                        trade.setOrderNo(oe.getOrderNo());
                                        trade.setTradeNo(tradeNo);
                                        Date date = new Date();
                                        trade.setCreateTime(date);
                                        tradeMapper.insert(trade);

                                        TradeLog tradeLog = new TradeLog();
                                        tradeLog.setTradeNo(tradeNo);
                                        tradeLog.setAliTrandeNo(refundRes.getTrade_no());
                                        tradeLog.setTradeStatus("1");
                                        tradeLog.setTradeType("2");
                                        tradeLog.setAmount(Double.parseDouble(refundRes.getRefund_fee()));
                                        tradeLog.setTradeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(refundRes.getGmt_refund_pay()));
                                        Timestamp now = new Timestamp(System.currentTimeMillis());
                                        tradeLog.setCreateTime(now);
                                        tradeLog.setLastUpdate(now);
                                        tradeLog.setPayMethod("ALIPAY");
                                        tradeLogService.insertTradeLog(tradeLog);

                                        oe.setStatus("8");
                                        oe.setRefundRemark(data.getRefundRemark());
                                        oe.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                                        orderExchangeMapper.update(oe);

                                        //查询会员特权-业务提醒
                                        String content = RemindConstant.REFUND_PREFIX + refundRes.getRefund_fee() + RemindConstant.REFUND_SUFFIX + order.getOrderNo();

                                        Map<String, String> map = new HashMap<>();
                                        map.put("first", "您好，欢迎使用财税平台");
                                        map.put("remark", "感谢使用财税平台，祝您生活愉快！");
                                        map.put("keyword1", content);
                                        map.put("keyword2", refundRes.getRefund_fee());
                                        map.put("keyword3", DateUtils.dateToStr(new Date()));
                                        String templateId = "NkWLcHrxI0it-LZm9yuFinPpSVJFtbUCDxyvxXSKsaM";

                                        //messageSendUtil.sendMsg(httpServletRequest, user, message,map,templateId);
                                        
                                        
                                        MessageSendBo messageSendBo =new MessageSendBo();
                                        messageSendBo.setType(MessageConstant.SYS_MESSAGE);
                                        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
                                        messageSendBo.setBusinessId(order.getOrderNo());
                                        messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/orderback/exchange/" + oe.getId() + "/" + order.getOrderNo() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
                                        messageSendBo.setWebMsg(content);
                                        messageSendBo.setPhoneMsg(content);
                                        messageSendBo.setTemplateid(templateId);
                                        messageSendBo.setDataList(map);
                                        
                                        List<String> userIds =new ArrayList<String>();
                                        userIds.add(order.getUserId());
                                        messageSendBo.setUserIds(userIds);
                                        
                                        msgSendV2Service.sendMsgV2(messageSendBo);
                                        return ResponseEntity.ok(Utils.kv("data", refundRes));
                                    } else {
                                        return ResponseEntity.ok(Utils.bodyStatus(9999, response.getSubMsg()));
                                    }
                                } catch (Exception e) {
                                    LOGGER.error("支付宝退款失败：", e);
                                    return ResponseEntity.ok(Utils.bodyStatus(9999, e.getMessage()));
                                }
                            }
                        }
                    }

                }else if ("WEIXIN".equals(order.getPayMethod())) {
                    TradeLog log = new TradeLog();
                    log.setTradeNo(oe.getOrderNo());
                    log.setTradeStatus("1");
                    log.setPayMethod("WEIXIN");
                    List<TradeLog> logList = tradeLogRoMapper.selectList(log);

                    if (logList.size() > 0) {
                        for (int i = 0; i < logList.size(); i++) {
                            if (logList.get(i).getAmount() >= data.getAmount()) {
                                WxRefund wxRefund = new WxRefund();
                                wxRefund.setTransaction_id(logList.get(i).getAliTrandeNo());
                                wxRefund.setOut_trade_no(logList.get(i).getTradeNo());
                                //商户退款单号
                                String tradeNo = DateUtils.getJYLSH();
                                wxRefund.setOut_refund_no(tradeNo);
                                int amount = (int)(data.getAmount()*100);
                                int price = (int)(order.getTotalPrice()*100);
                                wxRefund.setRefund_fee(String.valueOf(amount));
                                wxRefund.setTotal_fee(String.valueOf(price));
                                wxRefund.setNotify_url(SpringCtxHolder.getProperty("abc.mch_refund"));

                                //扣除订单获得的积分
                                if(order.getGiftPoints() != null && order.getGiftPoints() != 0){
                                    insertPoints(order,0d,order.getGiftPoints());
                                }

                                // 插入订单日志-已完成
                                insertLog(oe.getOrderNo(), "8", Utils.getAdminId(), "已完成退款", "1", oe.getId());

                                //将订单状态改成已结束
                                order.setOrderStatus("7");
                                orderMapper.update(order);

                                //微信退款
                                wxRefund.setAppid(SpringCtxHolder.getProperty("abc.appid")).setMch_id(SpringCtxHolder.getProperty("abc.mch_id"))
                                        .setNonce_str(SignUtil.getRandomString(30)).setSign(SignUtil.signKey(wxRefund));

                                WxRefundRsp wxrefundrsp = WxMchConnectFactory.post(WechatUrl.WXREFUND, null, wxRefund, WxRefundRsp.class);
                                LOGGER.info("微信退款返回信息{}", JSON.toJSONString(wxrefundrsp));
                                if ("SUCCESS".equals(wxrefundrsp.getReturn_code())) {
                                    if ("SUCCESS".equals(wxrefundrsp.getResult_code())) {
                                        LOGGER.info("微信退款成功,插入退款流水记录");
                                        Trade trade = new Trade();
                                        trade.setOrderNo(oe.getOrderNo());
                                        trade.setTradeNo(tradeNo);
                                        Date date = new Date();
                                        trade.setCreateTime(date);
                                        tradeMapper.insert(trade);

                                        TradeLog tradeLog = new TradeLog();
                                        tradeLog.setTradeNo(tradeNo);
                                        tradeLog.setAliTrandeNo(wxrefundrsp.getRefund_id());
                                        tradeLog.setTradeStatus("1");
                                        tradeLog.setTradeType("2");
                                        tradeLog.setAmount(Double.parseDouble(wxrefundrsp.getRefund_fee()));
                                        tradeLog.setTradeTime(date);
                                        Timestamp now = new Timestamp(System.currentTimeMillis());
                                        tradeLog.setCreateTime(now);
                                        tradeLog.setLastUpdate(now);
                                        tradeLog.setPayMethod("WEIXIN");
                                        tradeLogService.insertTradeLog(tradeLog);

                                        oe.setStatus("8");
                                        oe.setRefundRemark(data.getRefundRemark());
                                        oe.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                                        orderExchangeMapper.update(oe);
                                        String content = RemindConstant.REFUND_PREFIX + wxrefundrsp.getRefund_fee() + RemindConstant.REFUND_SUFFIX + order.getOrderNo();

                                        Map<String, String> map = new HashMap<>();
                                        map.put("first", "您好，欢迎使用财税平台");
                                        map.put("remark", "感谢使用财税平台，祝您生活愉快！");
                                        map.put("keyword1", content);
                                        map.put("keyword2", wxrefundrsp.getRefund_fee());
                                        map.put("keyword3", DateUtils.dateToStr(new Date()));
                                        String templateId = "NkWLcHrxI0it-LZm9yuFinPpSVJFtbUCDxyvxXSKsaM";

                                        MessageSendBo messageSendBo =new MessageSendBo();
                                        messageSendBo.setType(MessageConstant.SYS_MESSAGE);
                                        messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
                                        messageSendBo.setBusinessId(order.getOrderNo());
                                        messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/orderback/exchange/" + oe.getId() + "/" + order.getOrderNo() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
                                        messageSendBo.setWebMsg(content);
                                        messageSendBo.setPhoneMsg(content);
                                        messageSendBo.setTemplateid(templateId);
                                        messageSendBo.setDataList(map);

                                        List<String> userIds =new ArrayList<>();
                                        userIds.add(order.getUserId());
                                        messageSendBo.setUserIds(userIds);

                                        msgSendV2Service.sendMsgV2(messageSendBo);
                                        return ResponseEntity.ok(Utils.kv("data", wxrefundrsp));
                                    }else{
                                        LOGGER.error("微信退款失败：", wxrefundrsp.getErr_code_des());
                                        return ResponseEntity.ok(Utils.bodyStatus(9999, wxrefundrsp.getReturn_msg()));
                                    }
                                }else {
                                    LOGGER.error("微信退款失败：", wxrefundrsp.getReturn_msg());
                                    return ResponseEntity.ok(Utils.bodyStatus(9999, wxrefundrsp.getReturn_msg()));
                                }

                            }
                        }
                    }

                } else {
                    throw new ServiceException(4956);
                }
            } else if("POINTS".equals(order.getTradeMethod())){
                //修改订单状态
                oe.setStatus("8");
                oe.setRefundRemark(data.getRefundRemark());
                oe.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                orderExchangeMapper.update(oe);

                // 插入订单日志-已退款
                insertLog(oe.getOrderNo(), "8", Utils.getAdminId(), "已完成退款。退款金额为："+data.getAmount(), "1", oe.getId());

                //将订单状态改成已结束
                order.setOrderStatus("7");
                orderMapper.update(order);

                LOGGER.info("支付宝退款成功,插入退款流水记录");
                String tradeNo = DateUtils.getJYLSH();
                Trade trade = new Trade();
                trade.setOrderNo(oe.getOrderNo());
                trade.setTradeNo(tradeNo);
                Date date = new Date();
                trade.setCreateTime(date);
                tradeMapper.insert(trade);

                TradeLog tradeLog = new TradeLog();
                tradeLog.setTradeNo(tradeNo);
                tradeLog.setAliTrandeNo(oe.getOrderNo());
                tradeLog.setTradeStatus("1");
                tradeLog.setTradeType("2");
                tradeLog.setAmount(data.getAmount());
                tradeLog.setCreateTime(date);
                tradeLog.setLastUpdate(date);
                tradeLog.setPayMethod("POINTS");
                tradeLogService.insertTradeLog(tradeLog);
                //退积分
                insertPoints(order, data.getAmount(),0);

            } else {
                throw new ServiceException(4957);
            }
        }
        return ResponseEntity.ok(Utils.kv());
    }


    /**
     * 插入积分日志
     *
     * @param orderBO 订单对象
     */
    private void insertPoints(Order orderBO,Double amount,int outgo) {
        //如果积分规则为空则返回
        PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_ORDER_RETURN_CODE);
        if (pointsRuleBO == null) {
            return;
        }
        PointsLogBO pointsLog = new PointsLogBO();
        pointsLog.setUserId(orderBO.getUserId());
        pointsLog.setRuleId(pointsRuleBO.getId());
        pointsLog.setId(Utils.uuid());
        //成交总积分 - 赠送积分
        pointsLog.setIncome(amount.intValue());
        pointsLog.setOutgo(outgo);
        pointsLog.setRemark("用户退单- 订单号：" + orderBO.getOrderNo());
        pointsLog.setLogType("ORDER_EXCHANGE");
        pointsLogService.insertNoVip(pointsLog);
    }

    //@Transactional("db1TxManager")
    @Override
    public void automaticReceipt() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        OrderExchange oe = new OrderExchange.Builder()
                .status("3")
                .lastUpdate(now)
                .build();
        List<OrderExchange> dataList = orderExchangeRoMapper.selectList(oe);
        // 更新退换货状态
        dataList.stream().filter(data -> data.getLastUpdate().after(DateUtils.addDays(new Date(), -Constant
                .ORDER_EXCHANGE_RECEIPT_DAYS))).forEach(data -> {
            data.setStatus("4");
            data.setLastUpdate(now);
            orderExchangeMapper.update(data);

            // 更新订单状态
            // 插入订单日志
            insertLog(data.getOrderNo(), "4", "", "系统自动完成收货", "1", data.getId());
        });
    }

    @Override
    public List<ExchangeOrderInvoiceBO> selectInvoice(String orderNo) {
        return orderExchangeRoMapper.selectInvoice(orderNo);
    }

    @Transactional("db1TxManager")
    @Override
    public OrderExchange receive(String id) {
        OrderExchange oe = orderExchangeRoMapper.selectById(id);
        oe.setStatus("4");
        oe.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        orderExchangeMapper.update(oe);

        // 插入订单日志
        insertLog(oe.getOrderNo(), "4", Utils.getUserId(), "用户确认收货", "1", id);
        return oe;
    }

    @Override
    public Integer selectTodoListCount(String status) {
        return orderExchangeRoMapper.selectTodoListCount(status);
    }

    @Override
    public List<OrderExchange> selectList(OrderExchange oe, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        return orderExchangeRoMapper.selectList(oe);
    }

    @Override
    public List<OrderExchange> selectListForFinance(OrderExchange oe, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        return orderExchangeRoMapper.selectListForFinance(oe);
    }

    @Transactional("db1TxManager")
    @Override
    public OrderExchange disagree(ExchangeAdminBO data, HttpServletRequest request) {
        OrderExchange oe = orderExchangeRoMapper.selectById(data.getId());

        if (oe != null) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            oe.setAdminRemark(data.getAdminRemark());
            oe.setLastUpdate(now);
            oe.setStatus("5");
            orderExchangeMapper.update(oe);

            // 插入订单日志
            insertLog(oe.getOrderNo(), "5", Utils.getAdminId(), oe.getAdminRemark(), "1", data.getId());

            //发送消息
            Order order = orderRoMapper.selectByPrimaryKey(oe.getOrderNo());
            if (order == null) {
                LOGGER.warn("订单信息查询失败：{}", oe.getOrderNo());
                throw new ServiceException(4102, "订单信息查询失败");
            }

            //User user = userMapper.selectOne(order.getUserId());
            //服务类型：1-换货 2-退货
            //Message message = new Message();
            String content = "";
            if ("1".equals(oe.getType())) {
                content = RemindConstant.EXCHANGE_CHECK_REFUSE.replaceAll("\\{#DATA.ORDER\\}", order.getOrderNo());
            }
            else if ("2".equals(oe.getType())) {
                content = RemindConstant.RETREAT_CHECK_REFUSE.replaceAll("\\{#DATA.ORDER\\}", order.getOrderNo());
            }
            //message.setContent(content);
            //message.setBusinessId(oe.getOrderNo());
            //message.setBusiType(MessageConstant.SPDD);
            //message.setType(MessageConstant.SYS_MESSAGE);
            //message.setUrl("<a href=\"" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/orderback/exchange/" + oe.getId() + "/" + order.getOrderNo() + "\">" + MessageConstant.VIEW_DETAILS + "</a>");
            //message.setUserId(order.getUserId());
            Map<String, String> map = new HashMap<>();
            //map.put("userId", user.getId());
            //map.put("openId", user.getWxopenid());
            map.put("first", "您好，审核结果如下");
            map.put("remark", "感谢您的使用。");
            map.put("keyword1", data.getId());
            map.put("keyword2", content);
            String templateId = "W1udf26l5sI7OReFNlchAiGFbOV3z3dKoHb1MGSMVAc";
            //messageSendUtil.sendMsg(request, user, message,map,templateId);
            
            MessageSendBo messageSendBo =new MessageSendBo();
            messageSendBo.setType(MessageConstant.SYS_MESSAGE);
            messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
            messageSendBo.setBusinessId(order.getOrderNo());
            messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/orderback/exchange/" + oe.getId() + "/" + order.getOrderNo() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
            messageSendBo.setWebMsg(content);
            messageSendBo.setPhoneMsg(content);
            messageSendBo.setTemplateid(templateId);
            messageSendBo.setDataList(map);
            
            List<String> userIds =new ArrayList<String>();
            userIds.add(order.getUserId());
            messageSendBo.setUserIds(userIds);
            
            msgSendV2Service.sendMsgV2(messageSendBo);
        }
        return oe;
    }

    @Override
    public OrderExchange selectOne(String id) {
        return orderExchangeRoMapper.selectOne(id);
    }

    @Transactional("db1TxManager")
    @Override
    public OrderExchange agree(ExchangeAdminBO data, HttpServletRequest request) {
        OrderExchange oe = orderExchangeRoMapper.selectById(data.getId());

        if (oe != null) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            oe.setAdminRemark(data.getAdminRemark());
            oe.setLastUpdate(now);
            oe.setStatus("2");
            orderExchangeMapper.update(oe);

            // 插入订单日志
            insertLog(oe.getOrderNo(), "2", Utils.getAdminId(), oe.getAdminRemark(), "1", oe.getId());

            //发送消息
            Order order = orderRoMapper.selectByPrimaryKey(oe.getOrderNo());
            if (order == null) {
                LOGGER.warn("订单信息查询失败：{}");
                throw new ServiceException(4102, "订单信息查询失败");
            }
            //User user = userMapper.selectOne(order.getUserId());

            //Message message = new Message();
            String content = "";
            //服务类型：1-换货 2-退货
            if ("1".equals(oe.getType())) {
                content = RemindConstant.EXCHANGE_CHECK_ADOPT.replaceAll("\\{#DATA.ORDER\\}", order.getOrderNo());
            } else if ("2".equals(oe.getType())) {
                content = RemindConstant.RETREAT_CHECK_ADOPT.replaceAll("\\{#DATA.ORDER\\}", order.getOrderNo());
            }
            //web消息

            Map<String, String> map = new HashMap<>();
            //map.put("userId", user.getId());
            //map.put("openId", user.getWxopenid());
            map.put("first", "您好，审核结果如下");
            map.put("remark", "感谢您的使用。");
            map.put("keyword1", data.getId());
            map.put("keyword2", content);
            String templateId = "W1udf26l5sI7OReFNlchAiGFbOV3z3dKoHb1MGSMVAc";
            //messageSendUtil.sendMsg(request, user, message,map,templateId);
            
            MessageSendBo messageSendBo =new MessageSendBo();
            messageSendBo.setType(MessageConstant.SYS_MESSAGE);
            messageSendBo.setBusiType(MessageConstant.BUSI_TYPE_ORDER);
            messageSendBo.setBusinessId(order.getOrderNo());
            messageSendBo.setSkipUrl("<a href='" + SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/orderback/exchange/" + oe.getId() + "/" + order.getOrderNo() + "'>" + MessageConstant.VIEW_DETAILS + "</a>");
            messageSendBo.setWebMsg(content);
            messageSendBo.setPhoneMsg(content);
            messageSendBo.setTemplateid(templateId);
            messageSendBo.setDataList(map);
            
            List<String> userIds =new ArrayList<String>();
            userIds.add(order.getUserId());
            messageSendBo.setUserIds(userIds);
            
            msgSendV2Service.sendMsgV2(messageSendBo);
        }
        return oe;
    }

//    /**
//     * 发送短信公告方法
//     * @param request http
//     * @param content 内容
//     * @param user 用户
//     */
//    private void sendPhoneMessage(HttpServletRequest request, String content, User user) {
//        //发送短信
//        Map<String, String> maps = new HashMap<>();
//        maps.put("var", content);
//        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//        list.add(maps);
//
//        String accessToken = request.getHeader(Constant.APP_TOKEN_HEAD);
//        messageSendUtil.sendPhoneMessage(user.getPhone(),"625", list, accessToken);
//    }

    @Transactional("db1TxManager")
    @Override
    public OrderExchange confirm(ExchangeConfirmBO data) {
        OrderExchange oe = orderExchangeRoMapper.selectById(data.getId());

        if (oe != null) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            oe.setExpressNo(data.getExpressNo());
            oe.setExpressComp(data.getExpressComp());
            oe.setAdminRemark(data.getAdminRemark());
            oe.setLastUpdate(now);
            oe.setStatus("6");
            orderExchangeMapper.update(oe);

            // 插入订单日志
            insertLog(oe.getOrderNo(), "6", Utils.getAdminId(), oe.getAdminRemark(), "1", oe.getId());
        }
        return oe;
    }

    private List<OrderExchange> selectUndoneList(String orderNo) {
        return orderExchangeRoMapper.selectUndoneList(orderNo);
    }

    private String selectFieldValue(String fieldValue, String value) {
        Dict dict = new Dict();
        dict.setDictId(fieldValue);
        dict.setFieldValue(value);
        dict = dictRoMapper.selectOne(dict);
        return dict != null ? dict.getFieldKey() : "";
    }

    private void insertLog(String orderNo, String status, String userId, String remark, String logType, String exchangeId) {
        // 插入订单日志
        OrderLog ol = new OrderLog.Builder()
                .id(Utils.uuid())
                .orderNo(orderNo)
                .action(selectFieldValue("exchange_status", status))
                .createTime(new Timestamp(System.currentTimeMillis()))
                .createUser(userId)
                .remark(remark)
                .logType(logType)
                .build();
        ol.setExchangeId(exchangeId);
        orderLogMapper.insert(ol);
    }
    
    /*private void insertLog(String orderNo, String dictId, String status, String userId, String remark,String logType) {
        // 插入订单日志
        OrderLog ol = new OrderLog.Builder()
            .id(Utils.uuid())
            .orderNo(orderNo)
            .action(selectFieldValue(dictId, status))
            .createTime(new Timestamp(new Date().getTime()))
            .createUser(userId)
            .remark(remark)
            .logType(logType)
            .build();
        orderLogMapper.insert(ol);
    }*/
}
