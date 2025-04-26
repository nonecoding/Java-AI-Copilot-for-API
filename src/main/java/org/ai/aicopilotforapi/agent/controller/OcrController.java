package org.ai.aicopilotforapi.agent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    @Autowired
    private DocumentTextExtractor extractor;
    private static final Logger logger = LoggerFactory.getLogger(DocumentTextExtractor.class);


    @PostMapping(value = "/extract", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String extractText(@RequestParam("file") MultipartFile file) throws Exception {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            extractor.extractText(file.getInputStream(), out);
            String extractedText = out.toString("UTF-8");
            if (extractedText.isEmpty()) {
                // 如果提取的文本为空，输出日志或返回错误信息
                logger.warn("No text extracted from file.");
            }
            return extractedText;
        }
    }
}
