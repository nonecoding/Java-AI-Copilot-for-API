package org.ai.aicopilotforapi.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ocr")
public class OcrProperties {

    private String language;
    private String tesseractPath;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTesseractPath() {
        return tesseractPath;
    }

    public void setTesseractPath(String tesseractPath) {
        this.tesseractPath = tesseractPath;
    }
}
