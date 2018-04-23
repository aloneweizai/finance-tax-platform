package com.abc12366.message.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.model.MsgChannelSendLog;
import com.abc12366.message.model.bo.GetCodeParam;
import com.abc12366.message.model.bo.VerifyParam;
import com.abc12366.message.service.MobileVerifyCodeService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse.SmsSendDetailDTO;
import com.github.pagehelper.Page;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证码发送校验控制器 User: liuguiyao<435720953@qq.com> Date: 2017-07-20 Time: 0:08
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class MobileVerifyCodeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MobileVerifyCodeController.class);

	@Autowired
	private MobileVerifyCodeService moboleVerifyCodeService;

	/**
	 * 获取验证码接口
	 *
	 * @param getCodeParam
	 *            获取验证码参数
	 * @return ResponseEntity
	 * @throws IOException
	 */
	@PostMapping(path = "/getcode")
	public ResponseEntity getCode(@Valid @RequestBody GetCodeParam getCodeParam) throws IOException {
		LOGGER.info("{}", getCodeParam);
		moboleVerifyCodeService.getCode(getCodeParam.getType().trim(), getCodeParam.getPhone().trim());
		return ResponseEntity.ok(Utils.kv());
	}

	/**
	 * 验证码校验接口
	 *
	 * @param verifyParam
	 *            验证码校验参数
	 * @return ResponseEntity
	 * @throws IOException
	 */
	@PostMapping(path = "/verify")
	public ResponseEntity verify(@Valid @RequestBody VerifyParam verifyParam) throws IOException {
		LOGGER.info("{}", verifyParam);
		moboleVerifyCodeService.verify(verifyParam);
		return ResponseEntity.ok(Utils.kv());
	}

	/**
	 * 获取注册验证码接口（校验号码是否已被注册）
	 *
	 * @param getCodeParam
	 *            获取注册验证码参数
	 * @return ResponseEntity
	 * @throws IOException
	 */
	@PostMapping(path = "/regis/code")
	public ResponseEntity getRegisCode(@Valid @RequestBody GetCodeParam getCodeParam) throws IOException {
		LOGGER.info("{}", getCodeParam);
		moboleVerifyCodeService.getRegisCode(getCodeParam.getType().trim(), getCodeParam.getPhone().trim());
		return ResponseEntity.ok(Utils.kv());
	}

	/**
	 * 调用阿里接口查询手机短信发送情况
	 * 
	 * @param phone
	 *            手机号码
	 * @param bizId
	 *            短信流水
	 * @param logId
	 *            日志id
	 * @param sendDate
	 *            发送日期
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/ali/detail")
	public ResponseEntity querySendDetails(@RequestParam String phone, @RequestParam String bizId,
			@RequestParam String logId, @RequestParam String sendDate) {
		LOGGER.info("调用阿里短信发送情况查询接口：{}:{}:{}:{}", phone, bizId, logId, sendDate);
		QuerySendDetailsResponse.SmsSendDetailDTO detail = moboleVerifyCodeService.querySendDetails(phone, bizId, logId,
				sendDate);
		return ResponseEntity.ok(Utils.kv("data", detail));
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(path = "/channel/msgquery")
	public ResponseEntity msgQuery(@RequestParam String phone, @RequestParam String sendDate,
			@RequestParam String channel, @RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
		LOGGER.info("调用第三方渠道短信查询：{}:{}:{}", channel, phone, sendDate, page, size);
		List<MsgChannelSendLog> channelSendLogs = new ArrayList<MsgChannelSendLog>();
		String total = "0";
		if ("ali".equals(channel)) {
			QuerySendDetailsResponse detailsResponse = moboleVerifyCodeService.msgQueryAli(channel, phone, sendDate,
					page, size);
			total = detailsResponse.getTotalCount();
			for (SmsSendDetailDTO detailDTO : detailsResponse.getSmsSendDetailDTOs()) {
				MsgChannelSendLog sendLog = new MsgChannelSendLog(detailDTO.getTemplateCode(), detailDTO.getPhoneNum(),
						String.valueOf(detailDTO.getSendStatus()), detailDTO.getContent(), detailDTO.getSendDate(),
						detailDTO.getReceiveDate(), "ali");
				channelSendLogs.add(sendLog);
			}
		} else if ("yp".equals(channel)) {
			JSONObject result = moboleVerifyCodeService.msgQueryYp(channel, phone, sendDate, page, size);
			total = result.getString("total");
			JSONArray array = result.getJSONArray("messages");
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				String stas;
				if ("processing".equals(obj.getString("status"))) {
					stas = "1";
				} else if ("success".equals(obj.getString("status"))) {
					stas = "3";
				} else {
					stas = "2";
				}
				MsgChannelSendLog sendLog = new MsgChannelSendLog(obj.getString("id"), obj.getString("mobile"), stas,
						obj.getString("content"),
						(StringUtils.isEmpty(obj.getString("created_at")) ? "-"
								: obj.getString("created_at").replace("T", " ").replace(".000Z", "")),
						(StringUtils.isEmpty(obj.getString("sent_at")) ? "-"
								: obj.getString("sent_at").replace("T", " ").replace(".000Z", "")),
						"yp");
				channelSendLogs.add(sendLog);
			}
		} else {
			return ResponseEntity.ok(Utils.kv(Utils.bodyStatus(9999, "查询异常,未找到相应的短信渠道!")));
		}
		return ResponseEntity.ok(Utils.kv("dataList", channelSendLogs, "total", total));

	}
}
