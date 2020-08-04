package com.hzh.calculator.function;

import com.hzh.calculator.Computable;
import com.hzh.calculator.Number;

import java.math.BigDecimal;

public class PowerFunction implements Computable {


    @Override
    public Number getResult(Number... nums) {
        if (nums.length != 2)
            throw new RuntimeException("only allow 2 params");
        BigDecimal num1 = nums[0].number;
        BigDecimal num2 = nums[1].number;
        if (num1.toString().contains(".") || num2.toString().contains(".")) {
            return new Number(Math.pow(num1.doubleValue(), num2.doubleValue()));
        }
        long res = 1;
        long tmp = num1.longValue();
        long n2 = Math.abs(num2.longValue());
        for (int i = 0; i < 31; i++) {
            if (((1 << i) & n2) != 0) {
                res = res * tmp;
            }
            tmp *= tmp;
        }
        return num2.longValue() < 0 ? new Number((1 / (double) res)) : new Number(res);
    }

    @Override
    public Number getResult() {
        return null;
    }
}
