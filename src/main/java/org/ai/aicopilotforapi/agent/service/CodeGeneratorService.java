// CodeGeneratorService.java
package org.ai.aicopilotforapi.agent.service;


import org.ai.aicopilotforapi.agent.client.OpenAIClient;
import org.ai.aicopilotforapi.agent.vo.GenerateCodeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

@Service
public class CodeGeneratorService {

    @Autowired
    private FileService fileService;

    private final OpenAIClient openAIClient;

    public CodeGeneratorService(OpenAIClient openAIClient) {
        this.openAIClient = openAIClient;
    }

    /**
     * testAiApi
     *
     * @param generateCodeReq
     * @return
     */
    public Flux<String> generateApiCode(GenerateCodeReq generateCodeReq) {
        String prompt = "plz write for me a Spring Boot REST API，include Controller、Service、Repository，entity name is："
                + generateCodeReq.getEntityName() + "，field include：" + generateCodeReq.getFields();
        return openAIClient.generateCodeStream(prompt);
    }

    /**
     * convertDocToTxt
     *
     * @param docPath
     * @return
     */
    public String convertDocToTxt(String docPath) {

        return null;
    }

    /**
     * uploadToMinIo
     *
     * @param multipartFile
     * @return
     */
    public String uploadToMinIo(MultipartFile multipartFile) {
        return fileService.uploadFile(multipartFile);
    }
}