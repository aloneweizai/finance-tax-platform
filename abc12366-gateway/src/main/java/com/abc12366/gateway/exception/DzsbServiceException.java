package com.abc12366.gateway.exception;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.util.Utils;

/**
 * 调用电子申报接口时，如果有错误系统消息返回，调用此异常
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-30 4:02 PM
 * @since 1.0.0
 * @see ServiceException
 * @see DzsjServiceException
 */
public class DzsbServiceException extends RuntimeException {

    private BodyStatus bodyStatus;

    public DzsbServiceException() {
        super();
    }

    public DzsbServiceException(int code, String message) {
        this.bodyStatus = Utils.bodyStatus(code, message);
    }

    public DzsbServiceException(String code, String message) {
        this.bodyStatus = Utils.bodyStatus(code, message);
    }

    public BodyStatus getBodyStatus() {
        return bodyStatus;
    }

    public void setBodyStatus(BodyStatus bodyStatus) {
        this.bodyStatus = bodyStatus;
    }
}