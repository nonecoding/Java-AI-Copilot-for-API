// CodeGenController.java
package org.ai.aicopilotforapi.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ai.aicopilotforapi.common.config.ApiResult;
import org.ai.aicopilotforapi.service.CodeGeneratorService;
import org.ai.aicopilotforapi.vo.GenerateCodeReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/codegen")
@Validated
@Api(value = "Java + AI Copilot for API")
public class CodeGenController {

    private final CodeGeneratorService codeGeneratorService;

    public CodeGenController(CodeGeneratorService codeGeneratorService) {
        this.codeGeneratorService = codeGeneratorService;
    }

    /**
     * @param generateCodeReq
     * @return
     */
    @ApiOperation(value = "test ai api")
    @PostMapping("/generate")
    public Flux<String> generateCode(@NotBlank @RequestBody GenerateCodeReq generateCodeReq) {
        return codeGeneratorService.generateApiCode(generateCodeReq);
    }


    /**
     * convertDocToTxt
     *
     * @return
     */
    @PostMapping("/convertDocToTxt")
    @ApiOperation(value = "convertDocToTxt")
    public ApiResult<Boolean> convertDocToTxt() {
        return null;
    }

    /**
     * uploadToMinIo
     *
     * @return
     */
    @PostMapping("uploadToMinIo")
    @ApiOperation(value = "uploadToMinIo")
    public ApiResult<Boolean> uploadToMinIo() {
        return null;
    }

}