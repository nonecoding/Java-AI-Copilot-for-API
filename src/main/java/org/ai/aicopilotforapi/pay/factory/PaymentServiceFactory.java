package org.ai.aicopilotforapi.pay.factory;

import org.ai.aicopilotforapi.pay.service.BasePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFactory {
    @Autowired
    private ApplicationContext ctx;

    /**
     * 根据 payType（对应 @Service 的 value）获取实际 Service Bean
     */
    public BasePaymentService getService(String payType) {
        // payType 必须和 @Service("…") 名称一致
        return ctx.getBean(payType, BasePaymentService.class);
    }
}
