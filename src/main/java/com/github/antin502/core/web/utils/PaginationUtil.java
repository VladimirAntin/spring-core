package com.github.antin502.core.web.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 10.06.2019
 * Time: 19:36
 */
public class PaginationUtil {

    public static HttpHeaders generatePaginationHttpHeaders(Page<?> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        headers.add("X-Total-Pages", String.valueOf(page.getTotalPages()));
        return headers;
    }

    public static <D> Page<D> generateBody(Collection<D> dtos, Page<?> page) {
        return new PageImpl<D>(new ArrayList<D>(dtos), page.getPageable(), page.getTotalElements());
    }


}
