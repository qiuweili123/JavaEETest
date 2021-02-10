package org.sang;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import javax.annotation.Resource;

public class FeignTest extends ApplicationTests {

    @Resource
    private PlatformClient client;

    @Test
    public void testDecryptMobile() {
        long current = System.currentTimeMillis();

        JSONObject jsonObject = client.decryptMobile("M20000000095");
        System.out.println(System.currentTimeMillis() - current);
    }
}
