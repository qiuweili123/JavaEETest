package org.sang;

import com.alibaba.fastjson.JSONObject;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableRetry
public class PlatformClient {

    @Resource
    private PlatformFeign feign;

//  @Retryable(maxAttempts = 2)
    public JSONObject decryptMobile(String mobile) {
        long current = System.currentTimeMillis();
        try {
            JSONObject jsonObject = feign.decryptMobile(mobile, 1);
            return jsonObject;
        } catch (Exception e) {
            System.out.println("exec "+(System.currentTimeMillis() - current));
            throw e;
        }
    }

}
