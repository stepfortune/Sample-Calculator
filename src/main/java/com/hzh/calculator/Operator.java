package com.hzh.calculator;

public class Operator extends AbstractToken {
    public final char value;

    public Operator(char value) {
        if (value == '+' || value == '-' || value == '*' || value == '/')
            this.value = value;
        else
            throw new RuntimeException("Unknown Opeartor char: " + value);
    }

    public String text() {
        return String.valueOf(value);
    }

    public boolean isHighPriority() {
        if (value == '*' || value == '/')
            return true;
        return false;
    }

    public TokenType getType() {
        return TokenType.OPERATOR;
    }
}
