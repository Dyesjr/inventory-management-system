package com.dyes.inventory_management_system.api;

import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.service.command.*;
import com.dyes.inventory_management_system.service.query.GetAllProductsQueryService;
import com.dyes.inventory_management_system.service.query.GetProductByIdQuery;
import com.dyes.inventory_management_system.service.query.GetProductByIdQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
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
        return ResponseEntity.ok(CreateProductCommandService.execute(createProductCommand));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestBody UpdateProductCommand updateProductCommand, @PathVariable String id) {
        updateProductCommand.setProductId(productId);
        return ResponseEntity.ok(updateProductCommandService.execute(updateProductCommand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long productId) {
        DeleteProductCommand command = new DeleteProductCommand();
        command.setProductId(productId);
        deleteProductCommandService.execute(command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(getAllProductsQueryService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        GetProductByIdQuery query = new GetProductByIdQuery();
        query.setProductId(productId);
        return ResponseEntity.ok(getProductByIdQueryService.execute(query));
    }
}
