package com.dyes.inventory_management_system.api;

import com.dyes.inventory_management_system.dto.StockDTO;
import com.dyes.inventory_management_system.model.Stock;
import com.dyes.inventory_management_system.service.command.CreateStockCommand;
import com.dyes.inventory_management_system.service.command.CreateStockCommandService;
import com.dyes.inventory_management_system.service.command.UpdateStockCommand;
import com.dyes.inventory_management_system.service.command.UpdateStockCommandService;
import com.dyes.inventory_management_system.service.query.GetAllStocksQueryService;
import com.dyes.inventory_management_system.service.query.GetStockByProductIdQuery;
import com.dyes.inventory_management_system.service.query.GetStockByProductIdQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final CreateStockCommandService createStockCommandService;
    private final UpdateStockCommandService updateStockCommandService;
    private final GetStockByProductIdQueryService getStockByProductIdQueryService;
    private final GetAllStocksQueryService getAllStocksQueryService;

    @Autowired
    public StockController(CreateStockCommandService createStockCommandService, UpdateStockCommandService updateStockCommandService, GetStockByProductIdQueryService getStockByProductIdQueryService, GetAllStocksQueryService getAllStocksQueryService) {
        this.createStockCommandService = createStockCommandService;
        this.updateStockCommandService = updateStockCommandService;
        this.getStockByProductIdQueryService = getStockByProductIdQueryService;
        this.getAllStocksQueryService = getAllStocksQueryService;
    }

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody CreateStockCommand createStockCommand) {
        Stock createdStock = createStockCommandService.execute(createStockCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateSTck(@PathVariable Long id, @RequestBody UpdateStockCommand updateStockCommand) {
        updateStockCommand.setStockId(id);
        Stock updatedStock = updateStockCommandService.execute(updateStockCommand);
        return ResponseEntity.ok(updatedStock);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<StockDTO> getStockByProductId(@PathVariable Long productId) {
        GetStockByProductIdQuery query = new GetStockByProductIdQuery(productId);
        StockDTO stockDTO = getStockByProductIdQueryService.execute(query);
        return ResponseEntity.ok(stockDTO);
    }

    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        List<StockDTO> stocks = getAllStocksQueryService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }
}
