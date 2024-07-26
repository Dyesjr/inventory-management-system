package com.dyes.inventory_management_system.service.command;

import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.model.Stock;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import com.dyes.inventory_management_system.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStockCommandService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Autowired
    public UpdateStockCommandService(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    public Stock execute(UpdateStockCommand command) {
        Stock stock = stockRepository.findById(command.getStockId())
                .orElseThrow(() -> new ProductNotFoundException("Stock with id not found " + command.getStockId()));

        int oldQuantity = stock.getQuantity();
        stock.setQuantity(command.getQuantity());

        Stock updatedStock = stockRepository.save(stock);

        Product product = productRepository.findById(stock.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product with id not found " + stock.getProductId()));

        product.setQuantity(product.getQuantity() - oldQuantity + command.getQuantity());
        productRepository.save(product);

//        stock.setProductId(command.getProductId());
//        stock.setQuantity(command.getQuantity());
//
//        return stockRepository.save(stock);

        return updatedStock;
    }
}
