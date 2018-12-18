import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * Created by abhay.kumar on 19/12/18.
 */
public class StringUtilsUtility {

    public static String getCSV(Collection<String> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return null;
        }
        return StringUtils.join(coll, ",");
    }

}
