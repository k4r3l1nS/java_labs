package com.k4r3l1ns.service.utils;

import java.util.*;
public class FormConversionUtil {
    private static final String OPERATORS = "*/+-";
    private static final Map<Character, Integer> PRECEDENCE = Map.of(
            '*', 2,
            '/', 2,
            '+', 1,
            '-', 1
    );
    public static String expressionToPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for (char token : infix.toCharArray()) {
            if (Character.isDigit(token)) {
                output.append(token);
            } else if (OPERATORS.contains(String.valueOf(token))) {
                while (!stack.isEmpty() && stack.peek() != '(' &&
                        PRECEDENCE.get(token) <= PRECEDENCE.get(stack.peek())) {
                    output.append(' ').append(stack.pop());
                }
                output.append(' ');
                stack.push(token);
            } else if (token == '(') {
                stack.push(token);
            } else if (token == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(' ').append(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            output.append(' ').append(stack.pop());
        }
        return output.toString();
    }
}
