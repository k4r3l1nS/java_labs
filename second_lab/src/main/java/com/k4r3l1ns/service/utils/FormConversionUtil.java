package com.k4r3l1ns.service.utils;

import java.util.*;

import static com.k4r3l1ns.service.utils.BracketUtil.isClosingBracket;
import static com.k4r3l1ns.service.utils.BracketUtil.isOpeningBracket;

public class FormConversionUtil {

    /**
     * Поддерживаемые операторы
     */
    public static final Set<Character> OPERATORS = Set.of('+', '-', '*', '/', '^');

    /**
     * Наивысший возможный приоритет операции
     */
    private static final int MAX_PRIORITY = 4;

    /**
     * Map с приоритетами операций
     */
    private static final Map<Character, Integer> PRIORITY_MAP = Map.of(
            '+', 1,
            '-', 1,
            '*', 2,
            '/', 2,
            '^', 3
    );

    /**
     * Переводит выражение из инфиксной формы в префиксную
     *
     * @param mathText Выражение
     * @return Префиксная форма
     */
    public static String expressionToPostfix(String mathText) {

        StringBuilder postfix = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        var expressionLength = mathText.length();

        for (int index = 0; index < expressionLength; ++index) {

            var currentCharacter = mathText.charAt(index);

            // Символ является числом
            if (Character.isDigit(currentCharacter) || currentCharacter == '.') {

                postfix.append(currentCharacter);

            // Символ является оператором
            } else if (OPERATORS.contains(currentCharacter)) {

                while (!stack.isEmpty() &&
                        !isOpeningBracket(stack.peek()) &&
                        PRIORITY_MAP.getOrDefault(currentCharacter, MAX_PRIORITY)
                                <= PRIORITY_MAP.getOrDefault(stack.peek(), MAX_PRIORITY)
                ) {
                    StringBuilder word = new StringBuilder();
                    if (!stack.isEmpty() && Character.isLetter(stack.peek())) {
                        while (!stack.isEmpty() && Character.isLetter(stack.peek())) {
                            word.insert(0, stack.pop());
                        }
                    } else {
                        word.append(stack.pop());
                    }
                    postfix.append(' ').append(word);
                }
                postfix.append(' ');
                stack.push(currentCharacter);

            // Символ является открывающей скобкой
            } else if (isOpeningBracket(currentCharacter)) {

                stack.push(currentCharacter);

            // Символ является закрывающей скобкой
            } else if (isClosingBracket(currentCharacter)) {

                while (!stack.isEmpty() && !isOpeningBracket(stack.peek())) {
                    StringBuilder word = new StringBuilder();
                    if (Character.isLetter(stack.peek())) {
                        while (!stack.isEmpty() && Character.isLetter(stack.peek())) {
                            word.insert(0, stack.pop());
                        }
                    } else {
                        word.append(stack.pop());
                    }
                    postfix.append(' ').append(word);
                }
                stack.pop();

            // Символ является буквой
            } else if (Character.isLetter(currentCharacter)) {

                while (Character.isLetter(currentCharacter)) {
                    stack.push(currentCharacter);
                    ++index;
                    currentCharacter = mathText.charAt(index);
                }
                --index;
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(' ');
            Character currentCharacter = stack.pop();
            if (Character.isLetter(currentCharacter)) {
                StringBuilder word = new StringBuilder();
                while (currentCharacter != null && Character.isLetter(currentCharacter)) {
                    word.insert(0, currentCharacter);
                    currentCharacter = stack.isEmpty() ? null : stack.pop();
                }
                if (currentCharacter != null) {
                    stack.push(currentCharacter);
                }
                postfix.append(word).append(' ');
            } else {
                postfix.append(currentCharacter).append(' ');
            }
        }

        return postfix.toString();
    }
}
