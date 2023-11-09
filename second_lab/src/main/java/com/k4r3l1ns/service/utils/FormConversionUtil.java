package com.k4r3l1ns.service.utils;

import java.util.*;

import static com.k4r3l1ns.service.utils.BracketUtil.isClosingBracket;
import static com.k4r3l1ns.service.utils.BracketUtil.isOpeningBracket;

public class FormConversionUtil {
    private static final Set<Character> OPERATORS = Set.of('*', '/', '+', '-');
    private static final Map<Character, Integer> PRIORITY_MAP = Map.of(
            '*', 2,
            '/', 2,
            '+', 1,
            '-', 1
    );
    public static String expressionToPostfix(String infix) {

        StringBuilder postfix = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        var charArray = infix.toCharArray();

        for (char currentCharacter : charArray) {

            // Символ является числом
            if (Character.isDigit(currentCharacter)) {

                postfix.append(currentCharacter);

            // Символ является оператором
            } else if (OPERATORS.contains(currentCharacter)) {

                while (!stack.isEmpty() &&
                        !isOpeningBracket(stack.peek()) &&
                        PRIORITY_MAP.get(currentCharacter) <= PRIORITY_MAP.get(stack.peek())
                ) {
                    postfix.append(' ').append(stack.pop());
                }
                postfix.append(' ');
                stack.push(currentCharacter);

            // Символ является открывающей скобкой
            } else if (isOpeningBracket(currentCharacter)) {

                stack.push(currentCharacter);

            // Символ является закрывающей скобкой
            } else if (isClosingBracket(currentCharacter)) {

                while (!stack.isEmpty() && !isOpeningBracket(stack.peek())) {
                    postfix.append(' ').append(stack.pop());
                }
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(' ').append(stack.pop());
        }

        return postfix.toString();
    }
}
