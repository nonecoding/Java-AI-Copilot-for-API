//package org.ai.aicopilotforapi.common.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//
//import lombok.var;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JacksonConfig {
//  @Bean
//  public ObjectMapper objectMapper(){
//    var mapper = new ObjectMapper();
//    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
//    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//    return mapper;
//  }
//}