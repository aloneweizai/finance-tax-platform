package com.abc12366.gateway.exception;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.util.Utils;

/**
 * 财税平台需要返回异常消息，调用此异常；除非是公司开发的系统（如：电子申报、电子税局），否则都是调用此异常（如支付宝、短信接口）
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-30 4:02 PM
 * @since 1.0.0
 * @see DzsbServiceException
 * @see DzsjServiceException
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1401593546385403720L;
    private BodyStatus bodyStatus;

    public ServiceException() {
        super();
    }

    public ServiceException(int code) {
        this.bodyStatus = Utils.bodyStatus(code);
    }

    public ServiceException(int code, String message) {
        this.bodyStatus = Utils.bodyStatus(code, message);
    }

    public ServiceException(String code, String message) {
        this.bodyStatus = Utils.bodyStatus(code, message);
    }

    public BodyStatus getBodyStatus() {
        return bodyStatus;
    }

    public void setBodyStatus(BodyStatus bodyStatus) {
        this.bodyStatus = bodyStatus;
    }
}