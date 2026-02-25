package com.ecommerce.controller;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setRewardsPoints(user.getRewardsPoints());
        userDTO.setRefundAmount(user.getRefundAmount());
        userDTO.setRole(user.getRole());

        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateProfile(@RequestBody UserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userDTO.getName() != null)
            user.setName(userDTO.getName());
        if (userDTO.getPhoneNumber() != null)
            user.setPhoneNumber(userDTO.getPhoneNumber());
        if (userDTO.getAddress() != null)
            user.setAddress(userDTO.getAddress());

        User savedUser = userRepository.save(user);

        // Return updated DTO
        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setEmail(savedUser.getEmail());
        responseDTO.setName(savedUser.getName());
        responseDTO.setPhoneNumber(savedUser.getPhoneNumber());
        responseDTO.setAddress(savedUser.getAddress());
        responseDTO.setRewardsPoints(savedUser.getRewardsPoints());
        responseDTO.setRefundAmount(savedUser.getRefundAmount());
        responseDTO.setRole(savedUser.getRole());

        return ResponseEntity.ok(responseDTO);
    }
}
