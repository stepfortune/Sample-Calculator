package com.hzh.calculator;

public class Bracket extends AbstractToken {
    public static final char CHAR_LEFT_BRACKET = '(';
    public static final char CHAR_RIGHT_BRACKET = '(';

    private final char value;

    public Bracket(char value)  {
        if (value == CHAR_LEFT_BRACKET || value == CHAR_RIGHT_BRACKET) {
            this.value = value;
        } else throw new RuntimeException("unknown bracket char: " + value);
    }

    public TokenType getType() {
        return TokenType.BRACKET;
    }

    public boolean isLeft() {
        return value == CHAR_LEFT_BRACKET;
    }

    public boolean isRight() {
        return value == CHAR_RIGHT_BRACKET;
    }

    public String text() {
        return String.valueOf(value);
    }
}
