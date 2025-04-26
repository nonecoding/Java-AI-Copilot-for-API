package org.ai.aicopilotforapi.pay.controller;




import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.ai.aicopilotforapi.pay.dto.PaymentRequest;
import org.ai.aicopilotforapi.pay.dto.PaymentResponse;
import org.ai.aicopilotforapi.pay.factory.PaymentServiceFactory;
import org.ai.aicopilotforapi.pay.service.BasePaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pay")
@Api(tags = "支付接口")
@RequiredArgsConstructor
public class PayController {
    private final PaymentServiceFactory factory;

    @PostMapping("/qr")
    @ApiOperation("生成支付二维码")
    public PaymentResponse createQr(@RequestBody PaymentRequest req) throws Exception {
        BasePaymentService svc = factory.getService(req.getPayType());
        return svc.pay(req);
    }
}
