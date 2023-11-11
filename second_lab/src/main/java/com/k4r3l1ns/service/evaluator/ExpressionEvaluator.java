package com.k4r3l1ns.service.evaluator;

import com.k4r3l1ns.models.Expression;
import com.k4r3l1ns.models.exceptions.UndetectedVariablesException;
import com.k4r3l1ns.service.utils.BracketUtil;
import com.k4r3l1ns.service.designation.VariableService;
import com.k4r3l1ns.service.utils.FormConversionUtil;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RequiredArgsConstructor
public class ExpressionEvaluator {

    public static final int DECIMAL_SCALE = 10;

    /**
     * Вычисляет значение выражения
     *
     * @param expression Класс, содержащий выражение и map с переменными
     * @return Значение выражения
     */
    public static BigDecimal apply(Expression expression) {

        try {
            BracketUtil.throwIfUnbalanced(expression);

            String numericExpressionText = VariableService.replaceVariables(expression);
            String postfix = FormConversionUtil.expressionToPostfix(numericExpressionText);

            if (expression.getVariableMap().containsValue(null)) {
                throw new UndetectedVariablesException();
            }

            Deque<BigDecimal> stack = new ArrayDeque<>();
            Scanner scanner = new Scanner(postfix);

            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    stack.push(BigDecimal.valueOf(scanner.nextDouble()));
                } else {
                    String operation = scanner.next();
                    if (!FormConversionUtil.OPERATORS.contains(operation.charAt(0))) {
                        stack.push(
                                BigDecimal.valueOf(
                                        Expression
                                                .FUNCTION_MAP
                                                .get(operation)
                                                .apply(stack.pop().doubleValue())
                                )
                        );
                    } else {
                        BigDecimal secondNumber = stack.pop();
                        BigDecimal firstNumber = stack.pop();
                        switch (operation.charAt(0)) {
                            case '+':
                                stack.push(firstNumber.add(secondNumber));
                                break;
                            case '-':
                                stack.push(firstNumber.subtract(secondNumber));
                                break;
                            case '*':
                                stack.push(firstNumber.multiply(secondNumber));
                                break;
                            case '/':
                                stack.push(
                                        firstNumber.divide(
                                                secondNumber,
                                                DECIMAL_SCALE,
                                                RoundingMode.HALF_UP
                                        )
                                );
                                break;
                            case '^':
                                stack.push(
                                        BigDecimal.valueOf(
                                                Math.pow(
                                                        firstNumber.doubleValue(),
                                                        secondNumber.doubleValue()
                                                )
                                        )
                                );
                        }
                    }
                }
            }
            scanner.close();
            return stack.pop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Вычисляет значение выражения
     *
     * @param expression Класс, содержащий выражение и map с переменными
     * @param scale Значение приближения "после запятой"
     * @return Значение выражения
     */
    public static BigDecimal apply(
            Expression expression,
            int scale
    ) {
        BigDecimal result = apply(expression);
        return result == null ? null : result.setScale(scale, RoundingMode.HALF_EVEN);
    }

    /**
     * Вычисляет значение выражения
     *
     * @param expression Класс, содержащий выражение и map с переменными
     * @param scale Значение приближения "после запятой"
     * @param roundingMode Режим округления результата
     * @return Значение выражения
     */
    public static BigDecimal apply(
            Expression expression,
            int scale,
            RoundingMode roundingMode
    ) {
        BigDecimal result = apply(expression);
        return result == null ? null : result.setScale(scale, roundingMode);
    }
}