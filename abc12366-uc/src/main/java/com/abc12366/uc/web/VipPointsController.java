package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db2.VipPrivilegeLevelRoMapper;
import com.abc12366.uc.model.VipPointsLog;
import com.abc12366.uc.model.bo.PointsRuleBO;
import com.abc12366.uc.model.bo.UserBO;
import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;
import com.abc12366.uc.service.PointsLogService;
import com.abc12366.uc.service.PointsRuleService;
import com.abc12366.uc.service.PointsService;
import com.abc12366.uc.service.UserService;
import com.abc12366.uc.util.wx.SignUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * VIP积分增送接口
 * @author zhushuai 2017-11-20
 *
 */
@Controller
@RequestMapping(path = "/vip", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class VipPointsController {
     
	 private static final Logger LOGGER = LoggerFactory.getLogger(VipPointsController.class);
	 
	 @Autowired
	 private  UserService userService;
	 
	 @Autowired
	 private VipPrivilegeLevelRoMapper vipPrivilegeLevelRoMapper;
	 
	 @Autowired
	 private PointsLogService pointsLogService;
	 
	 @Autowired
	 private PointsRuleService pointsRuleService;
	 
	 @Autowired
	 private PointsService pointsService;
	 
	 /**
	  * 会员积分装让接口
	  * @param vippointslog  转让参数
	  * @return
	  */
	 @SuppressWarnings("rawtypes")
	 @PostMapping("/integralmultiplication")
	 public ResponseEntity integralMultiplication(@RequestBody VipPointsLog vippointslog){
		 LOGGER.info("积分转让失败接收参数:"+JSONObject.toJSONString(vippointslog));
		 if(StringUtils.isEmpty(vippointslog.getSendId())||StringUtils.isEmpty(vippointslog.getReciveId())||StringUtils.isEmpty(vippointslog.getSignature())){
			 return ResponseEntity.ok(Utils.bodyStatus(9999, "积分转让失败：接收数据异常!"));
		 }
		 
		 if(SignUtil.checkSignature("integralmultiplication", vippointslog.getSignature(), vippointslog.getSendId(), vippointslog.getReciveId())){
                PointsRuleBO bo=pointsRuleService.selectValidOneByCode("P-hyjfzs");
                if(bo==null){
			    	LOGGER.info("积分转让失败：积分转让规则异常!");
			    	return ResponseEntity.ok(Utils.bodyStatus(9999, "积分转让失败：积分转让规则异常!"));
			    }
                
                
			    UserBO sendUser=userService.selectByUsernameOrPhone(vippointslog.getSendId());
			    if(sendUser==null){
			    	LOGGER.info("积分转让失败：转让用户异常!");
			    	return ResponseEntity.ok(Utils.bodyStatus(9999, "积分转让失败：转让用户异常!"));
			    }
			    
		        
		        if(sendUser.getPoints()<bo.getPoints()){
		        	LOGGER.info("积分转让失败：用户积分不足!");
			    	return ResponseEntity.ok(Utils.bodyStatus(9999, "积分转让失败：用户积分不足!"));
		        }
			    
			    UserBO reciveUser=userService.selectByUsernameOrPhone(vippointslog.getReciveId());
			    if(reciveUser==null){
			    	LOGGER.info("积分转让失败：接收用户异常!");
			    	return ResponseEntity.ok(Utils.bodyStatus(9999, "积分转让失败：接收用户异常!"));
			    }
			    
			    VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
		        obj.setLevelId(sendUser.getVipLevel());
		        obj.setPrivilegeId("A_YHJFZR");
		        VipPrivilegeLevelBO findObj = vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);
		        
		        if(findObj!=null){
		        	
		        	Integer times=0;
			        
		        	try {
						times=Integer.parseInt(findObj.getVal1());
					} catch (NumberFormatException e) {
						LOGGER.info("积分转让失败：本月积分转让次数已用完!");
						return ResponseEntity.ok(Utils.bodyStatus(9999, "积分转让失败：本月积分装让次数已用完!"));
					}
			        
			        if(times!=-1){//不为无限次数
			        	
			        	Map<String, Object> map=new HashMap<String, Object>();
			        	map.put("userId", sendUser.getId());
			        	map.put("code", bo.getCode());
			        	map.put("tformat","%Y%m");
			        	map.put("sendtime", new SimpleDateFormat("yyyyMM").format(new Date()));
			        	
			        	int num=pointsLogService.selecttimes(map);
			        	if(num>=times){
			        		LOGGER.info("积分转让失败：本月积分转让次数已用完!");
			   			    return ResponseEntity.ok(Utils.bodyStatus(9999, "积分转让失败：本月积分装让次数已用完!"));
			        	}
			        }
			        
			        //开始操作积分转让
			        return pointsService.integralMultiplication(sendUser, reciveUser, bo);
		        }else{
		        	 LOGGER.info("积分转让失败：账户没有积分转让特权!");
					 return ResponseEntity.ok(Utils.bodyStatus(9999, "积分转让失败：账户没有积分转让特权!"));
		        }  
		 }else{
			 LOGGER.info("积分转让失败：签名校验异常!");
			 return ResponseEntity.ok(Utils.bodyStatus(9999, "积分转让失败：签名校验异常!"));
		 }
	 }
	 
	 
	
	
}
