import com.k4r3l1ns.models.Expression;
import com.k4r3l1ns.service.designation.VariableService;
import com.k4r3l1ns.service.evaluator.ExpressionEvaluator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SecondLabApplicationTests {

    @ParameterizedTest
    @ValueSource(strings = {
            "abs(4 + sin[cos(3)]) + 2.3 * exp(neg(2.7))",   // ✓✓✓
            "2 + [6 * (4 - 1])",                            // Скобки не сбалансированы
            "7 + + 9",                                      // Кривое выражение
            "43 / 0",                                       // Деление на 0
            "abs(abs(abs(neg(5))))",                        // ✓✓✓
            "neg(87) ^ 1",                                  // ✓✓✓
            ""                                              // Выражения нет
    }) public void testApplyingExpression(String mathText) {

        Expression expression = new Expression(mathText);

        var result = ExpressionEvaluator.apply(expression, 3);
        System.out.println(
                "Результат вычислений: " + VariableService.replaceVariables(expression) +
                        " = " + (result == null ? "-E-" : result) + "\n\n\n"
        );
    }

}
