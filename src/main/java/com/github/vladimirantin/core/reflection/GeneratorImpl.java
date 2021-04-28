package com.github.vladimirantin.core.reflection;


import com.github.vladimirantin.core.template.CreateController;
import com.github.vladimirantin.core.template.CreateMapper;
import com.github.vladimirantin.core.template.CreateRepo;
import com.github.vladimirantin.core.template.CreateService;
import com.squareup.javapoet.ClassName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        HashMap<CoreImpl.ImplType, Boolean> types = new HashMap();
        if (coreImpl.type().length == 0) {
            types.put(CoreImpl.ImplType.STANDARD, true);
        }
        for (CoreImpl.ImplType implType : coreImpl.type()) {
            types.put(implType, true);
        }
        String pkg = String.format("%s.coreImpl.%s",defaultPackage, classNameLower);
        boolean all = types.getOrDefault(CoreImpl.ImplType.STANDARD, false);
        boolean extended = types.getOrDefault(CoreImpl.ImplType.EXTENDED, false);

        if (types.getOrDefault(CoreImpl.ImplType.REPO, false) || all || extended) {
            retVal.add(new FileReflection().setContent(CreateRepo.create(aClass, defaultPackage)).setClassName(className.concat("Repo")).setPackagePath(pkg));
        }
        if (types.getOrDefault(CoreImpl.ImplType.SERVICE, false) || all || extended) {
            retVal.add(new FileReflection().setContent(CreateService.create(aClass, defaultPackage)).setClassName(className.concat("Service")).setPackagePath(pkg));
        }
        if (types.getOrDefault(CoreImpl.ImplType.MAPPER, false) || all || extended) {
            retVal.add(new FileReflection().setContent(CreateMapper.create(aClass, dto, defaultPackage)).setClassName(className.concat("Mapper")).setPackagePath(pkg));
        }
        if (types.getOrDefault(CoreImpl.ImplType.CONTROLLER, false) || all) {
            retVal.add(new FileReflection().setContent(CreateController.create(aClass, dto, defaultPackage)).setClassName(className.concat("Controller")).setPackagePath(pkg));
        }
        if (types.getOrDefault(CoreImpl.ImplType.EXTENDED_CONTROLLER, false) || extended) {
            retVal.add(new FileReflection().setContent(CreateController.createExtended(aClass, dto, defaultPackage)).setClassName(className.concat("Controller")).setPackagePath(pkg));
        }
        return retVal;
    }




}
