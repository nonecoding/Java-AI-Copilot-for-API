package org.ai.aicopilotforapi.controller;

import org.ai.aicopilotforapi.common.config.ApiResult;
import org.ai.aicopilotforapi.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ApiResult<String> upload(@RequestParam("file") MultipartFile file) {
        String path = service.uploadFile(file);
        return ApiResult.success(path);
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] download(@RequestParam String path) throws Exception {
        InputStream in = service.getFile(path);
        return in.readAllBytes();
    }
}
