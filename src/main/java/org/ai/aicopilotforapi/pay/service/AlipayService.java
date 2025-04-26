package org.ai.aicopilotforapi.pay.service;

import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;




import lombok.RequiredArgsConstructor;
import org.ai.aicopilotforapi.pay.config.PaymentProperties;
import org.ai.aicopilotforapi.pay.dto.PaymentRequest;
import org.ai.aicopilotforapi.pay.dto.PaymentResponse;
import org.ai.aicopilotforapi.pay.util.QrCodeUtil;
import org.springframework.stereotype.Service;

@Service("ALIPAY")
@RequiredArgsConstructor
public class AlipayService extends BasePaymentService {
    private final PaymentProperties props;

    @Override
    public PaymentResponse pay(PaymentRequest req) throws Exception {
        var cfg = props.getAlipay();
        AlipayClient client = new DefaultAlipayClient(
            cfg.getGatewayUrl(), cfg.getAppId(),
            cfg.getPrivateKey(), "json", "UTF-8",
            cfg.getPublicKey(), "RSA2"
        );
        var pre = new AlipayTradePrecreateRequest();
        pre.setBizContent("""
            {"out_trade_no":"%s","total_amount":"%s","subject":"订单支付"}
            """.formatted(req.getOrderId(), req.getAmount()));
        var resp = client.execute(pre);
        if (!resp.isSuccess()) {
            throw new RuntimeException("支付宝下单失败：" + resp.getMsg());
        }
        String qr = resp.getQrCode();
        String qrUrl = QrCodeUtil.generateBase64Qr(qr);
        return new PaymentResponse(req.getOrderId(), qrUrl);
    }
}
