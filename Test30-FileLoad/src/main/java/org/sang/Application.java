package org.sang;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;


/**
 * @author tiny
 */
@SpringBootApplication
public class Application  implements ApplicationRunner {
    //也可以使用http进行远程加载
    @Value("classpath:file/cityCode.json")
   private Resource cityJson;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String jsonStr = FileUtils.readFileToString(cityJson.getFile());
        List<Map> list = JSON.parseArray(jsonStr, Map.class);
        System.out.println(list);
    }
}
