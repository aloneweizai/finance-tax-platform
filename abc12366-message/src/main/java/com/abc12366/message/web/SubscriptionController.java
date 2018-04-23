package com.abc12366.message.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.model.Subscriptions;
import com.abc12366.message.model.UserSubscription;
import com.abc12366.message.model.UserSubscriptionInfo;
import com.abc12366.message.service.ISubscriptionService;
import com.github.pagehelper.PageInfo;

/**
 * 消息订阅控制类
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping(path = "/subscription", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SubscriptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private ISubscriptionService subscriptionService;

	/**
	 * 消息订阅设置列表
	 * 
	 * @param type
	 *            消息类型
	 * @param busiType
	 *            业务类型
	 * @param page
	 *            页数
	 * @param size
	 *            大小
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity selectList(@RequestParam(required = false) String type,
			@RequestParam(required = false) String busiType,
			@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
			@RequestParam(value = "size", defaultValue = Constant.pageSize) int size, HttpServletRequest request) {
		LOGGER.info("接收参数:{},{},{},{}", type, busiType, page, size);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", type);
		param.put("busiType", busiType);
		List<Subscriptions> dataList = subscriptionService.selectList(param, page, size);
		PageInfo<Subscriptions> pageInfo = new PageInfo<>(dataList);
		return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
	}

	/**
	 * 查询单个订阅设置
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity selectOneSetting(@PathVariable("id") String id) {
		Subscriptions subscriptions = subscriptionService.selectOneSetting(id);
		if(subscriptions!=null){
			return ResponseEntity.ok(Utils.kv("data", subscriptions));
		}else{
			return ResponseEntity.ok(Utils.kv(Utils.bodyStatus(9999, "消息订阅设置不存在!")));
		}
		
	}

	/**
	 * 新增订阅设置
	 * 
	 * @param subscriptions
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity addOneSetting(@Valid @RequestBody Subscriptions subscriptions) {
		subscriptions = subscriptionService.addOneSetting(subscriptions);
		return ResponseEntity.ok(Utils.kv("data", subscriptions));
	}

	/**
	 * 跟新订阅设置
	 * 
	 * @param subscriptions
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PutMapping
	public ResponseEntity updateOneSetting(@Valid @RequestBody Subscriptions subscriptions) {
		subscriptions = subscriptionService.updateOneSetting(subscriptions);
		return ResponseEntity.ok(Utils.kv("data", subscriptions));
	}

	/**
	 * 删除订阅设置
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delOneSetting(@PathVariable("id") String id) {
		int num = subscriptionService.delOneSetting(id);
		return ResponseEntity.ok(Utils.kv("data", num));
	}
	
	
	/*************************前端用户订阅***************************/
	
	/**
	 * 初始化用户订阅设置
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/initial")
	public ResponseEntity initialSubscriptions(HttpServletRequest request){
		  String userId = Utils.getUserId(request);
		  int num = subscriptionService.initial(userId);
		  return ResponseEntity.ok(Utils.kv("data", num));
	}
	
	/**
	 * 个人的订阅设置
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/usersettings")
	public ResponseEntity selectUserList(HttpServletRequest request) {
		String userId = Utils.getUserId(request);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		List<UserSubscriptionInfo> dataList = subscriptionService.selectUserSubscriptionList(param);
		PageInfo<UserSubscriptionInfo> pageInfo = new PageInfo<>(dataList);
		return ResponseEntity.ok(Utils.kv("dataList", pageInfo.getList(), "total", pageInfo.getTotal()));
	}
	
	/**
	 * 个人订阅设置保存
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/usersetsave")
	public ResponseEntity userSetSave(@RequestBody List<UserSubscription> dataList,HttpServletRequest request) {
		String userId = Utils.getUserId(request);
		  int num = subscriptionService.userSetSave(userId,dataList);
		  return ResponseEntity.ok(Utils.kv("data", num));
	}
	
}
