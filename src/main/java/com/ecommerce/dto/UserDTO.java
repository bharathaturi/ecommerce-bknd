package com.ecommerce.dto;

import lombok.Data;
import com.ecommerce.entity.Role;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private Role role;
    private String password;
    private String address;
    private Integer rewardsPoints;
    private java.math.BigDecimal refundAmount;
}
