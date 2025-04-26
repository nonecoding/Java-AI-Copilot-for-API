package org.ai.aicopilotforapi.pay.service;



import org.ai.aicopilotforapi.pay.dto.PaymentRequest;
import org.ai.aicopilotforapi.pay.dto.PaymentResponse;
import org.springframework.stereotype.Service;

@Service("WECHAT")
public class WechatService extends BasePaymentService {
    @Override
    public PaymentResponse pay(PaymentRequest req) {
        // TODO: 实现微信支付预下单逻辑
        throw new UnsupportedOperationException("微信支付暂未实现");
    }
}
