package com.k4r3l1ns.models.exceptions;

public class UnbalancedBracketsException extends RuntimeException {

    public UnbalancedBracketsException() {
        super("Скобки выражения не сбалансированы. Выражение не может быть вычислено");
    }
}
