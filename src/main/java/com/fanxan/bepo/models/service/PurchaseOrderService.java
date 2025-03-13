package com.fanxan.bepo.models.service;


import com.fanxan.bepo.models.entity.PurchaseOrderDetail;
import com.fanxan.bepo.models.entity.PurchaseOrderHeader;
import com.fanxan.bepo.repository.PurchaseOrderHeaderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderHeaderRepository poHeaderRepository;

    public PurchaseOrderService(PurchaseOrderHeaderRepository poHeaderRepository) {
        this.poHeaderRepository = poHeaderRepository;
    }

    public List<PurchaseOrderHeader> getAllPurchaseOrders() {
        return poHeaderRepository.findAll();
    }

    public Optional<PurchaseOrderHeader> getPurchaseOrderById(Integer id) {
        return poHeaderRepository.findById(id);
    }

    @Transactional
    public PurchaseOrderHeader createPurchaseOrder(PurchaseOrderHeader purchaseOrder) {
        calculateTotals(purchaseOrder);

        return poHeaderRepository.save(purchaseOrder);
    }

    @Transactional
    public Optional<PurchaseOrderHeader> updatePurchaseOrder(Integer id, PurchaseOrderHeader poDetails) {
        return poHeaderRepository.findById(id).map(po -> {
            po.setDatetime(poDetails.getDatetime());
            po.setDescription(poDetails.getDescription());

            if (po.getDetails() != null) {
                po.getDetails().clear();
            }
            if (poDetails.getDetails() != null) {
                po.setDetails(poDetails.getDetails());
            }

            calculateTotals(po);

            return poHeaderRepository.save(po);
        });
    }

    @Transactional
    public boolean deletePurchaseOrder(Integer id) {
        return poHeaderRepository.findById(id).map(po -> {
            poHeaderRepository.delete(po);
            return true;
        }).orElse(false);
    }

    private void calculateTotals(PurchaseOrderHeader po) {
        if (po.getDetails() != null && !po.getDetails().isEmpty()) {
            int totalCost = 0;
            int totalPrice = 0;

            for (PurchaseOrderDetail detail : po.getDetails()) {
                int lineCost = detail.getItemCost() * detail.getItemQty();
                int linePrice = detail.getItemPrice() * detail.getItemQty();

                totalCost += lineCost;
                totalPrice += linePrice;
            }

            po.setTotalCost(totalCost);
            po.setTotalPrice(totalPrice);
        } else {
            po.setTotalCost(0);
            po.setTotalPrice(0);
        }
    }
}