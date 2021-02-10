package org.sang;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.spring.SpringContextFunctionLoader;
import org.junit.Test;
import org.sang.service.UserService;
import org.sang.util.IDCardUtil;
import org.sang.util.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;

public class FunctionTest extends ApplicationTests {



 /*    static {
         try {
             AviatorEvaluator.addStaticFunctions("IDCardUtil", IDCardUtil.class);
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (NoSuchMethodException e) {
             e.printStackTrace();
         }
     }
*/
    @Test
    public void testAdd(){
    /*    AviatorEvaluator.addFunctionLoader(springContextFunctionLoader);
        System.out.println(springContextFunctionLoader==null);
*/
           AviatorEvaluator.execute("let obj=springAdd(x,y);println(obj.name);", AviatorEvaluator.newEnv("x", 1, "y", 99));

    }

    @Test
    public void testIdCard(){
    /*    AviatorEvaluator.addFunctionLoader(springContextFunctionLoader);
        System.out.println(springContextFunctionLoader==null);
*/
     //   AviatorEvaluator.execute("let obj=IDCardUtil.getBrithPlace(cardId);println(obj);", AviatorEvaluator.newEnv("cardId", "132135"));
        UserService bean = SpringUtil.getBean(UserService.class);

        System.out.println(bean);


    }
}
