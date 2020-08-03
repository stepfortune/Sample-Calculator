package com.hzh.calculator.util;

import com.hzh.calculator.function.Function;
import com.hzh.calculator.function.LogFunction;
import com.hzh.calculator.function.PiFunction;
import com.hzh.calculator.function.PowerFunction;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;


public class ClassUtilTest {
    @Test
    public void getClasses() throws Exception {
        List<Class<?>> classes = ClassUtil.getClassesInSamePackage(Function.class, true);
        Assert.assertTrue(classes.contains(PiFunction.class) &&
                classes.contains(LogFunction.class) &&
                classes.contains(PowerFunction.class));

    }


}
