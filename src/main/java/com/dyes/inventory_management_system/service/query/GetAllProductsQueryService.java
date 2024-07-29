package com.dyes.inventory_management_system.service.query;

import com.dyes.inventory_management_system.dto.ProductDTO;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllProductsQueryService {

    private final ProductRepository productRepository;

    @Autowired
    public GetAllProductsQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getQuantity(),
                        product.getPrice(),
                        product.getSupplierName()
                ))
                .collect(Collectors.toList());
    }
}
