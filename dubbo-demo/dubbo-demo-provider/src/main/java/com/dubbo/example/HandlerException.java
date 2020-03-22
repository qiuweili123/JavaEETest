package com.dubbo.example;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class HandlerException {
    @ExceptionHandler(BusinessException.class)
    public   Object businessExcBusinessExceptionepstionHandler(BusinessException e) {
        System.out.println("businessExcBusinessExceptionepstionHandler#################");

        Response response = new Response();
        response.setData(null);
        response.setCode(e.getCode());
        response.setMsg(e.getMsg());
        return response;
    }


    @ExceptionHandler(Exception.class)
    public   Object exceptionHandler(Exception e) {

        System.out.println("##########################################");
        Response response = new Response();
        response.setData("sdsdsd");
        response.setCode(CommonErrorCode.SYSTEM_INTERNAL_EXCEPTION.getCode());
        response.setMsg(CommonErrorCode.SYSTEM_INTERNAL_EXCEPTION.getMsg());
        return response;
    }
}
