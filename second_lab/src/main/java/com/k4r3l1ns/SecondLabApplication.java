package com.k4r3l1ns;

import java.util.ArrayDeque;
import java.util.Deque;

public class SecondLabApplication {
    public static void main(String[] args) {

        Deque<Integer> stack = new ArrayDeque<>();

        // <--
        // 4 3 2 1 0
        for (int i = 0; i < 5; ++i) {
            stack.push(i);
        }
        stack.forEach(System.out::println);

        // -->
        // 2 1 0
        for (int i = 0; i < 2; ++i) {
            stack.pop();
        }
        stack.forEach(System.out::println);
    }
}