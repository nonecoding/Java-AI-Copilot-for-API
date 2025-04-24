// CodeGenController.java
package org.ai.aicopilotforapi.controller;


import org.ai.aicopilotforapi.service.CodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/codegen")
public class CodeGenController {

    private final CodeGeneratorService codeGeneratorService;

    public CodeGenController(CodeGeneratorService codeGeneratorService) {
        this.codeGeneratorService = codeGeneratorService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateCode(@RequestParam String entityName, @RequestParam String fields) {
        String code = codeGeneratorService.generateApiCode(entityName, fields);
        return ResponseEntity.ok(code);
    }


}