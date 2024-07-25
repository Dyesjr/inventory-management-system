package com.dyes.inventory_management_system.repositories;

import com.dyes.inventory_management_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByProductName(String name);
    Optional<Product> findById(Long productId);
}
