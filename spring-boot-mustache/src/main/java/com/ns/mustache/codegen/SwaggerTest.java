package com.ns.mustache.codegen;

import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.ClientOpts;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
 
import java.io.*;

public class SwaggerTest
{

    public void Test(String filePath) throws IOException {
        String info = "";//FileUtils.readFile(filePath);

        //将yaml文件转化为Swagger对象
        Swagger swagger = new SwaggerParser().parse(info);

        //JavaServiceCodegen继承JavaClientCodegen（存放类的信息，类型对应["integer", "Integer"]表等等），用于扩展一些自定义功能
        JavaServiceCodegen serviceCodegen = new JavaServiceCodegen();
        ClientOptInput input = new ClientOptInput().opts(new ClientOpts()).swagger(swagger);
        input.setConfig(serviceCodegen);

        ApiCodegen apiCodegen = new ApiCodegen();
        apiCodegen.opts(input).generate();

    }
}