package org.ai.aicopilotforapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiCopilotForApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCopilotForApiApplication.class, args);
        System.out.println("--------------------------------------------------");
        System.out.println("✅ Knife4j 文档地址: http://localhost:8080/doc.html");
        System.out.println("--------------------------------------------------");
    }

}
