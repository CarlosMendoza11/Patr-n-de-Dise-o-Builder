package com.example.payment.Infrastructure.Controller;

import com.example.payment.Application.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public double procesarPago(@RequestParam String tipo,
                               @RequestParam double monto,
                               @RequestParam(defaultValue = "EMAIL") String canal) {
        return paymentService.processPayment(tipo, monto, canal);
    }
}
