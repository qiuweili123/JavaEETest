package com.ns.springboothikaricp.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseContoller {

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpServletResponse getResponse() {
        return new ServletWebRequest(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()).getResponse();
    }
}
