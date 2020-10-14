package com.github.vladimirantin.core.reflection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 27.02.2020
 * Time: 21:33
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class FileReflection {

    private String path;
    private String generatedSource;
    private String content;
    private String className;

}
