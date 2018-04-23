package com.abc12366.uc.model.pay.bo;

import java.io.Serializable;
/**
 * 扩展参数
 * @author zhushuai 2017-8-4
 *
 */
public class PayExtend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//系统商编号  该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
    private String  sys_service_provider_id;
    
	public String getSys_service_provider_id() {
		return sys_service_provider_id;
	}
	public void setSys_service_provider_id(String sys_service_provider_id) {
		this.sys_service_provider_id = sys_service_provider_id;
	}
}
