package com.dyes.inventory_management_system.service.query;

import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryService {

    private final ProductRepository productRepository;

    @Autowired
    public GetAllProductsQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
