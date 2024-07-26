package com.dyes.inventory_management_system.service.query;

import com.dyes.inventory_management_system.dto.StockDTO;
import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.model.Stock;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import com.dyes.inventory_management_system.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetStockByProductIdQueryService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Autowired
    public GetStockByProductIdQueryService(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    public StockDTO execute(GetStockByProductIdQuery query) {

        Stock stock = stockRepository.findByProductId(query.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Stock not found with product Id " + query.getProductId()));

        Product product = productRepository.findById(query.getProductId())
                .orElseThrow(()-> new ProductNotFoundException("Product not found with id: " + stock.getProductId()));

        StockDTO stockDTO = new StockDTO();
        stockDTO.setStockId(stock.getStockId());
        stockDTO.setProductId(stock.getProductId());
        stockDTO.setQuantity(stock.getQuantity());
        stockDTO.setProductName(product.getProductName());

        System.out.println("Retrieved StockDTO: " + stockDTO);

        return stockDTO;
    }
}
