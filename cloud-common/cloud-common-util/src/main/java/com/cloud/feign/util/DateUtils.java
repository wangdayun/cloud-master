package com.cloud.feign.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 *
 * @author : dayun_wang
 */
public class DateUtils {
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        return format.format(new Date());
    }
}
