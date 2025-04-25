package org.ai.aicopilotforapi.repository;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetObjectArgs;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

@Repository
public class FileRepository {
    private final MinioClient client;

    public FileRepository(MinioClient client) {
        this.client = client;
    }

    public void upload(String bucket, String objectName, InputStream data, long size, String contentType) throws Exception {
        // 确保桶存在可选；示例忽略
        client.putObject(
            PutObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .stream(data, size, -1)
                .contentType(contentType)
                .build()
        );
    }

    public InputStream download(String bucket, String objectName) throws Exception {
        return client.getObject(
            GetObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .build()
        );
    }
}
