package com.k4r3l1ns.service.designation;

import com.k4r3l1ns.models.Expression;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VariableService {

    public static String replaceVariables(Expression expression) {

        var mathText = expression.getMathText();
        var variableMap = expression.getVariableMap();

        if (variableMap == null) {
            return mathText;
        }

        final String[] result = {mathText};
        variableMap.forEach((variable, value) -> {
            String valueString =
                    value < 0 ? "neg(" + Math.abs(value) + ")" : String.valueOf(value);
            for (int index = 0; index < result[0].length(); ++index) {

                int position = result[0].substring(index).indexOf(variable);

                if (position == -1) {
                    break;
                }

                index += position;

                int variableLength = variable.length();
                var currentCharacter = result[0].charAt(index);
                if (!(index > 0 && Character.isLetter(result[0].charAt(index - 1)) ||
                        index + variableLength < result[0].length() &&
                                (Character.isLetter(result[0].charAt(index + variableLength))
                                        || currentCharacter == '('))) {
                    result[0] = result[0].substring(0, index) + valueString +
                            result[0].substring(index + variableLength);
                    // ++index;
                }
            }
        });
        return result[0];
    }
}
