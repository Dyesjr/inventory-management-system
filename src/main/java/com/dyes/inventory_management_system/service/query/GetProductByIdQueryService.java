package com.dyes.inventory_management_system.service.query;

import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdQueryService {

    private final ProductRepository productRepository;

    @Autowired
    public GetProductByIdQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(GetProductByIdQuery query) {
        return productRepository.findById(query.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found " + query.getProductId() ));
    }
}
