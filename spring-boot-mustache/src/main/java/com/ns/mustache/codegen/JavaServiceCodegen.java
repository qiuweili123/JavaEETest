package com.ns.mustache.codegen;

import io.swagger.codegen.*;
import io.swagger.codegen.languages.JavaClientCodegen;


public class JavaServiceCodegen extends JavaClientCodegen
{
    public JavaServiceCodegen()
    {
        apiPackage = "ni.jun.yang.api";
        modelPackage = "ni.jun.yang.api.bean";
        modelTemplateFiles.put("bean.mustache", ".java");
        apiTemplateFiles.put("servicerest.mustache", ".java");
    }
}