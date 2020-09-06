package com.js.entity;

import lombok.Data;

import java.util.Map;

/**
 * @program: OANacos
 * @Date: 2020/8/13 13:54
 * @Author: jiangshuang
 * @Description:
 */
@Data
public class PredicateEntity {
    private Map<String,String> args;

    private String name;
}
