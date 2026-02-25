package com.ecommerce.controller;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.CustomUserDetailsService;
import com.ecommerce.util.JwtUtil;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        User user = getAuthenticatedUser();
        return ResponseEntity.ok(cartService.getCart(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody Map<String, Object> request) {
        User user = getAuthenticatedUser();
        Long productId = Long.valueOf(request.get("productId").toString());
        int quantity = Integer.parseInt(request.get("quantity").toString());
        return ResponseEntity.ok(cartService.addToCart(user.getId(), productId, quantity));
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long cartItemId,
            @RequestBody Map<String, Integer> request) {
        User user = getAuthenticatedUser();
        int quantity = request.get("quantity");
        return ResponseEntity.ok(cartService.updateCartItem(user.getId(), cartItemId, quantity));
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long cartItemId) {
        User user = getAuthenticatedUser();
        return ResponseEntity.ok(cartService.removeFromCart(user.getId(), cartItemId));
    }

    @DeleteMapping
    public ResponseEntity<Cart> clearCart() {
        User user = getAuthenticatedUser();
        return ResponseEntity.ok(cartService.clearCart(user.getId()));
    }
}
