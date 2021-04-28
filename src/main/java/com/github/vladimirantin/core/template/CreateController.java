package com.github.vladimirantin.core.template;

import com.github.vladimirantin.core.template.parent.CreateFreeMaker;
import com.squareup.javapoet.ClassName;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.IOException;
import java.util.Map;


/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 22.02.2020
 * Time: 00:19
 */
public class CreateController extends CreateFreeMaker {

    public static String create(ClassName aClass, ClassName dtoClass, String defaultPackage) throws IOException {

        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(CreateController.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/CONTROLLER.ftl");


        String newClassName = String.format("%sController.java", aClass.simpleName());
        Map<String, Object> templateData = getDefaultTepmlateData(aClass, defaultPackage);
        templateData.put("DTO", dtoClass);
        return writeFile(templateData, template);
    }

    public static String createExtended(ClassName aClass, ClassName dtoClass, String defaultPackage) throws IOException {

        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(CreateController.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/EXTENDED_CONTROLLER.ftl.ftl");


        String newClassName = String.format("%sController.java", aClass.simpleName());
        Map<String, Object> templateData = getDefaultTepmlateData(aClass, defaultPackage);
        templateData.put("DTO", dtoClass);
        return writeFile(templateData, template);
    }

}
