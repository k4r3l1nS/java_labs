package com.k4r3l1ns.service;

import com.k4r3l1ns.models.UnbalancedBracketsException;
import com.k4r3l1ns.service.utils.BracketUtil;
import com.k4r3l1ns.service.utils.FormConversionUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ExpressionEvaluator {
    public static final int DECIMAL_SCALE = 10;

    public static BigDecimal apply(String expression) {

        try {
            BracketUtil.throwIfUnbalanced(expression);

            String postfix = FormConversionUtil.expressionToPostfix(expression);

            Deque<BigDecimal> stack = new ArrayDeque<>();
            Scanner scanner = new Scanner(postfix);

            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    stack.push(BigDecimal.valueOf(scanner.nextDouble()));
                } else {
                    BigDecimal secondNumber = stack.pop();
                    BigDecimal firstNumber = stack.pop();
                    char op = scanner.next().charAt(0);
                    switch (op) {
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
            scanner.close();
            return stack.pop();
        } catch (UnbalancedBracketsException | ArithmeticException ex){
            ex.printStackTrace();
        }
        return null;
    }
}