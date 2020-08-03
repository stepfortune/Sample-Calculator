package com.hzh.calculator;

public abstract class AbstractToken implements Token {

    @Override
    public boolean isOpeartor() {
        return getType() == TokenType.OPERATOR;
    }

    @Override
    public boolean isFunction() {
        return getType() == TokenType.FUNCTION;
    }

    @Override
    public boolean isNumber() {
        return getType() == TokenType.NUMBER;
    }

    @Override
    public boolean isBracket() {
        return getType() == TokenType.BRACKET;
    }
}
