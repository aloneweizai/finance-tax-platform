package com.abc12366.uc.web.admin;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.service.admin.IAdminSpreadService;
import com.abc12366.uc.util.QRCodeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * 销售推广相关控制器
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(path = "/admin/spread", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AdminSpreadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminSpreadController.class);

	@Autowired
	private IAdminSpreadService adminSpreadService;

	/**
	 * 我的推广客户
	 * 
	 * @param recommend
	 *            工号
	 * @param pageNum
	 * @param pageSize
	 * @param username
	 *            用户名
	 * @param realname
	 *            真实姓名
	 * @param phone
	 *            电话
	 * @param nickname
	 *            昵称
	 * @param createTimeB
	 *            注册时间
	 * @return
	 */
	@GetMapping("/myconstomers")
	public ResponseEntity myConstomers(@RequestParam String recommend,@RequestParam(value = "recommendPhone", required = false) String recommendPhone,
			@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
			@RequestParam(required = false) String username, @RequestParam(required = false) String realname,
			@RequestParam(required = false) String phone, @RequestParam(required = false) String nickname,
			@RequestParam(required = false) String createTimeB,
			@RequestParam(required = false) String createTimeE) {
		LOGGER.info("查询我的客户信息:{},{},{},{},{},{},{}", recommend, username, realname, phone, nickname, createTimeB,createTimeE);
		Map<String, Object> map = new HashMap<>(16);
		map.put("recommend", recommend);
		map.put("recommendPhone", recommendPhone);
		map.put("username", username);
		map.put("realname", realname);
		map.put("phone", phone);
		map.put("nickname", nickname);
		map.put("createTimeB", createTimeB);
		map.put("createTimeE", createTimeE);
		List<Map<String, Object>> dataList = adminSpreadService.myConstomers(map, pageNum, pageSize);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(dataList);
		LOGGER.info("{}", JSONArray.toJSONString(pageInfo));
		return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
	}

	@SuppressWarnings("restriction")
	@PostMapping("/myspreadurl")
	public ResponseEntity mySpreadUrl(HttpServletRequest request, @RequestBody Map<String, String> body) {
		LOGGER.info("生成我的推广信息:{}", JSONObject.toJSONString(body));
	
		String adminUsername = Utils.getAdminInfo().getUsername();
		String url = body.get("url");
		if (StringUtils.isEmpty(url)) {
			return ResponseEntity.ok(Utils.bodyStatus(9999, "请求参数异常,请检查请求数据!"));
		}
			
		String spreadurl = SpringCtxHolder.getProperty("abc12366.api.url.uc") + "/extension.html?p="
				+ new sun.misc.BASE64Encoder().encode((adminUsername +"|"+ url).getBytes()).replaceAll("\r", "").replaceAll("\n", "");
		String codeIo = QRCodeUtil.getCreatQRcodeString(spreadurl, 200, "bmp");
		return ResponseEntity.ok(Utils.kv("qrcode", codeIo, "spreadurl", spreadurl));
	}

}
