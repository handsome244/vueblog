package com.markerhub.utils;

import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 日期工具
 *
 * @author admin
 */
public class DateUtils extends DateUtil {
    /**
     * 长期
     */
    public static final Date LONG_TERM = DateUtil.parse("2099-12-31 23:59:59.999");

    public static final String[] PATTERNS = {"yyyy-MM", "HH:mm:ss", "HH:mm"};

    /**
     * 到期状态（1-正常,2-快到期,3-过期）
     */
    public static final Integer EXPIRE_NORMAL = 1;
    public static final Integer EXPIRE_ALMOST_EXPIRES = 2;
    public static final Integer EXPIRE_ALREADY_EXPIRES = 3;


    public static DateTime parse(CharSequence dateString) {
        if (StrUtil.isBlank(dateString)) {
            return null;
        }
        try {
            return DateUtil.parse(dateString);
        } catch (Exception e) {
            try {
                for (String pattern : PATTERNS) {
                    return DateUtil.parse(dateString, pattern);
                }
            } catch (Exception ignored) {
            }
        }
        throw new DateException("No format fit for date String [{}] !", dateString);
    }

    /**
     * LocalDate 转 Date
     *
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Date 转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        return DateUtil.toLocalDateTime(date).toLocalDate();
    }

    /**
     * Date 转 LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime toLocalTime(Date date) {
        return DateUtil.toLocalDateTime(date).toLocalTime();
    }


    /**
     * localDate  localTime转为date
     *
     * @param localDate
     * @param localTime
     * @return
     */
    public static Date toDate(LocalDate localDate, LocalTime localTime) {
        return Date.from(LocalDateTime.of(localDate, localTime).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 判断是否是周六周日
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        int dayOfWeek = DateUtil.date(date).dayOfWeek();
        return dayOfWeek == 1 || dayOfWeek == 7;
    }

    /**
     * 时间段格式化
     * e.g. 11月25日10:00到10:40
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String formatTimeQuantum(Date startTime, Date endTime) {
        Assert.notNull(startTime);
        Assert.notNull(endTime);
        Assert.isTrue(DateUtil.isSameDay(startTime, endTime));
        String start = DateUtil.format(startTime, "HH:mm");
        String end = DateUtil.format(endTime, "HH:mm");
        String date = DateUtil.format(startTime, "MM月dd日");
        String template = "{}{}到{}";
        if (startTime.compareTo(endTime) < 0) {
            return StrUtil.format(template, date, start, end);
        } else {
            return StrUtil.format(template, date, end, start);
        }
    }

    /**
     * 格式化时间戳（秒）
     *
     * @param date
     * @return
     */
    public static Long unixTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.getTime() / 1000;
    }

    /**
     * 时间戳（秒）格式化成时间
     *
     * @param unixTime
     * @return
     */
    public static Date toDate(Long unixTime) {
        if (unixTime == null) {
            return null;
        }
        return new Date(unixTime * 1000);
    }

    /**
     * 是否过去的时间
     *
     * @param date
     * @return
     */
    public static boolean isPast(Date date) {
        return com.markerhub.utils.DateUtils.date().compareTo(date) > 0;
    }

    /**
     * 是否未来的时间
     *
     * @param date
     * @return
     */
    public static boolean isFuture(Date date) {
        return com.markerhub.utils.DateUtils.date().compareTo(date) < 0;
    }


    /**
     * 是否在某个时间段内（包含开始时间，不包含结束时间）
     *
     * @param date
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isBetween(Date date, Date startTime, Date endTime) {
        return date.compareTo(startTime) >= 0 && date.compareTo(endTime) < 0;
    }

    /**
     * 判断是否过期
     *
     * @param date             日期
     * @param almostExpireDays 快要过期时间判定
     * @return
     */
    public static Integer isExpire(LocalDate date, Integer almostExpireDays) {
        if (date == null) {
            return EXPIRE_NORMAL;
        }
        long days = ChronoUnit.DAYS.between(LocalDate.now(), date);
        return days > almostExpireDays ? EXPIRE_NORMAL : (days >= 0 ? EXPIRE_ALMOST_EXPIRES : EXPIRE_ALREADY_EXPIRES);
    }

    /**
     * 判断是否过期
     *
     * @param date 日期
     * @return
     */
    public static Integer isExpire(LocalDate date) {
        return isExpire(date, 30);
    }

    public static String format(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
