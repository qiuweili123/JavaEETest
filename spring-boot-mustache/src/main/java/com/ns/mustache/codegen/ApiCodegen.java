package com.ns.mustache.codegen;

import ch.qos.logback.core.util.FileUtil;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.models.Path;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiCodegen extends DefaultGenerator
{
    public List<File> generate() {
        List <Map<String,Object>> infoList = new ArrayList<>();
        List <Map<String,String>> importList = new ArrayList<>();
        Map<String,Path> pathMap = swagger.getPaths();
        Info info = new Info();
        info.apiPackage = config.apiPackage();
        info.modelPackage = config.modelPackage();
        info.basePath = swagger.getBasePath();
        info.className = swagger.getTags().get(0).getName();

        for (Map.Entry<String,Path> entry : pathMap.entrySet())
        {
            Map<String,Object> infoMap =  new HashMap<>();
            infoMap.put("urlName", entry.getKey());
            Path path = entry.getValue();
            changeType(path,infoMap,importList);
            infoMap.put("path",path);
            infoList.add(infoMap);
        }
        info.infoList = infoList;
        info.importList = importList;
        String outputFilePath = "src/main/java/ni/jun/yang/api/" + info.className + ".java";
        String templateFilePath = "src/main/resources/servicerest.mustache";
        String templateFileInfo = "";
        try {


            //获取模板信息
            templateFileInfo ="";// FileUtils.copyreadFile(templateFilePath);
            //生成模板
            Template template = Mustache.compiler().compile(templateFileInfo);
            //解析模板
            String result = template.execute(info);
            //生成Controller文件

          //  FileUtils.writwriteFile(result, outputFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    private void changeType(Path path, Map<String,Object> infoMap, List <Map<String,String>> importList)
    {
        List<Parameter> parameterList;
        Map<String, String> typeMap = config.typeMapping();
        if (path.getGet() != null)
        {
            infoMap.put("hasGet", true);
            parameterList = path.getGet().getParameters();
            for (Parameter parameter : parameterList)
            {
                PathParameter pathParameter = (PathParameter)parameter;
                pathParameter.setType(typeMap.get(pathParameter.getType()));
            }
            Property property = path.getGet().getResponses().get("200").getSchema();
            if (property != null)
            {
                RefProperty refProperty = (RefProperty)property;
                infoMap.put("responseType", refProperty.getSimpleRef());
                Map<String,String> map = new HashMap<>();
                map.put("import",config.modelPackage() + "." + refProperty.getSimpleRef());
                importList.add(map);
            }

        }
        //TODO 其他几种请求 put，post,delete...

    }

    class Info
    {
        public String apiPackage;
        public String modelPackage;
        public String basePath;
        public String className;
        public List <Map<String,String>> importList;
        public List <Map<String,Object>> infoList;
    }
}