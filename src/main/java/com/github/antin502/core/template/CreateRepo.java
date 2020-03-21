package com.github.antin502.core.template;

import com.github.antin502.core.template.parent.CreateFreeMaker;
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
public class CreateRepo extends CreateFreeMaker {

    public static String create(Class<?> aClass, String defaultPackage) throws IOException {

        Configuration cfg = new Configuration(new Version("2.3.23"));

        cfg.setClassForTemplateLoading(CreateRepo.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("templates/REPO.ftl");

        Map<String, Object> templateData = getDefaultTepmlateData(aClass, defaultPackage);
        return writeFile(templateData, template);
    }



}
