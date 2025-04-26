package org.ai.aicopilotforapi.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "payment")
public class PaymentProperties {
    private Alipay alipay;
    private Wechat wechat;

    @Data
    public static class Alipay {
        private String appId;
        private String privateKey;
        private String publicKey;
        private String gatewayUrl;
    }

    @Data
    public static class Wechat {
        private String appId;
        private String mchId;
        private String apiKey;
    }
}
