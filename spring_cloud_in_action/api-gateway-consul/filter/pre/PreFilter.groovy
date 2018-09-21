package com.didispace.filter.pre

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest

class PreFilter extends ZuulFilter {

    Logger log = LoggerFactory.getLogger(PreFilter.class);

    @Override
    String filterType() {
        return "pre"
    }

    @Override
    int filterOrder() {
        return 1000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        log.info("this is a pre filter")
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest()
        log.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()))
        return null
    }
}