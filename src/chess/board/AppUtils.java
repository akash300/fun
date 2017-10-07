package chess.board;

import java.util.Collection;

/**
 * @author akashMaurya
 * @Date 07/10/17.
 */
public class AppUtils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }
}
