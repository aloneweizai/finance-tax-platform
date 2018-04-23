package com.abc12366.uc.wsbssoa.response;


import com.abc12366.uc.wsbssoa.utils.CommonUtils;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月10日 下午3:52:30
 * @description
 */
public class BaseResponse implements java.io.Serializable {

    private boolean success = false;

    private String code;

    private String msg;

    private String userToken;

    private String yjfpdm;
    
    private String message;

    public BaseResponse() {
    }

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        if (!CommonUtils.nullOrBlank(code) && "000".equals(code)) {
            this.success = true;
        }
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getYjfpdm() {
        return yjfpdm;
    }

    public void setYjfpdm(String yjfpdm) {
        this.yjfpdm = yjfpdm;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
