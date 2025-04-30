package org.ai.aicopilotforapi;


import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.ai.aicopilotforapi.agent.service.OcrService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class OcrController {
    private final OcrService ocrService;

    @PostMapping(value = "/recognize", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String recognizeImage(@RequestParam("file") MultipartFile file) throws TesseractException, IOException {

      // 调用OcrService中的方法进行文字识别
      return ocrService.recognizeText(file);
    }
}
