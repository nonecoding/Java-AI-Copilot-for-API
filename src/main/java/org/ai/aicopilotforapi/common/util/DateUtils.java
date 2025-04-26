package org.ai.aicopilotforapi.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
  public static String format(LocalDateTime dt) { return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); }
  public static LocalDateTime parse(String s) { return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); }
}