package com.github.vladimirantin.core;

import com.github.vladimirantin.core.reflection.*;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 14.10.2020
 * Time: 15:09
 */
//@SupportedAnnotationTypes("com.github.vladimirantin.core.reflection.CoreImpl")
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class CoreProcessor extends AbstractProcessor {
    ProcessingEnvironment processingEnv;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.processingEnv = processingEnv;
    }

    @SneakyThrows
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        String defaultPackage = "";
        for (Element root : roundEnvironment.getElementsAnnotatedWith(SpringBootApplication.class)) {
            try {
                TypeElement typeElement = (TypeElement) root;
                ClassName className = ClassName.get(typeElement);
                defaultPackage = className.packageName();
                break;
            } catch (Exception e) { }
        }

        Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(CoreImpl.class);
        for (Element annotatedElement : annotatedElements) {
            CoreImpl coreImpl = annotatedElement.getAnnotation(CoreImpl.class);
            TypeElement typeElement = (TypeElement) annotatedElement;
            ClassName className = ClassName.get(typeElement);
            System.out.println("[Vladimir_Antin] - "+className.canonicalName());
            System.out.println("[Vladimir_Antin] - "+annotatedElement);

            List<FileReflection> fileReflections = GeneratorImpl.one(className, coreImpl, getDto(coreImpl), defaultPackage);
            for (FileReflection fileReflection : fileReflections) {
                ReflectionCore.createClass(fileReflection);
            }

        }

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(CoreImpl.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private ClassName getDto(CoreImpl coreImpl) {
        TypeMirror clazzType = null;
        try {
            coreImpl.DTO();
        } catch (MirroredTypeException mte) {
            clazzType = mte.getTypeMirror();
        }
        return getExtracted(clazzType);
    }

    private ClassName getExtracted(TypeMirror typeMirror) {
        Element element = processingEnv.getTypeUtils().asElement(typeMirror);

        // instanceof implies null-ckeck
        return (element instanceof TypeElement)
                ? ClassName.get((TypeElement)element) : null;
    }
}
