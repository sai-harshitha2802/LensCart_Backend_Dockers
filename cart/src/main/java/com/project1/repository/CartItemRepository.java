package com.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Optional extra methods if needed
}
