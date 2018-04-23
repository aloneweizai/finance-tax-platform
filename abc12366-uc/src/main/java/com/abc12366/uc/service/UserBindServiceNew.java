package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.model.order.bo.RegsAndNsrloginStatBO;
import com.abc12366.uc.model.tdps.TY21Xml2Object;
import com.abc12366.uc.wsbssoa.response.HngsNsrLoginResponse;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserBindServiceNew {

	UserDzsbBO dzsbBind(UserDzsbInsertBO userDzsbInsertBO, HttpServletRequest request) throws Exception;

	UserHngsBO hngsBind(UserHngsInsertBO userHngsInsertBO, HttpServletRequest request);

	TY21Xml2Object nsrLogin(NsrLogin login, HttpServletRequest request) throws Exception;

	UserDzsbBO cszjdzsbBind(UserDzsbInsertBO userDzsbInsertBO, HttpServletRequest request)  throws Exception;

	UserHngsBO cszjhngsBind(UserHngsInsertBO userHngsInsertBO, HttpServletRequest request);

	TY21Xml2Object cszjnsrLoginShb(NsrLogin login, HttpServletRequest request) throws Exception;

	HngsNsrLoginResponse nsrLoginDzsj(UserHngsInsertBO login, HttpServletRequest request);


	/**
	 * regsDay-今天用户注册数、regsMonth-本月注册数统计，dzsbLoginsDay-增加今天纳税人登录电子申报数、nsrLoginsMonth-本月纳税人登录数统计
	 * @return RegsAndNsrloginStatBO
	 */
	RegsAndNsrloginStatBO staRegsAndNsrlogins();
	/**
	 * 电子申报纳税人一天内登录次数统计
	 */
	void dzsbnsrLoginTimesDay();

	/**
	 * 纳税人一个月内登录次数统计
	 */
	void nsrLoginTimesMonth();

	/**
	 * 电子申报纳税人一天内登录次数
	 * @return int
	 */
	int getDzsbnsrLoginTimesDay();
	/**
	 *获取纳税人一个月内登录次数
	 * @return int
	 */
	int getNnsrLoginTimesMonth();

	public void recordNsrLoginTimes();

	int userBatchDzsbBind(UserDzsbBatchBO batchBO, HttpServletRequest request);

	List<com.abc12366.uc.model.DzsbRegisterStat> dzsbRegisterStat(Map<String, String> param);

	List<Map<String, String>> dzsbRegisterStatInfo(String date);
}
