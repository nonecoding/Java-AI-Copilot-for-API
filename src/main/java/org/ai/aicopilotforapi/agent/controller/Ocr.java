package org.ai.aicopilotforapi.agent.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.ai.aicopilotforapi.common.config.ApiResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author think
 * @version 1.0
 * @description: OCR 识别控制器
 * @date 2025/4/26 17:12
 */
@RestController
@RequestMapping("/ocr")
@Api(tags = "OCR接口")
public class Ocr {

    private static final Logger logger = LoggerFactory.getLogger(Ocr.class);

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "图像识别", notes = "上传图像进行OCR识别并返回文本内容")
    public ApiResult<String> ocr(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info("上传文件名: {}, 大小: {}", file.getOriginalFilename(), file.getSize());
        if (file.isEmpty()) {
            throw new IllegalArgumentException("上传文件为空！");
        }
        // 1. 创建临时文件并保存上传的文件
        File tempFile = File.createTempFile("tempfile", file.getOriginalFilename());
        file.transferTo(tempFile);
        logger.info("临时文件路径: {}，是否存在: {}，文件大小: {}", tempFile.getAbsolutePath(), tempFile.exists(), tempFile.length());

        // 2. 创建 Tesseract OCR 实例
        ITesseract tess = new Tesseract();

        // 3. 设置 OCR 配置（可以指定语言包等）
        tess.setLanguage("eng"); // 设置识别的语言为英语
        String tessdataPath = "C:\\Program Files\\Tesseract-OCR\\tessdata";
        tess.setDatapath(tessdataPath);  // 你需要提供实际的 tessdata 路径
        logger.info("Tesseract datapath: {}，目录是否存在: {}", tessdataPath, new java.io.File(tessdataPath).exists());

        // 4. 从临时文件中读取图片并进行 OCR 识别
        BufferedImage image = ImageIO.read(tempFile);
        if (image == null) {
            logger.error("ImageIO 读取图片失败，文件路径: {}", tempFile.getAbsolutePath());
            throw new IllegalArgumentException("图片文件格式不支持或已损坏");
        }

        // 5. 获取识别结果
        String result;
        try {
            result = tess.doOCR(image); // 执行OCR识别
        } catch (Exception e) {
            throw new Exception("OCR识别失败", e);
        }

        // 删除临时文件
        tempFile.delete();

        // 6. 返回识别结果
        return ApiResult.success(result);
    }
}
