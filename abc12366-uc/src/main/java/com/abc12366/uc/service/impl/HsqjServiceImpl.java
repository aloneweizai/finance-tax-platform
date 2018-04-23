package com.abc12366.uc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.exolab.castor.xml.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.abc12366.gateway.exception.DzsbServiceException;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.HsqjMapper;
import com.abc12366.uc.mapper.db1.UserBindMapper;
import com.abc12366.uc.model.HsqjBo;
import com.abc12366.uc.model.UserDzsb;
import com.abc12366.uc.model.tdps.TY21Xml2Object;
import com.abc12366.uc.service.IHsqjService;
import com.abc12366.uc.service.UserBindService;
import com.abc12366.uc.webservice.AcceptClient;
import com.alibaba.fastjson.JSONObject;

@Service
public class HsqjServiceImpl implements IHsqjService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HsqjServiceImpl.class);

	@Autowired
	private AcceptClient client;

	@Autowired
	private UserBindService userBindService;

	@Autowired
	private HsqjMapper hsqjMapper;

	@Autowired
	private UserBindMapper userBindMapper;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public void register(HsqjBo hsqjBo, HttpServletRequest request) throws ValidationException, ParseException {
		Map<String, String> map = new HashMap<>();
		map.put("serviceid", "TY11");
		map.put("NSRSBH", hsqjBo.getNsrsbh());

		Map<String, String> resMap = client.process(map);
		LOGGER.info("{}", JSONObject.toJSONString(resMap));
		TY21Xml2Object object = userBindService.analyzeXmlTY11(resMap, hsqjBo.getNsrsbh());
		LOGGER.info("获取纳税人信息:{}", JSONObject.toJSONString(object));
		if (object != null) {// 纳税人信息存在

			if (!hsqjBo.getNsrmc().trim().equals(object.getNSRMC().trim())) {
				throw new ServiceException(9999, "纳税人名称错误!");
			}
			SimpleDateFormat ff= new SimpleDateFormat("yyyy-MM-dd");
			
			if (StringUtils.isNotEmpty(object.getRJDQR())) {
				Date dqr = ff.parse(object.getRJDQR());
				if (ff.parse(ff.format(new Date())).before(dqr) ||
						ff.format(new Date()).equals(object.getRJDQR())){
					throw new ServiceException(9999, "您已经是电子申报用户或汇算清缴用户，不需要重复注册!");
				}else{
					if (StringUtils.isNotEmpty(object.getYQDQR())) {
						Date ydqr = ff.parse(object.getYQDQR());
						if (ff.parse(ff.format(new Date())).before(ydqr) || ff.format(new Date()).equals(object.getYQDQR())) {
							throw new ServiceException(9999, "您已经是电子申报用户或汇算清缴用户，不需要重复注册!");
						}
					}
				}
			}
			

			Map<String, String> mapTY06 = new HashMap<>();
			mapTY06.put("serviceid", "TY06");
			mapTY06.put("nsrsbh", hsqjBo.getNsrsbh());
			mapTY06.put("yqdqr", "2018-12-31");
			mapTY06.put("khsj", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			mapTY06.put("nsrlx", "01");
			Map<String, String> resMapTY06 = client.process(mapTY06);
			LOGGER.info("TY06返回信息:{}", JSONObject.toJSONString(resMapTY06));

			if (resMapTY06 == null || resMapTY06.isEmpty()) {
				throw new ServiceException(4629);
			}
			if (!"00000000".equals(resMap.get("rescode"))) {
				throw new DzsbServiceException((String) resMapTY06.get("rescode"), (String) resMapTY06.get("message"));
			}
			if (!resMapTY06.containsKey("taxML_CRM_NSRXXGX_" + hsqjBo.getNsrsbh() + ".xml")) {
				throw new ServiceException(4634);
			}
			String val = String.valueOf(resMapTY06.get("taxML_CRM_NSRXXGX_" + hsqjBo.getNsrsbh() + ".xml"));
			String res = StringUtils.substringBetween(val, "<GXJG>", "</GXJG>");
			if (!"1".equals(res)) {
				throw new DzsbServiceException("9999", StringUtils.substringBetween(val, "<CWYY>", "</CWYY>"));
			} else {
				Map<String, Object> mapO = new HashMap<String, Object>();
				mapO.put("id", Utils.uuid());
				mapO.put("nsrmc", hsqjBo.getNsrmc());
				mapO.put("nsrsbh", hsqjBo.getNsrsbh());
				mapO.put("ywlx", "01");
				mapO.put("rtstatus", "OK");
				mapO.put("enddate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-12-31 23:59:59"));
				mapO.put("createTime", new Date());
				mapO.put("updateTime", new Date());
				hsqjMapper.insert(mapO);

				// 绑定电子申报信息到用户
				String userId = Utils.getUserId(request);
				if (StringUtils.isNotEmpty(userId)) {

					redisTemplate.delete(userId + "_DzsbList");
					Date date = new Date();

					UserDzsb userDzsb = new UserDzsb();
					userDzsb.setUserId(userId);
					userDzsb.setNsrsbh(object.getY_NSRSBH());
					userDzsb.setLastUpdate(date);
					userDzsb.setStatus(true);
					userDzsb.setLastLoginTime(date);

					userDzsb.setDjxh(object.getDJXH());
					userDzsb.setNsrmc(object.getNSRMC());
					userDzsb.setShxydm(object.getSHXYDM());
					userDzsb.setNsrlx(object.getNSRLX());
					userDzsb.setSfgtjzh(object.getSFGTJZH());
					if (object.getSHXYDM() == null || "".equals(object.getSHXYDM().trim())) {
						userDzsb.setShxydm(object.getY_NSRSBH());
					}
					userDzsb.setSwjgMc(object.getSWJGMC());
					userDzsb.setSwjgDm(object.getSWJGDM());
					if (object.getRJDQR() != null && !"".equals(object.getRJDQR().trim())) {
						userDzsb.setExpireTime(DateUtils.strToDate(object.getRJDQR()));
					}
					if (object.getYQDQR() != null && !"".equals(object.getYQDQR().trim())) {
						userDzsb.setExpandExpireTime(DateUtils.strToDate(object.getYQDQR()));
					}
					userDzsb.setFrmc(object.getFRXM());
					userDzsb.setFrzjh(object.getFRZJH());
					userDzsb.setDjrq(object.getDJRQ());

					List<UserDzsb> nsrxxboList2 = userBindMapper.selectListByUserIdAndNsrsbh(userDzsb);

					if (nsrxxboList2 == null || nsrxxboList2.size() == 0) {
						userDzsb.setId(Utils.uuid());
						userDzsb.setCreateTime(date);
						userBindMapper.dzsbBind(userDzsb);
					} else if (nsrxxboList2.size() == 1) {
						userDzsb.setId(nsrxxboList2.get(0).getId());
						userBindMapper.update(userDzsb);
					} else {
						userDzsb.setId(nsrxxboList2.get(0).getId());
						userBindMapper.update(userDzsb);
						nsrxxboList2.remove(nsrxxboList2.get(0));
						for (UserDzsb ud : nsrxxboList2) {
							userBindMapper.deleteDzsb(ud.getId());
						}
					}

				}
			}
		}
	}

}
