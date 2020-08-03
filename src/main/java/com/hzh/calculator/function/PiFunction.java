package com.hzh.calculator.function;

import com.hzh.calculator.Computable;
import com.hzh.calculator.Number;

public class PiFunction implements Computable {


    @Override
    public Number getResult(Number... nums) {
        if(nums.length == 0) return getResult();
        throw new RuntimeException("Unsupported Operation");
    }

    @Override
    public Number getResult() {
        return new Number(String.valueOf(Math.PI));
    }
}
