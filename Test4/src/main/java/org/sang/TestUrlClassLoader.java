package org.sang;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;


public class TestUrlClassLoader {

   // private static final String url="http://secoo-pro:secoo-PRO@nexus.secoo.com:8081/nexus/content/groups/secoo-group-pro/com/secoo/mall/common-core/1.0.14.RELEASE/common-core-1.0.14.RELEASE.jar";
    //private static final String url = "http://repo1.maven.org/maven2/com/google/guava/guava/18.0/guava-18.0.jar";
   //private static final String url ="http://nexus.secoo.com:8081/nexus/content/repositories/aliyun/commons-lang/commons-lang/2.6/commons-lang-2.6.jar";
   private static final String url ="http://localhost:6080/data/getSecurityFile.jar";
    public static void main(String[] args) throws Exception {
        System.out.println("loading start ...");
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        URLClassLoader urlClassLoader18 = new URLClassLoader(
                new URL[]{new URL(url)},
                currentClassLoader);

        //Class<?> aClass = urlClassLoader18.loadClass("com.secoo.mall.common.core.bean.BizResponse");
        // Class<?> aClass = urlClassLoader18.loadClass("com.google.common.base.Ascii");
       // Class<?> aClass = urlClassLoader18.loadClass("org.apache.commons.lang.ArrayUtils");
        Class<?> aClass = urlClassLoader18.loadClass("com.secoo.mall.app.security.encryptor.StringEncryptor");
       // Class.forName()
        Object o = aClass.newInstance();
        System.out.println("loading end ...");
        System.out.println(aClass.getName());
    }
}
