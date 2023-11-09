package com.k4r3l1ns;

import com.k4r3l1ns.service.ExpressionEvaluator;
import com.k4r3l1ns.service.utils.FormConversionUtil;

public class SecondLabApplication {

    public static void main(String[] args) {

        String expression = "3 + 2 * (5 + 17) / 10";

        System.out.println(
                FormConversionUtil.expressionToPostfix(expression)
        );

        System.out.println(
                ExpressionEvaluator.apply(expression)
        );
    }
}