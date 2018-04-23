package com.abc12366.uc.web.pay;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.Date;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.pay.WxOrderQueryRsp;
import com.abc12366.uc.model.pay.WxPayOrder;
import com.abc12366.uc.model.pay.WxRefund;
import com.abc12366.uc.model.pay.WxRefundRsp;
import com.abc12366.uc.model.pay.WxpayOrderRsp;
import com.abc12366.uc.model.pay.Wxrefundquery;
import com.abc12366.uc.model.pay.WxrefundqueryRsp;
import com.abc12366.uc.service.pay.IWxPayService;
import com.abc12366.uc.util.QRCodeUtil;
import com.abc12366.uc.util.wx.MessageUtil;
import com.abc12366.uc.util.wx.SignUtil;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxMchConnectFactory;
import com.alibaba.fastjson.JSONObject;
import com.abc12366.uc.model.pay.WxDownloadbill;
/**
 * 微信支付
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping(path = "/wxpay", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class WxPayController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WxPayController.class);

	@Autowired
	private IWxPayService wxPayServiceImpl;

	/**
	 * 统一下单接口
	 * 
	 * @param wxpayorder
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/payorder")
	public ResponseEntity wxPayOrder(@Valid @RequestBody WxPayOrder wxpayorder) throws Exception {
		LOGGER.info("微信支付统一下单，收到信息:{}", JSONObject.toJSONString(wxpayorder));
		Date date = new Date();
		wxpayorder.setAppid(SpringCtxHolder.getProperty("abc.appid"))
				.setMch_id(SpringCtxHolder.getProperty("abc.mch_id")).setNonce_str(SignUtil.getRandomString(30))
				.setTime_start(DateUtils.getDateFormat(date, "yyyyMMddHHmmss"))
				.setTime_expire(DateUtils.getDateFormat(DateUtils.addHours(date, 2), "yyyyMMddHHmmss"))
				.setNotify_url(SpringCtxHolder.getProperty("abc.mch_rturl")).setSign(SignUtil.signKey(wxpayorder));

		WxpayOrderRsp wxpayorderrsp = WxMchConnectFactory.post(WechatUrl.PAYORDER, null, wxpayorder,
				WxpayOrderRsp.class);
		LOGGER.info("微信支付统一下单，微信返回信息:{}", JSONObject.toJSONString(wxpayorderrsp));
		if (wxpayorderrsp != null) {
			if ("SUCCESS".equals(wxpayorderrsp.getReturn_code())) {
				if ("SUCCESS".equals(wxpayorderrsp.getResult_code())) {
					if ("NATIVE".equals(wxpayorderrsp.getTrade_type())) {
						wxpayorderrsp
								.setCode_img(QRCodeUtil.getCreatQRcodeString(wxpayorderrsp.getCode_url(), 200, "bmp"));
					}
				} else {
					return ResponseEntity
							.ok(Utils.bodyStatus(wxpayorderrsp.getResult_code(), wxpayorderrsp.getErr_code_des()));
				}
			} else {
				return ResponseEntity
						.ok(Utils.bodyStatus(wxpayorderrsp.getReturn_code(), wxpayorderrsp.getReturn_msg()));
			}
			Map<String, Object> returnMap = wxpayorderrsp.getReturn();
			if("MWEB".equalsIgnoreCase(returnMap.get("trade_type").toString())){
				long timeStamp = System.currentTimeMillis();
				String nonce_str = SignUtil.getRandomString(30);
				StringBuffer buffer = new StringBuffer("appId=");
				buffer.append(SpringCtxHolder.getProperty("abc.appid"))
				.append("&nonceStr=").append(nonce_str)
				.append("&package=prepay_id=").append(returnMap.get("prepay_id").toString())
				.append("&signType=MD5").append("&timeStamp=").append(timeStamp);
				returnMap.put("nonce_str", nonce_str);
				returnMap.put("paySign", Utils.md5(buffer.toString() + "&key=" + SpringCtxHolder.getProperty("abc.mch_key")).toUpperCase());
				returnMap.put("timeStamp", timeStamp);
				return ResponseEntity.ok(Utils.kv("data", returnMap));
			}else{
				return ResponseEntity.ok(Utils.kv("data", returnMap));
			}
		} else {
			return ResponseEntity.ok(Utils.bodyStatus(9999, "微信支付下单异常!!"));
		}
	}

	/**
	 * 微信订单查询接口
	 * 
	 * @param transaction_id
	 * @param out_trade_no
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/wxorderquery")
	public ResponseEntity WxOrderQuery(@RequestParam(required = false) String transaction_id,
			@RequestParam(required = false) String out_trade_no) {
		if (StringUtils.isEmpty(transaction_id) && StringUtils.isEmpty(out_trade_no)) {
			return ResponseEntity.ok(Utils.bodyStatus(9999, "商家订单号和微信订单号不能同时为空!"));
		} else {
			com.abc12366.uc.model.pay.WxOrderQuery wxorderquery = new com.abc12366.uc.model.pay.WxOrderQuery();
			wxorderquery.setAppid(SpringCtxHolder.getProperty("abc.appid"))
					.setMch_id(SpringCtxHolder.getProperty("abc.mch_id")).setNonce_str(SignUtil.getRandomString(30))
					.setOut_trade_no(out_trade_no).setTransaction_id(transaction_id)
					.setSign(SignUtil.signKey(wxorderquery));
			WxOrderQueryRsp wxpayreturn = WxMchConnectFactory.post(WechatUrl.WXORDERQUERY, null, wxorderquery,
					WxOrderQueryRsp.class);
			if (wxpayreturn != null) {
				if ("SUCCESS".equals(wxpayreturn.getReturn_code())) {
					if ("SUCCESS".equals(wxpayreturn.getResult_code())) {
						return ResponseEntity.ok(Utils.kv("data", wxpayreturn.getReturn()));
					} else {
						return ResponseEntity
								.ok(Utils.bodyStatus(wxpayreturn.getResult_code(), wxpayreturn.getErr_code_des()));
					}
				} else {
					return ResponseEntity
							.ok(Utils.bodyStatus(wxpayreturn.getReturn_code(), wxpayreturn.getReturn_msg()));
				}
			} else {
				return ResponseEntity.ok(Utils.bodyStatus(9999, "微信支付订单查询异常!!"));
			}
		}
	}

	/**
	 * 微信支付退款申请
	 * 
	 * @param wxrefund
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/dowxrefund")
	public ResponseEntity doWxRefund(@Valid @RequestBody WxRefund wxrefund) {
		WxRefundRsp wxrefundrsp = wxPayServiceImpl.doWxRefund(wxrefund);
		if (wxrefundrsp != null) {
			if ("SUCCESS".equals(wxrefundrsp.getReturn_code())) {
				if ("SUCCESS".equals(wxrefundrsp.getResult_code())) {
					return ResponseEntity.ok(Utils.kv("data", wxrefundrsp.getReturn()));
				} else {
					return ResponseEntity
							.ok(Utils.bodyStatus(wxrefundrsp.getResult_code(), wxrefundrsp.getErr_code_des()));
				}
			} else {
				return ResponseEntity.ok(Utils.bodyStatus(wxrefundrsp.getReturn_code(), wxrefundrsp.getReturn_msg()));
			}
		} else {
			return ResponseEntity.ok(Utils.bodyStatus(9999, "微信支付退款申请异常!!"));
		}
	}
	
	/**
	 * 微信退款查询
	 * @param refund_id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/dowxrefundquery")
	public ResponseEntity doWxRefundQuery(@RequestParam String refund_id){
		Wxrefundquery wxrefundquery = new Wxrefundquery();
		wxrefundquery.setRefund_id(refund_id);
		WxrefundqueryRsp wxrefundqueryrsp = wxPayServiceImpl.doWxRefundQuery(wxrefundquery);
		if (wxrefundqueryrsp != null) {
			if ("SUCCESS".equals(wxrefundqueryrsp.getReturn_code())) {
				if ("SUCCESS".equals(wxrefundqueryrsp.getResult_code())) {
					return ResponseEntity.ok(Utils.kv("data", wxrefundqueryrsp.getReturn()));
				} else {
					return ResponseEntity
							.ok(Utils.bodyStatus(wxrefundqueryrsp.getResult_code(), wxrefundqueryrsp.getErr_code_des()));
				}
			} else {
				return ResponseEntity.ok(Utils.bodyStatus(wxrefundqueryrsp.getReturn_code(), wxrefundqueryrsp.getReturn_msg()));
			}
		} else {
			return ResponseEntity.ok(Utils.bodyStatus(9999, "微信支付退款查询异常!!"));
		}
	}
	
	
	/**
	 * 微信对账单下载
	 * @param bill_date
	 * @param httpresponse
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/wxdownloadbill")
	public ResponseEntity WxDownloadbill(@RequestParam String bill_date,HttpServletResponse httpresponse) throws Exception{
		WxDownloadbill wxdownloadbill = new WxDownloadbill();
		wxdownloadbill.setBill_date(bill_date);
		wxdownloadbill.setAppid(SpringCtxHolder.getProperty("abc.appid"));
		wxdownloadbill.setMch_id(SpringCtxHolder.getProperty("abc.mch_id"));
		wxdownloadbill.setNonce_str(SignUtil.getRandomString(30));
		wxdownloadbill.setSign(SignUtil.signKey(wxdownloadbill));
		
		String outputStr = MessageUtil.objToXml(wxdownloadbill);
		
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
        InputStream instream = new ClassPathResource("cer/apiclient_cert.p12").getInputStream();
        String mch_id = SpringCtxHolder.getProperty("abc.mch_id");
        try {
            keyStore.load(instream, mch_id.toCharArray());
        } finally {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, mch_id.toCharArray())
                .build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httpPost = new HttpPost(SpringCtxHolder.getProperty("abc.mch_url") + WechatUrl.WXDOWNBILL.uri);
            httpPost.setEntity(new ByteArrayEntity(outputStr.getBytes()));

            LOGGER.info("Request Body: {}", outputStr);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            BufferedOutputStream bos = new BufferedOutputStream(httpresponse.getOutputStream());
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                	httpresponse.setContentType("text/plain");
                    httpresponse.setHeader("Content-disposition","attachment; filename="+bill_date+".txt");
                	
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    
                    while ((text = bufferedReader.readLine()) != null) {
                    	bos.write(text.replaceAll("`", "").getBytes());
                    	bos.write("\n".getBytes());
                    }  
                }
            } finally {
                response.close();
                bos.close();
            }
        } finally {
            httpclient.close();
        }
		
		return null;
	}

}
