package com.k4r3l1ns.models.exceptions;

public class NoExpressionFoundException extends RuntimeException {

    public NoExpressionFoundException() {
        super("Выражение отсутствует. Заполните его");
    }
}
