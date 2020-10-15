package com.github.vladimirantin.core;

import com.github.vladimirantin.core.model.CoreModel;
import com.squareup.javapoet.ClassName;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 28.02.2020
 * Time: 23:27
 */
public class CoreImplPlugin {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        run();

        ClassName className = ClassName.get(CoreModel.class);
        Class c = Class.forName(className.canonicalName());
        System.out.println(c.getName());
    }


    private static void run() throws IOException {
/*        Reflections reflections = new Reflections("");
        List<Class<?>> classes = new ArrayList<>(reflections.getTypesAnnotatedWith(SpringBootApplication.class));
        if(classes.size() == 1) {
            Map<String, List<FileReflection>> result = GeneratorImpl.all(classes.get(0));

            for (String key : result.keySet()) {
                for (FileReflection fileReflection : result.get(key)) {
                    ReflectionCore.createClass(fileReflection);
                }
            }
        }*/

    }

}
