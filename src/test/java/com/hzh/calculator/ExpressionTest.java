package com.hzh.calculator;

import com.hzh.calculator.util.ClassUtil;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ExpressionTest {
    private static String exp1 = "(multi(1,2) + pow(5,2)) / 6";

    @Test
    public void getClasses() throws ClassNotFoundException, IOException {
/*        List<Class<?>> cls = ClassUtil.getClasses(Expression.class);
        for (Class<?> c : cls) {
            System.out.println(c.getSimpleName());
        }*/
    }

}
