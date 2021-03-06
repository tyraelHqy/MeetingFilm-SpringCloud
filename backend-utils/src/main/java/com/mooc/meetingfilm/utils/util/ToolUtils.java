package com.mooc.meetingfilm.utils.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基础工具类
 */
public class ToolUtils {
    private ToolUtils() {
    }

    /**
     * 验证字符串是否为空
     * @param str
     * @return
     */
    public static boolean strIsNull(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证字符串是否是非空
     * @param str
     * @return
     */
    public static boolean strIsNotNull(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     *  判断数字正则表达式
     */
    private static final Pattern pattern = Pattern.compile("[0-9]*");

    /**
     * 检查字符串是不是int类型
     * @param str
     * @return
     */
    public static boolean checkInt(String str) {
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 字符串转换为int类型
     * @param str
     * @return
     */
    public static Integer str2Int(String str) {
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return 0;
        } else {
            return Integer.parseInt(str);
        }
    }

    /**
     * 字符串转换为LocalDateTime
     * @param dateStr
     * @return
     */
    public static LocalDateTime str2LocalDateTime(String dateStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dateStr,df);
        return ldt;
    }

}
