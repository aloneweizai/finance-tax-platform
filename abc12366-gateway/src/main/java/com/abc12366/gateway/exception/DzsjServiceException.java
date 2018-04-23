package com.abc12366.gateway.exception;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.util.Utils;

/**
 * 调用电子税局接口时，如果有错误系统消息返回，调用此异常
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-30 4:02 PM
 * @since 1.0.0
 * @see ServiceException
 * @see DzsbServiceException
 */
public class DzsjServiceException extends RuntimeException {

    private BodyStatus bodyStatus;

    public DzsjServiceException() {
        super();
    }

    public DzsjServiceException(int code, String message) {
        this.bodyStatus = Utils.bodyStatus(code, message);
    }

    public DzsjServiceException(String code, String message) {
        this.bodyStatus = Utils.bodyStatus(code, message);
    }

    public BodyStatus getBodyStatus() {
        return bodyStatus;
    }

    public void setBodyStatus(BodyStatus bodyStatus) {
        this.bodyStatus = bodyStatus;
    }}