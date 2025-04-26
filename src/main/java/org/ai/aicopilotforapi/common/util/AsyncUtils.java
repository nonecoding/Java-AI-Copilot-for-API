package org.ai.aicopilotforapi.common.util;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class AsyncUtils {
  public static <T> CompletableFuture<T> supply(Supplier<T> sup){
    return CompletableFuture.supplyAsync(sup);
  }
}