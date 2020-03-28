package com.github.antin502.core;

import com.github.antin502.core.reflection.FileReflection;
import com.github.antin502.core.reflection.GeneratorImpl;
import com.github.antin502.core.reflection.ReflectionCore;
import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 28.02.2020
 * Time: 23:27
 */
public class CoreImplPlugin {

    public static void main(String[] args) throws IOException{
        run();
    }


    private static void run() throws IOException {
        Reflections reflections = new Reflections("");
        List<Class<?>> classes = new ArrayList<>(reflections.getTypesAnnotatedWith(SpringBootApplication.class));
        if(classes.size() == 1) {
            Map<String, List<FileReflection>> result = GeneratorImpl.all(classes.get(0));

            for (String key : result.keySet()) {
                for (FileReflection fileReflection : result.get(key)) {
                    ReflectionCore.createClass(fileReflection);
                }
            }
        }

    }

}
