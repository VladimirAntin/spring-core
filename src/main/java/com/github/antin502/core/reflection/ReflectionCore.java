package com.github.antin502.core.reflection;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 27.02.2020
 * Time: 20:28
 */
public class ReflectionCore {

    public static void createClass(FileReflection fileReflection) throws IOException {
        File sourceFile = new File(fileReflection.getPath().concat("/").concat(fileReflection.getClassName()).concat(".java"));
        File generatedSources = new File(fileReflection.getGeneratedSource().concat("/").concat(fileReflection.getClassName()).concat(".java"));

        writeInFile(sourceFile, fileReflection.getContent());
        writeInFile(generatedSources, fileReflection.getContent());

        // compile the source file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        sourceFile.delete();
        fileManager.close();

    }


    private static void writeInFile(File sourceFile, String content) throws IOException {
        FileWriter writer = new FileWriter(sourceFile);
        writer.write(content);
        writer.close();
    }

}
