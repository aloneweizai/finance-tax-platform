package com.abc12366.gateway.component;

import com.abc12366.gateway.exception.DzsbServiceException;
import com.abc12366.gateway.exception.DzsjServiceException;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.gateway.model.BodyValidStatus;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局Exception处理
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-29 2:59 PM
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handle(Exception e, HttpServletRequest request) {
        String uri = (String) request.getAttribute("org.springframework.web.servlet.HandlerMapping" +
                ".bestMatchingPattern");
        BodyStatus bodyStatus;
        if (e instanceof ServiceException) {
            bodyStatus = ((ServiceException) e).getBodyStatus();
            LOGGER.warn("主动抛出异常:访问{}时，出现[{}:{}]", uri, bodyStatus.getCode(), bodyStatus.getMessage());
            bodyStatus.setMessage("提示:" + bodyStatus.getMessage() +
                    "【财税平台" + bodyStatus.getCode() + "】");
            return new ResponseEntity<>(bodyStatus, HttpStatus.OK);
        } else if (e instanceof DzsbServiceException) {
            bodyStatus = ((DzsbServiceException) e).getBodyStatus();
            LOGGER.warn("主动抛出异常:访问{}时，出现[{}:{}]", uri, bodyStatus.getCode(), bodyStatus.getMessage(), e);
            bodyStatus.setMessage("提示:" + bodyStatus.getMessage() +
                    "【电子申报" + bodyStatus.getCode() + "】");
            return new ResponseEntity<>(bodyStatus, HttpStatus.OK);
        } else if (e instanceof DzsjServiceException) {
            bodyStatus = ((DzsjServiceException) e).getBodyStatus();
            LOGGER.warn("主动抛出异常:访问{}时，出现[{}:{}]", uri, bodyStatus.getCode(), bodyStatus.getMessage(), e);
            bodyStatus.setMessage("提示:" + bodyStatus.getMessage() +
                    "【电子税局" + bodyStatus.getCode() + "】");
            return new ResponseEntity<>(bodyStatus, HttpStatus.OK);
        }

        if (e instanceof HttpRequestMethodNotSupportedException) {
            bodyStatus = Utils.bodyStatus(4005);
            LOGGER.warn("{}", bodyStatus.getMessage(), e);
            return new ResponseEntity<>(bodyStatus, HttpStatus.OK);

        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            bodyStatus = Utils.bodyStatus(4011);
            LOGGER.warn("{}", bodyStatus.getMessage(), e);
            return new ResponseEntity<>(bodyStatus, HttpStatus.OK);

        } else if (e instanceof HttpMessageNotReadableException) {
            bodyStatus = Utils.bodyStatus(4004);
            LOGGER.warn("{}", bodyStatus.getMessage(), e);
            return new ResponseEntity<>(bodyStatus, HttpStatus.OK);

        } else if (e instanceof NumberFormatException) {
            bodyStatus = Utils.bodyStatus(4008);
            LOGGER.warn("{}", bodyStatus.getMessage(), e);
            return new ResponseEntity<>(bodyStatus, HttpStatus.OK);

        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            if (bindingResult.hasErrors() && bindingResult.hasFieldErrors()) {
                FieldError fieldError = bindingResult.getFieldError();
                BodyValidStatus bodyValidStatus = new BodyValidStatus.Builder()
                        .code(4006)
                        .message(fieldError.getDefaultMessage())
                        .field(fieldError.getField())
                        .build();
                LOGGER.warn("{}", bodyValidStatus.getMessage(), e);
                return new ResponseEntity<>(bodyValidStatus, HttpStatus.OK);
            } else {
                bodyStatus = Utils.bodyStatus(4006);
                LOGGER.warn("{}", bodyStatus.getMessage(), e);
                return new ResponseEntity<>(bodyStatus, HttpStatus.OK);
            }
        } else {
            bodyStatus = Utils.bodyStatus(5000);
            LOGGER.error("被动抛出异常:访问{}时，出现[{}:{}]", uri, bodyStatus.getCode(), bodyStatus.getMessage(), e);
            bodyStatus.setMessage("提示:" + bodyStatus.getMessage() +
                    "【财税平台" + bodyStatus.getCode() + "】");
            return new ResponseEntity<>(bodyStatus, HttpStatus.OK);
        }
    }
}
