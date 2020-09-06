package com.js.entity;

import lombok.Data;

/**
 * @program: OANacos
 * @Date: 2020/8/13 13:53
 * @Author: jiangshuang
 * @Description:
 */
@Data
public class RouteEntity {

    private String id;

    private PredicateEntity[] predicates;

    private FilterEntity[] filters;

    private String uri;
}
