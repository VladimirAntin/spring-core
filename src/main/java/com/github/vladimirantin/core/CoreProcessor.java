package com.github.vladimirantin.core;

import com.github.vladimirantin.core.reflection.CoreImpl;
import com.github.vladimirantin.core.reflection.FileReflection;
import com.github.vladimirantin.core.reflection.GeneratorImpl;
import com.github.vladimirantin.core.utils.Try;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 14.10.2020
 * Time: 15:09
 */

/**
 * References
 * https://www.logicbig.com/tutorials/core-java-tutorial/java-se-annotation-processing-api/annotation-processor-generate-code.html
 * https://www.baeldung.com/java-annotation-processing-builder
 */
@AutoService(Processor.class)
public class CoreProcessor extends AbstractProcessor {
    @SneakyThrows
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        AtomicReference<String> defaultPackage = new AtomicReference<>("");
        Try.then(() -> {
            for (Element root : roundEnvironment.getElementsAnnotatedWith(SpringBootApplication.class)) {
                TypeElement typeElement = (TypeElement) root;
                ClassName className = ClassName.get(typeElement);
                defaultPackage.set(className.packageName());
                break;
            }
        });

        Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(CoreImpl.class);
        for (Element annotatedElement : annotatedElements) {
            CoreImpl coreImpl = annotatedElement.getAnnotation(CoreImpl.class);
            TypeElement typeElement = (TypeElement) annotatedElement;
            ClassName className = ClassName.get(typeElement);

            List<FileReflection> fileReflections = GeneratorImpl.one(className, coreImpl, getDto(coreImpl), defaultPackage.get());
            for (FileReflection fileReflection : fileReflections) {
                generateClass(String.format("%s.%s", fileReflection.getPackagePath(), fileReflection.getClassName()), fileReflection.getContent());
            }

        }

        return true;
    }

    private void generateClass(String path, String content) throws IOException {
        JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(path);
        Writer writer = sourceFile.openWriter();
        writer.write(content);
        writer.close();
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
        AtomicReference<TypeMirror> clazzType = null;
        Try.thenCatch(()  -> {
            coreImpl.DTO();
        }, e -> {
            if (e instanceof MirroredTypeException) {
                MirroredTypeException mte = (MirroredTypeException) e;
                clazzType.set(mte.getTypeMirror());
            }
        });
        return getExtracted(clazzType.get());
    }

    private ClassName getExtracted(TypeMirror typeMirror) {
        Element element = processingEnv.getTypeUtils().asElement(typeMirror);

        // instanceof implies null-ckeck
        return (element instanceof TypeElement)
                ? ClassName.get((TypeElement)element) : null;
    }
}
