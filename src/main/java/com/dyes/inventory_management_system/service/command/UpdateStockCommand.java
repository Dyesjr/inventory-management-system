package com.dyes.inventory_management_system.service.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStockCommand {
    private Long stockId;
    private Long productId;
    private int quantity;
}
