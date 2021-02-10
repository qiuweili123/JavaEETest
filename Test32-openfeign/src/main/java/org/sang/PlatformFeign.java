package org.sang;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 全局设置hearder
 */
//@FeignClient(name = "platformFeign", url = "http://127.0.0.1:8076/platform")
//@RequestMapping(value = "/", headers = {"Authorization=Basic aXNzX3Jpc2tfc2VydmljZTpmZmNjM2M1YTlmMzY0MDY1ODI4ODQ5ZDcyM2QyMTllMw=="}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
@FeignClient(name = "platformFeign", url = "#{systemProperties['url']}")
@RequestMapping(value = "/", headers = {"Authorization=Basic aXNzX3Jpc2tfc2VydmljZToxMjM0NTY=}"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//@RequestMapping( headers = {"Authorization=Basic aXNzX3Jpc2tfc2VydmljZTpmZmNjM2M1YTlmMzY0MDY1ODI4ODQ5ZDcyM2QyMTllMw==}"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public interface PlatformFeign {
    @PostMapping(value = "/desensitization/decryptPartOne")
    JSONObject decryptMobile(@RequestParam("busPwds") String busPwds, @RequestParam("type") Integer type);

}

