// CodeGenController.java
package org.ai.aicopilotforapi.controller;


import org.ai.aicopilotforapi.service.CodeGeneratorService;
import org.ai.aicopilotforapi.vo.GenerateCodeReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/codegen")
@Validated
public class CodeGenController {

    private final CodeGeneratorService codeGeneratorService;

    public CodeGenController(CodeGeneratorService codeGeneratorService) {
        this.codeGeneratorService = codeGeneratorService;
    }

    /**
     *
     * @param generateCodeReq
     * @return
     */
    @PostMapping("/generate")
    public Flux<String> generateCode(@NotBlank @RequestBody GenerateCodeReq generateCodeReq) {
        return codeGeneratorService.generateApiCode(generateCodeReq);
    }


}