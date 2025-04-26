//package org.ai.aicopilotforapi.common.config;
//
//import lombok.var;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//
//@Configuration
//public class ThreadPoolConfig {
//  @Bean
//  public Executor taskExecutor(){
//    var exec = new ThreadPoolTaskExecutor();
//    exec.setCorePoolSize(10);
//    exec.setMaxPoolSize(50);
//    exec.setQueueCapacity(200);
//    return exec;
//  }
//}