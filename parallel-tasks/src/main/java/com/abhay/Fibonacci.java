package com.abhay;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by abhay.kumar on 31/03/19.
 */
public class Fibonacci {

    public static List<Long> getFibonacciRecursive(List<Long> numbers) {
        return numbers.stream()
                .map(n -> getFibonacciRecursive(n))
                .collect(Collectors.toList());
    }

    public static List<Long> getFibonacciIterative(List<Long> numbers) {
        return numbers.stream()
                .map(n -> getFibonacciIterative(n))
                .collect(Collectors.toList());
    }

    public static Long getFibonacciRecursive(Long n) {
        if (n == null || n <= 1) {
            return n;
        }
        return getFibonacciRecursive(n - 1) + getFibonacciRecursive(n - 2);
    }

    public static Long getFibonacciIterative(Long n) {
        if (n == null || n <= 1) {
            return n;
        }
        Long a = 0l, b = 1l, c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
