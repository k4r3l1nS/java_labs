package com.k4r3l1ns.service.utils;

import com.k4r3l1ns.models.Expression;
import com.k4r3l1ns.models.exceptions.UnbalancedBracketsException;

import java.util.*;

public class BracketUtil {

    private static final Set<Character> OPENING_BRACKET_SYMBOLS = Set.of('(', '[', '{');
    private static final Set<Character> CLOSING_BRACKET_SYMBOLS = Set.of(')', ']', '}');

    /**
     * Проверяет, сбалансированы ли скобки в выражении и выбрасывает
     * исключение, если они не сбалансированы
     *
     * @param expression Класс, содержащий выражение и map с переменными
     */
    public static void throwIfUnbalanced(Expression expression) {

        var mathText = expression.getMathText();
        Deque<Character> stack = new ArrayDeque<>();
        var charStream = mathText.toCharArray();

        for (var currentCharacter : charStream) {

            if (
                    OPENING_BRACKET_SYMBOLS.contains(currentCharacter) ||
                    !CLOSING_BRACKET_SYMBOLS.contains(currentCharacter)
            ) {
                if (OPENING_BRACKET_SYMBOLS.contains(currentCharacter)) {
                    stack.push(currentCharacter);
                }
                continue;
            }

            if (stack.isEmpty() || !isCompatible(currentCharacter, stack.pop())) {
                throw new UnbalancedBracketsException();
            }
        }

        if (!stack.isEmpty()) {
            throw new UnbalancedBracketsException();
        }
    }

    /**
     * Проверяет, соответствует ли закрывающая скобка открывающей
     *
     * @param bracketCharacter Закрывающая скобка
     * @param stackTop Открывающая скобка
     * @return Результат проверки
     */
    private static boolean isCompatible(Character bracketCharacter, Character stackTop) {

        switch (bracketCharacter) {
            case ')':
                if (stackTop == '{' || stackTop == '[') {
                    return false;
                }
                break;
            case '}':
                if (stackTop == '(' || stackTop == '[') {
                    return false;
                }
                break;
            case ']':
                if (stackTop == '(' || stackTop == '{') {
                    return false;
                }
                break;
        }
        return true;
    }

    public static boolean isOpeningBracket(Character bracketCharacter) {
        return OPENING_BRACKET_SYMBOLS.contains(bracketCharacter);
    }

    public static boolean isClosingBracket(Character bracketCharacter) {
        return CLOSING_BRACKET_SYMBOLS.contains(bracketCharacter);
    }
}
