package com.dyes.inventory_management_system.dto;

import com.dyes.inventory_management_system.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    private Long stockId;
    private Long productId;
    private int quantity;
    private String productName;
}
