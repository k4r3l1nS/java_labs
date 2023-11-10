package com.k4r3l1ns.models.exceptions;

public class UndetectedVariablesException extends RuntimeException {

    public UndetectedVariablesException() {
        super("Обнаружено 1 или более незаменённых переменных. Воспользуйтесь методом scanVariables()");
    }
}
