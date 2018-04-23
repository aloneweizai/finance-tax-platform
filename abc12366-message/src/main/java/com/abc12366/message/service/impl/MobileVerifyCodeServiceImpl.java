package com.abc12366.message.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.config.ApplicationConfig;
import com.abc12366.message.mapper.db1.MessageSendLogMapper;
import com.abc12366.message.mapper.db1.PhoneCodeMapper;
import com.abc12366.message.mapper.db2.MsgUcUserRoMapper;
import com.abc12366.message.model.MessageSendLog;
import com.abc12366.message.model.PhoneCode;
import com.abc12366.message.model.PhoneExist;
import com.abc12366.message.model.bo.NeteaseTemplateResponseBO;
import com.abc12366.message.model.bo.UpyunErrorBO;
import com.abc12366.message.model.bo.UpyunMessageResponse;
import com.abc12366.message.model.bo.VerifyParam;
import com.abc12366.message.service.MobileVerifyCodeService;
import com.abc12366.message.service.SendMsgLogService;
import com.abc12366.message.util.CheckSumBuilder;
import com.abc12366.message.util.RandomNumber;
import com.abc12366.message.util.WeightFactorProduceStrategy;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse.SmsSendDetailDTO;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * User: liuguiyao<435720953@qq.com> Date: 2017-07-20 Time: 0:10
 */
@Service
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MobileVerifyCodeServiceImpl.class);

	@Autowired
	private ApplicationConfig config;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PhoneCodeMapper phoneCodeMapper;

	@Autowired
	private MessageSendLogMapper sendLogMapper;

	@Autowired
	private MsgUcUserRoMapper msgUcUserRoMapper;

	@Autowired
	private SendMsgLogService sendMsgLogService;

	@Override
	public void getCode(String type, String phone) throws IOException {
		// 五分钟之内重复获取验证码，就发送之前的那个
		Optional<String> code = getPriviousCode(phone);
		if (!code.isPresent()) {
			// 删除无效的验证码记录
			phoneCodeMapper.delete(phone);

			// 生成新都验证码信息写入表
			code = Optional.of(RandomNumber.getRandomNumber(MessageConstant.VERIFY_CODE_LENGTH));
			PhoneCode phoneCode = new PhoneCode();
			phoneCode.setId(Utils.uuid());
			phoneCode.setPhone(phone);
			phoneCode.setCode(code.get());
			phoneCode.setExpireDate(
					new Date(System.currentTimeMillis() + 1000 * MessageConstant.VERIFY_CODE_VALID_SECONDS));
			phoneCode.setType(type);
			phoneCodeMapper.insert(phoneCode);
		}

		// 根据轮询发送短信消息
		String channel = WeightFactorProduceStrategy.getInstance().getPartitionIdForTopic();
		LOGGER.info("短信发送通道[" + channel + "],内容:" + (type + code));
		if (MessageConstant.MSG_CHANNEL_ALI.equals(channel)) {
			sendAliYunMsg(phone, type, code.get(), MessageConstant.ALIYUNTEMP_YZM);
		} else if (MessageConstant.MSG_CHANNEL_YOUPAI.equals(channel)) {
			sendYoupaiTemplate(phone, type, code.get());
		} else {
			sendNeteaseTemplate(phone, type, code.get());
		}
	}

	@Override
	public void verify(VerifyParam verifyParam) {
		PhoneCode p = new PhoneCode();
		p.setPhone(verifyParam.getPhone().trim());
		p.setCode(verifyParam.getCode().trim());
		p.setExpireDate(new Date());
		p = phoneCodeMapper.selectOne(p);

		if (p == null) {
			throw new ServiceException(4202);
		}
		phoneCodeMapper.delete(p.getPhone());
	}

	/**
	 * 阿里云信息发送
	 *
	 * @param phone
	 *            手机号码
	 * @param type
	 *            短信类型
	 * @param msg
	 *            消息内容
	 * @param temCode
	 *            模板ID
	 * @return 是否发送成功
	 */
	@Override
	public boolean sendAliYunMsg(String phone, String type, String msg, String temCode) {
		try {
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");

			// 短信API产品名称（短信产品名固定，无需修改）
			final String product = "Dysmsapi";
			// 短信API产品域名（接口地址固定，无需修改）
			final String domain = "dysmsapi.aliyuncs.com";
			// 你的accessKeyId "LTAItz4dVk9FX02a"
			final String accessKeyId = SpringCtxHolder.getProperty("message.aliyun.accessid");
			// 你的accessKeySecret "m9FiMcm2nmh7gj9uXQIx0mzNcZQzN5"
			final String accessKeySecret = SpringCtxHolder.getProperty("message.aliyun.accesskey");
			// 初始化ascClient,暂时不支持多region（请勿修改）
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			SendSmsRequest request = new SendSmsRequest();
			// 使用post提交
			request.setMethod(MethodType.POST);
			// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,
			// 批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
			request.setPhoneNumbers(phone);

			request.setSignName(SpringCtxHolder.getProperty("message.aliyun.signname"));

			request.setTemplateCode(temCode);

			String sendMsg;

			if (MessageConstant.ALIYUNTEMP_YZM.equals(temCode)) {
				sendMsg = "{\"code\":\"" + msg + "\"}";
			} else {
				sendMsg = "{\"name\":\"" + msg + "\"}";
			}
			request.setTemplateParam(sendMsg);

			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			String bizId = sendSmsResponse.getBizId();
			// 请求成功
			if (sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
				messageLog(MessageConstant.MSG_CHANNEL_ALI, phone, type, msg, MessageConstant.SEND_MSG_STATUS_SUCCESS,
						MessageConstant.SEND_MSG_SUCCESS_CODE, MessageConstant.SEND_MSG_SUCCESS_CONTENT, bizId);
				return true;
			} else {
				messageLog(MessageConstant.MSG_CHANNEL_ALI, phone, type, msg, MessageConstant.SEND_MSG_STATUS_FAIL,
						sendSmsResponse.getCode(), sendSmsResponse.getMessage(), bizId);
				return false;
			}
		} catch (ClientException e) {
			messageLog(MessageConstant.MSG_CHANNEL_ALI, phone, type, msg, MessageConstant.SEND_MSG_STATUS_FAIL,
					"SYSTEM.EXCEPTION", e.getMessage());
			return false;
		}
	}

	@Override
	public void getRegisCode(String type, String phone) {
		PhoneExist user = msgUcUserRoMapper.selectPhoneExist(phone);
		if (user != null) {
			throw new ServiceException(4117);
		}
		try {
			getCode(type, phone);
		} catch (Exception e) {
			LOGGER.info("发送短信失败：{}", e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 调用网易短信服务接口请求头设置
	 *
	 * @return HttpHeaders请求头
	 */
	private HttpHeaders getHeader() {
		String nonce = Utils.uuid();
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(config.getAppSecret(), nonce, curTime);
		// 请求头设置
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("appKey", config.getAppKey());
		httpHeaders.add("appSecret", config.getAppSecret());
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
		httpHeaders.add("charset", "utf-8");
		httpHeaders.add("Nonce", nonce);
		httpHeaders.add("CurTime", curTime);
		httpHeaders.add("CheckSum", checkSum);
		return httpHeaders;
	}

	/**
	 * 网易发送短信
	 *
	 * @param phone
	 *            手机号
	 * @param type
	 *            验证码类型
	 * @param code
	 *            验证码
	 * @return 是否发送成功
	 * @throws IOException
	 */
	private boolean sendNeteaseTemplate(String phone, String type, String code) throws IOException {
		// 发送通知类短信接口地址
		String url = SpringCtxHolder.getProperty("message.netease.api.url") + "/sendtemplate.action";
		String templateId = SpringCtxHolder.getProperty("message.netease.templateid");
		// 调用网易接口请求头设置
		HttpHeaders httpHeaders = getHeader();
		// 调用网易接口请求体设置
		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("templateid", templateId);
		requestBody.add("mobiles", "['" + phone + "']");
		requestBody.add("params", "['" + type + "','" + code + "']");

		HttpEntity requestEntity = new HttpEntity(requestBody, httpHeaders);
		ResponseEntity neteaseResponse;
		try {
			neteaseResponse = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		} catch (Exception e) {
			return false;
		}
		if (neteaseResponse != null && neteaseResponse.getStatusCode().is2xxSuccessful() && neteaseResponse.hasBody()) {
			NeteaseTemplateResponseBO neteaseTemplateResponseBO = JSON
					.parseObject(String.valueOf(neteaseResponse.getBody()), NeteaseTemplateResponseBO.class);
			String respCode = neteaseTemplateResponseBO.getCode();
			String msg = neteaseTemplateResponseBO.getMsg();
			if ("200".equals(respCode)) {
				// 记日志
				messageLog("wy", phone, type, code, "1", respCode, "发送成功");
				return true;
			} else if ("416".equals(respCode) || "417".equals(respCode) || "419".equals(respCode)) {
				// 如果发送失败状态码是416、417、419中的一个，就将异常信息抛出给用户
				// 记日志
				messageLog("wy", phone, type, code, "4", respCode, msg);
				throw new ServiceException(respCode, msg);
			} else {
				// 记日志
				messageLog("wy", phone, type, code, "4", "4204", "网易短信发送通道异常");
				return false;
			}
		}
		// 记日志
		messageLog("wy", phone, type, code, "4", "4204", "网易短信发送通道异常");
		return false;
	}

	/**
	 * 五分钟之内重复获取验证码，就发送之前的那个
	 *
	 * @param phone
	 *            手机号
	 * @return 验证码
	 */
	private Optional<String> getPriviousCode(String phone) {
		PhoneCode p = new PhoneCode();
		p.setPhone(phone);
		p.setExpireDate(new Date());
		p = phoneCodeMapper.selectOne(p);
		return p != null && !StringUtils.isEmpty(p.getCode()) ? Optional.of(p.getCode()) : Optional.empty();
	}

	/**
	 * 发送又拍云短信
	 *
	 * @param phone
	 *            手机号
	 * @param type
	 *            验证码类型
	 * @param code
	 *            验证码
	 * @return 发送是否成功
	 * @throws IOException
	 */
	private boolean sendYoupaiTemplate(String phone, String type, String code) throws IOException {
		// 发送通知类短信接口地址
		String url = SpringCtxHolder.getProperty("message.upyun.send.url") + "/messages";
		// 调用又拍接口请求头设置
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
		httpHeaders.add("Authorization", SpringCtxHolder.getProperty("message.upyun.auth"));
		// 调用又拍接口请求体设置
		LinkedMultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("mobile", phone);
		requestBody.add("template_id", MessageConstant.MESSAGE_UPYUN_TEMPLATE_296);
		requestBody.add("vars", type + "|" + code);
		HttpEntity entity = new HttpEntity(requestBody, httpHeaders);
		ResponseEntity responseEntity;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		} catch (Exception e) {
			// 记日志
			MessageSendLog sendLog = new MessageSendLog(MessageConstant.MSG_CHANNEL_YOUPAI, phone, type, code,
					MessageConstant.SEND_MSG_STATUS_FAIL, MessageConstant.SEND_MSG_CHANNEL_ERROR_CODE,
					MessageConstant.SEND_MSG_CHANNEL_ERROR_YOUPAI);
			sendMsgLogService.insert(sendLog);
			throw new ServiceException(4204);
		}
		if (RestTemplateUtil.isExchangeSuccessful(responseEntity)) {
			try {
				UpyunMessageResponse umr = JSON.parseObject(String.valueOf(responseEntity.getBody()),
						UpyunMessageResponse.class);
				LOGGER.info("{}", umr);
				// 记日志
				MessageSendLog sendLog = new MessageSendLog(MessageConstant.MSG_CHANNEL_YOUPAI, phone, type, code,
						MessageConstant.SEND_MSG_STATUS_SUCCESS, MessageConstant.SEND_MSG_SUCCESS_CODE,
						MessageConstant.SEND_MSG_SUCCESS_CONTENT);
				sendMsgLogService.insert(sendLog);
			} catch (Exception e) {
				UpyunErrorBO response = JSON.parseObject(String.valueOf(responseEntity.getBody()), UpyunErrorBO.class);
				// 记日志
				MessageSendLog sendLog = new MessageSendLog(MessageConstant.MSG_CHANNEL_YOUPAI, phone, type, code,
						MessageConstant.SEND_MSG_STATUS_FAIL, response.getError_code(), response.getMessage());
				sendMsgLogService.insert(sendLog);
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 发送短信消息日志
	 *
	 * @param sendchanel
	 *            通道类型
	 * @param biztype
	 *            短信类型
	 * @param sendinfo
	 *            内容
	 * @param sendstatus
	 *            发送状态
	 * @param failcode
	 *            失败code
	 * @param failcause
	 *            失败原因
	 */
	private void messageLog(String sendchanel, String phone, String biztype, String sendinfo, String sendstatus,
			String failcode, String failcause) {
		MessageSendLog sendLog = new MessageSendLog();
		Date time = new Date();
		sendLog.setId(Utils.uuid());
		sendLog.setSendchanel(sendchanel);
		sendLog.setBiztype(biztype);
		sendLog.setSendinfo(sendinfo);
		sendLog.setSendtime(time);
		sendLog.setSendstatus(sendstatus);
		sendLog.setFailcode(failcode);
		sendLog.setFailcause(failcause);
		sendLog.setLogtime(time);
		sendLog.setPhone(phone);
		sendLogMapper.insert(sendLog);
	}

	/**
	 * 发送短信消息日志
	 *
	 * @param sendchanel
	 *            通道类型
	 * @param biztype
	 *            短信类型
	 * @param sendinfo
	 *            内容
	 * @param sendstatus
	 *            发送状态
	 * @param failcode
	 *            失败code
	 * @param failcause
	 *            失败原因
	 * @param bizId
	 *            发送流水
	 */
	private void messageLog(String sendchanel, String phone, String biztype, String sendinfo, String sendstatus,
			String failcode, String failcause, String bizId) {
		MessageSendLog sendLog = new MessageSendLog();
		Date time = new Date();
		sendLog.setId(Utils.uuid());
		sendLog.setSendchanel(sendchanel);
		sendLog.setBiztype(biztype);
		sendLog.setSendinfo(sendinfo);
		sendLog.setSendtime(time);
		sendLog.setSendstatus(sendstatus);
		sendLog.setFailcode(failcode);
		sendLog.setFailcause(failcause);
		sendLog.setLogtime(time);
		sendLog.setPhone(phone);
		sendLog.setBizId(bizId);
		sendLogMapper.insert(sendLog);
	}

	@Override
	public QuerySendDetailsResponse.SmsSendDetailDTO querySendDetails(String phone, String bizId, String logId,
			String sendDate) {
		try {
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");

			// 短信API产品名称（短信产品名固定，无需修改）
			final String product = "Dysmsapi";
			// 短信API产品域名（接口地址固定，无需修改）
			final String domain = "dysmsapi.aliyuncs.com";
			// 你的accessKeyId "LTAItz4dVk9FX02a"
			final String accessKeyId = SpringCtxHolder.getProperty("message.aliyun.accessid");
			// 你的accessKeySecret "m9FiMcm2nmh7gj9uXQIx0mzNcZQzN5"
			final String accessKeySecret = SpringCtxHolder.getProperty("message.aliyun.accesskey");
			// 初始化ascClient,暂时不支持多region（请勿修改）
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象
			QuerySendDetailsRequest request = new QuerySendDetailsRequest();
			// 必填-号码
			request.setPhoneNumber(phone);
			// 可选-调用发送短信接口时返回的BizId
			request.setBizId(bizId);
			// 必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
			request.setSendDate(sendDate);
			// 必填-页大小
			request.setPageSize(10L);
			// 必填-当前页码从1开始计数
			request.setCurrentPage(1L);
			// hint 此处可能会抛出异常，注意catch
			QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
			// 获取返回结果
			if (querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")
					&& querySendDetailsResponse.getSmsSendDetailDTOs() != null
					&& querySendDetailsResponse.getSmsSendDetailDTOs().size() > 0) {
				// 代表请求成功
				QuerySendDetailsResponse.SmsSendDetailDTO detailDTO = querySendDetailsResponse.getSmsSendDetailDTOs()
						.get(0);
				MessageSendLog sendLog = new MessageSendLog();
				sendLog.setId(logId);
				sendLog.setFailcode(detailDTO.getErrCode());
				sendLog.setLogtime(new Date());
				sendLog.setSendinfo(detailDTO.getContent());
				switch (detailDTO.getSendStatus().intValue()) {
				case 1:
					sendLog.setSendstatus("0");
					break;
				case 2:
					sendLog.setSendstatus("4");
					break;
				case 3:
					sendLog.setSendstatus("1");
					break;
				}
				sendMsgLogService.update(sendLog);
				return detailDTO;
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public QuerySendDetailsResponse msgQueryAli(String channel, String phone, String sendDate, int page, int size) {
		try {
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");

			// 短信API产品名称（短信产品名固定，无需修改）
			final String product = "Dysmsapi";
			// 短信API产品域名（接口地址固定，无需修改）
			final String domain = "dysmsapi.aliyuncs.com";
			// 你的accessKeyId "LTAItz4dVk9FX02a"
			final String accessKeyId = SpringCtxHolder.getProperty("message.aliyun.accessid");
			// 你的accessKeySecret "m9FiMcm2nmh7gj9uXQIx0mzNcZQzN5"
			final String accessKeySecret = SpringCtxHolder.getProperty("message.aliyun.accesskey");
			// 初始化ascClient,暂时不支持多region（请勿修改）
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象
			QuerySendDetailsRequest request = new QuerySendDetailsRequest();
			// 必填-号码
			request.setPhoneNumber(phone);

			// 必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
			request.setSendDate(sendDate);
			// 必填-页大小
			request.setPageSize((long) size);
			// 必填-当前页码从1开始计数
			request.setCurrentPage((long) page);
			// hint 此处可能会抛出异常，注意catch
			QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
			// 获取返回结果
			if (querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")
					&& querySendDetailsResponse.getSmsSendDetailDTOs() != null
					&& querySendDetailsResponse.getSmsSendDetailDTOs().size() > 0) {
				// 代表请求成功
				return querySendDetailsResponse;
			} else {
				throw new ServiceException(9998, "未查到相关记录!");
			}
		} catch (ClientException e) {
			e.printStackTrace();
			throw new ServiceException(9997, "第三方渠道短信发送内容查询异常!");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JSONObject msgQueryYp(String channel, String phone, String sendDate, int page, int size) {
		String date = sendDate.substring(0, 4)+"-"+sendDate.substring(4, 6)+"-"+sendDate.substring(6);
		String bg = date+" 00:00:00";
		String end = date+" 24:00:00";
		// 发送通知类短信接口地址
		String url = SpringCtxHolder.getProperty("message.upyun.send.url") + "/messages";

		url += "?mobile=" + phone + "&from=" + bg + "&to=" + end + "&page=" + page + "&per_page" + size;

		// 调用又拍接口请求头设置
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
		httpHeaders.add("Authorization", SpringCtxHolder.getProperty("message.upyun.auth"));
		// 调用又拍接口请求体设置
		LinkedMultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();

		HttpEntity entity = new HttpEntity(requestBody, httpHeaders);
		ResponseEntity responseEntity;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			if(responseEntity.getStatusCodeValue() == 200){
				return JSONObject.parseObject(responseEntity.getBody().toString());
			}else{
				throw new ServiceException(9997, "第三方渠道短信发送内容查询异常!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(9997, "第三方渠道短信发送内容查询异常!");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("20180313".substring(6));
	}
}
