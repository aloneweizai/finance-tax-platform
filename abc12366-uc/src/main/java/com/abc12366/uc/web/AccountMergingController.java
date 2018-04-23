package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db2.VipPrivilegeLevelRoMapper;
import com.abc12366.uc.model.UserExtend;
import com.abc12366.uc.model.bo.PointsRuleBO;
import com.abc12366.uc.model.bo.UserBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;
import com.abc12366.uc.service.IAccountMergingService;
import com.abc12366.uc.service.PointsLogService;
import com.abc12366.uc.service.PointsRuleService;
import com.abc12366.uc.service.UserService;
import com.abc12366.uc.util.wx.SignUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账号合并
 * @author zhushuai 2017-11-20
 *
 */
@Controller
@RequestMapping(path = "/account", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class AccountMergingController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(AccountMergingController.class);
	 
	 @Autowired
     private UserService userService;
	 
	 @Autowired
	 private IAccountMergingService accountMergingService;
	 
	 @Autowired
	 private VipPrivilegeLevelRoMapper vipPrivilegeLevelRoMapper;
	 
	 @Autowired
	 private PointsLogService pointsLogService;

	 @Autowired
	 private PointsRuleService pointsRuleService;

	 /**
	  * 账号合并
	  * @param body  参数
	  * @return
	  */
	 @SuppressWarnings("rawtypes")
	 @PostMapping("/merging")
	 public ResponseEntity merging(@RequestBody Map<String, Object> body){
		 LOGGER.info("账号合并接收参数:"+JSONObject.toJSONString(body));
		 if(StringUtils.isEmpty(body.get("mergeId")) || StringUtils.isEmpty(body.get("beMergeId")) || StringUtils.isEmpty(body.get("signature"))){
			 return ResponseEntity.ok(Utils.bodyStatus(9999, "账号合并失败：接收数据异常!"));
		 }
		 String mergeId=body.get("mergeId").toString();
		 String beMergeId=body.get("beMergeId").toString();
		 if(!SignUtil.checkSignature("accountmerging", body.get("signature").toString(), mergeId, beMergeId)){
			 LOGGER.info("账号合并失败：签名校验异常!");
			 return ResponseEntity.ok(Utils.bodyStatus(9999, "账号合并失败：签名校验异常!"));
		 }
		 
		 PointsRuleBO bo=pointsRuleService.selectValidOneByCode("P-zhhb");
         if(bo==null){
		    LOGGER.info("账号合并失败：积分合并规则异常!");
		    return ResponseEntity.ok(Utils.bodyStatus(9999, "账号合并失败：积分合并规则异常!"));
		 }
		 
		 
		 
		 //获取合并账号扩展信息
		 Map mergeMap=userService.selectOneForAdmin(mergeId);
		 if(mergeMap==null){
			 return ResponseEntity.ok(Utils.bodyStatus(9999, "账号合并失败：合并账号信息异常"));
		 }
		 UserBO mergeUserBO=(UserBO)mergeMap.get("user");
         UserExtend  mergeExtend=(UserExtend) mergeMap.get("user_extend");
         
		 //获取被合并账号扩展信息
		 Map beMergeMap=userService.selectOneForAdmin(beMergeId);
         if(beMergeMap==null){
        	 return ResponseEntity.ok(Utils.bodyStatus(9999, "账号合并失败：被合并账号信息异常"));
		 }
		 UserBO beMergeUserBO=(UserBO)beMergeMap.get("user");
		 UserExtend  beMergeExtend=(UserExtend) beMergeMap.get("user_extend");
		 
		 if(mergeExtend==null || 
				 beMergeExtend==null || StringUtils.isEmpty(mergeExtend.getIdcard()) 
				  || !mergeExtend.getIdcard().equals(beMergeExtend.getIdcard())){
			    LOGGER.info("账号合并接失败：账户扩展信息异常或账号不属于同一认证用户!");
		    	return ResponseEntity.ok(Utils.bodyStatus(9999, "账号合并接失败：账户信息异常或账号不属于同一认证用户!"));
		 }
		 
		 VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
	     obj.setLevelId(mergeUserBO.getVipLevel());
	     obj.setPrivilegeId("A_YHZHHB");
	     VipPrivilegeLevelBO findObj = vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);
	     
	     if(findObj!=null){
	    	 Integer times=0;
	    	 try {
				times=Integer.parseInt(findObj.getVal1());
			 } catch (NumberFormatException e) {
				LOGGER.info("账号合并接失败：本年度账号合并次数已用完!");
				return ResponseEntity.ok(Utils.bodyStatus(9999, "账号合并接失败：本年度账号合并次数已用完!"));
			 }
	    	 
	    	 Map<String, Object> map=new HashMap<String, Object>();
	         map.put("userId", mergeId);
	         map.put("code", bo.getCode());
	         map.put("tformat","%Y");
	         map.put("sendtime", new SimpleDateFormat("yyyy").format(new Date()));
	         int num=pointsLogService.selecttimes(map);
	         
	         if(num>=times){
	        		LOGGER.info("账号合并接失败：本年度账号合并次数已用完!");
	   			    return ResponseEntity.ok(Utils.bodyStatus(9999, "账号合并接失败：本年度账号合并次数已用完!"));
	         }
	         return accountMergingService.merging(mergeUserBO,beMergeUserBO,bo);
	     }else{
        	 LOGGER.info("账号合并接失败：该用户暂无账号合并特权!");
			 return ResponseEntity.ok(Utils.bodyStatus(9999, "账号合并接失败：该用户暂无账号合并特权!"));
        } 

	 }
	 
	 /**
	  * 可合并账号列表
	  * @param userId 用户ID
	  * @return
	  */
	 @SuppressWarnings("rawtypes")
	 @RequestMapping("/canmerging/{userid}")
	 public ResponseEntity canmerging(@PathVariable("userid")String userId){
		 Map mergeMap=userService.selectOneForAdmin(userId);
		 if(mergeMap==null){
			 return ResponseEntity.ok(Utils.bodyStatus(9999, "获取可合并账号列表异常:用户信息不存在!"));
		 }
		 UserExtend  mergeExtend=(UserExtend) mergeMap.get("user_extend");
		 if(mergeExtend==null || StringUtils.isEmpty(mergeExtend.getIdcard()) || !"2".equals(mergeExtend.getValidStatus())){
			 return ResponseEntity.ok(Utils.bodyStatus(9999, "获取可合并账号列表异常:该账号实名认证信息异常"));
		 }
		 Map<String,String> map=new HashMap<String,String>();
		 map.put("userid", userId);
		 map.put("idcard", mergeExtend.getIdcard());
		 List<Map<String,String>> datalist=accountMergingService.canmerging(map);
		 return ResponseEntity.ok(Utils.kv("dataList", datalist));
	 }
}
