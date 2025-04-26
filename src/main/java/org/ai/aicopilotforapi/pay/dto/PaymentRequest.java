package org.ai.aicopilotforapi.pay.dto;

import lombok.Data;

// PaymentRequest.java
@Data
public class PaymentRequest {
    private String orderId;  // 订单号
    private String amount;   // 金额（元）
    private String payType;  // ALIPAY 或 WECHAT
}

