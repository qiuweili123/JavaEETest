package org.sang;

import com.googlecode.aviator.AviatorEvaluator;
import org.sang.util.IDCardUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author tiny
 */
@SpringBootApplication
public class Application  implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int chinaIdMinLength = IDCardUtil.CHINA_ID_MIN_LENGTH;
        System.out.println(chinaIdMinLength);
        System.out.println(IDCardUtil.getAgeByIdCard("132135198312141770"));
        //  AviatorEvaluator.execute("let obj=IDCardUtil.getBrithPlace(cardId);println(obj);", AviatorEvaluator.newEnv("cardId", "132135"));
    }
}


