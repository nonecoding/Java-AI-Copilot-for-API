package org.ai.aicopilotforapi.agent.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.ocr.TesseractOCRParser;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
public class DocumentTextExtractor {

    private final Parser parser;
    private final ParseContext context;
    private Tika tika = new Tika();  // 初始化 Tika

    @Autowired
    public DocumentTextExtractor(@Value("${ocr.tesseract-path}") String tesseractPath,
                                 @Value("${ocr.language}") String language) {
        this.parser = new AutoDetectParser();

        // 1. 使用 TesseractOCRParser 解析器
        TesseractOCRParser tesseractOCRParser = new TesseractOCRParser();

        // 配置 OCR
        TesseractOCRConfig ocrConfig = new TesseractOCRConfig();
        ocrConfig.setLanguage(language);

        // 将路径传递给 TesseractOCRParser
        tesseractOCRParser.setTesseractPath(tesseractPath);  // 设置路径

        // 配置 ParseContext
        ParseContext ctx = new ParseContext();
        ctx.set(TesseractOCRParser.class, tesseractOCRParser);
        ctx.set(Parser.class, parser);

        // PDF 内嵌图像走 OCR
        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);
        pdfConfig.setOcrStrategy(PDFParserConfig.OCR_STRATEGY.OCR_ONLY);
        ctx.set(PDFParserConfig.class, pdfConfig);

        this.context = ctx;
    }

    private static final Logger logger = LoggerFactory.getLogger(DocumentTextExtractor.class);

    public String extractText(InputStream inputStream, OutputStream out) throws IOException, TikaException, SAXException {
        // 先检测 MIME 类型
        int availableBytes = inputStream.available();
        logger.debug("InputStream available bytes: {}", availableBytes);
        if (availableBytes == 0) {
            logger.warn("InputStream is empty, aborting extraction.");
            throw new IOException("InputStream is empty, cannot extract text.");
        }
        String mimeType = tika.detect(inputStream);
        logger.debug("Detected MIME type: {}", mimeType);  // 打印日志输出 MIME 类型

        // 调用 Tika 的 AutoDetectParser 进行文本提取
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(out);  // 通过 OutputStream 输出内容
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        try {
            // 进行文件解析
            parser.parse(inputStream, handler, metadata, context);

            // 提取文本到 OutputStream，输出内容到 out
            String extractedText = handler.toString();

            // 输出日志查看提取结果
            logger.debug("Extracted text: {}", extractedText);

            // 如果没有提取出内容，记录警告
            if (extractedText.isEmpty()) {
                logger.warn("No text extracted from the file.");
            }

            return extractedText;  // 返回提取的文本
        } catch (TikaException | SAXException | IOException e) {
            logger.error("Error extracting text with Tika", e);
            throw e;  // 如果发生异常，继续抛出异常
        }
    }

}
