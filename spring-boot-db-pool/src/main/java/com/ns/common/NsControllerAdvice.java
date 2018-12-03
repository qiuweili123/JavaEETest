package com.ns.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class NsControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger APP_LOGGER = LoggerFactory.getLogger("app");

    private static final Logger SERVICE_LOGGER = LoggerFactory.getLogger(ServiceException.class);


    /**
     * 403错误，权限不足
     *
     * @param ex
     * @return
     */
    //@ExceptionHandler(value = { Exception403.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public JSONObject forbiddenExceptionHandler(Exception ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", ex.getMessage());
        jsonObject.put("code", "100");
        return jsonObject;
    }

    /**
     * 处理其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object errorHandler(Exception e) {

        APP_LOGGER.error(ExceptionUtils.getStackTrace(e));

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("msg", e.getMessage());
        jsonObject.put("code", "100");

        return jsonObject;
    }

    /**
     * 处理业务异常
     *
     * @param e
     * @return
     */

    @ExceptionHandler(ServiceException.class)
    public Object serviceExceptionHandler(ServiceException e) {

        APP_LOGGER.info("进入 serviceException");

        APP_LOGGER.warn(ExceptionUtils.getStackTrace(e));

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("msg", e.getMsg());
        jsonObject.put("code", e.getCode());

        return jsonObject;
    }

}
