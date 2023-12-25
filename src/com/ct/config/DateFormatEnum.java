package com.ct.config;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/11/29 15:29
 **/
public enum DateFormatEnum {
    DATE_FORMAT_DATETIME("yyyy-MM-dd HH:mm:ss", "日期时间格式"),

    DATE_FORMAT_DATE("yyyy-MM-dd", "日期格式"),

    DATE_FORMAT_TIME("HH:mm:ss", "时间格式");

    final String pattern;

    final String desc;

    DateFormatEnum(String pattern, String desc) {
        this.pattern = pattern;
        this.desc = desc;
    }

    public String getPattern() {
        return pattern;
    }

    public String getDesc() {
        return desc;
    }
}
