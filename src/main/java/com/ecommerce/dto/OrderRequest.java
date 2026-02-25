package com.ecommerce.dto;

import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

@Data
public class OrderRequest {
    private List<OrderItemRequest> items;
    private String shippingAddress;
    private String paymentMethod;
    private BigDecimal totalAmount;
}
