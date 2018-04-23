package com.abc12366.uc.web.pay;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.bo.TradeBillBO;
import com.abc12366.uc.model.order.TradeLog;
import com.abc12366.uc.model.order.bo.OrderPayBO;
import com.abc12366.uc.model.pay.WxPayReturn;
import com.abc12366.uc.model.pay.WxrefundNotify;
import com.abc12366.uc.model.pay.WxrefundNotifyResult;
import com.abc12366.uc.service.order.OrderService;
import com.abc12366.uc.service.order.TradeLogService;
import com.abc12366.uc.util.AliPayConfig;
import com.abc12366.uc.util.wx.MessageUtil;
import com.abc12366.uc.util.wx.SignUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * 支付接口回调地址
 *
 * @author zhushuai 2017-8-4
 */
@Controller
@RequestMapping(path = "/payreturn")
public class PayReturnController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PayReturnController.class);

	@Autowired
	private TradeLogService tradeLogService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * uc支付宝回调信息签名校验
	 * 
	 * @param params
	 *            参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/validate")
	public ResponseEntity validate(@RequestBody Map<String, String> params) {
		LOGGER.info("验证回调信息签名:", JSON.toJSONString(params));
		try {
			return ResponseEntity.ok(Utils.kv("data", AliPayConfig.rsaCheckV1(params)));
		} catch (AlipayApiException e) {
			LOGGER.error("验证回调信息签名异常,原因:", e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "验证回调信息签名异常:" + e.getMessage()));
		}
	}

	/**
	 * 微信退款回调
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/wxrefund")
	public @ResponseBody String wxrefund(HttpServletRequest request) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			String text;
			while ((text = bufferedReader.readLine()) != null) {
				buffer.append(text);
			}
			LOGGER.info("微信退款回调信息:{}", buffer.toString());
			WxrefundNotify wxrefundnotify = JSON.parseObject(MessageUtil.xml2JSON(buffer.toString()),
					WxrefundNotify.class);

			if ("SUCCESS".equals(wxrefundnotify.getReturn_code())
					&& StringUtils.isNoneEmpty(wxrefundnotify.getReq_info())) {
				byte[] dstr = Base64.getDecoder().decode(wxrefundnotify.getReq_info());
				String key = Utils.md5(SpringCtxHolder.getProperty("abc.mch_key")).toLowerCase();
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding",new BouncyCastleProvider());
				SecretKeySpec secretkeyspec = new SecretKeySpec(key.getBytes(), "AES");
				cipher.init(Cipher.DECRYPT_MODE, secretkeyspec);
				String result = new String(cipher.doFinal(dstr));
				LOGGER.info("微信退款回调解密后信息:{}", result);
				
				WxrefundNotifyResult wxrefundnotifyresult = JSON.parseObject(MessageUtil.xml2JSON(result),WxrefundNotifyResult.class);
				
				TradeBillBO tradebillbo = new TradeBillBO();
				tradebillbo.setTradeNo(wxrefundnotifyresult.getOut_refund_no());
				tradebillbo.setAliTrandeNo(wxrefundnotifyresult.getRefund_id());
				TradeLog tradeLog = tradeLogService.selectOne(tradebillbo);
				String sta = "1";
				if("CHANGE".equals(wxrefundnotifyresult.getRefund_status())){
					sta = "4";
				}else if("REFUNDCLOSE".equals(wxrefundnotifyresult.getRefund_status())){
					sta = "3";
				}
				if(tradeLog!=null){
					tradeLog.setTradeStatus(sta);
					Timestamp now = new Timestamp(new Date().getTime());
					tradeLog.setTradeTime(DateUtils.strToDate(wxrefundnotifyresult.getSuccess_time(), "yyyy-MM-dd HH:mm:ss"));
					tradeLog.setLastUpdate(now);
					tradeLogService.update(tradeLog);
				}
				return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}

		} catch (Exception e) {
			LOGGER.error("微信退款回调异常:", e);
		}
		return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[WRONG]]></return_msg></xml>";
	}

	/**
	 * 微信支付回调
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/wxpay")
	public @ResponseBody String wxPayReturn(HttpServletRequest request) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			String text;
			while ((text = bufferedReader.readLine()) != null) {
				buffer.append(text);
			}
			LOGGER.info("微信支付回调信息:{}", buffer.toString());
			WxPayReturn wxpayreturn = JSON.parseObject(MessageUtil.xml2JSON(buffer.toString()), WxPayReturn.class);
			if (wxpayreturn != null) {
				if ("SUCCESS".equals(wxpayreturn.getReturn_code())) {
					// if ("SUCCESS".equals(wxpayreturn.getResult_code())) {
					String str = SignUtil.objectSort(wxpayreturn);
					String sgin = Utils.md5(str + "&key=" + SpringCtxHolder.getProperty("abc.mch_key")).toUpperCase();
					LOGGER.info("微信支付回调签名信息:{}", sgin);
					if (sgin.equals(wxpayreturn.getSign())) {
						TradeBillBO tradeBillBO = new TradeBillBO();
						tradeBillBO.setAliTrandeNo(wxpayreturn.getTransaction_id());
						TradeLog tradeLogUpdate = tradeLogService.selectOne(tradeBillBO);
						String tradeStatus = "SUCCESS".equals(wxpayreturn.getResult_code()) ? "1" : "4";
						if (tradeLogUpdate != null) {
							tradeLogUpdate.setTradeStatus(tradeStatus);
							tradeLogUpdate.setTradeType("1");
							tradeLogUpdate.setAmount(Double.parseDouble(wxpayreturn.getTotal_fee()) / 100);
							tradeLogUpdate.setTradeTime(
									new SimpleDateFormat("yyyyMMddHHmmss").parse(wxpayreturn.getTime_end()));
							Timestamp now = new Timestamp(new Date().getTime());
							tradeLogUpdate.setLastUpdate(now);
							tradeLogUpdate.setPayMethod("WEIXIN");
							tradeLogService.update(tradeLogUpdate);
						} else {
							TradeBillBO data = new TradeBillBO();
							data.setTradeNo(wxpayreturn.getOut_trade_no());
							TradeLog log = tradeLogService.selectOne(data);

							TradeLog tradeLog = new TradeLog();
							tradeLog.setTradeNo(wxpayreturn.getOut_trade_no());
							tradeLog.setAliTrandeNo(wxpayreturn.getTransaction_id());
							tradeLog.setTradeStatus(tradeStatus);
							tradeLog.setTradeType("1");
							tradeLog.setAmount(Double.parseDouble(wxpayreturn.getTotal_fee()) / 100);
							tradeLog.setTradeTime(
									new SimpleDateFormat("yyyyMMddHHmmss").parse(wxpayreturn.getTime_end()));
							Timestamp now = new Timestamp(new Date().getTime());
							tradeLog.setCreateTime(now);
							tradeLog.setLastUpdate(now);
							tradeLog.setPayMethod("WEIXIN");

							if (log != null) {
								tradeLogService.update(tradeLog);
								LOGGER.info("微信回调信息:插入支付流水记录成功，开始更新订单状态");
								OrderPayBO orderPayBO = new OrderPayBO();
								orderPayBO.setTradeNo(wxpayreturn.getOut_trade_no());
								orderPayBO.setIsPay(2);
								orderPayBO.setPayMethod("WEIXIN");
								orderService.paymentOrder(orderPayBO, "RMB", request);
								LOGGER.info("更新订单状态:{}", wxpayreturn.getOut_trade_no());
							} else {
								tradeLogService.insertTradeLog(tradeLog);
								LOGGER.info("微信回调信息:插入支付流水记录成功");
							}
						}
						if ("SUCCESS".equals(wxpayreturn.getResult_code())
								&& StringUtils.isNotEmpty(wxpayreturn.getAttach())) {
							new Thread(
									new CallBack(wxpayreturn.getAttach() + "?jylsh=" + wxpayreturn.getOut_trade_no()))
											.start();
						}
						return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
					} else {
						LOGGER.info("微信支付回调交易信息:校验签名失败");
					}
					// } else {
					// LOGGER.info("微信支付回调交易信息:{}",
					// wxpayreturn.getErr_code_des());
					// }
				} else {
					LOGGER.info("微信支付回调交易信息:{}", wxpayreturn.getReturn_msg());
				}
			} else {
				LOGGER.info("微信支付回调交易异常!");
			}
		} catch (Exception e) {
			LOGGER.error("微信支付回调异常:", e);
		}
		return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[WRONG]]></return_msg></xml>";
	}

	/**
	 * UC前段回调更新支付状态，订单状态
	 * 
	 * @param request
	 *            上下文
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/alipay")
	public ResponseEntity aliPayReturn(HttpServletRequest request) {
		LOGGER.info("{}", request);
		try {
			// Map<String, String> params = new HashMap<String, String>();
			// Map<String, String[]> requestParams = request.getParameterMap();
			// for (Iterator<String> iter = requestParams.keySet().iterator();
			// iter
			// .hasNext();) {
			// String name = (String) iter.next();
			// String[] values = (String[]) requestParams.get(name);
			// String valueStr = "";
			// for (int i = 0; i < values.length; i++) {
			// valueStr = (i == values.length - 1) ? valueStr + values[i]
			// : valueStr + values[i] + ",";
			// }
			// // 乱码解决，这段代码在出现乱码时使用
			// valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
			// params.put(name, valueStr);
			// }
			//
			// LOGGER.info("支付宝回调信息:", JSON.toJSONString(params));
			// boolean signVerified = AliPayConfig.rsaCheckV1(params); //
			// 调用SDK验证签名
			// if (signVerified) {// 验证成功
			LOGGER.info("支付宝回调信息:验证签名成功");
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("utf-8"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("utf-8"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("utf-8"), "UTF-8");

			String total_amount = new String(request.getParameter("total_amount").getBytes("utf-8"), "UTF-8");

			String date = new String(request.getParameter("gmt_payment").getBytes("utf-8"), "UTF-8");

			if (trade_status.equals("TRADE_FINISHED")) {// 交易结束

			} else if (trade_status.equals("TRADE_SUCCESS")) {// 交易成功,插入交易记录
				LOGGER.info("支付宝回调信息:交易成功,插入支付流水记录");

				TradeBillBO tradeBillBO = new TradeBillBO();
				tradeBillBO.setAliTrandeNo(trade_no);
				TradeLog tradeLogUpdate = tradeLogService.selectOne(tradeBillBO);
				if (tradeLogUpdate != null) {
					tradeLogUpdate.setTradeNo(out_trade_no);
					tradeLogUpdate.setTradeStatus("1");
					tradeLogUpdate.setTradeType("1");
					tradeLogUpdate.setAmount(Double.parseDouble(total_amount));
					tradeLogUpdate.setTradeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
					Timestamp now = new Timestamp(new Date().getTime());
					tradeLogUpdate.setLastUpdate(now);
					tradeLogUpdate.setPayMethod("ALIPAY");
					tradeLogService.update(tradeLogUpdate);
				} else {
					TradeBillBO data = new TradeBillBO();
					data.setTradeNo(out_trade_no);
					TradeLog log = tradeLogService.selectOne(data);
					if (log != null) {
						TradeLog tradeLog = new TradeLog();
						tradeLog.setTradeNo(out_trade_no);
						tradeLog.setAliTrandeNo(trade_no);
						tradeLog.setTradeStatus("1");
						tradeLog.setTradeType("1");
						tradeLog.setAmount(Double.parseDouble(total_amount));
						tradeLog.setTradeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
						Timestamp now = new Timestamp(new Date().getTime());
						tradeLog.setCreateTime(now);
						tradeLog.setLastUpdate(now);
						tradeLog.setPayMethod("ALIPAY");
						tradeLogService.update(tradeLog);
						LOGGER.info("支付宝回调信息:插入支付流水记录成功，开始更新订单状态");
						OrderPayBO orderPayBO = new OrderPayBO();
						orderPayBO.setTradeNo(out_trade_no);
						orderPayBO.setIsPay(2);
						orderPayBO.setPayMethod("ALIPAY");
						orderService.paymentOrder(orderPayBO, "RMB", request);
						LOGGER.info("更新订单状态:{}", out_trade_no);
					}

				}
			}
			return ResponseEntity.ok(Utils.kv("data", "OK"));
			// } else {// 验证失败
			// LOGGER.info("支付宝回调信息:验证签名失败");
			// return ("fail");
			// }

		} catch (Exception e) {
			LOGGER.error("支付宝回调处理异常,原因:", e);
			return ResponseEntity.ok(Utils.bodyStatus(9999, "支付宝回调处理异常,原因:" + e.getMessage()));
		}
	}

	class CallBack implements Runnable {
		private String url;

		public CallBack(String url) {
			this.url = url;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void run() {
			LOGGER.info("开始回调第三方地址:" + url);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Version", "1");
			HttpEntity requestEntity = new HttpEntity(httpHeaders);
			try {
				restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
			} catch (Exception e) {
				LOGGER.error("调第三方地址:", e);
			}

		}

	}
}
