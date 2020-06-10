package org.apache.rocketmq.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author luoyonghua
 * @since 2020-06-09 16:42
 */
public class CommonUtils {

    /**
     * 获取环境标识
     *
      * @param name
     * @return
     */
    public static String getPodEnvVar(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        String property = System.getProperty(name);
        if (StringUtils.isEmpty(property)) {
            return System.getenv(name);
        }
        return property;
    }
    
    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
