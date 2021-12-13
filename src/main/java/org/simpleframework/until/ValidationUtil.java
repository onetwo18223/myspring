package org.simpleframework.until;

import java.util.Collection;
import java.util.Map;

public class ValidationUtil {

    /**
     * 对集合进行判空
     * @param objs
     * @return
     */
    public static boolean isEmpty(Collection<?> objs){
        return objs == null || objs.isEmpty();
    }

    /**
     * 对字符串集合进行判空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    /**
     * 对Map进行判空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map){
        return map == null || map.isEmpty();
    }

    /**
     * 对数组进行判空
     * @param objects
     * @return
     */
    public static boolean isEmpty(Object[] objects){
        return objects == null || objects.length == 0;
    }


}
