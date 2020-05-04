package com.abhay;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by abhay.kumar on 31/03/19.
 */
public class ForkJoinPoolUtil {

    private static final ForkJoinPool defaultForkJoinPool = new ForkJoinPool(Constants.DEFAULT_PARALLELISM);

    public static <T, R> List<R> executeParallel(List<List<T>> input,
                                                 Function<List<T>, List<R>> function) throws
                                                                                      ExecutionException,
                                                                                      InterruptedException {
        return executeParallel(defaultForkJoinPool, input, function);
    }

    public static <T, R> List<R> executeParallel(ForkJoinPool forkJoinPool,
                                                 List<List<T>> input,
                                                 Function<List<T>, List<R>> function) throws
                                                                                      ExecutionException,
                                                                                      InterruptedException {
        return forkJoinPool.submit(() -> input
                .parallelStream()
                .map((list) -> function.apply(list))
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(List::stream)
                .collect(Collectors.toList())).get();
    }

}
