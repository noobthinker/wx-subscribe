package com.xkorey.subscribe.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class Common {

    public  static String cookieKey="server-remark";

    public static String redisAllCountKey="count:all";
    public static String redisDayCountKey="count:day:";

    public static String staffKey="staff:";
    public static String functionKey="func:";
    public static String magicWord="magic:";
    public static String activityJoin="activity:join:";

    public static String generateId(String prefix,Integer len){
        return StringUtils.join(prefix, RandomStringUtils.randomAlphanumeric(len));
    }
}
