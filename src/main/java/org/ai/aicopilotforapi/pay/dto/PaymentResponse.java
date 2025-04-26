package org.ai.aicopilotforapi.pay.dto;

import lombok.Data;

// PaymentResponse.java
@Data
public class PaymentResponse {
    private String qrCodeUrl;  // 二维码 Base64
    private String orderId;

    public PaymentResponse(String orderId, String qrUrl) {

    }
}