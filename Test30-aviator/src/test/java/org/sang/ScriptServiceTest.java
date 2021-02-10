package org.sang;

import org.junit.Test;
import org.sang.service.ScriptService;
import org.sang.util.IpUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class ScriptServiceTest extends ApplicationTests {
    @Resource
    private ScriptService service;

    @Test
    public void  testFillData(){
        Map<String,String> dataMap=new HashMap<>();
        dataMap.put("cardId","132135198312141770");
        dataMap.put("ip","106.38.3.106");

        String script=" dataMap.birthPlace=IDCardUtil.getBrithPlace(dataMap.cardId);" +
                " dataMap.cityId=IpUtil.getCityId(dataMap.ip);";
        service.fillData(script,"dataMap",dataMap);
        System.out.println("输出："+dataMap);
    }
}
