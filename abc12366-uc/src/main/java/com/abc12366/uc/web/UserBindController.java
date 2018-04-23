package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.NsrsbhPasswordLog;
import com.abc12366.uc.model.UserDzsb;
import com.abc12366.uc.model.UserHnds;
import com.abc12366.uc.model.UserHngs;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.model.tdps.TY21Xml2Object;
import com.abc12366.uc.service.UserBindService;
import com.abc12366.uc.wsbssoa.response.HngsNsrLoginResponse;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户绑定办税身份控制器类，以常规JSON形式返回数据
 *
 * @author liuguiyao 435720953@qq.com
 * @date 2017-07-25
 */
@RestController
@RequestMapping(headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class UserBindController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserBindController.class);

	@Autowired
	private UserBindService userBindService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

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
	@PostMapping(path = "/bind/dzsb")
	public ResponseEntity userDzsbBind(@Valid @RequestBody UserDzsbInsertBO userDzsbInsertBO,
			HttpServletRequest request) throws Exception {
		LOGGER.info("{}:{}", userDzsbInsertBO, request);
		UserDzsbBO userDzsb = userBindService.dzsbBind(userDzsbInsertBO, request);
		LOGGER.info("{}", userDzsb);

		return ResponseEntity.ok(Utils.kv("data", userDzsb));
	}

	/**
	 * 用户解除纳税人绑定（电子申报）
	 *
	 * @param id
	 *            绑定表主键
	 * @return 是否解绑成功
	 */
	@PutMapping(path = "/unbind/dzsb/{id}")
	public ResponseEntity userDzsbUnbind(@PathVariable String id) {
		LOGGER.info("{}", id);
		userBindService.dzsbUnbind(id);

		return ResponseEntity.ok(Utils.kv());
	}

	/**
	 * 根据用户ID查询电子申报绑定列表客户端
	 *
	 * @param userId
	 *            用户ID
	 */
	@GetMapping(path = "/bind/dzsbs/{userId}")
	public ResponseEntity getUserDzsbBinds(@PathVariable String userId) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		List<UserDzsbListBO> userDzsbBOList = null;
		if (redisTemplate.hasKey(userId + "_DzsbList")) {
			userDzsbBOList = JSONArray.parseArray(redisTemplate.opsForValue().get(userId + "_DzsbList"),
					UserDzsbListBO.class);
			LOGGER.info("从redis获取电子申报绑定列表:{}", JSONArray.toJSONString(userDzsbBOList));
		} else {
			userDzsbBOList = userBindService.getUserDzsbBind(map);
			redisTemplate.opsForValue().set(userId + "_DzsbList", JSONArray.toJSONString(userDzsbBOList),
					RedisConstant.DAY_1, TimeUnit.DAYS);
		}
		LOGGER.info("{}", userDzsbBOList);
		return (userDzsbBOList == null) ? ResponseEntity.ok(Utils.kv())
				: ResponseEntity.ok(Utils.kv("dataList", userDzsbBOList, "total", userDzsbBOList.size()));
	}

	/**
	 * 根据用户ID查询湖南国税绑定列表客户端
	 *
	 * @param userId
	 *            用户ID
	 */
	@GetMapping(path = "/bind/hngss/{userId}")
	public ResponseEntity getUserhngsBinds(@PathVariable String userId) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		List<UserHngsListBO> userHngsListBO = null;
		if (redisTemplate.hasKey(userId + "_HngsList")) {
			userHngsListBO = JSONArray.parseArray(redisTemplate.opsForValue().get(userId + "_HngsList"),
					UserHngsListBO.class);
			LOGGER.info("从redis获取湖南国税绑定列表:{}", JSONArray.toJSONString(userHngsListBO));
		} else {
			userHngsListBO = userBindService.getUserhngsBind(map);
			redisTemplate.opsForValue().set(userId + "_HngsList", JSONArray.toJSONString(userHngsListBO),
					RedisConstant.DAY_1, TimeUnit.DAYS);
		}
		LOGGER.info("{}", userHngsListBO);
		return (userHngsListBO == null) ? ResponseEntity.ok(Utils.kv())
				: ResponseEntity.ok(Utils.kv("dataList", userHngsListBO, "total", userHngsListBO.size()));
	}

	/**
	 * 根据用户ID查询湖南地税绑定列表客户端
	 *
	 * @param userId
	 *            用户ID
	 */
	@GetMapping(path = "/bind/hndss/{userId}")
	public ResponseEntity getUserhndsBinds(@PathVariable String userId) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		List<UserHndsBO> uerHndsBO = null;
		if (redisTemplate.hasKey(userId + "_HndsList")) {
			uerHndsBO = JSONArray.parseArray(redisTemplate.opsForValue().get(userId + "_HndsList"), UserHndsBO.class);
			LOGGER.info("从redis获取湖南国税绑定列表:{}", JSONArray.toJSONString(uerHndsBO));
		} else {
			uerHndsBO = userBindService.getUserhndsBind(map);
			redisTemplate.opsForValue().set(userId + "_HndsList", JSONArray.toJSONString(uerHndsBO),
					RedisConstant.DAY_1, TimeUnit.DAYS);
		}
		LOGGER.info("{}", uerHndsBO);
		return (uerHndsBO == null) ? ResponseEntity.ok(Utils.kv())
				: ResponseEntity.ok(Utils.kv("dataList", uerHndsBO, "total", uerHndsBO.size()));
	}

	/**
	 * 根据用户ID查询电子申报绑定列表
	 *
	 * @param userId
	 *            用户ID
	 * @param page
	 *            当前页
	 * @param size
	 *            每页大小
	 * @return 电子申报绑定列表
	 */
	@GetMapping(path = "/bind/dzsb/{userId}")
	public ResponseEntity getUserDzsbBind(@PathVariable String userId, @RequestParam(required = false) String nsrsbh,
			@RequestParam(required = false) String nsrmc, @RequestParam(required = false) String bdgroup,
			@RequestParam(required = false) String remark,
			@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		if (StringUtils.isNotEmpty(nsrsbh)) {
			map.put("nsrsbh", nsrsbh);
		}
		if (StringUtils.isNotEmpty(nsrmc)) {
			map.put("nsrmc", nsrmc);
		}
		if (StringUtils.isNotEmpty(bdgroup)) {
			map.put("bdgroup", bdgroup);
		}
		if (StringUtils.isNotEmpty(remark)) {
			map.put("remark", remark);
		}
		LOGGER.info("{}", map);
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<UserDzsbListBO> userDzsbBOList = userBindService.getUserDzsbBind(map);
		LOGGER.info("{}", userDzsbBOList);
		return (userDzsbBOList == null) ? ResponseEntity.ok(Utils.kv())
				: ResponseEntity.ok(Utils.kv("dataList", userDzsbBOList, "total", ((Page) userDzsbBOList).getTotal()));
	}

	/**
	 * 查找用户电子申报分组列表
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(path = "/bind/dzsbgroup/{userId}")
	public ResponseEntity getUserDzsbGroup(@PathVariable String userId) {
		List<Map<String, String>> list = userBindService.findBroup(userId);
		return ResponseEntity.ok(Utils.kv("dataList", list));
	}
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping(path = "/update/dzsb/group")
	public ResponseEntity updateDzsbgroup(@RequestBody Map<String,String> map,HttpServletRequest request){
		
		int num = userBindService.updateDzsbgroup(map);
		//跟新分组删除缓存
		String userId = Utils.getUserId(request);
		if(StringUtils.isNotEmpty(userId)){
			redisTemplate.delete(userId + "_DzsbList");
		}
		return ResponseEntity.ok(Utils.kv("data", num));
	}

	/**
	 * 根据用户ID查询电子税局绑定列表
	 *
	 * @param userId
	 *            用户ID
	 * @param page
	 *            当前页
	 * @param size
	 *            每页大小
	 * @return 电子税局绑定列表
	 */
	@GetMapping(path = "/bind/hngs/{userId}")
	public ResponseEntity getUserhngsBind(@PathVariable String userId, @RequestParam(required = false) String nsrsbh,
			@RequestParam(required = false) String nsrmc,
			@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		if (StringUtils.isNotEmpty(nsrsbh)) {
			map.put("nsrsbh", nsrsbh);
		}
		if (StringUtils.isNotEmpty(nsrmc)) {
			map.put("nsrmc", nsrmc);
		}
		LOGGER.info("{}", map);
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<UserHngsListBO> userHngsBOList = userBindService.getUserhngsBind(map);
		LOGGER.info("{}", userHngsBOList);
		return (userHngsBOList == null) ? ResponseEntity.ok(Utils.kv())
				: ResponseEntity.ok(Utils.kv("dataList", userHngsBOList, "total", ((Page) userHngsBOList).getTotal()));
	}

	/**
	 * 根据用户ID查询湖南地税绑定列表
	 *
	 * @param userId
	 *            用户ID
	 * @param page
	 *            当前页
	 * @param size
	 *            每页大小
	 * @return 湖南地税绑定列表
	 */
	@GetMapping(path = "/bind/hnds/{userId}")
	public ResponseEntity getUserhndsBind(@PathVariable String userId, @RequestParam(required = false) String nsrsbh,
			@RequestParam(required = false) String nsrmc,
			@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		if (StringUtils.isNotEmpty(nsrsbh)) {
			map.put("nsrsbh", nsrsbh);
		}
		if (StringUtils.isNotEmpty(nsrmc)) {
			map.put("nsrmc", nsrmc);
		}
		LOGGER.info("{}", map);
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		List<UserHndsBO> userHngsBOList = userBindService.getUserhndsBind(map);
		LOGGER.info("{}", userHngsBOList);
		return (userHngsBOList == null) ? ResponseEntity.ok(Utils.kv())
				: ResponseEntity.ok(Utils.kv("dataList", userHngsBOList, "total", ((Page) userHngsBOList).getTotal()));
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
	@PostMapping(path = "/bind/hngs")
	public ResponseEntity userHngsBind(@Valid @RequestBody UserHngsInsertBO userHngsInsertBO,
			HttpServletRequest request) throws Exception {
		LOGGER.info("{}:{}", userHngsInsertBO, request);
		UserHngsBO userHngs = userBindService.hngsBind(userHngsInsertBO, request);

		return ResponseEntity.ok(Utils.kv("data", userHngs));
	}

	/**
	 * 解绑湖南国税用户
	 *
	 * @param id
	 *            湖南国税绑定表主键
	 * @return ResponseEntity
	 */
	@PutMapping(path = "/unbind/hngs/{id}")
	public ResponseEntity userHngsUnbind(@PathVariable String id, HttpServletRequest request) {
		LOGGER.info("{}", id);
		userBindService.hngsUnbind(id);

		return ResponseEntity.ok(Utils.kv());
	}

	/**
	 * 绑定湖南地税用户
	 *
	 * @param userHndsInsertBO
	 *            地税用户信息
	 * @param request
	 *            HttpServletRequest
	 * @return 地税登陆信息
	 */
	@PostMapping(path = "/bind/hnds")
	public ResponseEntity userHndsBind(@Valid @RequestBody UserHndsInsertBO userHndsInsertBO,
			HttpServletRequest request) {
		LOGGER.info("{}:{}", userHndsInsertBO, request);
		UserHndsBO userHnds = userBindService.hndsBind(userHndsInsertBO, request);

		return ResponseEntity.ok(Utils.kv("data", userHnds));
	}

	/**
	 * 解绑湖南地税用户
	 *
	 * @param id
	 *            地税绑定表主键
	 * @return ResponseEntity
	 */
	@PutMapping(path = "/unbind/hnds/{id}")
	public ResponseEntity userHndsUnbind(@PathVariable String id) {
		LOGGER.info("{}", id);
		userBindService.hndsUnbind(id);

		return ResponseEntity.ok(Utils.kv());
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
	 * 重置电子申报用户密码
	 *
	 * @param data
	 *            重置密码信息
	 * @param request
	 *            HttpServletRequest
	 * @return ResponseEntity
	 * @throws IOException
	 *             网络异常
	 * @throws MarshalException
	 *             解包异常
	 * @throws ValidationException
	 *             验证异常
	 */
	@PostMapping(path = "/shb/resetpassword")
	public ResponseEntity resetPassword(@RequestBody NsrResetPwd data, HttpServletRequest request)
			throws IOException, MarshalException, ValidationException {
		LOGGER.info("{}", data);
		userBindService.resetPassword(data, request);
		return ResponseEntity.ok(Utils.kv("code", "2000", "message", "您的申报服务密码已重置为'88888888',请尽快修改密码！"));
	}

	/**
	 * 修改电子申报密码
	 *
	 * @param data
	 *            修改密码信息
	 * @return ResponseEntity
	 * @throws ValidationException
	 *             验证异常
	 */
	@PostMapping(path = "/shb/updatepassword")
	public ResponseEntity updatePassword(@Valid @RequestBody UpdatePwd data, HttpServletRequest request)
			throws ValidationException {
		LOGGER.info("用户修改纳税人登录电子申报密码，{}", data);
		userBindService.updatePassword(data, request);
		return ResponseEntity.ok(Utils.kv());
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
	 * 调用电子税局查看用户是否实名认证
	 *
	 * @param sfzjhm
	 *            身份证件号码
	 * @param xm
	 *            姓名
	 * @param request
	 *            HttpServletRequest
	 * @return 是否实名认证
	 */
	@GetMapping(path = "/realname/dzsj")
	public ResponseEntity isRealNameValidatedDzsj(@RequestParam String sfzjhm, @RequestParam String xm,
			HttpServletRequest request) {
		LOGGER.info("调用电子税局实名认证查询接口：{},{}", sfzjhm, xm);
		boolean result = userBindService.isRealNameValidatedDzsj(sfzjhm, xm, request);
		LOGGER.info("电子税局返回查询结果：{}", result);
		return ResponseEntity.ok(Utils.kv("data", result));
	}

	/**
	 * 更新电子申报绑定关系
	 *
	 * @param userId
	 *            用户id
	 * @param nsrsbh
	 *            纳税人识别号
	 * @return ResponseEntity
	 */
	@PutMapping(path = "/bind/dzsb/{userId}/{nsrsbh}")
	public ResponseEntity updateDzsb(@PathVariable String userId, @PathVariable String nsrsbh) {
		LOGGER.info("更新电子申报绑定关系：{},{}", userId, nsrsbh);
		UserDzsbListBO userDzsb = userBindService.updateDzsb(userId, nsrsbh);
		LOGGER.info("更新电子申报绑定关系返回：{}");

		return ResponseEntity.ok(Utils.kv("data", userDzsb));
	}

	/**
	 * 查询电子申报绑定关系详情
	 *
	 * @param id
	 *            绑定关系id
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/bind/dzsb/detail/{id}")
	public ResponseEntity dzsbDetail(@PathVariable String id) {
		LOGGER.info("查询电子申报绑定关系详情：{}", id);
		UserDzsb userDzsb = userBindService.dzsbDetail(id);
		LOGGER.info("查询电子申报绑定关系详情返回：{}");
		return ResponseEntity.ok(Utils.kv("data", userDzsb));
	}

	/**
	 * 查询湖南国税绑定关系详情
	 *
	 * @param id
	 *            绑定关系id
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/bind/hngs/detail/{id}")
	public ResponseEntity hngsDetail(@PathVariable String id) {
		LOGGER.info("查询湖南国税绑定关系详情：{}", id);
		UserHngs userHngs = userBindService.hngsDetail(id);
		LOGGER.info("查询湖南国税绑定关系详情返回：{}");
		return ResponseEntity.ok(Utils.kv("data", userHngs));
	}

	/**
	 * 查询湖南地税绑定关系详情
	 *
	 * @param id
	 *            绑定关系id
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/bind/hnds/detail/{id}")
	public ResponseEntity hndsDetail(@PathVariable String id) {
		LOGGER.info("查询湖南地税绑定关系详情：{}", id);
		UserHnds userHnds = userBindService.hndsDetail(id);
		LOGGER.info("查询湖南地税绑定关系详情返回：{}");
		return ResponseEntity.ok(Utils.kv("data", userHnds));
	}

	/**
	 * 电子申报修改密码记录列表
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(path = "/shb/restpwdloglist")
	public ResponseEntity restPwdLogList(@RequestParam(required = false) String nsrsbh,
			@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
		Map map = new HashMap<>();
		map.put("nsrsbh", nsrsbh);
		List<NsrsbhPasswordLog> passwordLogs = userBindService.restPwdLogList(map, page, size);
		return (passwordLogs == null) ? ResponseEntity.ok(Utils.kv())
				: ResponseEntity.ok(Utils.kv("dataList", passwordLogs, "total", passwordLogs.size()));
	}
}
