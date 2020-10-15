package com.github.vladimirantin.core.template;

import com.github.vladimirantin.core.template.parent.CreateFreeMaker;
import com.squareup.javapoet.ClassName;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.Map;


/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 22.02.2020
 * Time: 00:19
 */
public class CreateMapper extends CreateFreeMaker {

    public static String create(ClassName entityClass, ClassName dtoClass, String defaultPackage) throws IOException {

        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(CreateMapper.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/MAPPER.ftl");


        String newClassName = String.format("%sMapper.java", entityClass.simpleName());
        Map<String, Object> templateData = getDefaultTepmlateData(entityClass, defaultPackage);
        templateData.put("DTO", dtoClass);
        return writeFile(templateData, template);
    }

}
