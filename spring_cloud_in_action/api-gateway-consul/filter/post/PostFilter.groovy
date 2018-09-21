package com.didispace.filter.post

import com.netflix.zuul.ZuulFilter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PostFilter extends ZuulFilter {

    Logger log = LoggerFactory.getLogger(PostFilter.class);

    @Override
    String filterType() {
        return "post"
    }

    @Override
    int filterOrder() {
        return 2000
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() {
        log.info("this is a post filter aaaaaaa");
    }
}
