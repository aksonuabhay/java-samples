package com.abhay;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by abhay.kumar on 31/03/19.
 */
public class ExecutorServiceUtil {

    private static final ExecutorService defaultExecutorService = Executors.newFixedThreadPool(
            Constants.DEFAULT_PARALLELISM);

    public static <T, R> List<R> executeParallel(List<List<T>> input,
                                                 Function<List<T>, List<R>> function) throws
                                                                                      ExecutionException,
                                                                                      InterruptedException {
        return executeParallel(defaultExecutorService, input, function);
    }

    public static <T, R> List<R> executeParallel(ExecutorService executorService,
                                                 List<List<T>> input,
                                                 Function<List<T>, List<R>> function) throws
                                                                                      ExecutionException,
                                                                                      InterruptedException {
        if (CollectionUtils.isEmpty(input)) {
            return null;
        }
        List<Future<List<R>>> futures = input.stream()
                .map((list) -> executorService.submit(() -> function.apply(list)))
                .collect(Collectors.toList());
        List<R> result = Lists.newArrayListWithExpectedSize(input.size());
        for (Future<List<R>> future : futures) {
            result.addAll(future.get());
        }
        return result;
    }
}
