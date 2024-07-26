package com.dyes.inventory_management_system.service.query;

import com.dyes.inventory_management_system.dto.StockDTO;
import com.dyes.inventory_management_system.exceptions.ProductNotFoundException;
import com.dyes.inventory_management_system.model.Product;
import com.dyes.inventory_management_system.model.Stock;
import com.dyes.inventory_management_system.repositories.ProductRepository;
import com.dyes.inventory_management_system.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllStocksQueryService {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Autowired
    public GetAllStocksQueryService(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    public List<StockDTO> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        List<StockDTO> stockDTOs = new ArrayList<>();

        for (Stock stock : stocks) {
            Product product = productRepository.findById(stock.getProductId())
                    .orElseThrow(()-> new ProductNotFoundException("Product not found"));

            StockDTO dto = new StockDTO();
            dto.setStockId(stock.getStockId());
            dto.setProductId(stock.getProductId());
            dto.setQuantity(stock.getQuantity());
            dto.setProductName(stock.getProductName());

            stockDTOs.add(dto);
        }

        return stockDTOs;
    }
}
