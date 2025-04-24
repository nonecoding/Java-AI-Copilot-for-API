// CodeGeneratorService.java
package org.ai.aicopilotforapi.service;


import org.ai.aicopilotforapi.client.OpenAIClient;
import org.ai.aicopilotforapi.vo.GenerateCodeReq;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CodeGeneratorService {

    private final OpenAIClient openAIClient;

    public CodeGeneratorService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    public Flux<String> generateApiCode(GenerateCodeReq generateCodeReq) {
        String prompt = "帮我写一个Spring Boot REST API，包括Controller、Service、Repository，实体名是：" + generateCodeReq.getEntityName() + "，字段有：" + generateCodeReq.getFields();
        return openAIClient.generateCodeStream(prompt);
    }
}