package com.markerhub.utils;

import java.util.regex.Pattern;

/**
 * 正则工具类
 *
 * @author shenxr
 * @date 2020-06-03 17:27
 * @description
 **/
public class PatternUtils {

    public static final String ID_CARD_PATTERN = "^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    public static final String CHINESE_PATTERN = "[\u4e00-\u9fa5]";
    public static final String USER_NAME_PATTERN = "^(?!_)(?!.*?_$)[a-zA-Z0-9_]+${6,18}";
    public static final String MOBILE_PATTERN = "^[1]([3-9])[0-9]{9}$";
    public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9-*/+.~!@#$%^&*{}()]{6,18}$";
    public static final String EMAIL_PATTERN = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    public static final String BANK_CARD_NUM_PATTERN = "^([1-9]{1})(\\d{14}|\\d{18})$";

    public static boolean isContainChinese(String str) {
        return Pattern.compile(CHINESE_PATTERN).matcher(str).find();
    }
}
