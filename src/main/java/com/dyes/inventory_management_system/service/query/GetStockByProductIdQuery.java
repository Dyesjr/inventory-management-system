package com.dyes.inventory_management_system.service.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStockByProductIdQuery {
    private Long productId;
    private Long stockId;


    public GetStockByProductIdQuery(Long productId) {
        this.productId = productId;
    }
}
