package com.hzh.calculator;

public interface Token {

    String text();

    boolean isOpeartor();

    boolean isFunction();

    boolean isNumber();

    boolean isBracket();

    TokenType getType();
}
