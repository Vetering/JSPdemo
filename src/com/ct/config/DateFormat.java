package com.ct.config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/11/29 15:28
 **/
public class DateFormat {
    public static String getDateFormat(Date date, String pattern) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);

    }
}
