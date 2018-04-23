package com.abc12366.uc.service.impl.pay;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.uc.model.bo.TradeBillBO;
import com.abc12366.uc.model.order.TradeLog;
import com.abc12366.uc.model.pay.WxRefund;
import com.abc12366.uc.model.pay.WxRefundRsp;
import com.abc12366.uc.model.pay.Wxrefundquery;
import com.abc12366.uc.model.pay.WxrefundqueryRsp;
import com.abc12366.uc.service.order.TradeLogService;
import com.abc12366.uc.service.pay.IWxPayService;
import com.abc12366.uc.util.wx.SignUtil;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxMchConnectFactory;
import com.alibaba.fastjson.JSONObject;

@Service
public class WxPayServiceImpl implements IWxPayService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WxPayServiceImpl.class);

	@Autowired
	private TradeLogService tradeLogService;

	@Override
	public WxRefundRsp doWxRefund(WxRefund wxrefund) {
		LOGGER.info("微信支付退款申请:{}", JSONObject.toJSONString(wxrefund));
		wxrefund.setAppid(SpringCtxHolder.getProperty("abc.appid")).setMch_id(SpringCtxHolder.getProperty("abc.mch_id"))
				.setNonce_str(SignUtil.getRandomString(30))
				.setNotify_url(SpringCtxHolder.getProperty("abc.mch_refund"))
				.setSign(SignUtil.signKey(wxrefund));

		WxRefundRsp wxrefundrsp = WxMchConnectFactory.post(WechatUrl.WXREFUND, null, wxrefund, WxRefundRsp.class);
		if ("SUCCESS".equals(wxrefundrsp.getReturn_code())) {
			if ("SUCCESS".equals(wxrefundrsp.getResult_code())) {
				LOGGER.info("微信退款申请成功,插入退款流水记录");
				TradeBillBO tradebillbo = new TradeBillBO();
				tradebillbo.setTradeNo(wxrefundrsp.getOut_refund_no());
				TradeLog tradeLog = tradeLogService.selectOne(tradebillbo);
				if (tradeLog == null) {
					tradeLog = new TradeLog();
					tradeLog.setTradeNo(wxrefundrsp.getOut_refund_no());
					tradeLog.setAliTrandeNo(wxrefundrsp.getRefund_id());// 存储微信退款单号
					tradeLog.setTradeStatus("1");
					tradeLog.setTradeType("2");
					tradeLog.setAmount(Double.parseDouble("-" + wxrefundrsp.getRefund_fee()) / 100);
					Timestamp now = new Timestamp(new Date().getTime());
					tradeLog.setTradeTime(now);
					tradeLog.setCreateTime(now);
					tradeLog.setLastUpdate(now);
					tradeLog.setPayMethod("WEIXIN");
					tradeLogService.insertTradeLog(tradeLog);
				} else {
					tradeLog.setAliTrandeNo(wxrefundrsp.getRefund_id());// 存储微信退款单号
					tradeLog.setTradeStatus("1");
					tradeLog.setTradeType("2");
					tradeLog.setAmount(Double.parseDouble("-" + wxrefundrsp.getRefund_fee()) / 100);
					Timestamp now = new Timestamp(new Date().getTime());
					tradeLog.setTradeTime(now);
					tradeLog.setLastUpdate(now);
					tradeLog.setPayMethod("WEIXIN");
					tradeLogService.update(tradeLog);
				}

			}
		}
		return wxrefundrsp;
	}

	@Override
	public WxrefundqueryRsp doWxRefundQuery(Wxrefundquery wxrefundquery) {
		wxrefundquery.setAppid(SpringCtxHolder.getProperty("abc.appid"))
				.setMch_id(SpringCtxHolder.getProperty("abc.mch_id")).setNonce_str(SignUtil.getRandomString(30))
				.setSign(SignUtil.signKey(wxrefundquery));
		
		WxrefundqueryRsp wxrefundqueryrsp = WxMchConnectFactory.post(WechatUrl.WXREFUNDQUERY, null, wxrefundquery, WxrefundqueryRsp.class);
		if ("SUCCESS".equals(wxrefundqueryrsp.getReturn_code())) {
			if ("SUCCESS".equals(wxrefundqueryrsp.getResult_code())) {
				TradeLog tradeLog = new TradeLog();
				tradeLog.setTradeNo(wxrefundqueryrsp.getOut_refund_no_0());
				tradeLog.setAliTrandeNo(wxrefundqueryrsp.getRefund_id_0());// 存储微信退款单号
				String sta = "1";
				if("REFUNDCLOSE".equals(wxrefundqueryrsp.getRefund_status_0())){
					sta = "3";
				}else if("CHANGE".equals(wxrefundqueryrsp.getRefund_status_0())){
					sta = "4";
				}else{
					sta = "0";
				}
				tradeLog.setTradeStatus(sta);
				tradeLog.setTradeType("2");
				tradeLog.setAmount(Double.parseDouble("-" + wxrefundqueryrsp.getRefund_fee_0()) / 100);
				Timestamp now = new Timestamp(new Date().getTime());
				tradeLog.setTradeTime(DateUtils.strToDate(wxrefundqueryrsp.getRefund_success_time_0(), "yyyy-MM-dd HH:mm:ss"));
				tradeLog.setLastUpdate(now);
				tradeLog.setPayMethod("WEIXIN");
//				tradeLogService.update(tradeLog);
			}
		}
		return wxrefundqueryrsp;
	}

}
