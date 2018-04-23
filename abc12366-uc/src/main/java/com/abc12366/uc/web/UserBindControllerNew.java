package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.DzsbRegisterStat;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.model.tdps.TY21Xml2Object;
import com.abc12366.uc.service.UserBindServiceNew;
import com.abc12366.uc.wsbssoa.response.HngsNsrLoginResponse;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户绑定办税身份控制器类，以常规JSON形式返回数据
 *
 * @author liuguiyao 435720953@qq.com
 * @date 2017-07-25
 */
@RestController
@RequestMapping("/v2")
public class UserBindControllerNew {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserBindControllerNew.class);

	@Autowired
	private UserBindServiceNew userBindService;

	/**
	 * 用户绑定纳税人（电子申报）
	 *
	 * @param userDzsbInsertBO
	 *            纳税人信息
	 * @param request
	 *            HttpServletRequest
	 * @return 纳税人信息
	 * @throws Exception
	 *             访问网络、解包异常
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/bind/dzsb")
	public ResponseEntity userDzsbBind(@Valid @RequestBody UserDzsbInsertBO userDzsbInsertBO,
			HttpServletRequest request) throws Exception {
		LOGGER.info("{}:{}", userDzsbInsertBO, request);
		UserDzsbBO userDzsb = userBindService.dzsbBind(userDzsbInsertBO, request);
		LOGGER.info("{}", userDzsb);

		return ResponseEntity.ok(Utils.kv("data", userDzsb));
	}

	/**
	 * 用户绑定纳税人（电子申报）
	 *
	 * @param userDzsbInsertBO
	 *            纳税人信息
	 * @param request
	 *            HttpServletRequest
	 * @return 纳税人信息
	 * @throws Exception
	 *             访问网络、解包异常
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/cszj/bind/dzsb")
	public ResponseEntity cszjuserDzsbBind(@Valid @RequestBody UserDzsbInsertBO userDzsbInsertBO,
			HttpServletRequest request) throws Exception {
		LOGGER.info("{}:{}", userDzsbInsertBO, request);
		UserDzsbBO userDzsb = userBindService.cszjdzsbBind(userDzsbInsertBO, request);
		LOGGER.info("{}", userDzsb);

		return ResponseEntity.ok(Utils.kv("data", userDzsb));
	}

	/**
	 * 绑定湖南国税用户
	 *
	 * @param userHngsInsertBO
	 *            湖南国税用户信息
	 * @param request
	 *            HttpServletRequest
	 * @return 国税用户登陆信息
	 * @throws Exception
	 *             网络异常
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/bind/hngs")
	public ResponseEntity userHngsBind(@Valid @RequestBody UserHngsInsertBO userHngsInsertBO,
			HttpServletRequest request) throws Exception {
		LOGGER.info("{}:{}", userHngsInsertBO, request);
		UserHngsBO userHngs = userBindService.hngsBind(userHngsInsertBO, request);

		return ResponseEntity.ok(Utils.kv("data", userHngs));
	}

	/**
	 * 绑定湖南国税用户
	 *
	 * @param userHngsInsertBO
	 *            湖南国税用户信息
	 * @param request
	 *            HttpServletRequest
	 * @return 国税用户登陆信息
	 * @throws Exception
	 *             网络异常
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/cszj/bind/hngs")
	public ResponseEntity cszjuserHngsBind(@Valid @RequestBody UserHngsInsertBO userHngsInsertBO,
			HttpServletRequest request) throws Exception {
		LOGGER.info("{}:{}", userHngsInsertBO, request);
		UserHngsBO userHngs = userBindService.cszjhngsBind(userHngsInsertBO, request);

		return ResponseEntity.ok(Utils.kv("data", userHngs));
	}

	/**
	 * 电子申报纳税人登录绑定
	 *
	 * @param login
	 *            登录信息
	 * @param request
	 *            HttpServletRequest
	 * @return 纳税人信息
	 * @throws Exception
	 *             访问网络、解包异常
	 */
	@PostMapping(path = "/nsrlogin/shb")
	public ResponseEntity nsrLoginShb(@Valid @RequestBody NsrLogin login, HttpServletRequest request) throws Exception {
		LOGGER.info("{}", login);
		TY21Xml2Object loginResponse = userBindService.nsrLogin(login, request);
		return ResponseEntity.ok(Utils.kv("data", loginResponse));
	}

	/**
	 * 电子申报纳税人登录绑定
	 *
	 * @param login
	 *            登录信息
	 * @param request
	 *            HttpServletRequest
	 * @return 纳税人信息
	 * @throws Exception
	 *             访问网络、解包异常
	 */
	@PostMapping(path = "/cszj/nsrlogin/shb")
	public ResponseEntity cszjnsrLoginShb(@Valid @RequestBody NsrLogin login, HttpServletRequest request)
			throws Exception {
		LOGGER.info("{}", login);
		TY21Xml2Object loginResponse = userBindService.cszjnsrLoginShb(login, request);
		return ResponseEntity.ok(Utils.kv("data", loginResponse));
	}

	/**
	 * 电子税局纳税人登录绑定
	 *
	 * @param login
	 *            登陆信息
	 * @param request
	 *            HttpServletRequest
	 * @return 用户登陆信息
	 */
	@PostMapping(path = "/nsrlogin/dzsj")
	public ResponseEntity nsrLoginDzsj(@Valid @RequestBody UserHngsInsertBO login, HttpServletRequest request) {
		LOGGER.info("{}", login);
		HngsNsrLoginResponse loginResponse = userBindService.nsrLoginDzsj(login, request);
		return ResponseEntity.ok(loginResponse);
	}

	/**
	 * 用户批量绑定纳税人（电子申报）
	 *
	 * @param userDzsbInsertBO
	 *            纳税人信息
	 * @param request
	 *            HttpServletRequest
	 * @return 纳税人信息
	 * @throws Exception
	 *             访问网络、解包异常
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/batch/bind/dzsb")
	public ResponseEntity userBatchDzsbBind(@Valid @RequestBody UserDzsbBatchBO batchBO, HttpServletRequest request)
			throws Exception {
		LOGGER.info("{}:{}", JSONObject.toJSONString(batchBO), request);
		if (batchBO.getUserDzsbs() == null || batchBO.getUserDzsbs().size() == 0) {
			return ResponseEntity.ok(Utils.bodyStatus(9999, "批量绑定纳税人列表为空!"));
		}
		int num = userBindService.userBatchDzsbBind(batchBO, request);

		return ResponseEntity.ok(Utils.kv("data", num));
	}

	/**
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/dzsbregisterstat")
	public ResponseEntity dzsbRegisterStat(@RequestParam String beginDate, @RequestParam String endDate,
			HttpServletRequest request) {
		LOGGER.info("企业注册情况统计:{},{}", beginDate, endDate);
		Map<String, String> param = new HashMap<String, String>();
		param.put("beginDate", beginDate);
		param.put("endDate", endDate);
		List<DzsbRegisterStat> dataList = userBindService.dzsbRegisterStat(param);

		return ResponseEntity.ok(Utils.kv("dataList", dataList));
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/dzsbregisterstat/{date}")
	public ResponseEntity dzsbRegisterStatInfo(@PathVariable String date,
			@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size, HttpServletRequest request) {
		LOGGER.info("企业注册情况统计详情:{},{},{}", date, page, size);
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<Map<String, String>> dataList = userBindService.dzsbRegisterStatInfo(date);

		return ResponseEntity.ok(Utils.kv("dataList", dataList,"total", ((Page) dataList)
                .getTotal()));
	}

}
