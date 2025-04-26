package org.ai.aicopilotforapi.common.util;

public class StringUtils {
  public static boolean isBlank(String s) { return s==null || s.trim().isEmpty(); }
  public static String camelToSnake(String s) { return s.replaceAll("([A-Z])","_$1").toLowerCase(); }
}