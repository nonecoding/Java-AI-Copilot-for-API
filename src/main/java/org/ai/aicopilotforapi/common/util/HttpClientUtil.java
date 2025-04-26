package org.ai.aicopilotforapi.common.util;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class HttpClientUtil {
  private static final CloseableHttpClient CLIENT = HttpClients.createDefault();
  public static String get(String url) { /*…*/
      return url;
  }
  public static String postJson(String url, String json) { /*…*/
      return url;
  }
}