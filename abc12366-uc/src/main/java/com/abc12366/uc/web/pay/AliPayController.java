package com.abc12366.uc.web.pay;


import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.order.TradeLog;
import com.abc12366.uc.model.order.bo.TradeLogBO;
import com.abc12366.uc.model.pay.*;
import com.abc12366.uc.model.pay.bo.AliCodePay;
import com.abc12366.uc.model.pay.bo.AliRefund;
import com.abc12366.uc.service.order.OrderService;
import com.abc12366.uc.service.order.TradeLogService;
import com.abc12366.uc.util.AliPayConfig;
import com.abc12366.uc.util.QRCodeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 支付宝支付接口
 * @author zhushuai 2017-8-4
 *
 */
@Controller()
@RequestMapping(path = "/alipay")
public class AliPayController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AliPayController.class);
	@Autowired
	private TradeLogService tradeLogService;

	@Autowired
	private OrderService orderService;


	/**
	 * 支付宝支付接口,返回支付页面
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/payform")
	public ResponseEntity aliPayForm(@RequestBody AliPayReq payReq){
		try {
			LOGGER.info("支付宝网页支付接收信息{}",JSON.toJSONString(payReq));
			AlipayClient alipayClient = AliPayConfig.getInstance();
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			//alipayRequest.setReturnUrl(payReq.getReturn_url());
			//alipayRequest.setNotifyUrl(AliPayConfig.NOTIFY_URL);
			alipayRequest.setReturnUrl(payReq.getReturn_url());
			alipayRequest.setNotifyUrl(payReq.getNotify_url());
			alipayRequest.setBizContent(AliPayConfig.toCharsetJsonStr(payReq.getPayContent()));
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			System.out.println(result);
            return ResponseEntity.ok(Utils.kv("data", result));
		} catch (Exception e) {
			LOGGER.error("发起支付宝支付异常,原因:",e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "发起支付宝支付异常"));
		}
		
	}


	/**
	 * 支付宝支付接口,返回二维码
	 * @param payReq 支付内容
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/paycode")
	public ResponseEntity aliPayCode(@RequestBody AliCodePay payReq){
		try {
			//查询订单库存
            orderService.selectStock(payReq.getOut_trade_no());

			LOGGER.info("支付宝二维码支付接收信息{}",JSON.toJSONString(payReq));
			AlipayClient alipayClient = AliPayConfig.getInstance();
			AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
			request.setBizContent(AliPayConfig.toCharsetJsonStr(payReq));
			request.setNotifyUrl(payReq.getNotify_url());
			request.setReturnUrl(payReq.getReturn_url());
			AlipayTradePrecreateResponse response = alipayClient.execute(request);
			LOGGER.info("支付宝二维码支付支付宝返回信息{}",JSON.toJSONString(response));
			if(response.isSuccess()){
				PayCodeRsp payCodeRsp=new PayCodeRsp(response.getOutTradeNo(),QRCodeUtil.getCreatQRcodeString(response.getQrCode(), payReq.getQrCodeSize(), "bmp"));
				return ResponseEntity.ok(Utils.kv("data", payCodeRsp));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, response.getSubMsg()));
			}
		} catch (Exception e) {
			LOGGER.error("发起支付宝支付异常,原因:",e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "发起支付宝支付异常"));
		}
		
	}
	
	
	
	
	
	/**
	 * 支付宝交易结果查询
	 * @param payqueryReq 结果内容
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/alipayquery")
	public ResponseEntity aliPayQuery(PayqueryReq payqueryReq){
		try {
			AlipayClient alipayClient = AliPayConfig.getInstance();
			AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
			alipayRequest.setBizContent(AliPayConfig.toCharsetJsonStr(payqueryReq));
			AlipayTradeQueryResponse response = alipayClient.execute(alipayRequest);
			LOGGER.info("支付宝支付结果查询支付宝返回信息{}",JSON.toJSONString(response));
			if(response.isSuccess()){
				JSONObject object = JSON.parseObject(response.getBody());
				return ResponseEntity.ok(Utils.kv("data", 
						JSON.parseObject(object.getString("alipay_trade_query_response"), PayqueryRes.class)));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, response.getSubMsg()));
			}
		} catch (Exception e) {
			LOGGER.error("支付宝支付结果查询异常,原因:",e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "支付宝支付结果查询"));
		}
	}

	/**
	 * 订单交易结果查询
	 * @param payqueryReq 查询内容
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/orderpayquery")
	public ResponseEntity orderPayQuery(PayqueryReq payqueryReq){
        LOGGER.info("订单交易结果查询{}",payqueryReq);
        TradeLog data = new TradeLog();
        data.setAliTrandeNo(payqueryReq.getTrade_no());
        TradeLogBO tradeLog = tradeLogService.selectByAliNo(data);
        return ResponseEntity.ok(Utils.kv("data", tradeLog));
	}

	/**
	 * 支付宝退款
	 * @param aliRefund 退款内容
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/refund")
	public ResponseEntity aliPayRefund(@RequestBody AliRefund aliRefund){
		try {
			AlipayClient alipayClient = AliPayConfig.getInstance();
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			request.setBizContent(AliPayConfig.toCharsetJsonStr(aliRefund));
			AlipayTradeRefundResponse response = alipayClient.execute(request);
			LOGGER.info("支付宝退款支付宝返回信息{}",JSON.toJSONString(response));
			if(response.isSuccess()){
				
				JSONObject object = JSON.parseObject(response.getBody());
				RefundRes refundRes=JSON.parseObject(object.getString("alipay_trade_refund_response"), RefundRes.class);
				
				LOGGER.info("支付宝退款成功,插入退款流水记录");
				TradeLog tradeLog=new TradeLog();
//				tradeLog.setId(Utils.uuid());
				tradeLog.setTradeNo(refundRes.getOut_trade_no());
				tradeLog.setAliTrandeNo(refundRes.getTrade_no());
				tradeLog.setTradeStatus("1");
				tradeLog.setTradeType("2");
				tradeLog.setAmount(Double.parseDouble("-"+refundRes.getRefund_fee()));
				tradeLog.setTradeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(refundRes.getGmt_refund_pay()));
				Timestamp now = new Timestamp(new Date().getTime());
				tradeLog.setCreateTime(now);
				tradeLog.setLastUpdate(now);
				tradeLog.setPayMethod("ALIPAY");
				tradeLogService.insertTradeLog(tradeLog);
				
				return ResponseEntity.ok(Utils.kv("data", refundRes));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, response.getSubMsg()));
			}
		} catch (Exception e) {
			LOGGER.error("支付宝退款异常,原因:",e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "支付宝退款异常"));
		}
	}
	
	/**
	 * 退款结果查询
	 * @param out_trade_no 交易ID
	 * @param out_request_no 交易流水号
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/refund/{out_trade_no}/{out_request_no}")
	public ResponseEntity aliPayRefundQuery(@PathVariable("out_trade_no") String out_trade_no,
			@PathVariable("out_request_no") String out_request_no){
		try {
			AlipayClient alipayClient = AliPayConfig.getInstance();
			AlipayTradeFastpayRefundQueryRequest  request = new AlipayTradeFastpayRefundQueryRequest ();
			request.setBizContent(AliPayConfig.toCharsetJsonStr(new RefundQueryReq(out_trade_no,out_request_no)));
			AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
			LOGGER.info("支付宝退款结果查询支付宝返回信息{}",JSON.toJSONString(response));
			if(response.isSuccess()){
				JSONObject object = JSON.parseObject(response.getBody());
				return ResponseEntity.ok(Utils.kv("data", 
						JSON.parseObject(object.getString("alipay_trade_fastpay_refund_query_response"), RefundQueryRes.class)));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, response.getSubMsg()));
			}
		} catch (Exception e) {
			LOGGER.error("支付宝退款结果查询异常,原因:",e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "支付宝退款异常"));
		}
	}
	/**
	 * 交易关闭
	 * @param payqueryReq 关闭内容
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/alipayclose")
	public ResponseEntity aliPayClose(PayqueryReq payqueryReq){
		try {
			AlipayClient alipayClient = AliPayConfig.getInstance();
			AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
			alipayRequest.setBizContent(AliPayConfig.toCharsetJsonStr(payqueryReq));
			AlipayTradeCloseResponse response = alipayClient.execute(alipayRequest);
			if(response.isSuccess()){
				return ResponseEntity.ok(Utils.kv("data", response));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, response.getSubMsg()));
			}
		} catch (Exception e) {
			LOGGER.error("支付宝支付交易关闭异常,原因:",e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "支付宝支付交易关闭异常"));
		}
	}
	
	/**
	 * 交易取消
	 * @param payqueryReq 取消内容
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/alipaycancel")
	public ResponseEntity aliPayCancel(PayqueryReq payqueryReq){
		try {
			AlipayClient alipayClient = AliPayConfig.getInstance();
			AlipayTradeCancelRequest alipayRequest = new AlipayTradeCancelRequest();
			alipayRequest.setBizContent(AliPayConfig.toCharsetJsonStr(payqueryReq));
			AlipayTradeCancelResponse response = alipayClient.execute(alipayRequest);
			if(response.isSuccess()){
				return ResponseEntity.ok(Utils.kv("data", response));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, response.getSubMsg()));
			}
		} catch (Exception e) {
			LOGGER.error("支付宝支付交易取消异常,原因:",e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "支付宝支付交易取消异常"));
		}
	}
	
	/**
	 * 交易对账单
	 * @param bill_date 对账日期
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/alipaylis")
	public ResponseEntity aliPaylist(@RequestParam(value="bill_date",required=true) String bill_date){
		try {
			BillListReq billListReq=new BillListReq();
			billListReq.setBill_date(bill_date);
			AlipayClient alipayClient = AliPayConfig.getInstance();
			AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
			request.setBizContent(AliPayConfig.toCharsetJsonStr(billListReq));
			AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
				JSONObject object = JSON.parseObject(response.getBody());
				return ResponseEntity.ok(Utils.kv("data", 
						JSON.parseObject(object.getString("alipay_data_dataservice_bill_downloadurl_query_response"), BillListRes.class)));
			}else{
				return ResponseEntity.ok(Utils.bodyStatus(9999, response.getSubMsg()));
			}
		} catch (Exception e) {
			LOGGER.error("支付宝交易对账单地址查询异常,原因:",e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "支付宝交易对账单地址查询异常"));
		}
	}
	
}
