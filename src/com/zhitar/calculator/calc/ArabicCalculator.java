package com.zhitar.calculator.calc;

import com.zhitar.calculator.exception.BadExpressionException;

public class ArabicCalculator implements Calculator {
    @Override
    public String calculate(String expression) throws BadExpressionException {
        Result result = plusMinus(expression);
        if (!result.rest.isEmpty()) {
            throw new BadExpressionException();
        }
        return String.valueOf(result.accumulator);
    }

    protected Result plusMinus(String expression) {
        Result current = mulDiv(expression);
        int accumulator = current.accumulator;

        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) {
                break;
            }
            char sign = current.rest.charAt(0);

            String next = current.rest.substring(1);
            current = mulDiv(next);
            if (sign == '+') {
                accumulator += current.accumulator;
            } else {
                accumulator -= current.accumulator;
            }
        }
        return new Result(accumulator, current.rest);
    }

    protected Result mulDiv(String expression) {
        Result current = getNumber(expression);
        int accumulator = current.accumulator;

        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if (sign != '*' && sign != '/') {
                return current;
            }
            String next = current.rest.substring(1);
            Result right = getNumber(next);
            switch (sign) {
                case '*':
                    accumulator *= right.accumulator;
                    break;
                case '/':
                    accumulator /= right.accumulator;
            }
            current = new Result(accumulator, right.rest);
        }
    }

    protected Result getNumber(String str) {
        int i = 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            i++;
        }
        if (i == 0) {
            throw new BadExpressionException();
        }
        int number = Integer.parseInt(str.substring(0, i));
        String rest = str.substring(i);
        return new Result(number, rest);
    }
}
