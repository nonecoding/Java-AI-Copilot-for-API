package org.ai.aicopilotforapi.agent.service;

import org.ai.aicopilotforapi.common.config.FileStorageProperties;
import org.ai.aicopilotforapi.common.util.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    private final Path storageLocation;

    public FileStorageService(FileStorageProperties props) {
        this.storageLocation = Paths.get(props.getLocation()).toAbsolutePath().normalize();
    }

    /**
     * Stores the uploaded file and returns the stored filename.
     */
    public String store(MultipartFile file) throws Exception {
        try (InputStream in = file.getInputStream()) {
            return FileUtils.saveFile(in, file.getOriginalFilename(), storageLocation);
        }
    }

    /**
     * Loads a file as a Resource for download.
     */
    public Resource loadAsResource(String filename) throws Exception {
        Path filePath = storageLocation.resolve(filename).normalize();
        return FileUtils.loadAsResource(filePath);
    }
}
