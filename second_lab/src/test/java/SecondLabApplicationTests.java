import com.k4r3l1ns.models.Expression;
import com.k4r3l1ns.service.designation.VariableService;
import com.k4r3l1ns.service.evaluator.ExpressionEvaluator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SecondLabApplicationTests {

    final int DECIMAL_SCALE = 10;

    @ParameterizedTest
    @ValueSource(strings = {
            "abs(4 + sin[PI * cos(3)]) + 2.3 * exp(neg(2.7))",      // ✓✓✓
            "2 + [6 * (4 - 1])",                                    // Скобки не сбалансированы
            "7 + + 9",                                              // Кривое выражение
            "43 / 0",                                               // Деление на 0
            "abs(abs(abs(neg(5))))",                                // ✓✓✓
            "neg(87) ^ neg(1)",                                     // ✓✓✓
            "",                                                     // Выражения нет
            "PI * E - 5",                                           // ✓✓✓
            "3"                                                     // ✓✓✓
    }) void testApplyingExpression(String mathText) {

        Expression expression = new Expression(mathText);

        var result = ExpressionEvaluator.apply(expression, DECIMAL_SCALE);
        System.out.println(
                "\nРезультат вычислений: " + VariableService.replaceVariables(expression)
                        + " = " + (result == null ? "-E-" : result) + "\n\n\n"
        );
    }

}
