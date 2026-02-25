package com.ecommerce.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentDTO {
    private Long id;
    private String paymentMode;
    private String paymentStatus;
    private BigDecimal amount;
    private String transactionId;
    private Long orderId;
}
