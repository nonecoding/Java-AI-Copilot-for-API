package org.ai.aicopilotforapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.ai.aicopilotforapi.controller.DocumentTextExtractor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * TODO 全局过滤掉git commit和push中的appilication.yml和application-test.yml
 * <p>
 * TODO 用户注册，用户登录
 * TODO 支付功能
 * TODO 出色的产品
 * TODO 解析文档
 */
@SpringBootApplication
public class AiCopilotForApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCopilotForApiApplication.class, args);
        System.out.println("--------------------------------------------------");
        System.out.println("✅ Knife4j 文档地址: http://localhost:8080/doc.html");
        System.out.println("--------------------------------------------------");


    }


    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }


}
