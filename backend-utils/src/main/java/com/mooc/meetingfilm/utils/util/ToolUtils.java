package com.mooc.meetingfilm.utils.util;

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
}
