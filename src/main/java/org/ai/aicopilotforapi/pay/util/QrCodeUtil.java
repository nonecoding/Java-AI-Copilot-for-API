package org.ai.aicopilotforapi.pay.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Map;

public class QrCodeUtil {
    public static String generateBase64Qr(String text) throws Exception {
        int w=300,h=300;
        var hints = Map.of(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix matrix = new MultiFormatWriter()
            .encode(text, BarcodeFormat.QR_CODE, w, h, hints);
        var baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
        return "data:image/png;base64," +
          Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
