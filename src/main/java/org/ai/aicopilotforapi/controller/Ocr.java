package org.ai.aicopilotforapi.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.ai.aicopilotforapi.common.config.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
public class Ocr {

    @PostMapping("/ocr")
    @ApiOperationSupport(order = 3) // 用于控制swagger显示顺序
    @ApiOperation(value = "图像识别", notes = "上传图像进行OCR识别并返回文本内容")
    public ApiResult<String> ocr(@RequestParam("file") MultipartFile file) throws Exception {
        // 1. 创建临时文件并保存上传的文件
        File tempFile = File.createTempFile("tempfile", file.getOriginalFilename());
        file.transferTo(tempFile);

        // 2. 创建 Tesseract OCR 实例
        ITesseract tess = new Tesseract();

        // 3. 设置 OCR 配置（可以指定语言包等）
        tess.setLanguage("eng"); // 设置识别的语言为英语
// 设置 tesseract 数据路径
        tess.setDatapath("/path/to/tessdata/");  // 你需要提供实际的 tessdata 路径


        // 4. 从临时文件中读取图片并进行 OCR 识别
        BufferedImage image = ImageIO.read(tempFile);

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
