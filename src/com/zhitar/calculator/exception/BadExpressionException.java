package com.zhitar.calculator.exception;

public class BadExpressionException extends RuntimeException {
    public BadExpressionException() {
        super("Bad expression!");
    }
}
