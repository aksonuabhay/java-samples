import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by abhay.kumar on 19/12/18.
 */
public class StreamsUtility {

    public static String getCSV(Collection<String> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return null;
        }
        return coll.stream().collect(Collectors.joining(","));
    }

}
