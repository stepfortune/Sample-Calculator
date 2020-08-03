package com.hzh.calculator;

import java.math.BigDecimal;

public class Number extends AbstractToken {
    private final BigDecimal number;

    public Number(String num) {
        number = new BigDecimal(num);
    }

    public String text() {
        return number.toPlainString();
    }

    public TokenType getType() {
        return TokenType.NUMBER;
    }
}
