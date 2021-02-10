package org.sang;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.spring.SpringContextFunctionLoader;
import com.googlecode.aviator.spring.SringContextFunctionLoader;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

public class FunctionTest2 extends ApplicationTests {
    private SringContextFunctionLoader loader;

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        loader = new SringContextFunctionLoader(ctx);
        AviatorEvaluator.addFunctionLoader(loader);
    }

    @Test
    public void testAdd() {
        Object exec = AviatorEvaluator.exec("springAdd(x,y)", 1, 99);
        System.out.println(exec);
    }

    @AfterClass
    public static void tearDown() {
        AviatorEvaluator.addFunctionLoader(null);
    }
}
