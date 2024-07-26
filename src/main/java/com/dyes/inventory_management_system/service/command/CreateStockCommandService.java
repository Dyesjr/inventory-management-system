package com.dyes.inventory_management_system.service.command;

import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.model.Stock;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import com.dyes.inventory_management_system.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateStockCommandService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;


    @Autowired
    public CreateStockCommandService(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    public Stock execute(CreateStockCommand command) {
        Stock stock = new Stock();
        stock.setProductId(command.getProductId());
        stock.setQuantity(command.getQuantity());
        stock.setProductName(command.getProductName());

        Stock savedStock = stockRepository.save(stock);

        //update product quantity
        productRepository.findById(command.getProductId()).ifPresent(product -> {
            product.setQuantity(product.getQuantity() + command.getQuantity());
            productRepository.save(product);
        });

//        Product product = productRepository.findById(command.getProductId())
//                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
//
//        product.setQuantity(product.getQuantity() + command.getQuantity());
//        productRepository.save(product);

        return savedStock;

//        return stockRepository.save(stock);
    }
}
