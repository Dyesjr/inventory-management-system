package com.dyes.inventory_management_system.api;

import com.dyes.inventory_management_system.dto.ProductDTO;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.service.command.*;
import com.dyes.inventory_management_system.service.query.GetAllProductsQueryService;
import com.dyes.inventory_management_system.service.query.GetProductByIdQuery;
import com.dyes.inventory_management_system.service.query.GetProductByIdQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    private final CreateProductCommandService createProductCommandService;
    private final UpdateProductCommandService updateProductCommandService;
    private final DeleteProductCommandService deleteProductCommandService;
    private final GetAllProductsQueryService getAllProductsQueryService;
    private final GetProductByIdQueryService getProductByIdQueryService;

    @Autowired
    public ProductController(CreateProductCommandService createProductCommandService,
                             UpdateProductCommandService updateProductCommandService,
                             DeleteProductCommandService deleteProductCommandService,
                             GetAllProductsQueryService getAllProductsQueryService,
                             GetProductByIdQueryService getProductByIdQueryService) {
        this.createProductCommandService = createProductCommandService;
        this.updateProductCommandService = updateProductCommandService;
        this.deleteProductCommandService = deleteProductCommandService;
        this.getAllProductsQueryService = getAllProductsQueryService;
        this.getProductByIdQueryService = getProductByIdQueryService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductCommand createProductCommand) {
        log.info("Received request to create product: {}", createProductCommand);
        try {
            Product createdProduct = createProductCommandService.execute(createProductCommand);
            log.info("Product created successfully: {}", createdProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            log.error("Error occurred while creating product", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestBody UpdateProductCommand updateProductCommand) {
        updateProductCommand.setProductId(id);
        Product updatedProduct = updateProductCommandService.execute(updateProductCommand);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        DeleteProductCommand command = new DeleteProductCommand(id);

        deleteProductCommandService.execute(command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = getAllProductsQueryService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        GetProductByIdQuery query = new GetProductByIdQuery(id);
        Product product = getProductByIdQueryService.execute(query);

        return ResponseEntity.ok(product);
    }
}
