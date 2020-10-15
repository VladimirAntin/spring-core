package com.github.vladimirantin.core.reflection;


import com.github.vladimirantin.core.template.*;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import org.reflections.Reflections;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 21.02.2020
 * Time: 23:36
 */
public class GeneratorImpl {

    public static List<FileReflection> one(ClassName aClass, CoreImpl coreImpl, ClassName dto, String defaultPackage) throws IOException {
        List<FileReflection> retVal = new ArrayList<>();
        String classNameLower = aClass.simpleName().toLowerCase();
        String className = aClass.simpleName();

        String targetClasses = createFolder(defaultPackage, classNameLower, "classes");
        String targetGeneratedSource = createFolder(defaultPackage, classNameLower, "generated-sources/annotations");
        HashMap<CoreImpl.ImplType, Boolean> types = new HashMap();
/*
            for (CoreImpl.ImplType implType : aClass.getDeclaredAnnotation(CoreImpl.class).type()) {
                types.put(implType, true);
            }
*/
        if (coreImpl.type().length == 0) {
            types.put(CoreImpl.ImplType.ALL, true);
        }
        for (CoreImpl.ImplType implType : coreImpl.type()) {
            types.put(implType, true);
        }
        boolean all = types.getOrDefault(CoreImpl.ImplType.ALL, false);
//        retVal.add(new FileReflection().setContent(CreateDTO.create(coreImpl.DTO(), defaultPackage))/*.setPath(targetClasses)*/.setClassName(className.concat("DTO")).setGeneratedSource(targetGeneratedSource));
        if (types.getOrDefault(CoreImpl.ImplType.REPO, false) || all) {
            retVal.add(new FileReflection().setContent(CreateRepo.create(aClass, defaultPackage)).setPath(targetClasses).setClassName(className.concat("Repo")).setGeneratedSource(targetGeneratedSource));
        }
        if (types.getOrDefault(CoreImpl.ImplType.SERVICE, false) || all) {
            retVal.add(new FileReflection().setContent(CreateService.create(aClass, defaultPackage)).setPath(targetClasses).setClassName(className.concat("Service")).setGeneratedSource(targetGeneratedSource));
        }
        if (types.getOrDefault(CoreImpl.ImplType.MAPPER, false) || all) {
            retVal.add(new FileReflection().setContent(CreateMapper.create(aClass, dto, defaultPackage)).setPath(targetClasses).setClassName(className.concat("Mapper")).setGeneratedSource(targetGeneratedSource));
        }
        if (types.getOrDefault(CoreImpl.ImplType.CONTROLLER, false) || all) {
            retVal.add(new FileReflection().setContent(CreateController.create(aClass, dto, defaultPackage)).setPath(targetClasses).setClassName(className.concat("Controller")).setGeneratedSource(targetGeneratedSource));
        }
        return retVal;
    }


    private static String createFolder(String defaultPackageName, String className, String targetChildFolder) {
        String folder = getRoot(defaultPackageName, targetChildFolder).concat("/generatedSources/").concat(className.toLowerCase());
        new File(folder).mkdirs();
        return folder;
    }

    private static String getRoot(String packageName, String targetChildFolder) {
        File file = new File("");
        return file.getAbsolutePath().concat(String.format("/target/%s/%s", targetChildFolder, packageName.replaceAll("\\.", "/")));
    }


}
