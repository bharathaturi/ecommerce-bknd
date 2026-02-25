package com.ecommerce.service;

import com.ecommerce.dto.PaymentDTO;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Payment;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public PaymentDTO createPayment(Long orderId, String paymentMode) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMode(paymentMode);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentStatus("PENDING");
        // payment.setTransactionId(generateTransactionId()); // Logic for gateway

        Payment savedPayment = paymentRepository.save(payment);
        return mapToDTO(savedPayment);
    }

    // Mock method for verification
    public boolean verifyPayment(String paymentId, String status) {
        // Logic to verify with Razorpay/Stripe
        return "SUCCESS".equals(status);
    }

    private PaymentDTO mapToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setPaymentMode(payment.getPaymentMode());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setAmount(payment.getAmount());
        dto.setOrderId(payment.getOrder().getId());
        dto.setTransactionId(payment.getTransactionId());
        return dto;
    }
}
