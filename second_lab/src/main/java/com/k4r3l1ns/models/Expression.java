package com.k4r3l1ns.models;

import com.k4r3l1ns.models.exceptions.NoExpressionFoundException;
import lombok.*;

import java.io.InputStream;
import java.util.*;
import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expression {

    /**
     * Map с обозначениями стандартных функций и их математических имплементациями
     */
    public static final Map<String, Function<Double, Double>> FUNCTION_MAP
            = Map.of(
            "sin", Math::sin,
            "cos", Math::cos,
            "tan", Math::tan,
            "asin", Math::asin,
            "acos", Math::acos,
            "atan", Math::atan,
            "exp", Math::exp,
            "log", Math::log,
            "abs", Math::abs,
            "neg", x -> -x
    );

    /**
     * Текст выражения
     */
    private String mathText;

    /**
     * Map с переменными и их значениями
     */
    private static final Map<String, Double> CONSTANT_MAP = Map.of(
            "PI", Math.PI,
            "E", Math.E,
            "TAU", Math.TAU
    );

    /**
     * Map с переменными и их значениями
     */
    private Map<String, Double> variableMap = new HashMap<>(CONSTANT_MAP);

    public Expression(String mathText) {
        this.mathText = mathText;
    }

    /**
     * Сканирует значения переменных из InputStream
     *
     * @param inputStream Поток ввода
     */
    public void scanVariables(InputStream inputStream) {

        List<String> variables = detectVariables();

        Scanner scanner = new Scanner(inputStream);
        if (variables.isEmpty()) {
            System.out.println("Переменных не обнаружено");
        } else {
            for (var variable : variables) {
                if (variableMap.get(variable) == null) {
                    System.out.println("Сканируем переменную " + variable + ":");
                    Double value = scanner.nextDouble();
                    variableMap.put(variable, value);
                }
            }
        }
    }

    /**
     * Сканирует выражение из InputStream
     *
     * @param inputStream Поток ввода
     */
    public void scanExpression(InputStream inputStream) {

        Scanner scanner = new Scanner(inputStream);
        System.out.println("Введите выражение: ");
        mathText = scanner.nextLine();
    }

    /**
     * Находит обозначения переменных
     *
     * @return Список обозначений
     */
    public List<String> detectVariables() {

        if (mathText == null || mathText.isEmpty()) {
            throw new NoExpressionFoundException();
        }

        List<String> variables = new ArrayList<>();
        int mathTextLength = mathText.length();

        for (int index = 0; index < mathTextLength; ++index) {
            Character currentCharacter = mathText.charAt(index);
            if (Character.isLetter(currentCharacter)) {
                StringBuilder wordBuilder = new StringBuilder();
                while (index < mathTextLength && Character.isLetterOrDigit(currentCharacter)) {
                    wordBuilder.append(currentCharacter);
                    ++index;
                    currentCharacter = mathText.charAt(index);
                }
                String word = wordBuilder.toString();
                if (!FUNCTION_MAP.containsKey(word)) {
                    variables.add(word);
                }
            }
        }

        return variables;
    }

    /**
     * Проверка, содержатся ли переменные в выражении
     *
     * @return Результат проверки
     */
    public boolean containsVariables() {
        return detectVariables().isEmpty();
    }
}
