import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by abhay.kumar on 19/12/18.
 */
public class Main {

    private static final int listSize = 100;
    private static final int collSize = 10000;
    private static final int WARMUP_ITERATIONS = 10000;

    public static void main(String[] args) {

        jvmWarmup();

        List<Collection<String>> randomSets = Lists.newArrayListWithCapacity(listSize);
        for (int i = 0; i < listSize; i++) {
            Collection<String> tempSet = Sets.newHashSet();
            for (int j = 0; j < collSize; j++) {
                tempSet.add(UUID.randomUUID().toString());
            }
            randomSets.add(tempSet);
        }

        //Using Streams Joiner
        long total = 0;
        for (Collection<String> collection : randomSets) {
            long start = System.nanoTime();
            String out = StreamsUtility.getCSV(collection);
            total += (System.nanoTime() - start);
        }
        System.out.println("Using Streams Joiner : " + total / listSize);

        //Using StringUtils Joiner
        total = 0;
        for (Collection<String> collection : randomSets) {
            long start = System.nanoTime();
            String out = StringUtilsUtility.getCSV(collection);
            total += (System.nanoTime() - start);
        }
        System.out.println("Using StringUtils Joiner : " + total / listSize);

    }

    private static void jvmWarmup() {
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            UUID.randomUUID().toString();
        }
    }
}
