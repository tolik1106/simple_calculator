package com.zhitar.calculator.calc;

public enum CalculatorType {
    ARABIC, ROME;

    public static CalculatorType getType(String expr) {
        if (expr != null && expr.length() > 0) {
            return Character.isDigit(expr.charAt(0)) ? ARABIC : ROME;
        }
        return null;
    }
}
