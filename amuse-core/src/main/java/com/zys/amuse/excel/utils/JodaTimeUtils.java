package com.zys.amuse.excel.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * JodaTime封装的日期工具类，用来替换jdk自带的Calendar
 *
 * Created by zhongjunkai on 18/9/21.
 */
public class JodaTimeUtils {

    public static final String STAND_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String STAND_DATE_PATTERN = "yyyy-MM-dd";

    public static final String STAND_YEAR_MONTH = "yyyy-MM";

    /**
     * 按照标准格式格式化时间
     *
     * @param time
     * @return 具有标准格式的时间字符串，如：2018-05-01 01:01:01
     */
    public static String getDate(Date time) {
        DateTime dt = new DateTime(time);
        return dt.toString(DateTimeFormat.forPattern(STAND_TIME_PATTERN));
    }

    /**
     * 按照标准格式格式化日期
     *
     * @param time
     * @return 具有标准格式的日期字符串，如：2018-05-01
     */
    public static String formatStandDate(Object time) {
        DateTime dt = new DateTime(time);
        return dt.toString(DateTimeFormat.forPattern(STAND_DATE_PATTERN));
    }

    /**
     * 按照标准格式格式化时间，只到月份
     *
     * @param time
     * @return 如：2018-05
     */
    public static String getStandYearAndMonth(Object time) {
        DateTime dt = new DateTime(time);
        return dt.toString(DateTimeFormat.forPattern(STAND_YEAR_MONTH));
    }

    /**
     * 解析具有标准格式的时间字符串
     *
     * @param dateTime 2018-01-01 01:01:01
     * @return 字符串的对应的时间
     */
    public static Date parseStandTime(String dateTime) {
        return DateTimeFormat.forPattern(STAND_TIME_PATTERN).parseDateTime(dateTime).toDate();
    }

    /**
     * 解析具有标准格式的日期字符串
     *
     * @param date 2018-01-01
     * @return 字符串对应的日期
     */
    public static Date parseStandDate(String date) {
        return DateTimeFormat.forPattern(STAND_DATE_PATTERN).parseDateTime(date).toDate();
    }

    /**
     * 根据一定的格式解析日期字符串
     *
     * @param dateTime 时间字符串
     * @param pattern 格式
     * @return 时间字符串对应的date
     */
    public static Date parseByPattern(String dateTime, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(dateTime).toDate();
    }

    /**
     * 获取指定日期当天开始时间
     *
     * @param date
     * @return
     */
    public static Date getStartTimeOfDay(Date date) {
        MutableDateTime mdt = new MutableDateTime(date);
        mdt.setHourOfDay(0);
        mdt.setMinuteOfDay(0);
        mdt.setSecondOfDay(0);
        return mdt.toDate();
    }

    /**
     * 获取指定日期当天结束时间
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOfDay(Date date) {
        MutableDateTime mdt = new MutableDateTime(date);
        mdt.setHourOfDay(23);
        mdt.setMinuteOfDay(59);
        mdt.setSecondOfDay(59);
        return mdt.toDate();
    }

    /**
     * 获取当天开始时间
     *
     * @return
     */
    public static Date getStartTimeOfCurrentDay() {
        return getStartTimeOfDay(new Date());
    }

    /**
     * 获取当天结束时间
     *
     * @return
     */
    public static Date getEndTimeOfCurrentDay() {
        return getEndTimeOfDay(new Date());
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurrentYear() {
        DateTime dt = new DateTime();
        return dt.getYear();
    }

    /**
     * 获取当前月的当前天
     *
     * @return 当天为当前月的第几天
     */
    public static int howManyDayFromFirstDayOfMonth() {
        return howManyDayFromFirstDayOfMonth(new Date());
    }

    /**
     * 获取指定时间月份的当前天
     *
     * @param date
     * @return 当天为当月的第几天
     */
    public static int howManyDayFromFirstDayOfMonth(Date date) {
        DateTime dt = new DateTime(date);
        return dt.getDayOfMonth();
    }

    /**
     * 几天之前的日期
     *
     * @param howManyDay 向前推的天数
     * @return
     */
    public static Date recentlyDay(int howManyDay) {
        DateTime dt = new DateTime();
        return dt.minusDays(howManyDay).toDate();
    }

    /**
     * 几个月之前的日期
     *
     * @param howManyMonth 向前推的月数
     * @return
     */
    public static Date recentlyMonth(int howManyMonth) {
        DateTime dt = new DateTime();
        return dt.minusMonths(howManyMonth).toDate();
    }

    /**
     * 几个月之前,且从1号开始，比如当前为5月15日，howManyMonth为1，则返回的日期为4月1日
     *
     * @param howManyMonth 向前推的月数
     * @return
     */
    public static Date recentlyMonthFromStartDay(int howManyMonth) {
        DateTime dt = new DateTime();
        return dt.minusMonths(howManyMonth).minusDays(dt.getDayOfMonth() - 1).toDate();
    }

    /**
     * 判断时间是否是当天
     *
     * @param date
     * @return
     */
    public static boolean isToday(DateTime date) {
        DateTime dt = new DateTime(date);
        return new DateTime().equals(dt);
    }

    /**
     * 获取日期的月份
     *
     * @param date
     * @return
     */
    public static int getMonthNum(Date date) {
        DateTime dt = new DateTime(date);
        return dt.getMonthOfYear();
    }

    /**
     * 两个日期间隔的天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int minusDays(Date start, Date end) {
        DateTime dt1 = new DateTime(start);
        DateTime dt2 = new DateTime(end);
        return Days.daysBetween(dt1, dt2).getDays();
    }

    /**
     * 计算每个月的天数
     *
     * @param date
     * @return
     */
    public static int getLastDayOfMonth(Date date) {
        DateTime dt = new DateTime(date);
        return dt.dayOfMonth().getMaximumValue();
    }

    /**
     * 计算每个月的天数
     *
     * @param month 月份:数字，不带0
     * @return
     */
    public static int getLastDayOfMonth(int month) {
        DateTime dt = new DateTime().withMonthOfYear(month);
        return dt.dayOfMonth().getMaximumValue();
    }

    /**
     * 获取指定日期的年份
     *
     * @param date
     * @return
     */
    public static String getYear(Date date) {
        DateTime dt = new DateTime(date);
        return String.valueOf(dt.getYear());
    }

    /**
     * 获取指定时间的月份
     *
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        DateTime dt = new DateTime(date);
        return String.valueOf(dt.getMonthOfYear());
    }
}
