package com.socket.common.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <pre>
 *     日期工具类，用于日期时间与字符串之间的转换
 * </pre>
 * 2017-06-28 16:59 xuyang@richinfo.cn 创建
 *
 * @author xuyang
 * @version on 2017/6/28.
 */
public class DateUtils {

  /**
   * 格式：yyyy-MM-dd HH:mm:ss.SSS
   */
  public static final String FULL_STANDARD_DATE_TIME = "yyyy-MM-dd HH:mm:ss.S";

  /**
   * 格式：yyyy-MM-dd HH:mm:ss
   */
  public static final String STANDARD_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

  /**
   * 格式：yyyy-MM-dd HH:mm
   */
  public static final String INCOMPLETE_DATE_TIME = "yyyy-MM-dd HH:mm";

  /**
   * 格式：yyyyMMddHHmmssSSS
   */
  public static final String FULL_SHORT_DATE_TIME = "yyyyMMddHHmmssS";

  /**
   * 格式：yyyyMMddHHmmss
   */
  public static final String SHORT_DATE_TIME = "yyyyMMddHHmmss";

  /**
   * 格式：yyyy-MM-dd
   */
  public static final String STANDARD_DATE = "yyyy-MM-dd";

  /**
   * 格式：yyyyMMdd
   */
  public static final String SHORT_DATE = "yyyyMMdd";

  /**
   * 格式：MMdd
   */
  public static final String SHORT_MMDD = "MMdd";

  /**
   * 格式：HHmmss
   */
  public static final String SHORT_TIME = "HHmmss";

  /**
   * 格式: yyyy-MM-dd'T'HH:mm:ss'Z'
   */
  public static final String UTC_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  private static DateFormatThreadLocal fullStandardDateTime = new DateFormatThreadLocal(
      FULL_STANDARD_DATE_TIME);

  private static DateFormatThreadLocal standardDateTime = new DateFormatThreadLocal(
      STANDARD_DATE_TIME);

  private static DateFormatThreadLocal incompleteDateTime = new DateFormatThreadLocal(
      INCOMPLETE_DATE_TIME);

  private static DateFormatThreadLocal fullShortDateTime = new DateFormatThreadLocal(
      FULL_SHORT_DATE_TIME);

  private static DateFormatThreadLocal shortDateTime = new DateFormatThreadLocal(SHORT_DATE_TIME);

  private static DateFormatThreadLocal standardDate = new DateFormatThreadLocal(STANDARD_DATE);

  private static DateFormatThreadLocal shortDate = new DateFormatThreadLocal(SHORT_DATE);

  private static DateFormatThreadLocal shortMMDD = new DateFormatThreadLocal(SHORT_MMDD);

  private static DateFormatThreadLocal shortTime = new DateFormatThreadLocal(SHORT_TIME);

  private static DateFormatThreadLocal utcDateTime = new DateFormatThreadLocal(UTC_DATE_TIME);

  private DateUtils() {
  }

  /**
   * 格式化日期时间（格式：yyyy-MM-dd HH:mm:ss.SSS）
   *
   * @param date 日期时间
   * @return 日期时间字符串
   */
  public static String formatFullStandardDateTime(Date date) {
    return fullStandardDateTime.get().format(date);
  }

  /**
   * 解析日期时间字符串（格式：yyyy-MM-dd HH:mm:ss.SSS）
   *
   * @param dateTime 日期时间字符串
   * @return 日期时间
   */
  public static Date parseFullStandardDateTime(String dateTime) throws ParseException {
    return fullStandardDateTime.get().parse(dateTime);
  }

  /**
   * 格式化日期时间（格式：yyyy-MM-dd HH:mm:ss）
   *
   * @param date 日期时间
   * @return 日期时间字符串
   */
  public static String formatStandardDateTime(Date date) {
    return standardDateTime.get().format(date);
  }

  /**
   * 解析日期时间字符串（格式：yyyy-MM-dd HH:mm:ss）
   *
   * @param dateTime 日期时间字符串
   * @return 日期时间
   */
  public static Date parseStandardDateTime(String dateTime) throws ParseException {
      return standardDateTime.get().parse(dateTime);
  }

  /**
   * 格式化日期时间字符串（格式：yyyy-MM-dd HH:mm）
   *
   * @param date 日期时间
   * @return 日期时间字符串
   */
  public static String formatIncompleteDateTime(Date date) {
    return incompleteDateTime.get().format(date);
  }

  /**
   * 解析日期时间字符串（格式：yyyy-MM-dd HH:mm）
   *
   * @param dateTime 日期时间字符串
   * @return 日期时间
   */
  public static Date parseIncompleteDateTime(String dateTime) throws ParseException {
    return incompleteDateTime.get().parse(dateTime);
  }

  /**
   * 格式化日期时间字符串（格式：yyyyMMddHHmmssSSS）
   *
   * @param date 日期时间
   * @return 日期时间字符串
   */
  public static String formatFullShortDateTime(Date date) {
    return fullShortDateTime.get().format(date);
  }

  /**
   * 解析日期时间字符串（格式：yyyyMMddHHmmssSSS）
   *
   * @param dateTime 日期时间字符串
   * @return 日期时间
   */
  public static Date parseFullShortDateTime(String dateTime) throws ParseException {
    return fullShortDateTime.get().parse(dateTime);
  }

  /**
   * 格式化日期时间（格式：yyyyMMddHHmmss）
   *
   * @param date 日期时间
   * @return 日期时间字符串
   */
  public static String formatShortDateTime(Date date) {
    return shortDateTime.get().format(date);
  }


  /**
   * 解析日期时间字符串（格式：yyyyMMddHHmmss）
   *
   * @param dateTime 日期时间字符串
   * @return 日期时间
   */
  public static Date parseShortDateTime(String dateTime) throws ParseException {
    return shortDateTime.get().parse(dateTime);
  }

  /**
   * 格式化日期时间（格式：yyyyMMddHHmmss）
   *
   * @param date 日期时间
   * @param zone 时区
   * @return 日期时间字符串
   */
  public static String formatShortDateTime(Date date, TimeZone zone) {
    shortDateTime.get().setTimeZone(zone);
    return shortDateTime.get().format(date);
  }


  /**
   * 解析日期时间字符串（格式：yyyyMMddHHmmss）
   *
   * @param dateTime 日期时间字符串
   * @param zone 时区
   * @return 日期时间
   */
  public static Date parseShortDateTime(String dateTime, TimeZone zone) throws ParseException {
    shortDateTime.get().setTimeZone(zone);
    return shortDateTime.get().parse(dateTime);
  }

  /**
   * 格式化日期（格式：yyyy-MM-dd）
   *
   * @param date 日期
   * @return 日期字符串
   */
  public static String formatStandardDate(Date date) {
    return standardDate.get().format(date);
  }

  /**
   * 解析日期字符串（格式：yyyy-MM-dd）
   *
   * @param date 日期字符串
   * @return 日期
   */
  public static Date parseStandardDate(String date) throws ParseException {
    return standardDate.get().parse(date);
  }


  /**
   * 格式化日期（格式：yyyyMMdd）
   *
   * @param date 日期
   * @return 日期字符串
   */
  public static String formatShortDate(Date date) {
    return shortDate.get().format(date);
  }

  /**
   * 解析日期字符串（格式：yyyyMMdd）
   *
   * @param date 日期字符串
   * @return 日期
   */
  public static Date parseShortDate(String date) throws ParseException {
    return shortDate.get().parse(date);
  }

  /**
   * 格式化日期（格式：yyyyMMdd）
   *
   * @param date 日期
   * @param zone 时区
   * @return 日期字符串
   */
  public static String formatShortDate(Date date, TimeZone zone) {
    shortDate.get().setTimeZone(zone);
    return shortDate.get().format(date);
  }

  /**
   * 解析日期字符串（格式：yyyyMMdd）
   *
   * @param date 日期字符串
   * @param zone 时区
   * @return 日期
   */
  public static Date parseShortDate(String date, TimeZone zone) throws ParseException {
    shortDate.get().setTimeZone(zone);
    return shortDate.get().parse(date);
  }

  /**
   * 格式化日期（格式：MMdd）
   *
   * @param date 日期
   * @return 日期字符串
   */
  public static String formatShortMMDDDate(Date date) {
    return shortMMDD.get().format(date);
  }

  /**
   * 解析日期字符串（格式：MMdd）
   *
   * @param date 日期字符串
   * @return 日期
   */
  public static Date parseShortMMDDDate(String date) throws ParseException {
    return shortMMDD.get().parse(date);
  }

  /**
   * 格式化时间（格式：HHmmss）
   *
   * @param time 时间
   * @return 时间字符串
   */
  public static String formatShortTime(Date time) {
    return shortTime.get().format(time);
  }

  /**
   * 解析时间字符串（格式：HHmmss）
   *
   * @param time 时间字符串
   * @return 时间
   */
  public static Date parseShortTime(String time) throws ParseException {
    return shortTime.get().parse(time);
  }

  /**
   * 格式化日期时间（格式: yyyy-MM-dd'T'HH:mm:ss'Z'）
   *
   * @param dateTime 日期时间
   * @return 日期时间字符串
   */
  public static String formatUTCDateTime(Date dateTime) {
    utcDateTime.get().setTimeZone(TimeZone.getTimeZone("GMT-0:00"));
    return utcDateTime.get().format(dateTime);
  }

  /**
   * 解析日期时间字符串（格式: yyyy-MM-dd'T'HH:mm:ss'Z'）
   *
   * @param dateTime 日期时间字符串
   * @return 日期时间
   */
  public static Date parseUTCDateTime(String dateTime) throws ParseException {
    utcDateTime.get().setTimeZone(TimeZone.getTimeZone("GMT-0:00"));
    return utcDateTime.get().parse(dateTime);
  }

  /**
   * 获取传入日期的0时0分0秒
   *
   * @param date 日期
   * @return 日期的0时0分0秒
   */
  public static Date getStartTimeOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  /**
   * 获取传入日期的23时59分59秒
   *
   * @param date 日期
   * @return 日期的23时59分59秒
   */
  public static Date getEndTimeOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
  }

  /**
   * 获取传入日期之前或之后的日期
   *
   * @param date 日期
   * @param amount 之前或之后的天数，负数为之前，正数为之后。
   * @return 之前或之后的日期
   */
  public static Date getPreviousOrNextDate(Date date, int amount) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, amount);
    return cal.getTime();
  }

}
