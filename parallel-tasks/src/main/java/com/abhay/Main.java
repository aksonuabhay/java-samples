package com.abhay;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Created by abhay.kumar on 31/03/19.
 */
public class Main {

    private static final int WARMUP_ITERATIONS = 100000;

    public static void main(String[] args) {
        jvmWarmup();
        try {
            //runRecursiveFibonacciParallel();
            runIterativeFibonacciParallel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void runRecursiveFibonacciParallel() throws ExecutionException, InterruptedException {
        List<List<Long>> input = getFibonacciInputList();

        long startTime = System.nanoTime();
        ExecutorServiceUtil.executeParallel(input, Fibonacci::getFibonacciRecursive);
        System.out.println("Recursive Fibonacci using executor Service : " + (System.nanoTime() - startTime));
        startTime = System.nanoTime();
        ForkJoinPoolUtil.executeParallel(input, Fibonacci::getFibonacciRecursive);
        System.out.println("Recursive Fibonacci using Fork Join Pool : " + (System.nanoTime() - startTime));
    }

    private static void runIterativeFibonacciParallel() throws ExecutionException, InterruptedException {
        List<List<Long>> input = getFibonacciInputList();

        long startTime = System.nanoTime();
        ExecutorServiceUtil.executeParallel(input, Fibonacci::getFibonacciIterative);
        System.out.println("Iterative Fibonacci using executor Service : " + (System.nanoTime() - startTime));
        startTime = System.nanoTime();
        ForkJoinPoolUtil.executeParallel(input, Fibonacci::getFibonacciIterative);
        System.out.println("Iterative Fibonacci using Fork Join Pool : " + (System.nanoTime() - startTime));
    }

    private static List<List<Long>> getFibonacciInputList() {
        List<List<Long>> input = Lists.newArrayList();
        int batches = 5;
        for (int i = 0; i < Constants.DEFAULT_BATCHES; i++) {
            input.add(Lists.newArrayList());
        }
        Long counter = 0l;
        for (int i = 0; i < batches; i++) {
            for (int j = 0; j < Constants.DEFAULT_BATCHES; j++) {
                input.get(j).add(counter++);
            }
        }
        return input;
    }

    private static void jvmWarmup() {
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            UUID.randomUUID().toString();
        }
    }
}
