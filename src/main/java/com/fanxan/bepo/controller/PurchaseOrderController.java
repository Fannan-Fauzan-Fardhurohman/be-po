package com.fanxan.bepo.controller;


import com.fanxan.bepo.exception.ResourceNotFoundException;
import com.fanxan.bepo.models.WebResponse;
import com.fanxan.bepo.models.entity.PurchaseOrderHeader;
import com.fanxan.bepo.models.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/po")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public WebResponse<List<PurchaseOrderHeader>> getPurchaseOrders(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return purchaseOrderService.getPurchaseOrderById(id)
                    .map(po -> new WebResponse<>(true, "Success", List.of(po)))
                    .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found with id: " + id));
        }
        List<PurchaseOrderHeader> poList = purchaseOrderService.getAllPurchaseOrders();
        return new WebResponse<>(true, "Success", poList);
    }

    @PostMapping
    public WebResponse<PurchaseOrderHeader> createPurchaseOrder(@RequestBody PurchaseOrderHeader purchaseOrder) {
        PurchaseOrderHeader newPO = purchaseOrderService.createPurchaseOrder(purchaseOrder);
        return new WebResponse<>(true, "Purchase Order created successfully", newPO);
    }

    @PutMapping("/{id}")
    public WebResponse<PurchaseOrderHeader> updatePurchaseOrder(@PathVariable Integer id, @RequestBody PurchaseOrderHeader purchaseOrder) {
        PurchaseOrderHeader updatedPO = purchaseOrderService.updatePurchaseOrder(id, purchaseOrder)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found with id: " + id));
        return new WebResponse<>(true, "Purchase Order updated successfully", updatedPO);
    }

    @DeleteMapping("/{id}")
    public WebResponse<Void> deletePurchaseOrder(@PathVariable Integer id) {
        if (!purchaseOrderService.deletePurchaseOrder(id)) {
            throw new ResourceNotFoundException("Purchase Order not found with id: " + id);
        }
        return new WebResponse<>(true, "Purchase Order deleted successfully", null);
    }
}