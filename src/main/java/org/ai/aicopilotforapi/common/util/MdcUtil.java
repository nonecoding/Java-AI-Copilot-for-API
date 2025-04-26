package org.ai.aicopilotforapi.common.util;

import org.apache.log4j.MDC;

public class MdcUtil {
  public static void put(String key, String val) { MDC.put(key, val); }
  public static void remove(String key){ MDC.remove(key); }
  public static void clear(){ MDC.clear(); }
}