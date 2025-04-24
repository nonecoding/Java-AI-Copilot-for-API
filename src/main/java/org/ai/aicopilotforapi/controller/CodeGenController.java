// CodeGenController.java
package org.ai.aicopilotforapi.controller;


import org.ai.aicopilotforapi.service.CodeGeneratorService;
import org.springframework.http.ResponseEntity;
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
     * TODO 改成流式返回
     *
     * @param entityName
     * @param fields
     * @return
     */
    @PostMapping("/generate")
    public Flux<String> generateCode(@RequestParam String entityName, @RequestParam String fields) {
        return codeGeneratorService.generateApiCode(entityName, fields);
    }


}