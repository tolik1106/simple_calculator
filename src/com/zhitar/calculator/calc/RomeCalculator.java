package com.zhitar.calculator.calc;

import com.zhitar.calculator.exception.BadExpressionException;

import java.util.HashMap;
import java.util.Map;

public class RomeCalculator extends ArabicCalculator {

    private static Map<Character, Integer> romeNumberMap = new HashMap<>();
    static {
        romeNumberMap.put('I', 1);
        romeNumberMap.put('V', 5);
        romeNumberMap.put('X', 10);
        romeNumberMap.put('L', 50);
        romeNumberMap.put('C', 100);
        romeNumberMap.put('D', 500);
        romeNumberMap.put('M', 1000);
    }
    @Override
    protected Result getNumber(String str) {
        int i = 0;
        while (i < str.length() && romeNumberMap.containsKey(str.charAt(i))) {
            i++;
        }
        if (i == 0) {
            throw new BadExpressionException();
        }
        String rome = str.substring(0, i);
        if (!rome.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
            throw new BadExpressionException();
        }
        String rest = str.substring(i);
        if (rome.length() == 1) {
            return new Result(romeNumberMap.get(rome.charAt(0)), rest);
        }
        int number = 0;
        for (int j = rome.length() - 1; j >= 0; j -= 2) {
            int first = romeNumberMap.get(rome.charAt(j));
            int second = 0;
            if (j != 0) {
                second = romeNumberMap.get(rome.charAt(j - 1));
            }
            if (first > second) {
                number += first - second;
            } else {
                number += first + second;
            }
        }
        return new Result(number, rest);
    }
}
