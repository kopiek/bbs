package com.top.sample.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * 日期工具类
 *
 * @author wangzhikang
 */
public class DateUtils {

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd";

    public static final String YEAR_MONTH_FORMAT = "yyyy-MM";

    public static final String MONTH_DAY_FORMAT = "MM-dd";

    private static final String DEFAULT_ZONE = "Asia/Shanghai";

    private static final ZoneId ZONE = ZoneId.of(DEFAULT_ZONE);

    /**
     * 判断sourceDate是否在targetDate后
     *
     * @param sourceDate
     * @param targetDate
     * @return
     */
    public static boolean isAfterDate(Date sourceDate, Date targetDate) {
        return isAfterDate(getLocalDateTimeFromDate(sourceDate), getLocalDateTimeFromDate(targetDate));
    }

    public static boolean isAfterDate(String soucrceDate, String targetDate) {
        return isAfterDate(getLocalDateTimeOfString(soucrceDate), getLocalDateTimeOfString(targetDate));
    }

    public static boolean isAfterDate(Long sourceDate, Long targetDate) {
        return isAfterDate(getDateTimeOfTimestamp(sourceDate), getDateTimeOfTimestamp(targetDate));
    }

    public static boolean isAfterDate(Long sourceDate, Date targetDate) {
        return isAfterDate(getDateTimeOfTimestamp(sourceDate), getLocalDateTimeFromDate(targetDate));
    }

    public static boolean isAfterDate(Long sourceDate, String targetDate) {
        return isAfterDate(getDateTimeOfTimestamp(sourceDate), getLocalDateTimeOfString(targetDate));
    }

    public static boolean isAfterDate(Date sourceDate, String targetDate) {
        return isAfterDate(getLocalDateTimeFromDate(sourceDate), getLocalDateTimeOfString(targetDate));
    }

    public static boolean isAfterDate(LocalDateTime sourceDate, LocalDateTime targetDate) {
        return sourceDate.isAfter(targetDate);
    }

    public static int diffDay(String sourceDate, String targetDate) {
        return diffDay(getLocalDateTimeOfString(sourceDate), getLocalDateTimeOfString(targetDate));
    }

    public static int diffDay(Date sourceDate, Date targetDate) {
        return diffDay(getLocalDateTimeFromDate(sourceDate), getLocalDateTimeFromDate(targetDate));
    }

    public static int diffDay(Long sourceDate, String targeDate) {
        return diffDay(getDateTimeOfTimestamp(sourceDate), getLocalDateTimeOfString(targeDate));
    }

    public static int diffDay(LocalDateTime sourceDate, LocalDateTime targetDate) {
        return Period.between(sourceDate.toLocalDate(), targetDate.toLocalDate()).getDays();
    }

    public static int diffMonth(String sourceDate, String targetDate) {
        return diffMonth(getLocalDateTimeOfString(sourceDate), getLocalDateTimeOfString(targetDate));
    }

    public static int diffMonth(Date sourceDate, Date targetDate) {
        return diffMonth(getLocalDateTimeFromDate(sourceDate), getLocalDateTimeFromDate(targetDate));
    }

    public static int diffMonth(Long sourceDate, String targeDate) {
        return diffMonth(getDateTimeOfTimestamp(sourceDate), getLocalDateTimeOfString(targeDate));
    }

    public static int diffMonth(LocalDateTime sourceDate, LocalDateTime targetDate) {
        return Period.between(sourceDate.toLocalDate(), targetDate.toLocalDate()).getMonths();
    }

    public static int diffYear(String sourceDate, String targetDate) {
        return diffYear(getLocalDateTimeOfString(sourceDate), getLocalDateTimeOfString(targetDate));
    }

    public static int diffYear(Date sourceDate, Date targetDate) {
        return diffYear(getLocalDateTimeFromDate(sourceDate), getLocalDateTimeFromDate(targetDate));
    }

    public static int diffYear(Long sourceDate, String targeDate) {
        return diffYear(getDateTimeOfTimestamp(sourceDate), getLocalDateTimeOfString(targeDate));
    }

    public static int diffYear(LocalDateTime sourceDate, LocalDateTime targetDate) {
        return Period.between(sourceDate.toLocalDate(), targetDate.toLocalDate()).getYears();
    }

    public static Date addDay(String date, int day) {
        return addDay(getLocalDateTimeOfString(date), day);
    }

    public static Date addDay(Long date, int day) {
        return addDay(getDateTimeOfTimestamp(date), day);
    }

    public static Date addDay(Date date, int day) {
        return addDay(getLocalDateTimeFromDate(date), day);
    }

    public static Date addDay(LocalDateTime date, int day) {
        return getDateFromLocalDate(date.toLocalDate().plusDays(day));
    }

    public static Date addMonth(String date, int month) {
        return addMonth(getLocalDateTimeOfString(date), month);
    }

    public static Date addMonth(Long date, int month) {
        return addMonth(getDateTimeOfTimestamp(date), month);
    }

    public static Date addMonth(Date date, int month) {
        return addMonth(getLocalDateTimeFromDate(date), month);
    }

    public static Date addMonth(LocalDateTime date, int month) {
        return getDateFromLocalDate(date.toLocalDate().plusMonths(month));
    }

    public static Date addYear(String date, int year) {
        return addYear(getLocalDateTimeOfString(date), year);
    }

    public static Date addYear(Long date, int year) {
        return addYear(getDateTimeOfTimestamp(date), year);
    }

    public static Date addYear(Date date, int year) {
        return addYear(getLocalDateTimeFromDate(date), year);
    }

    public static Date addYear(LocalDateTime date, int year) {
        return getDateFromLocalDate(date.toLocalDate().plusYears(year));
    }

    public static Date format(Long date,String formatStr) {
        return format(getDateTimeOfTimestamp(date),formatStr);
    }

    public static Date format(Date date,String formatStr) {
        return format(getLocalDateTimeFromDate(date),formatStr);
    }

    public static Date format(LocalDateTime date ,String formatStr) {
        if (Objects.isNull(formatStr) || Objects.equals(formatStr,"")) {
            formatStr = DEFAULT_FORMAT;
        }
        return getDateFromLocalDate(getLocalDateTimeOfString(date.format(DateTimeFormatter.ofPattern(formatStr))).toLocalDate());
    }

    public static Date now() {
        return getDateFromLocalDateTime(LocalDateTime.now());
    }

    private static LocalDateTime getDateTimeOfTimestamp(Long timestamp) {
        Objects.requireNonNull(timestamp,"timeStamp must not be null");
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZONE);
    }

    private static LocalDateTime getLocalDateTimeFromDate(Date date) {
        Objects.requireNonNull(date,"date must not be null");
        Long timestamp = date.getTime();
        return getDateTimeOfTimestamp(timestamp);
    }

    private static LocalDateTime getLocalDateTimeOfString(String dateStr) {
        Objects.requireNonNull(dateStr,"dateStr must not be null");
        Instant instant = Instant.parse(dateStr);
        return LocalDateTime.ofInstant(instant, ZONE);
    }

    private static Date getDateFromLocalDate(LocalDate localDate) {
        Objects.requireNonNull(localDate,"localdate must not be null");
        return Date.from(localDate.atStartOfDay(ZONE).toInstant());
    }

    private static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime,"localDateTime must not be null");
        return Date.from(localDateTime.atZone(ZONE).toInstant());
    }
}
