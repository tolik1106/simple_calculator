package com.zhitar.calculator.calc;

import com.zhitar.calculator.exception.BadExpressionException;

public interface Calculator {
    String calculate(String expression) throws BadExpressionException;
}
