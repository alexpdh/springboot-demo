package com.socket.common.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <pre>
 *
 * </pre>
 * 2017-06-28 16:57 xuyang@richinfo.cn 创建
 *
 * @author xuyang
 * @version on 2017/6/28.
 */
public class DateFormatThreadLocal extends ThreadLocal<DateFormat> {

  /**
   * 日期时间格式
   */
  private String pattern;

  /**
   * @param pattern 日期时间格式
   */
  public DateFormatThreadLocal(String pattern) {
    this.pattern = pattern;
  }

  @Override
  protected DateFormat initialValue() {
    return new SimpleDateFormat(pattern);
  }

}
