package org.ai.aicopilotforapi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file.storage")
@Data
public class FileStorageProperties {
    /**
     * Directory where uploaded files will be stored
     */
    private String location;
}
