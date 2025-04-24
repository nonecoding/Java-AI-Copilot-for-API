// CodeGenController.java
package org.ai.aicopilotforapi.controller;


import org.ai.aicopilotforapi.service.CodeGeneratorService;
import org.ai.aicopilotforapi.vo.GenerateCodeReq;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/codegen")
public class CodeGenController {

    private final CodeGeneratorService codeGeneratorService;

    public CodeGenController(CodeGeneratorService codeGeneratorService) {
        this.codeGeneratorService = codeGeneratorService;
    }

    /**
     *
     * @param entityName
     * @param fields
     * @return
     */
    @PostMapping("/generate")
    public Flux<String> generateCode(@RequestBody GenerateCodeReq generateCodeReq) {
        return codeGeneratorService.generateApiCode(generateCodeReq);
    }


}