package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.entity.Wishlist;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> getWishlist(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Wishlist wishlist = wishlistRepository.findByUserId(user.getId())
                .orElse(new Wishlist());

        if (wishlist.getProducts() == null) {
            return List.of();
        }

        return wishlist.getProducts().stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addToWishlist(String email, Long productId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Wishlist wishlist = wishlistRepository.findByUserId(user.getId())
                .orElse(new Wishlist());

        if (wishlist.getUser() == null) {
            wishlist.setUser(user);
        }

        if (wishlist.getProducts() == null) {
            wishlist.setProducts(new HashSet<>());
        }

        wishlist.getProducts().add(product);
        wishlistRepository.save(wishlist);
    }

    @Transactional
    public void removeFromWishlist(String email, Long productId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Wishlist wishlist = wishlistRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (wishlist.getProducts() != null) {
            wishlist.getProducts().remove(product);
            wishlistRepository.save(wishlist);
        }
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setImageUrl(product.getImageUrl());
        dto.setCategory(product.getCategory());
        dto.setFeatured(product.isFeatured());
        dto.setDiscountPrice(product.getDiscountPrice());
        dto.setRating(product.getRating());
        return dto;
    }
}
