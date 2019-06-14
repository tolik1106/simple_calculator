package com.zhitar.calculator;

import com.zhitar.calculator.calc.ArabicCalculator;
import com.zhitar.calculator.calc.Calculator;
import com.zhitar.calculator.calc.CalculatorType;
import com.zhitar.calculator.calc.RomeCalculator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorStorage {

    private Map<CalculatorType, Calculator> calculatorMap = new HashMap<>();

    public CalculatorStorage() {
        calculatorMap.put(CalculatorType.ARABIC, new ArabicCalculator());
        calculatorMap.put(CalculatorType.ROME, new RomeCalculator());
    }

    public Calculator getCalculator(CalculatorType type) {
        return (type != null) ? calculatorMap.get(type) : calculatorMap.get(CalculatorType.ARABIC);
    }
}
