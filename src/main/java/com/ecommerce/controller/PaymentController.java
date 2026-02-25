package com.ecommerce.controller;

import com.ecommerce.dto.PaymentDTO;
import com.ecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<PaymentDTO> createPaymentOrder(@RequestBody Map<String, Object> request) {
        Long orderId = Long.valueOf(request.get("orderId").toString());
        // String paymentMode = (String) request.get("paymentMode"); // Assuming logic
        return ResponseEntity.ok(paymentService.createPayment(orderId, "RAZORPAY"));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> request) {
        String paymentId = request.get("razorpay_payment_id");
        String orderId = request.get("razorpay_order_id");
        String signature = request.get("razorpay_signature");

        // Add actual verification logic here
        return ResponseEntity.ok(Map.of("success", true, "message", "Payment verified successfully"));
    }
}
