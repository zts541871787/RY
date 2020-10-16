package com.zts.ryx.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

//TODO 时间转换类
public class TimeUtil {
    public static String transferTimer(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        String format = simpleDateFormat.format(time);
        return format;
    }
}
