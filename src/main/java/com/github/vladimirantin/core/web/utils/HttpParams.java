package com.github.vladimirantin.core.web.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 27.11.2019
 * Time: 20:49
 */
@Getter
@Setter
public class HttpParams {
    public enum BodyType {page, array}

    private BodyType body = BodyType.page;

}
