package com.github.antin502.core.reflection;


import com.github.antin502.core.template.*;
import org.reflections.Reflections;

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

    public static Map<String, List<FileReflection>> all(Class<?> app) throws IOException {
        Reflections reflections = new Reflections("");
        if (app == null) {
            return new HashMap<>();
        }
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(CoreImpl.class);
        String defaultPackage = app.getPackage().getName();
        Map<String, List<FileReflection>> retVal = new LinkedHashMap<>();
        for (Class<?> aClass : annotated) {
            String classNameLower = aClass.getSimpleName().toLowerCase();
            String className = aClass.getSimpleName();
            retVal.put(classNameLower, new ArrayList<>());
            String dtoClasses = createFolder(defaultPackage, classNameLower, "web/DTO", "classes");
            String mapperClasses = createFolder(defaultPackage, classNameLower, "web/mapper", "classes");
            String repoClasses = createFolder(defaultPackage, classNameLower, "repo", "classes");
            String serviceClasses = createFolder(defaultPackage, classNameLower, "service", "classes");
            String controllerClasses = createFolder(defaultPackage, classNameLower, "web/rest", "classes");
            String dtoGeneratedSource = createFolder(defaultPackage, classNameLower, "web/DTO", "generated-sources/annotations");
            String mapperGeneratedSource = createFolder(defaultPackage, classNameLower, "web/mapper", "generated-sources/annotations");
            String repoGeneratedSource = createFolder(defaultPackage, classNameLower, "repo", "generated-sources/annotations");
            String serviceGeneratedSource = createFolder(defaultPackage, classNameLower, "service", "generated-sources/annotations");
            String controllerGeneratedSource = createFolder(defaultPackage, classNameLower, "web/rest", "generated-sources/annotations");
            HashMap<CoreImpl.ImplType, Boolean> types = new HashMap();
            for (CoreImpl.ImplType implType : aClass.getDeclaredAnnotation(CoreImpl.class).type()) {
                types.put(implType, true);
            }
            boolean all = types.getOrDefault(CoreImpl.ImplType.ALL,false);
            if (types.getOrDefault(CoreImpl.ImplType.DTO,false) || all) {
                retVal.get(classNameLower).add(new FileReflection().setContent(CreateDTO.create(aClass, defaultPackage)).setPath(dtoClasses).setClassName(className.concat("DTO")).setGeneratedSource(dtoGeneratedSource));
            }
            if (types.getOrDefault(CoreImpl.ImplType.MAPPER,false) || all) {
                retVal.get(classNameLower).add(new FileReflection().setContent(CreateMapper.create(aClass, defaultPackage)).setPath(mapperClasses).setClassName(className.concat("Mapper")).setGeneratedSource(mapperGeneratedSource));
            }
            if (types.getOrDefault(CoreImpl.ImplType.REPO,false) || all) {
                retVal.get(classNameLower).add(new FileReflection().setContent(CreateRepo.create(aClass, defaultPackage)).setPath(repoClasses).setClassName(className.concat("Repo")).setGeneratedSource(repoGeneratedSource));
            }
            if (types.getOrDefault(CoreImpl.ImplType.SERVICE,false) || all) {
                retVal.get(classNameLower).add(new FileReflection().setContent(CreateService.create(aClass, defaultPackage)).setPath(serviceClasses).setClassName(className.concat("Service")).setGeneratedSource(serviceGeneratedSource));
            }
            if (types.getOrDefault(CoreImpl.ImplType.CONTROLLER,false) || all) {
                retVal.get(classNameLower).add(new FileReflection().setContent(CreateController.create(aClass, defaultPackage)).setPath(controllerClasses).setClassName(className.concat("Controller")).setGeneratedSource(controllerGeneratedSource));
            }
        }
        return retVal;
    }


    private static String createFolder(String defaultPackageName, String className, String packageType, String targetChildFolder) {
        String folder = getRoot(defaultPackageName, targetChildFolder).concat("/generatedSources/").concat(className.toLowerCase()).concat("/").concat(packageType);
        new File(folder).mkdirs();
        return folder;
    }

    private static String getRoot(String packageName, String targetChildFolder){
        File file = new File("");
        return file.getAbsolutePath().concat(String.format("/target/%s/%s", targetChildFolder, packageName.replaceAll("\\.", "/")));
    }
}
