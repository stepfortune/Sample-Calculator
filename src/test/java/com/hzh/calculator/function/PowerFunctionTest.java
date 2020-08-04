package com.hzh.calculator.function;

import com.hzh.calculator.Number;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PowerFunctionTest {

    @Test
    public void test1() {
        Number n1 = new Number(3);
        Number n2 = new Number(9);
        Number res1 = new PowerFunction().getResult(n1,n2);
        Assert.assertEquals(res1.text(), "19683");
    }
}
