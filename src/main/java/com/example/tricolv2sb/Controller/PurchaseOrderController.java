package com.example.tricolv2sb.Controller;

import com.example.tricolv2sb.Controller.ControllerInterfaces.PurchaseOrderControllerInterface;
import com.example.tricolv2sb.DTO.CreatePurchaseOrderDTO;
import com.example.tricolv2sb.DTO.ReadPurchaseOrderDTO;
import com.example.tricolv2sb.DTO.UpdatePurchaseOrderDTO;
import com.example.tricolv2sb.Entity.Permission;
import com.example.tricolv2sb.Security.RequirePermission;
import com.example.tricolv2sb.Service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class PurchaseOrderController implements PurchaseOrderControllerInterface {

    private final PurchaseOrderService purchaseOrderService;

    @Override
    @RequirePermission(Permission.ORDER_READ)
    public ResponseEntity<List<ReadPurchaseOrderDTO>> getAllPurchaseOrders() {
        List<ReadPurchaseOrderDTO> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
        return ResponseEntity.ok(purchaseOrders);
    }

    @Override
    @RequirePermission(Permission.ORDER_READ)
    public ResponseEntity<ReadPurchaseOrderDTO> getPurchaseOrderById(Long id) {
        ReadPurchaseOrderDTO purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
        return ResponseEntity.ok(purchaseOrder);
    }

    @Override
    @RequirePermission(Permission.ORDER_CREATE)
    public ResponseEntity<ReadPurchaseOrderDTO> createPurchaseOrder(
            @Valid CreatePurchaseOrderDTO createPurchaseOrderDTO) {
        ReadPurchaseOrderDTO createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(createPurchaseOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPurchaseOrder);
    }

    @Override
    @RequirePermission(Permission.ORDER_CREATE)
    public ResponseEntity<ReadPurchaseOrderDTO> updatePurchaseOrder(Long id,
            @Valid UpdatePurchaseOrderDTO updatePurchaseOrderDTO) {
        ReadPurchaseOrderDTO updatedPurchaseOrder = purchaseOrderService.updatePurchaseOrder(id,
                updatePurchaseOrderDTO);
        return ResponseEntity.ok(updatedPurchaseOrder);
    }

    @Override
    @RequirePermission(Permission.ORDER_CREATE)
    public ResponseEntity<Void> deletePurchaseOrder(Long id) {
        purchaseOrderService.deletePurchaseOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @RequirePermission(Permission.ORDER_READ)
    public ResponseEntity<List<ReadPurchaseOrderDTO>> getPurchaseOrdersBySupplier(Long id) {
        List<ReadPurchaseOrderDTO> purchaseOrders = purchaseOrderService.getPurchaseOrdersBySupplier(id);
        return ResponseEntity.ok(purchaseOrders);
    }

    @PutMapping("/{id}/validate")
    @RequirePermission(Permission.ORDER_VALIDATE)
    public ResponseEntity<String> validateOrder(@PathVariable Long id) {
        purchaseOrderService.validateOrder(id);
        return ResponseEntity.ok("Purchase order " + id + " has been validated");
    }

    @PutMapping("/{id}/reception")
    @RequirePermission(Permission.ORDER_RECEIVE)
    public ResponseEntity<String> receiveOrder(@PathVariable Long id) {
        purchaseOrderService.receiveOrder(id);
        return ResponseEntity.ok("Purchase order " + id + " has been successfully received and stock lots created");
    }

    @PutMapping("/{id}/cancel")
    @RequirePermission(Permission.ORDER_CANCEL)
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        purchaseOrderService.cancelOrder(id);
        return ResponseEntity.ok("Purchase order " + id + " has been cancelled");
    }
}
