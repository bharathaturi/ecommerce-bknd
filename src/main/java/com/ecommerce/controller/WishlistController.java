package com.ecommerce.controller;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getWishlist() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return ResponseEntity.ok(wishlistService.getWishlist(email));
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addToWishlist(@PathVariable Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        wishlistService.addToWishlist(email, productId);
        return ResponseEntity.ok("Product added to wishlist");
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeFromWishlist(@PathVariable Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        wishlistService.removeFromWishlist(email, productId);
        return ResponseEntity.ok("Product removed from wishlist");
    }
}
