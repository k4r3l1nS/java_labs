package com.k4r3l1ns.service;

import com.k4r3l1ns.service.utils.FormConversionUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import static java.lang.Math.pow;

public class ExpressionEvaluator {

    public static int apply(String expression) {

        String postfix = FormConversionUtil.expressionToPostfix(expression);

        Deque<Integer> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(postfix);

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                stack.push(scanner.nextInt());
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                char op = scanner.next().charAt(0);
                switch (op) {
                    case '+':
                        stack.push(num1 + num2);
                        break;
                    case '-':
                        stack.push(num1 - num2);
                        break;
                    case '*':
                        stack.push(num1 * num2);
                        break;
                    case '/':
                        stack.push(num1 / num2);
                        break;
                }
            }
        }
        scanner.close();
        return stack.pop();
    }
}