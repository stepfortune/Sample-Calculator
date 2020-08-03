package com.hzh.calculator.function;

import com.hzh.calculator.AbstractToken;
import com.hzh.calculator.Computable;
import com.hzh.calculator.Number;
import com.hzh.calculator.TokenType;
import com.hzh.calculator.util.ClassUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function extends AbstractToken implements Computable {
    protected static Map<String, Computable> registry = new HashMap<>();

    public static void main(String[] args) {
        Set<Map.Entry<String, Computable>> entries = registry.entrySet();
        for (Map.Entry<String, Computable> entry : entries) {
            System.out.println(entry);
        }

        System.out.println("asdfasdfasdfasdfa");
    }

    /*static {
        try {
            List<Class<?>> classes = ClassUtil.getClasses(Function.class);
            for (Class<?> cls : classes) {
                if (Computable.class.isAssignableFrom(cls)) {
                    registry.put(cls.getSimpleName(), null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //do nothing
        }
    }*/

    private final String name;

    private final Number[] params;

    private static final Pattern pattern = Pattern.compile("(?<name>[A-Za-z]+)\\(\\s*(?<params>.*)\\s*\\)");

    public Function(String exp) {
        Matcher matcher = pattern.matcher(exp);
        if (!matcher.matches()) {
            throw new RuntimeException("wrong function expressiong: " + exp);
        }
        this.name = matcher.group("name");
        String[] nums = matcher.group("params").split("\\s*,\\s*");
        this.params = new Number[nums.length];
        for (int i = 0; i < nums.length; i++) {
            params[i] = new Number(nums[i]);
        }
    }

    @Override
    public Number getResult(Number... nums) {
        return null;
    }

    @Override
    public Number getResult() {
        Computable func = registry.get(name);
        if (func == null)
            throw new RuntimeException("function not existed: " + name);

        return func.getResult(params);
    }

    @Override
    public TokenType getType() {
        return null;
    }

    @Override
    public String text() {
        return null;
    }
}
