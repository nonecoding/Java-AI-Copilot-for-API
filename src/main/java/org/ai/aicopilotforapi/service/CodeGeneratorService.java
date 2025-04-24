// CodeGeneratorService.java
package org.ai.aicopilotforapi.service;


import org.ai.aicopilotforapi.client.OpenAIClient;
import org.springframework.stereotype.Service;

@Service
public class CodeGeneratorService {

    private final OpenAIClient openAIClient;

    public CodeGeneratorService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public String generateApiCode(String entityName, String fields) {
        String prompt = "帮我写一个Spring Boot REST API，包括Controller、Service、Repository，实体名是：" + entityName + "，字段有：" + fields;
        return openAIClient.generateCode(prompt);
    }
}