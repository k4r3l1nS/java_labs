package com.k4r3l1ns;

import com.k4r3l1ns.models.Expression;
import com.k4r3l1ns.service.designation.VariableService;
import com.k4r3l1ns.service.evaluator.ExpressionEvaluator;
import com.k4r3l1ns.service.utils.FormConversionUtil;

import java.io.InputStream;

public class SecondLabApplication {

    /**
     * Дефолтный поток ввода
     */
    private static final InputStream DEFAULT_INPUT_STREAM = System.in;

    public static void main(String[] args) {

        Expression expression =
                new Expression("abs(4 + sin[cos(x1)]) - 2.3 * exp(PI * neg(x2))");

        // Альтернативный вариант - ввод выражения с консоли
        // Expression expression = new Expression();
        // expression.scanExpression(DEFAULT_INPUT_STREAM);

        expression.scanVariables(DEFAULT_INPUT_STREAM);

        var result = ExpressionEvaluator.apply(expression, 3);
        System.out.println(
                "Результат вычислений: " + VariableService.replaceVariables(expression) +
                        " = " + (result == null ? "-E-" : result)
        );
    }
}