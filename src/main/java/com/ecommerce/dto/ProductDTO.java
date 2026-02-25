package com.ecommerce.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private String category;
    private Integer stockQuantity;
    private String brand;
    private String color;
    private String size;
    private Double rating;
    private BigDecimal discountPrice;
    private boolean featured;
}
