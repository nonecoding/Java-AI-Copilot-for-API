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
        String prompt = "plz write for me a Spring Boot REST API，include Controller、Service、Repository，entity name is：" + generateCodeReq.getEntityName() + "，field include：" + generateCodeReq.getFields();
        return openAIClient.generateCodeStream(prompt);
    }
}