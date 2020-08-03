package com.hzh.calculator;

import com.hzh.calculator.function.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Expression implements Computable {
    private static final Pattern pattern =
            Pattern.compile("\\s*((?<num>\\d*\\.\\d+|\\d+)|(?<op>[+\\-*/])|(?<bracket>[()])|(?<func>[A-Za-z]+\\(.*\\)))\\s*");

    public List<Token> tokens;

    public Expression(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Expression(String midExp) {
        tokens = praseTokens(midExp);
    }

    private List<Token> praseTokens(String exp) {
        List<Token> ts = new ArrayList<>();
        Matcher matcher = pattern.matcher(exp);
        int start = 0;
        int end = exp.length();
        while (start < end) {
            matcher.region(start, end);
            if (matcher.lookingAt()) {
                ts.add(getToken(matcher));
                start = matcher.end();
            } else {
                String errExp = exp.substring(start);
                throw new RuntimeException("Error Expression: " + errExp);
            }
        }
        return ts;

    }

    private Token getToken(Matcher matcher) {
        if (matcher.group(0) != null) {
            if (matcher.group("num") != null) {
                return new Number(matcher.group("name"));
            } else if (matcher.group("op") != null) {
                return new Operator(matcher.group("op").charAt(0));
            } else if (matcher.group("bracket") != null) {
                return new Bracket(matcher.group("bracket").charAt(0));
            } else {
                return new Function(matcher.group("func")).getResult();
            }
        }
        throw new RuntimeException("UnExpected Error");
    }

    @Override
    public Number getResult(Number... nums) {
        return null;
    }

    @Override
    public Number getResult() {
        return null;
    }
}
