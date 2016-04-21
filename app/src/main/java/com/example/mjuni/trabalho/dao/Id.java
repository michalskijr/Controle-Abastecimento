package com.example.mjuni.trabalho.dao;

/**
 * Created by mjunior on 03/04/2016.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id{
    boolean autoIncrement = true;
}