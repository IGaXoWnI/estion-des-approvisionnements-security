package com.example.tricolv2sb.Controller;

import com.example.tricolv2sb.DTO.ProductStockDetailDTO;
import com.example.tricolv2sb.DTO.StockSummaryDTO;
import com.example.tricolv2sb.DTO.StockValuationDTO;
import com.example.tricolv2sb.Entity.Permission;
import com.example.tricolv2sb.Security.RequirePermission;
import com.example.tricolv2sb.Service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService stockService;

    @GetMapping
    @RequirePermission(Permission.STOCK_READ)
    public ResponseEntity<List<StockSummaryDTO>> getGlobalStock() {
        List<StockSummaryDTO> stock = stockService.getGlobalStock();
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/product/{id}")
    @RequirePermission(Permission.STOCK_READ)
    public ResponseEntity<ProductStockDetailDTO> getProductStockDetail(@PathVariable Long id) {
        ProductStockDetailDTO detail = stockService.getProductStockDetail(id);
        return ResponseEntity.ok(detail);
    }

    @GetMapping("/valuation")
    @RequirePermission(Permission.STOCK_READ)
    public ResponseEntity<StockValuationDTO> getTotalValuation() {
        StockValuationDTO valuation = stockService.getTotalValuation();
        return ResponseEntity.ok(valuation);
    }

    @GetMapping("/alerts")
    @RequirePermission(Permission.STOCK_READ)
    public ResponseEntity<List<StockSummaryDTO>> getStockAlerts() {
        List<StockSummaryDTO> alerts = stockService.getStockAlerts();
        return ResponseEntity.ok(alerts);
    }
}
