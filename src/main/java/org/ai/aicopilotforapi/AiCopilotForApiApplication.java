package org.ai.aicopilotforapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO 全局过滤掉git commit和push中的appilication.yml和application-test.yml
 *
 * TODO 用户注册，用户登录
 */
@SpringBootApplication
public class AiCopilotForApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCopilotForApiApplication.class, args);
        System.out.println("--------------------------------------------------");
        System.out.println("✅ Knife4j 文档地址: http://localhost:8080/doc.html");
        System.out.println("--------------------------------------------------");
    }

}
