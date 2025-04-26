// CodeGenController.java
package org.ai.aicopilotforapi.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ai.aicopilotforapi.common.config.ApiResult;
import org.ai.aicopilotforapi.service.CodeGeneratorService;
import org.ai.aicopilotforapi.vo.GenerateCodeReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/codegen")
@Validated
@Api(tags = "Java + AI Copilot for API")
public class CodeGenController {

    private final CodeGeneratorService codeGeneratorService;

    public CodeGenController(CodeGeneratorService codeGeneratorService) {
        this.codeGeneratorService = codeGeneratorService;
    }

    /**
     * testAiApi
     *
     * @param generateCodeReq
     * @return
     */
    @ApiOperation(value = "testAiApi")
    @PostMapping("/generate")
    public Flux<String> generateCode(@NotNull @RequestBody GenerateCodeReq generateCodeReq) {
        return codeGeneratorService.generateApiCode(generateCodeReq);
    }


    /**
     * convertDocToTxt
     *
     * @param docPath
     * @return
     */
    @GetMapping("/convertDocToTxt")
    @ApiOperation(value = "convertDocToTxt")
    public ApiResult<String> convertDocToTxt(@ApiParam(value = "docPath", required = true) @RequestParam("docPath") String docPath) {
        return ApiResult.success(codeGeneratorService.convertDocToTxt(docPath));
    }

    /**
     * uploadToMinIo
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("uploadToMinIo")
    @ApiOperation(value = "uploadToMinIo")
    public ApiResult<Boolean> uploadToMinIo(@RequestBody MultipartFile multipartFile) {
        return ApiResult.success(codeGeneratorService.uploadToMinIo(multipartFile));
    }

}