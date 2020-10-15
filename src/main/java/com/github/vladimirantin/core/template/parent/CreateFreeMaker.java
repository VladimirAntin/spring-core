package com.github.vladimirantin.core.template.parent;

import com.google.common.base.CaseFormat;
import com.squareup.javapoet.ClassName;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 22.02.2020
 * Time: 01:36
 */
public class CreateFreeMaker {


    public static String writeFile(Map dataMap, Template template) {
        try {
            StringWriter stringWriter = new StringWriter();
            template.process(dataMap, stringWriter);
            return stringWriter.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static Map<String, Object> getDefaultTepmlateData(Class<?> aClass, String defaultPackage){
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("typeLower", aClass.getSimpleName().toLowerCase());
        templateData.put("type", aClass.getSimpleName());
        templateData.put("rootPackage", defaultPackage);
        templateData.put("package", aClass.getPackage().getName());
        templateData.put("api", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, aClass.getSimpleName()));
        return templateData;
    }
    public static Map<String, Object> getDefaultTepmlateData(ClassName aClass, String defaultPackage){
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("typeLower", aClass.simpleName().toLowerCase());
        templateData.put("type", aClass.simpleName());
        templateData.put("rootPackage", defaultPackage);
        templateData.put("package", aClass.packageName());
        templateData.put("api", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, aClass.simpleName()));
        return templateData;
    }
}
