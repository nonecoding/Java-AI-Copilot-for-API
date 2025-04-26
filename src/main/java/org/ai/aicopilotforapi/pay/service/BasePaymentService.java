package org.ai.aicopilotforapi.pay.service;


import org.ai.aicopilotforapi.pay.dto.PaymentRequest;
import org.ai.aicopilotforapi.pay.dto.PaymentResponse;

/**
 * 抽象基类：定义支付方法
 * （注意：不是接口，是抽象类）
 */
public abstract class BasePaymentService {
    /** 支付方法，必须由子类实现 */
    public abstract PaymentResponse pay(PaymentRequest request) throws Exception;
}
