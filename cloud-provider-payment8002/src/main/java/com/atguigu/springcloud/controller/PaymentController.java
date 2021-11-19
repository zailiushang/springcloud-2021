package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result = paymentService.create(payment);
        log.info("******插入结果*****"+result);
        if (result > 0) {
            return new CommonResult(200,"=========插入成功=========serverPort=="+serverPort,result);
        }else {
            return new CommonResult(-1,"=========插入失败=========",null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment  Payment;
        Payment = paymentService.getPaymentById(Long.valueOf(id));
        log.info("******插入结果*****"+Payment);
        if (Payment != null ) {
            return new CommonResult(200,"=========查询成功=========serverPort=="+serverPort,Payment);
        }else {
            return new CommonResult(-1,"=========查询失败6=========",null);
        }

    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
