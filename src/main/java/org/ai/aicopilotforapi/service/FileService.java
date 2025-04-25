package org.ai.aicopilotforapi.service;

import org.ai.aicopilotforapi.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class FileService {

    @Value("${minio.bucketName}")
    private String bucketName;

    private final FileRepository repo;

    public FileService(FileRepository repo) {
        this.repo = repo;
    }

    public String uploadFile(MultipartFile file) {
        try {
            String bucket = bucketName;
            String objectName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            repo.upload(bucket, objectName, file.getInputStream(), file.getSize(), file.getContentType());
            return bucket + "/" + objectName;
        } catch (Exception e) {
            throw new RuntimeException("上传失败", e);
        }
    }

    public InputStream getFile(String path) throws Exception {
        String[] parts = path.split("/", 2);
        return repo.download(parts[0], parts[1]);
    }
}
