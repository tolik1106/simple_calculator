package com.zhitar.calculator;

import com.zhitar.calculator.calc.CalculatorType;
import com.zhitar.calculator.exception.BadExpressionException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Simple Calculator!");
        try (Scanner scanner = new Scanner(System.in)) {
            CalculatorStorage calculatorStorage = new CalculatorStorage();
            String line;
            CalculatorType type;
            do {
                System.out.println("Please input an expression to calculate (or 'exit' to quit):");
                line = scanner.nextLine().replaceAll(" ", "").toUpperCase();
                if ("EXIT".equals(line)) {
                    break;
                }
                String result = null;
                try {
                    result = calculatorStorage
                            .getCalculator(
                                    CalculatorType.getType(line)
                            )
                            .calculate(line);
                    System.out.println(result);
                } catch (BadExpressionException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            } while (true);
        }
    }
}
