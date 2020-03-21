package com.github.antin502.core.web.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.antin502.core.reflection.Invoker;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 09.06.2019
 * Time: 17:22
 */
@Getter
@Setter
@ToString(includeFieldNames = false)
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CoreDTO implements Invoker {
    private long id;
    private int version;
}

