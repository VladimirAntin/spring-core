package com.github.vladimirantin.core.model;

import com.github.vladimirantin.core.reflection.Invoker;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 05.07.2020
 * Time: 18:30
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class CodeCoreModel extends CoreModel implements Invoker {

    @NotNull
    @Size(min = 3, max = 50)
    private String code;

    @NotNull
    @Size(min = 3, max = 255)
    private String name;

}

