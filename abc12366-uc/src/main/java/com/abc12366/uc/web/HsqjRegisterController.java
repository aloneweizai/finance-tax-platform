package com.abc12366.uc.web;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.exolab.castor.xml.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.HsqjBo;
import com.abc12366.uc.service.IHsqjService;
/**
 * 汇算清缴相关接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping(path = "/hsqj", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class HsqjRegisterController {
	 
	 @Autowired
	 private IHsqjService hsqjService;
      
	 @SuppressWarnings("rawtypes")
	 @PostMapping(path = "/register")
	 public ResponseEntity register(@RequestBody HsqjBo hsqjBo,HttpServletRequest request) throws ValidationException, ParseException{ 
	     hsqjService.register(hsqjBo, request);
		 return ResponseEntity.ok(Utils.kv());
	 }
	 
}
