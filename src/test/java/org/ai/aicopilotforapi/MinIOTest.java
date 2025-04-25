package org.ai.aicopilotforapi;

import io.minio.DownloadObjectArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.UploadObjectArgs;
import org.junit.jupiter.api.Test;

/**
 * @author lin
 */
public class MinIOTest {

    private static final MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://192.168.200.128:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();

    @Test
    void testUploadObject() {
        try {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket("test-bucket")
                    .object("pic.jpg")
                    .filename("D:\\images\\pic.jpg")
                    .build();
            minioClient.uploadObject(uploadObjectArgs);
            System.out.println("上传成功！");
        } catch (Exception e) {
            System.out.println("上传失败！");
        }
    }

    @Test
    void testUploadObject2() {
        try {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket("test-bucket")
                    .object("2023/1/14/pic.jpg")
                    .filename("D:\\images\\pic.jpg")
                    .build();
            minioClient.uploadObject(uploadObjectArgs);
            System.out.println("上传成功！");
        } catch (Exception e) {
            System.out.println("上传失败！");
        }
    }

    @Test
    void testRemoveObject() {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket("test-bucket")
                    .object("pic.jpg")
                    .build();
            minioClient.removeObject(removeObjectArgs);
            System.out.println("删除成功！");
        } catch (Exception e) {
            System.out.println("删除失败！");
        }
    }

    @Test
    void testDownloadObject() {
        try {
            DownloadObjectArgs downloadObjectArgs = DownloadObjectArgs.builder()
                    .bucket("test-bucket")
                    .object("2023/1/14/pic.jpg")
                    .filename("D:\\images\\pic2.jpg")
                    .build();
            minioClient.downloadObject(downloadObjectArgs);
            System.out.println("下载成功！");
        } catch (Exception e) {
            System.out.println("下载失败！");
        }
    }
}
